package hello;

import java.util.HashMap;
import java.util.Map;



public class GandalfPrank {

    private  Map<String,Boolean> gandalfClients= new HashMap<>();

    public Map<String, Boolean> getGandalfClients() {
        return gandalfClients;
    }

    public void setGandalfClients(Map<String, Boolean> gandalfClients) {
        this.gandalfClients = gandalfClients;
    }


}
