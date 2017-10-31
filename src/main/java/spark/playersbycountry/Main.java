package spark.playersbycountry;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PlayerCountByCountryService playerCountByCountryService = new PlayerCountByCountryService();
        Map<String, Long> teamToAggregatedOverallMapping = playerCountByCountryService.findPlayerCountByCountry();

        teamToAggregatedOverallMapping.forEach((country, count) -> System.out.println(country + " " + count));
    }
}
