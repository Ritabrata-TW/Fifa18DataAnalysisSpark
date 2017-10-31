package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class Main {
    public static void main(String[] args) {
        SparkConfig sparkConfig = new SparkConfig();
        JavaSparkContext javaSparkContext = sparkConfig.javaSparkContext;

        JavaRDD<String> datasetWithHeaders = javaSparkContext.textFile("/src/main/resources/CompleteDataset.csv");

        JavaPairRDD<String, Player> rdd = eliminateHeadersFromDataset(datasetWithHeaders)
            .mapToPair(Main::mapToPlayerData);

        rdd.take(10).forEach(System.out::println);
    }

    private static JavaRDD<String> eliminateHeadersFromDataset(JavaRDD<String> datasetWithHeaders) {
        String headerRow = datasetWithHeaders.first();
        return datasetWithHeaders.filter(row -> !row.equals(headerRow));
    }

    private static Tuple2<String, Player> mapToPlayerData(String line) {
        String[] splittedLine = line.split(",");

        String id = splittedLine[0];
        Player player = new Player(id, splittedLine[1], Integer.parseInt(splittedLine[6]), Integer.parseInt(splittedLine[7]));
        return new Tuple2<>(id, player);
    }
}
