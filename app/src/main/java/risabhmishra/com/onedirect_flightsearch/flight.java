package risabhmishra.com.onedirect_flightsearch;

/**
 * Created by Risabh Mishra on 8/22/2018.
 */

public class flight {
    public String getFlight_id() {
        return flight_id;
    }

    public String getSrc_time() {
        return Src_time;
    }

    public String getDest_time() {
        return dest_time;
    }

    public String getTotal_time() {
        return total_time;
    }

    public String getPrice() {
        return price;
    }

    String flight_id;
    String Src_time;
    String dest_time;
    String total_time;
    String price;

    public flight(String flight_id, String src_time, String dest_time, String total_time, String price) {
        this.flight_id = flight_id;
        Src_time = src_time;
        this.dest_time = dest_time;
        this.total_time = total_time;
        this.price = price;
    }
}
