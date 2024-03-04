
import additionalClasses.Connector;
import additionalClasses.Flight;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Guest_bookFlightController implements Initializable {

@FXML
private ChoiceBox<String> from;
@FXML
private ChoiceBox<String> to;
@FXML
private DatePicker d;
@FXML
private TableView<Flight> selectFlight_table;
@FXML
private TableColumn<Flight, String> flight_id;
@FXML
private TableColumn<Flight, String> date;
@FXML
private TableColumn<Flight, String> departure;
@FXML
private TableColumn<Flight, String> arrival;
@FXML
private TableColumn<Flight, String> charge;
@FXML
private ObservableList<Flight> bookings_list;

private final String[] from_city ={"Karachi","Lahore","Islamabad","Multan"};
private final String[] to_city ={"Karachi","Lahore","Islamabad","Multan","NewYork","London","Paris","Tokyo"};        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        from.getItems().addAll(from_city);
        from.show();
        to.getItems().addAll(to_city);
        to.show();
    } 
    @FXML
    public void searchFlight(){
         try{
        Connection con = Connector.CreateCon();
        selectFlight_table.getItems().clear();
        bookings_list = selectFlight_table.getItems();
        flight_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        charge.setCellValueFactory(new PropertyValueFactory<>("charges"));
        String query = "select * from flights where start_location = ? and destination = ? and date = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,from.getValue());
        pst.setString(2, to.getValue());
        pst.setString(3, d.getValue().toString());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          bookings_list.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
        }
        selectFlight_table.setItems(bookings_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void confirmBooking(){
        try{
        Parent root = FXMLLoader.load(getClass().getResource("booking_details.fxml"));
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
