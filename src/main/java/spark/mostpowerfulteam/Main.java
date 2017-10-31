package spark.mostpowerfulteam;

import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {
    public static void main(String[] args) {
        SparkConfig sparkConfig = new SparkConfig();
        JavaSparkContext javaSparkContext = sparkConfig.javaSparkContext;

        JavaRDD<String> dataset = javaSparkContext.textFile("/src/main/resources/CompleteDataset.csv");
        List<String> data = dataset.take(10);

        data.forEach(System.out::println);
    }
}
