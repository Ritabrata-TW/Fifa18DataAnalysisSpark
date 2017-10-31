package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;

public class Main {
    public static void main(String[] args) {
        DataAnalyserService dataAnalyserService = new DataAnalyserService();
        JavaPairRDD<String, Float> teamToAggregatedOverallMapping = dataAnalyserService.findMostPowerfulTeam();

        teamToAggregatedOverallMapping.take(10).forEach(System.out::println);
    }
}
