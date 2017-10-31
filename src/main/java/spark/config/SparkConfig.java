package spark.config;

import java.util.Date;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkConfig {
    private SparkConf sparkConf = new SparkConf().setAppName("Job - " + new Date());
    public JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
}
