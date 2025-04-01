import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPage extends Application {
	private static final int WINDOW_HEIGHT = 1080;
	private static final int WINDOW_WIDTH = 1920;
    @Override
    public void start(Stage primaryStage) {
    	Pane root = new Pane();
    	Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    	
    	primaryStage.setTitle("MathX");
        primaryStage.setScene(scene);
        primaryStage.show();
         
        Button btn = new Button("Click me for a math problem");
        btn.setPrefWidth(300);
        btn.setPrefHeight(100);
        btn.setLayoutX((WINDOW_WIDTH - 300) / 2);
        btn.setLayoutY(0);
        btn.setOnAction(e -> System.out.println("Button Clicked"));
        
        new Thread(() -> {
            try {
                Thread.sleep(1);
                javafx.application.Platform.runLater(() -> root.getChildren().add(btn));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}