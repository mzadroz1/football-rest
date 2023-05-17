package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetMatchesForClubRest extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getClub =
            exec(http("request: get club")
                    .get("/clubs/${clubId}"));
    ChainBuilder getMatches =
            exec(http("request: get matches for club")
                    .get("/clubs/${clubId}/matches"));
    ScenarioBuilder scn = scenario("Matches").feed(feeder).exec(getClub).exec(getMatches);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(1).during(10))
        ).protocols(restHttpProtocol);
    }
}
