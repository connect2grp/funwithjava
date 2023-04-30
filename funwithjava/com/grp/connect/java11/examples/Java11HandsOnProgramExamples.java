package com.grp.connect.java11.examples;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

/**
 * This class presents Java 11 features and hands on examples.
 * 
 * @author Gaurav Rajapurkar
 *
 */
public class Java11HandsOnProgramExamples {

	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();

	public static void main(String[] args) throws Exception {

		stringApiFeatures();
		newFileMethods();
		localVariableSyntaxForLambdaParameters();
		httpGetMethod("https://www.google.com", "test");
		IntConsumer errorHandler = null;
		Consumer<String> consumer = null;
		httpGetMethodAsync("https://www.google.com", "test", consumer, errorHandler);
	}

	public static void stringApiFeatures() {
		System.out.println(" ".isBlank()); // true
		String notBlankString = "Java11HandsOn";
		System.out.println(notBlankString.isBlank()); // false

		String blankString = "";
		System.out.println(blankString.isBlank()); // true

		String linesCount = "Java\n11\nFeatures\nHands\nOn";
		System.out.println(linesCount.lines().collect(Collectors.toList()));

		String repeatString = "Java11HandsOn";
		System.out.println(repeatString.repeat(4));

		String stringLeadingStrip = " Java11HandsOn";
		System.out.println(stringLeadingStrip.stripLeading());

		String stringTrailingStrip = "Java11HandsOn ";
		System.out.println(stringTrailingStrip.stripTrailing());

		String stringStrip = " Java11HandsOn ";
		System.out.println(stringStrip.strip());
	}

	public static void newFileMethods() throws IOException {
		Path tempDir = Path.of("C:", "Users", "Public");
		Path filePath = Files.writeString(Files.createTempFile(tempDir, "java11FeaturesDemo", ".txt"),
				"FileMethodsDemo--Java11HandsOn");
		String fileContent = Files.readString(filePath);
		System.out.println(fileContent);
	}

	public static void localVariableSyntaxForLambdaParameters() {

		List<String> list = Arrays.asList("a", "b", "c");
		String result = list.stream().map((var x) -> x.toUpperCase()).collect(Collectors.joining(","));
		System.out.println(result);
	}

	public static String httpGetMethod(String url, String data) throws IOException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java11HandsOnProgramExamples HttpClient Sync") // add request header
				.build();

		HttpResponse<String> syncResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// print response headers
		HttpHeaders headers = syncResponse.headers();
		headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

		// print status code
		System.out.println(syncResponse.statusCode());

		// print response body
		System.out.println(syncResponse.body());
		if (syncResponse.statusCode() != 200) {
			throw new IOException("HTTP response status: " + syncResponse.statusCode());
		}

		return syncResponse.body();
	}

	public static void httpGetMethodAsync(String url, String data, Consumer<String> consumer, IntConsumer errorHandler) throws InterruptedException, ExecutionException, TimeoutException {
		 HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java11HandsOnProgramExamples HttpClient Async")
                .build();

        CompletableFuture<HttpResponse<String>> asyncResponse =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String asynResult = asyncResponse.thenApply(HttpResponse::body).get(11, TimeUnit.SECONDS);

        System.out.println(asynResult);
	}

}
