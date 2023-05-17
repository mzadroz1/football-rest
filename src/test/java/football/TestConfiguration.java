package football;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class TestConfiguration {

    public static final String restBaseUrl = "http://localhost:8888/football";
    public static final String graphQlBaseUrl = "http://localhost:8080/graphql";

    public static final HttpProtocolBuilder restHttpProtocol =
            http.baseUrl(restBaseUrl);

    public static final HttpProtocolBuilder graphQLHttpProtocol =
            http.baseUrl(graphQlBaseUrl);


}
