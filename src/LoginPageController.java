
import additionalClasses.Connector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
    @FXML
    private TextField admin_username;
    @FXML
    private TextField admin_password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    public void adminLogin(){
        try{
        String au =admin_username.getText();
        String ap = admin_password.getText();
        Connection con = Connector.CreateCon();
        String Query = "select username,password from admin where username = ? and password = ?";
        PreparedStatement st = con.prepareStatement(Query);
        st.setString(1, au);
        st.setString(2, ap);
        ResultSet rs = st.executeQuery();
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("Admin crude.fxml"));
                Scene scene = new Scene(root); 
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Admin Login");
                primaryStage.setScene(scene);
                primaryStage.show();
            }else{
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setContentText("Incorrect username or password");
                al.show();
            }
            }catch(SQLException | IOException ex){
                ex.printStackTrace();
            }
    }
    @FXML
    public void guestLogin(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("guest_bookFlight.fxml"));
        Scene scene = new Scene(root); 
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Guest Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }catch(IOException ex){
        ex.printStackTrace();
    }
}
}
