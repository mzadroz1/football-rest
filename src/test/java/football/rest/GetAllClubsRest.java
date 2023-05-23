package football.rest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static football.TestConfiguration.rampRateOpenInjectionStep;
import static football.TestConfiguration.restHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GetAllClubsRest extends Simulation {

    ChainBuilder getClubs =
            exec(http("request: get all clubs")
                    .get("/clubs"));

    ScenarioBuilder scn = scenario("Clubs").exec(getClubs);

    {
        setUp(
                scn.injectOpen(rampRateOpenInjectionStep)
        ).protocols(restHttpProtocol);
    }
}
