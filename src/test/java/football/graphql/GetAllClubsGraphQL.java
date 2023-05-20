package football.graphql;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.graphQLHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllClubsGraphQL extends Simulation {

    ChainBuilder getClubs =
            exec(http("request: get all clubs")
                    .post("")
                    .header("Content-Type","application/json")
                    .body(RawFileBody("getAllClubsRequest.json"))
                    .check(jsonPath("$.errors").notExists()));

    ScenarioBuilder scn = scenario("Clubs").exec(getClubs);

    {
        setUp(
                scn.injectOpen(constantUsersPerSec(20).during(50))
        ).protocols(graphQLHttpProtocol);
    }
}
