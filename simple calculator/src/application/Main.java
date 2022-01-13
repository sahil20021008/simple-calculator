package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("calculator_icon.png"));
			primaryStage.setTitle("Simple Calculator");
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				exiter(primaryStage);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exiter(Stage stage) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		Stage x=(Stage)alert.getDialogPane().getScene().getWindow();
		x.getIcons().add(new Image("calculator_icon.png"));
		alert.setTitle("Exit Screen");
		alert.setHeaderText("You're about to exit the calculator");
		alert.setContentText("Are you sure you want to exit the calculator?");
		if(alert.showAndWait().get()==ButtonType.OK) {
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
