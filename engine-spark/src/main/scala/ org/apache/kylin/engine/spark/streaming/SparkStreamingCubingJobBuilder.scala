package  org.apache.kylin.engine.spark.streaming

import org.apache.commons.lang3.StringUtils
import org.apache.kylin.cube.CubeSegment
import org.apache.kylin.engine.mr.{CubingJob, JobBuilderSupport}
import org.apache.kylin.engine.spark.SparkExecutable
import org.apache.kylin.stream.core.util.HDFSUtil
import org.slf4j.LoggerFactory

class SparkStreamingCubingJobBuilder(segment: CubeSegment, submitter: String) extends JobBuilderSupport(segment, submitter) {
//  private val logger = LoggerFactory.getLogger(this.getClass)
//
//  def build(): CubingJob = {
//    assert(null != segment, "Segment to build cube can not be null")
//    assert(StringUtils.isNotEmpty(submitter), "Submitter to build cube can not be null or empty")
//
//    logger.info(s"Spark new job to BUILD streaming segment ${segment.toString}")
//
//    val streamJob = CubingJob.createStreamJob(segment, submitter, config)
//    val jobId = streamJob.getId
//    val streamingStoragePath = HDFSUtil.getStreamingSegmentFilePath(seg.getRealization().getName(), seg.getName())
//    val cuboidRootPath = getCuboidRootPath(jobId)
//
//    // Phase 1: Merge Dictionaries produced by each streaming receiver
//
//
//  }
//
//  private def createMergeDictStep(streamingStoragePath: String, jobId: String): SparkExecutable = {
//
//  }




}
