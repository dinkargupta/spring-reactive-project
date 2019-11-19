package dinkar.dev.stockui;

import dinkar.dev.stockclient.StockClient;
import dinkar.dev.stockclient.StockPrice;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class StockController implements Consumer<StockPrice> {

    @FXML
    private LineChart<String, Double> chart;
    private final StockClient stockClient;
    private final ObservableList<XYChart.Data<String, Double>> seriesData = observableArrayList();

    StockController(final StockClient stockClient) {
        this.stockClient = stockClient;
    }

    //this is the controller, and what it does is wires stuff together
    @FXML
    public void initialize() {
        String symbol = "SYMBOL";
        ObservableList<Series<String, Double>> data = observableArrayList(new Series<>(symbol, seriesData));
        chart.setData(data);

        //stockClient.pricesFor(symbol).subscribe(this);
        stockClient.pricesFor(symbol).subscribe(this);
    }

    @Override
    public void accept(StockPrice stockPrice) {
        Platform.runLater(() ->
                seriesData.add(new XYChart.Data<>(String.valueOf(stockPrice.getTime().getSecond()),
                        stockPrice.getPrice()))
        );
    }
}