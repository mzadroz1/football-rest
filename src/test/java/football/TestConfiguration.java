package football;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class TestConfiguration {

    public static final String URL = "http://ec2-52-90-5-160.compute-1.amazonaws.com";
    public static final String restBaseUrl = URL + ":8888/football";
    public static final String graphQlBaseUrl = URL + ":8080/graphql";

    public static final HttpProtocolBuilder restHttpProtocol =
            http.baseUrl(restBaseUrl);

    public static final HttpProtocolBuilder graphQLHttpProtocol =
            http.baseUrl(graphQlBaseUrl);


}
