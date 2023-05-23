package football;

import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;

public class TestConfiguration {

//    public static final String URL = "http://localhost";
    public static final String URL = "http://ec2-3-88-21-180.compute-1.amazonaws.com";
    public static final String restBaseUrl = URL + ":8888/football";
    public static final String graphQlBaseUrl = URL + ":8080/graphql";

    public static final HttpProtocolBuilder restHttpProtocol =
            http.baseUrl(restBaseUrl);

    public static final HttpProtocolBuilder graphQLHttpProtocol =
            http.baseUrl(graphQlBaseUrl);

    static int totalDesiredUserCount = 500;
    static double userRampUpPerInterval = 50;
    static double rampUpIntervalSeconds = 10;
    static int totalRampUptimeSeconds = 100;
    static int steadyStateDurationSeconds = 30;

    public static final OpenInjectionStep.RampRate.RampRateOpenInjectionStep rampRateOpenInjectionStep =
            rampUsersPerSec(userRampUpPerInterval / (rampUpIntervalSeconds / 60))
            .to(totalDesiredUserCount)
            .during(totalRampUptimeSeconds + steadyStateDurationSeconds);

}
