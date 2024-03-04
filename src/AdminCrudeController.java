
import additionalClasses.Booking;
import additionalClasses.Connector;
import additionalClasses.Flight;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

public class AdminCrudeController implements Initializable {
    @FXML
    private TextField from;
    @FXML
    private TextField to;
    @FXML
    private TextField total;
    @FXML
    private TextField economy;
    @FXML
    private TextField business;
    @FXML
    private DatePicker departure_date;
    @FXML
    private TextField charges;
    @FXML        
    private TextField arrival;
    @FXML
    private TextField departure;
    @FXML
    private TableView<Flight> flight_table;
    @FXML
    private TableColumn<Flight, String> id;
    @FXML
    private TableColumn<Flight, String> f_from;
    @FXML
    private TableColumn<Flight, String> f_to;
    @FXML
    private TableColumn<Flight, String> f_total;
    @FXML
    private TableColumn<Flight, String> f_economy;
    @FXML
    private TableColumn<Flight, String> f_business;
    @FXML
    private TableColumn<Flight, String> f_departure;
    @FXML
    private TableColumn<Flight, String> f_arrival;
    @FXML
    private TableColumn<Flight, String> f_charges;
    @FXML
    private TableColumn<Flight, String> f_date;
    @FXML   
    ObservableList<Flight> list;
    @FXML
    private TextField ufrom;
    @FXML
    private TextField uto;
    @FXML
    private TextField utotal;
    @FXML
    private TextField ueconomy;
    @FXML
    private TextField ubusiness;
    @FXML
    private DatePicker udeparture_date;
    @FXML
    private TextField ucharges;
    @FXML        
    private TextField uarrival;
    @FXML
    private TextField udeparture;
    @FXML
    private TextField sfrom;
    @FXML
    private TextField sto;
    @FXML
    private TextField flight_id;
    @FXML
    private TableView<Booking> booking_table;
    @FXML
    private TableColumn<Booking, String> column_flight_id;
    @FXML
    private TableColumn<Booking, String> booking_id;
    @FXML
    private TableColumn<Booking, String> name;
    @FXML
    private TableColumn<Booking, String> seat_type;
    @FXML
    private TableColumn<Booking, String> total_seats;
    @FXML
    private TableColumn<Booking, String> passport;
    @FXML
    private TableColumn<Booking, String> card_type;
    @FXML
    private TableColumn<Booking, String> card_no;
    @FXML
    private ObservableList<Booking> bookings_list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        Connection con = Connector.CreateCon();
        flight_table.getItems().clear();
        list = flight_table.getItems();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        f_from.setCellValueFactory(new PropertyValueFactory<>("from"));
        f_to.setCellValueFactory(new PropertyValueFactory<>("to"));
        f_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        f_economy.setCellValueFactory(new PropertyValueFactory<>("economy"));
        f_business.setCellValueFactory(new PropertyValueFactory<>("business"));
        f_departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
        f_arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        f_charges.setCellValueFactory(new PropertyValueFactory<>("charges"));
        f_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        String query = "select * from flights";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
          list.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
        }
        flight_table.setItems(list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void addFlight(){
        try{ 
            if(from.getText() != ""&& to.getText()!=""&& total.getText()!= ""&& economy.getText()!= ""&& business.getText()!= ""&& charges.getText()!= ""&& arrival.getText()!= ""&& departure.getText()!= ""){
                Connection con  = Connector.CreateCon();
                String query = "insert into flights(start_location, destination, date, total_seats, economy_seats,business_seats, charges, arrival_time, departure_time) value(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(3,departure_date.getValue().toString());
                pst.setString(4,total.getText());
                pst.setString(1,from.getText());
                pst.setString(2,to.getText());
                pst.setString(5,economy.getText());
                pst.setString(6,business.getText());
                pst.setString(7,charges.getText());
                pst.setString(8,arrival.getText());
                pst.setString(9,departure.getText());
                pst.executeUpdate();
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Flight added!");
                a.show();
                flight_table.getItems().clear();
                list = flight_table.getItems();
                Connection con2  = Connector.CreateCon();
                String query2 = "select * from flights";
                Statement st = con2.createStatement();
                ResultSet rs = st.executeQuery(query2);
                while(rs.next()){
                  list.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
                }
                flight_table.setItems(list);}
            else{
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("All Fields must be filled");
                b.show();
            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }}
    public void removeFlight(){
        try{
        Connection con = Connector.CreateCon();
        String id = flight_table.getSelectionModel().getSelectedItem().getId();
        String query = "delete from flights where flight_id = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,id);
        pst.executeUpdate();
        int selectedIndex = flight_table.getSelectionModel().getSelectedIndex();
        list.remove(selectedIndex);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("Flight removed successfully!");
        b.show();    
    }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void searchFlight(){
        try{
        Connection con = Connector.CreateCon();
        flight_table.getItems().clear();
        ObservableList<Flight> tempList = flight_table.getItems();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        f_from.setCellValueFactory(new PropertyValueFactory<>("from"));
        f_to.setCellValueFactory(new PropertyValueFactory<>("to"));
        f_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        f_economy.setCellValueFactory(new PropertyValueFactory<>("economy"));
        f_business.setCellValueFactory(new PropertyValueFactory<>("business"));
        f_departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
        f_arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        f_charges.setCellValueFactory(new PropertyValueFactory<>("charges"));
        f_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        String query = "select * from flights where start_location = ? and destination = ?";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1, sfrom.getText());
        pst.setString(2, sto.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          tempList.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
        }
        flight_table.setItems(tempList);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void refreshTable(){
        //try{
        //Connection con = Connector.CreateCon();
        try{
        flight_table.getItems().clear();
//        list = flight_table.getItems();
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        f_from.setCellValueFactory(new PropertyValueFactory<>("from"));
//        f_to.setCellValueFactory(new PropertyValueFactory<>("to"));
//        f_total.setCellValueFactory(new PropertyValueFactory<>("total"));
//        f_economy.setCellValueFactory(new PropertyValueFactory<>("economy"));
//        f_business.setCellValueFactory(new PropertyValueFactory<>("business"));
//        f_departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
//        f_arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
//        f_charges.setCellValueFactory(new PropertyValueFactory<>("charges"));
//        f_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Connection con3 = Connector.CreateCon();
        String query = "select * from flights";
        Statement st = con3.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
          list.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
        }
        flight_table.setItems(list);
        }catch(SQLException ex){
        ex.printStackTrace(); }
    }
    @FXML
    public void bookingDisplay(){
        try{
        Connection con = Connector.CreateCon();
        booking_table.getItems().clear();
        bookings_list = booking_table.getItems();
        column_flight_id.setCellValueFactory(new PropertyValueFactory<>("flight_id"));
        booking_id.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        seat_type.setCellValueFactory(new PropertyValueFactory<>("seat_type"));
        total_seats.setCellValueFactory(new PropertyValueFactory<>("total_seats"));
        passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        card_type.setCellValueFactory(new PropertyValueFactory<>("card_type"));
        card_no.setCellValueFactory(new PropertyValueFactory<>("card_no"));
        String query = "select * from bookings where Flight_id =? ";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, flight_id.getText());
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
          bookings_list.add(new Booking(rs.getString("Flight_id"),rs.getString("Booking_id"),rs.getString("Customer_name"),rs.getString("seat"),rs.getString("no_of_seats"),rs.getString("passport_no"),rs.getString("card_type"),rs.getString("card_no")));
        }
        booking_table.setItems(bookings_list);
        }catch(SQLException ex){
        ex.printStackTrace(); }

    }
    @FXML
    public void updateFlight(){
        try{
        String fid = flight_table.getSelectionModel().getSelectedItem().getId();
        Connection con = Connector.CreateCon();
        String query = "update flights set start_location = ?, destination =?, date=?, total_seats =?, economy_seats =?, business_seats =?, departure_time =?, arrival_time=?, charges=? where flight_id =? ";
        PreparedStatement pst =con.prepareStatement(query);
        pst.setString(1,ufrom.getText());
        pst.setString(2,uto.getText());
        pst.setString(3,udeparture_date.getValue().toString());
        pst.setString(4,utotal.getText());
        pst.setString(5,ueconomy.getText());
        pst.setString(6, ubusiness.getText());
        pst.setString(7, udeparture.getText());
        pst.setString(8, uarrival.getText());
        pst.setString(9, ucharges.getText());
        pst.setString(10, fid);
        pst.executeUpdate();
        flight_table.getItems().clear();
        Connection con4 = Connector.CreateCon();
        String query2 = "select * from flights";
        Statement st = con4.createStatement();
        ResultSet rs = st.executeQuery(query2);
        while(rs.next()){
          list.add(new Flight(rs.getString("flight_id"),rs.getString("start_location"),rs.getString("destination"),rs.getString("total_seats"),rs.getString("economy_seats"),rs.getString("business_seats"),rs.getString("departure_time"),rs.getString("arrival_time"),rs.getString("charges"),rs.getString("date")));
        }
        flight_table.setItems(list);
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setContentText("Book updated successfully");
        b.show();
    }catch(SQLException ex){
        ex.printStackTrace();
    }catch(NullPointerException e){
        e.printStackTrace();
    }
    }
    
}
