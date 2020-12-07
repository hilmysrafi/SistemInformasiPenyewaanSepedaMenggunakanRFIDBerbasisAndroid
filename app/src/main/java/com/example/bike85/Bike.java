package com.example.bike85;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
public class Bike {

    public String Sepeda;
    public String Manufactur;
    public String RSSI;
    public String lastUpdate;

    public Bike() {
    }

    public Bike(String Sepeda, String Manufactur, String RSSI, String lastUpdate) {
        this.Sepeda = Sepeda;
        this.Manufactur = Manufactur;
        this.RSSI = RSSI;
        this.lastUpdate = lastUpdate;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Sepeda", Sepeda);
        result.put("Manufactur", Manufactur);
        result.put("RSSI", RSSI);
        result.put("lastUpdate", lastUpdate);
        return result;
    }


}
