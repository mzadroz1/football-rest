package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetMatchesForClubGraphQL extends Simulation {

    FeederBuilder<String> feeder = csv("clubIds.csv").circular();
    ChainBuilder getClubs =
            exec(http("request: get all matches for club")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(ElFileBody("getMatchesForClubRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Matches").feed(feeder).exec(getClubs);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(10))
        ).protocols(graphQLHttpProtocol);
    }
}
