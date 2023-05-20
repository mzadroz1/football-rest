package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllPlayersRest extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getPlayers =
            exec(http("request: get all players")
                    .get("/clubs/${clubId}/players"));

    ScenarioBuilder scn = scenario("Players").feed(feeder).exec(getPlayers);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(50))
        ).protocols(restHttpProtocol);
    }
}
