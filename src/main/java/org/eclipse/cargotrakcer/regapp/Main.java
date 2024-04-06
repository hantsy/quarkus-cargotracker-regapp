package org.eclipse.cargotrakcer.regapp;

import io.quarkiverse.fx.FxStartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.eclipse.cargotrakcer.regapp.ui.HandlingReportController;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());


    @Inject
    FXMLLoader fxmlLoader;

    public void start(@Observes FxStartupEvent event) throws Exception {
        LOGGER.log(Level.INFO, "Observes FxStartupEvent: {}", new Object[]{event});
        // start primary stage.
        Stage stage = event.getPrimaryStage();

        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        try {
            InputStream fxml = this.getClass().getResourceAsStream("/org/eclipse/cargotrakcer/regapp/ui/HandlingReport.fxml");
            Parent root = this.fxmlLoader.load(fxml);
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(this.getClass().getResource("/style.css").toExternalForm());

            // set up stage
            stage.getIcons().add(new Image("/logo32.png"));
            stage.setTitle("Incident Logging Application");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
