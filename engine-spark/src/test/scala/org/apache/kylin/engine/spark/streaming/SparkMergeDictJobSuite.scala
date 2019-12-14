package org.apache.kylin.engine.spark.streaming

import org.apache.kylin.common.util.HadoopUtil
import org.scalatest.FunSuite

class SparkMergeDictJobSuite extends FunSuite {
  test("") {
    val mergeDictJob = new SparkMergeDictJob(HadoopUtil.getCurrentConfiguration)
    val args = Array("-input", "file:///Users/zhuweibin/Downloads/xx/kylin_metadata/stream/TestCube/20191219110000_20191219120000",
      "-output", "file:///Users/zhuweibin/Downloads/KYLIN-4008-OUTPUT",
      "-jobname", "Build Dimension Dictionaries For Steaming Job",
      "-cubename", "TestCube1207",
      "-segmentname", "20191219110000_20191219120000")
    mergeDictJob.run(args)
  }
}
