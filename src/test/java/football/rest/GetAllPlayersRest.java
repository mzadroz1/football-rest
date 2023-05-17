package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllPlayersRest extends Simulation {

    ChainBuilder getPlayers =
            exec(http("request: get all players")
                    .get("/players"));

    ScenarioBuilder scn = scenario("Players").exec(getPlayers);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(200).during(10))
        ).protocols(restHttpProtocol);
    }
}
