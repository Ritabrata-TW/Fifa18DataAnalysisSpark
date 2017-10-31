package spark.playersbycountry;

import importer.CsvDataImporter;
import java.util.Map;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;
import scala.Serializable;
import scala.Tuple2;

public class PlayerCountByCountryService implements Serializable {
    Map<String, Long> findPlayerCountByCountry() {
        CsvDataImporter csvDataImporter = new CsvDataImporter();
        JavaRDD<String> dataset = csvDataImporter.importFile("/src/main/resources/CompleteDataset.csv");

        JavaRDD<Player> playerRdd = dataset.map(this::mapToPlayerData);
        JavaPairRDD<String, Player> countryToPlayerMapping = playerRdd.mapToPair((PairFunction<Player, String, Player>) player ->
            new Tuple2<>(player.getNationality(), player));

        return countryToPlayerMapping.countByKey();
    }

    private Player mapToPlayerData(String line) {
        String[] splittedLine = line.split(",");
        return new Player(splittedLine[0], splittedLine[1], splittedLine[4]);
    }
}
