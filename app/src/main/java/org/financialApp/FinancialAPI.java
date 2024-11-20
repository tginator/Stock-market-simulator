package org.financialApp;

import Quotes.StockQuote;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

// Where information will be acquired through API
public class FinancialAPI {

    private final String apiKey;
    private final HttpClient client;

    public FinancialAPI(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    public StockQuote getRealTimeQuote(String symbol) throws IOException, InterruptedException {

        String uri = String.format(
                "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s",
                URLEncoder.encode(symbol, StandardCharsets.UTF_8),
                URLEncoder.encode(apiKey, StandardCharsets.UTF_8)
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());

            JsonNode quoteNode = rootNode.path("Global Quote");

            if (!quoteNode.isMissingNode()) {
                StockQuote quote = new StockQuote();

                quote.setSymbol(quoteNode.path("01. symbol").asText());
                quote.setPrice(quoteNode.path("05. price").asDouble());
                quote.setTimestamp(quoteNode.path("07. latest trading day").asText());

                return quote;
            } else {
                throw new IOException("Invalid response (query) structure");
            }
        } else {
            throw new IOException("HTTP error code: " + response.statusCode());
        }

    }

    // 1ZZRCRJPU87KLNZS API KEY



}