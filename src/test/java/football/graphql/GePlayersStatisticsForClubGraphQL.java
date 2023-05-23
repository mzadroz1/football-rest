package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GePlayersStatisticsForClubGraphQL extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getStatistics =
            exec(http("request: get statistics for players from club")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(ElFileBody("gePlayersStatisticsForClubRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Statistics").feed(feeder).exec(getStatistics);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(10).during(50))
        ).protocols(graphQLHttpProtocol);
    }
}
