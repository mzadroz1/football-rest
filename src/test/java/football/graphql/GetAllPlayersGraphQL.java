package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllPlayersGraphQL extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getPlayers =
            exec(http("request: get all players")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(ElFileBody("getAllPlayersRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Players").feed(feeder).exec(getPlayers);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(50))
        ).protocols(graphQLHttpProtocol);
    }
}
