package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Serializable;
import scala.Tuple2;

public class DataAnalyserService implements Serializable {
    public JavaPairRDD<String, Player> findMostPowerfulTeam() {
        CsvDataImporter csvDataImporter = new CsvDataImporter();
        JavaRDD<String> rdd = csvDataImporter.importFile("/src/main/resources/CompleteDataset.csv");
        return rdd.mapToPair(this::mapToPlayerData);
    }

    private Tuple2<String, Player> mapToPlayerData(String line) {
        String[] splittedLine = line.split(",");
        String id = splittedLine[0];
        Player player = new Player(id, splittedLine[1], Integer.parseInt(splittedLine[6]), Integer.parseInt(splittedLine[7]));
        return new Tuple2<>(id, player);
    }
}
