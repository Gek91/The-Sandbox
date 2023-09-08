package java11.httpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Async {

	final static List<URI> uris = Stream.of(
			"https://postman-echo.com/get?foo1=bar1&foo2=bar2"
	).map(URI::create).collect(Collectors.toList());

	public static void main(String[] args) throws Exception {

		HttpClient httpClient = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(50))
				.followRedirects(HttpClient.Redirect.ALWAYS)
				.build();

		CompletableFuture[] futures = uris.stream()
				.map(uri -> verifyUri(httpClient, uri))
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(futures).join();
	}

	private static CompletableFuture<Void> verifyUri(HttpClient httpClient, URI uri) {

		HttpRequest request = HttpRequest.newBuilder()
				.timeout(Duration.ofSeconds(50))
				.uri(uri)
				.build();

		return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::statusCode)
				.thenApply(statusCode -> statusCode == 200)
				.exceptionally(ex -> false)
				.thenAccept(valid -> {
					if(valid) {
						System.out.println("success " + uri);
					} else {
						System.out.println("failure " + uri);
					}
				});
	}
}
