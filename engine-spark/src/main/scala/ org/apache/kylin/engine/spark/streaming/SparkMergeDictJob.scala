package org.apache.kylin.engine.spark.streaming

import java.util
import java.util.Locale

import scala.collection.JavaConverters._
import org.apache.commons.cli.Options
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.KeyValueTextInputFormat
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.kylin.common.KylinConfig
import org.apache.kylin.common.streaming.{BatchConstants, JobOptions}
import org.apache.kylin.common.util.{HadoopUtil, OptionsHelper}
import org.apache.kylin.cube.{CubeInstance, CubeManager, CubeSegment}
import org.apache.kylin.engine.mr.streaming.{ColumnarSplitDictInputFormat, MergeDictReducer}
import org.apache.kylin.metadata.model.{SegmentStatusEnum, TblColRef}
import org.apache.spark.rdd.{NewHadoopRDD, RDD}
import org.apache.spark.sql.SparkSession

class SparkMergeDictJob(hadoopConf: Configuration) extends JobOptions(hadoopConf) {

  private var cubeName: String = null
  private var segmentName: String = null
//  private var cube: CubeInstance = null
//  private var segment: CubeSegment = null

  def run(args: Array[String]): Unit = {
    val options = new Options()
    try {
      options.addOption(JobOptions.OPTION_JOB_NAME)
      options.addOption(JobOptions.OPTION_CUBE_NAME)
      options.addOption(JobOptions.OPTION_SEGMENT_NAME)
      options.addOption(JobOptions.OPTION_INPUT_PATH)
      options.addOption(JobOptions.OPTION_OUTPUT_PATH)

      val optionsHelper = new OptionsHelper()
      optionsHelper.parseOptions(options, args)

      val input = new Path(optionsHelper.getOptionValue(JobOptions.OPTION_INPUT_PATH))
      val output = new Path(optionsHelper.getOptionValue(JobOptions.OPTION_OUTPUT_PATH))
      val jobName = optionsHelper.getOptionValue(JobOptions.OPTION_JOB_NAME)
      cubeName = optionsHelper.getOptionValue(JobOptions.OPTION_CUBE_NAME).toUpperCase(Locale.ROOT)
      segmentName = optionsHelper.getOptionValue(JobOptions.OPTION_SEGMENT_NAME)

//      val cubeMgr = CubeManager.getInstance(KylinConfig.getInstanceFromEnv())
//      cube = cubeMgr.getCube(cubeName)
//      segment = cube.getSegment(segmentName, SegmentStatusEnum.NEW)

      val spark = getSpark()

      val jobConf = HadoopUtil.getCurrentConfiguration()
      jobConf.set(FileInputFormat.INPUT_DIR, input.toUri.toString)
      jobConf.set(BatchConstants.CFG_CUBE_NAME, cubeName)
      jobConf.set(BatchConstants.CFG_CUBE_SEGMENT_NAME, segmentName)

      val inputRdd = new NewHadoopRDD[Text, Text](spark.sparkContext, classOf[ColumnarSplitDictInputFormat[Text, Text]],
        classOf[Text], classOf[Text], jobConf)

      inputRdd.count()

      val kvTextRdd = inputRdd.asInstanceOf[RDD[(Text, Text)]]

      // MergeDictMapper.doMap do nothing, skip map here

      val reducer = new MergeDictReducer()
      val reducedRdd = kvTextRdd.groupByKey().mapPartitions { partition =>
        // val colNeedDictMap = MergeDictReducer.getColNeedDictMap(cubeName, segmentName, cube, segment)
        val colNeedDictMap = new util.HashMap[String, TblColRef]()
        partition.map { p =>
          (p._1, MergeDictReducer.reduce(p._1, p._2.asJava, colNeedDictMap))
        }
      }

      reducedRdd.saveAsSequenceFile(output.toString, None)
    }
  }

  private def getSpark(): SparkSession = {
    SparkSession.builder().master("local").appName("SparkKylin").getOrCreate()
  }
}
