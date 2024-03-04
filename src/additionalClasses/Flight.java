
package additionalClasses;

public class Flight {
    
    private String id;
    private String from;
    private String to;
    private String total;
    private String economy;
    private String business;
    private String departure;
    private String arrival;
    private String charges;
    private String date;

    public Flight(String id, String from, String to, String total, String economy, String business, String departure, String arrival, String charges, String date) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.total = total;
        this.economy = economy;
        this.business = business;
        this.departure = departure;
        this.arrival = arrival;
        this.charges = charges;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTotal() {
        return total;
    }

    public String getEconomy() {
        return economy;
    }

    public String getBusiness() {
        return business;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getCharges() {
        return charges;
    }

    public String getDate() {
        return date;
    }
    
    
}
