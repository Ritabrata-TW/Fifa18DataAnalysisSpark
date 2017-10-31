package spark.mostpowerfulteam;

import org.apache.spark.api.java.JavaPairRDD;

public class Main {
    public static void main(String[] args) {
        MostPowerfulTeamService mostPowerfulTeamService = new MostPowerfulTeamService();
        JavaPairRDD<String, Float> teamToAggregatedOverallMapping = mostPowerfulTeamService.findMostPowerfulTeam();

        teamToAggregatedOverallMapping.take(10).forEach(System.out::println);
    }
}
