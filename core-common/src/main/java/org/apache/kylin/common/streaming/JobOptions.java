package org.apache.kylin.common.streaming;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;

public class JobOptions extends Configured {
    public JobOptions(Configuration hadoopConf) {
        super(hadoopConf);
    }

    public static final Option OPTION_PROJECT = OptionBuilder.withArgName(BatchConstants.ARG_PROJECT).hasArg()
            .isRequired(true).withDescription("Project name.").create(BatchConstants.ARG_PROJECT);
    public static final Option OPTION_JOB_NAME = OptionBuilder.withArgName(BatchConstants.ARG_JOB_NAME).hasArg()
            .isRequired(true).withDescription("Job name. For example, Kylin_Cuboid_Builder-clsfd_v2_Step_22-D)")
            .create(BatchConstants.ARG_JOB_NAME);
    public static final Option OPTION_CUBE_NAME = OptionBuilder.withArgName(BatchConstants.ARG_CUBE_NAME).hasArg()
            .isRequired(true).withDescription("Cube name. For exmaple, flat_item_cube")
            .create(BatchConstants.ARG_CUBE_NAME);
    public static final Option OPTION_CUBING_JOB_ID = OptionBuilder.withArgName(BatchConstants.ARG_CUBING_JOB_ID)
            .hasArg().isRequired(false).withDescription("ID of cubing job executable")
            .create(BatchConstants.ARG_CUBING_JOB_ID);
    //    @Deprecated
    public static final Option OPTION_SEGMENT_NAME = OptionBuilder.withArgName(BatchConstants.ARG_SEGMENT_NAME)
            .hasArg().isRequired(true).withDescription("Cube segment name").create(BatchConstants.ARG_SEGMENT_NAME);
    public static final Option OPTION_SEGMENT_ID = OptionBuilder.withArgName(BatchConstants.ARG_SEGMENT_ID).hasArg()
            .isRequired(true).withDescription("Cube segment id").create(BatchConstants.ARG_SEGMENT_ID);
    public static final Option OPTION_INPUT_PATH = OptionBuilder.withArgName(BatchConstants.ARG_INPUT).hasArg()
            .isRequired(true).withDescription("Input path").create(BatchConstants.ARG_INPUT);
    public static final Option OPTION_INPUT_FORMAT = OptionBuilder.withArgName(BatchConstants.ARG_INPUT_FORMAT)
            .hasArg().isRequired(false).withDescription("Input format").create(BatchConstants.ARG_INPUT_FORMAT);
    public static final Option OPTION_OUTPUT_PATH = OptionBuilder.withArgName(BatchConstants.ARG_OUTPUT).hasArg()
            .isRequired(true).withDescription("Output path").create(BatchConstants.ARG_OUTPUT);
    public static final Option OPTION_DICT_PATH = OptionBuilder.withArgName(BatchConstants.ARG_DICT_PATH).hasArg()
            .isRequired(false).withDescription("Dict path").create(BatchConstants.ARG_DICT_PATH);
    public static final Option OPTION_NCUBOID_LEVEL = OptionBuilder.withArgName(BatchConstants.ARG_LEVEL).hasArg()
            .isRequired(true).withDescription("N-Cuboid build level, e.g. 1, 2, 3...").create(BatchConstants.ARG_LEVEL);
    public static final Option OPTION_PARTITION_FILE_PATH = OptionBuilder.withArgName(BatchConstants.ARG_PARTITION)
            .hasArg().isRequired(true).withDescription("Partition file path.").create(BatchConstants.ARG_PARTITION);
    public static final Option OPTION_HTABLE_NAME = OptionBuilder.withArgName(BatchConstants.ARG_HTABLE_NAME)
            .hasArg().isRequired(true).withDescription("HTable name").create(BatchConstants.ARG_HTABLE_NAME);
    public static final Option OPTION_DICTIONARY_SHRUNKEN_PATH = OptionBuilder
            .withArgName(BatchConstants.ARG_SHRUNKEN_DICT_PATH).hasArg().isRequired(false)
            .withDescription("Dictionary shrunken path").create(BatchConstants.ARG_SHRUNKEN_DICT_PATH);

    public static final Option OPTION_STATISTICS_OUTPUT = OptionBuilder.withArgName(BatchConstants.ARG_STATS_OUTPUT)
            .hasArg().isRequired(false).withDescription("Statistics output").create(BatchConstants.ARG_STATS_OUTPUT);
    public static final Option OPTION_STATISTICS_SAMPLING_PERCENT = OptionBuilder
            .withArgName(BatchConstants.ARG_STATS_SAMPLING_PERCENT).hasArg().isRequired(false)
            .withDescription("Statistics sampling percentage").create(BatchConstants.ARG_STATS_SAMPLING_PERCENT);
    public static final Option OPTION_CUBOID_MODE = OptionBuilder.withArgName(BatchConstants.ARG_CUBOID_MODE)
            .hasArg().isRequired(false).withDescription("Cuboid Mode").create(BatchConstants.ARG_CUBOID_MODE);
    public static final Option OPTION_NEED_UPDATE_BASE_CUBOID_SHARD = OptionBuilder
            .withArgName(BatchConstants.ARG_UPDATE_SHARD).hasArg().isRequired(false)
            .withDescription("If need to update base cuboid shard").create(BatchConstants.ARG_UPDATE_SHARD);
    public static final Option OPTION_TABLE_NAME = OptionBuilder.withArgName(BatchConstants.ARG_TABLE_NAME)
            .hasArg().isRequired(true).withDescription("Table name. For exmaple, default.table1")
            .create(BatchConstants.ARG_TABLE_NAME);
    public static final Option OPTION_LOOKUP_SNAPSHOT_ID = OptionBuilder
            .withArgName(BatchConstants.ARG_LOOKUP_SNAPSHOT_ID).hasArg()
            .isRequired(true).withDescription("Lookup table snapshotID")
            .create(BatchConstants.ARG_LOOKUP_SNAPSHOT_ID);
    public static final Option OPTION_META_URL = OptionBuilder.withArgName(BatchConstants.ARG_META_URL)
            .hasArg().isRequired(true).withDescription("HDFS metadata url").create(BatchConstants.ARG_META_URL);
    public static final Option OPTION_HBASE_CONF_PATH = OptionBuilder
            .withArgName(BatchConstants.ARG_HBASE_CONF_PATH).hasArg()
            .isRequired(true).withDescription("HBase config file path").create(BatchConstants.ARG_HBASE_CONF_PATH);
}
