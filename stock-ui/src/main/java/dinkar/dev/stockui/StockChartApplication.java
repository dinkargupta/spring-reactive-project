package dinkar.dev.stockui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class StockChartApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder()
                .sources(StockUiApplication.class)
                .run();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return (Stage)this.getSource();
        }
    }
}