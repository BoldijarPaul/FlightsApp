package Domain;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "flights")
public class Flight {

    public Flight(){

    }
    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    @DatabaseField(id = true, columnName = "id", dataType = DataType.INTEGER)
    int id;
    @DatabaseField( columnName = "destination", dataType = DataType.STRING)
    String destination;
    @DatabaseField( columnName = "airport", dataType = DataType.STRING)
    String airport;
    @DatabaseField( columnName = "departure_date", dataType = DataType.STRING)
    String departure_date;
    @DatabaseField( columnName = "departure_date", dataType = DataType.STRING)
    String departure_time;
    @DatabaseField( columnName = "seats", dataType = DataType.INTEGER)
    int seats;

    public Flight(int id, String destination, String departure_date, String departure_time, String airport, int seats) {
        this.id = id;
        this.destination = destination;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.airport = airport;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Domain.Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", airport='" + airport + '\'' +
                ", seats=" + seats +
                '}';
    }
}
