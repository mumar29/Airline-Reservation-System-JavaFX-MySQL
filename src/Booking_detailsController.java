import java.sql.*;
import additionalClasses.Connector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Booking_detailsController implements Initializable {
    
    @FXML
    private TextField name;
    @FXML
    private TextField total_seats;
    @FXML
    private TextField passport_no;
    @FXML
    private TextField card_no;
    @FXML
    private TextField autoid;
    @FXML
    private TextArea total_price;
    @FXML
    private ComboBox<String> Seat;
    @FXML
    private ComboBox<String> card_type;
    
    private String[] seat_type = {"economy","business"};
    private String[] card = {"debit","visa","credit"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //autoId();
        Seat.getItems().addAll(seat_type);
        Seat.show();
        card_type.getItems().addAll(card);
        card_type.show();
    } 
    @FXML
    public void calculate_Total(){
        try{
        Connection con  = Connector.CreateCon();
        String query = "select * from flights where flight_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,autoid.getText());
        ResultSet rs = pst.executeQuery();
        rs.next();
        String sum_total =rs.getString("charges");
        String final_price = (Integer.parseInt(total_seats.getText())*Integer.parseInt(sum_total))+"";
        total_price.setText(final_price);
        }
    catch(Exception ex){
        ex.printStackTrace();
    }
}
        
    
    @FXML
    public void bookFlight(){
         try{ 
            if(autoid.getText()!="" && name.getText() != ""&& total_seats.getText()!=""&& passport_no.getText()!= ""&& card_no.getText()!= ""&& Seat.getValue()!= ""&& card_type.getValue()!= ""){
                Connection con  = Connector.CreateCon();
                String query = "insert into bookings(Flight_id,Customer_name,seat,passport_no, card_no, card_type,no_of_seats) value(?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,autoid.getText());
                pst.setString(2,name.getText());
                pst.setString(3,Seat.getValue());
                pst.setString(4,passport_no.getText());
                pst.setString(5,card_no.getText());
                pst.setString(6,card_type.getValue());
                pst.setString(7,total_seats.getText());
                pst.executeUpdate();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Booking Confirmed!");
                a.show();}
            else{
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("All Fields must be filled");
                b.show();
            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
    }}
    