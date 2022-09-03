package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        // httpGetRequest();
        // httpPostRequest();
        asynchronousGetRequest();
    }

    public static void httpGetRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create("http://jsonplaceholder.typicode.com/posts/1"))
                .headers("Accept-Encoding", "gzip, deflate")
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        System.out.println("httpGetRequest: " + responseBody);
        System.out.println("httpGetRequest status code: " + responseStatusCode);
    }

    public static void httpPostRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI("http://jsonplaceholder.typicode.com/posts"))
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString("Sample Post Request"))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println("http Post response: " + responseBody);
    }

    public static void asynchronousGetRequest() throws URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI httpURI = new URI("http://jsonplaceholder.typicode.com/posts/1");
        HttpRequest request = HttpRequest.newBuilder(httpURI)
                .version(HttpClient.Version.HTTP_2)
                .build();
        CompletableFuture<Void> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(resp -> {
                    System.out.println("Got pushed response " + resp.uri());
                    System.out.println("Response status code: " + resp.statusCode());
                    System.out.println("Response body: " + resp.body());
                });
        System.out.println("futureResponse" + futureResponse);
        TimeUnit.SECONDS.sleep(5);
    }
}
