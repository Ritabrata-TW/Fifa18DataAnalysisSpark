package spark.mostpowerfulteam;

import importer.CsvDataImporter;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Serializable;
import scala.Tuple2;
import utils.SparkRddReverserUtil;

class DataAnalyserService implements Serializable {
    JavaPairRDD<String, Float> findMostPowerfulTeam() {
        CsvDataImporter csvDataImporter = new CsvDataImporter();
        JavaRDD<String> rdd = csvDataImporter.importFile("/src/main/resources/CompleteDataset.csv");
        JavaRDD<Player> playersRDD = rdd.map(this::mapToPlayerData);
        JavaPairRDD<String, Player> clubToPlayerMapping = mapClubToPlayer(playersRDD);

        JavaPairRDD<String, Tuple2<Integer, Integer>> rdd1 = clubToPlayerMapping.mapValues(player -> new Tuple2<>(player.getOverall(), 1))
            .reduceByKey((v1, v2) -> new Tuple2<>(v1._1() + v2._1(), v1._2() + v2._2()));

        JavaPairRDD<String, Float> clubToAverageRating = rdd1.mapToPair(tuple2 ->
            new Tuple2<>(tuple2._1(), (float) (tuple2._2()._1() / tuple2._2()._2())));

        JavaPairRDD<Float, String> sortedAverageRatingToClubMapping = new SparkRddReverserUtil<Float, String>()
            .reverseJavaPairRdd(clubToAverageRating)
            .sortByKey(false);

        return new SparkRddReverserUtil<String, Float>().reverseJavaPairRdd(sortedAverageRatingToClubMapping);
    }

    private JavaPairRDD<String, Player> mapClubToPlayer(JavaRDD<Player> playersRDD) {
        return playersRDD.mapToPair(player -> new Tuple2<>(player.getClub(), player))
            .filter(clubToPairMapping -> {
                String clubName = clubToPairMapping._1();
                return !clubName.isEmpty();
            });
    }

    private Player mapToPlayerData(String line) {
        String[] splittedLine = line.split(",");
        String id = splittedLine[0];
        return new Player(id, splittedLine[1], splittedLine[8], Integer.parseInt(splittedLine[6]), Integer.parseInt(splittedLine[7]));
    }
}
