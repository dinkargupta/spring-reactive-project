package dinkar.dev.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class WebClientStockClientIntegrationTest {

    private WebClient webClient;

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        webClient = WebClient.builder().build();
        //given
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);
        //when
        Flux<StockPrice> prices = webClientStockClient.pricesFor("SYMBOL");
        //then
        Assertions.assertNotNull(prices);
        Assertions.assertTrue(prices.take(5).count().block() > 0);

        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5, fivePrices.count().block());
        Assertions.assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
    }
}