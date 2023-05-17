package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllPlayersGraphQL extends Simulation {

    ChainBuilder getPlayers =
            exec(http("request: get all players")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(RawFileBody("getAllPlayersRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Players").exec(getPlayers);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(50).during(10))
        ).protocols(graphQLHttpProtocol);
    }
}
