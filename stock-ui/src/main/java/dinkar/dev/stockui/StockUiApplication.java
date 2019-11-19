package dinkar.dev.stockui;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockUiApplication {

	public static void main(String[] args) {
		Application.launch(StockChartApplication.class, args);
	}

}
