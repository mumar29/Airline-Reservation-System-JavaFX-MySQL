import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {  
try {            
            Parent root = FXMLLoader.load(getClass().getResource("login page.fxml"));
            Scene scene = new Scene(root); 
            primaryStage.setTitle("Airline Reservation System");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public static void main(String[] args) {
        launch(args);
    }
    
}
