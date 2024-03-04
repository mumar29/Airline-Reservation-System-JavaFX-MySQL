
package additionalClasses;

public class Booking {
    private String flight_id;
    private String booking_id;
    private String name;
    private String seat_type;
    private String total_seats;
    private String passport;
    private String card_type;
    private String card_no;

    public Booking(String flight_id, String booking_id, String name, String seat_type, String total_seats, String passport, String card_type, String card_no) {
        this.flight_id = flight_id;
        this.booking_id = booking_id;
        this.name = name;
        this.seat_type = seat_type;
        this.total_seats = total_seats;
        this.passport = passport;
        this.card_type = card_type;
        this.card_no = card_no;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String getName() {
        return name;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public String getTotal_seats() {
        return total_seats;
    }

    public String getPassport() {
        return passport;
    }

    public String getCard_type() {
        return card_type;
    }

    public String getCard_no() {
        return card_no;
    }
    
    
}
