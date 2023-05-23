package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GePlayersStatisticsForClubRest extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getPlayers =
            exec(http("request: get players")
                    .get("/clubs/${clubId}/players")
                    .check(jsonPath("$[0].id").saveAs("playerIds")));
    ChainBuilder getMatches =
            repeat(11).on(exec(http("request: get statistics for player")
                    .get("/players/${playerIds}/statistics")));
    ScenarioBuilder scn = scenario("Matches").feed(feeder).exec(getPlayers).exec(getMatches);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(10).during(50))
        ).protocols(restHttpProtocol);
    }
}
