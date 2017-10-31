package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Serializable;
import scala.Tuple2;

class DataAnalyserService implements Serializable {
    JavaPairRDD<String, Integer> findMostPowerfulTeam() {
        CsvDataImporter csvDataImporter = new CsvDataImporter();
        JavaRDD<String> rdd = csvDataImporter.importFile("/src/main/resources/CompleteDataset.csv");

        JavaRDD<Player> playersRDD = rdd.map(this::mapToPlayerData);

        JavaPairRDD<String, Player> clubToPlayerMapping =
            playersRDD.mapToPair(player -> new Tuple2<>(player.getClub(), player))
                .filter(v1 -> !v1._1().isEmpty());

        JavaPairRDD<String, Integer> clubToAggregatedOverallMapping =
            clubToPlayerMapping.aggregateByKey(0, (v1, v2) -> v1 + v2.getOverall(), (v1, v2) -> v1 + v2);

        JavaPairRDD<Integer, String> overallToClubMapping =
            new SparkRddReverserUtil<Integer, String>().reverseJavaPairRdd(clubToAggregatedOverallMapping);

        JavaPairRDD<Integer, String> sortedOverallToClubMapping = overallToClubMapping.sortByKey(false);

        return new SparkRddReverserUtil<String, Integer>().reverseJavaPairRdd(sortedOverallToClubMapping);
    }

    private Player mapToPlayerData(String line) {
        String[] splittedLine = line.split(",");
        String id = splittedLine[0];
        return new Player(id, splittedLine[1], splittedLine[8], Integer.parseInt(splittedLine[6]), Integer.parseInt(splittedLine[7]));
    }
}
