package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetPlayersStatisticsGraphQL extends Simulation {

    ChainBuilder getPlayersStatistics =
            exec(http("request: get all players statistics")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(RawFileBody("getAllPlayersStatisticsRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Players").exec(getPlayersStatistics);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(50).during(10))
        ).protocols(graphQLHttpProtocol);
    }
}
