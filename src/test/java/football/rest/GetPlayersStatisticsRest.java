package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetPlayersStatisticsRest extends Simulation {

    ChainBuilder getPlayersStatistics =
            exec(http("request: get all players statistics")
                    .get("/players/ranking"));

    ScenarioBuilder scn = scenario("Players statistics").exec(getPlayersStatistics);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(10))
        ).protocols(restHttpProtocol);
    }
}
