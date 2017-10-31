package spark.mostpowerfulteam;

import java.util.Date;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

class SparkConfig {
    private SparkConf sparkConf = new SparkConf().setAppName("Job - " + new Date());

    JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
}
