package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {
    private Map<String, Boolean> gandalfClients = new HashMap<>();
    boolean fullParty = false;
    int partySize =5;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        if (!fullParty) {
            if (gandalfClients.size() < partySize-1) {
                gandalfClients.put(message.getName(), false);
                return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + ", you are add to GandalfPrank!");
            }
            if (gandalfClients.size() == partySize-1) {
                gandalfClients.put(message.getName(), false);
                fullParty = true;
                return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + ", you are add to GandalfPrank!"
                        + "\nParty is full!!!");
            }
        }
        
        if (message.getName().contains("READY") && gandalfClients.containsValue(false) && fullParty) {
            gandalfClients.replace(idClientForOrder66(message), true);
            if (!gandalfClients.containsValue(false)){
                return new Greeting("EXECUTE ORDER 66!");
            }
            return new Greeting(idClientForOrder66(message)+" confirmed.");
        }
        return new Greeting("Sorry, " + HtmlUtils.htmlEscape(message.getName()) + ", party full... Try agian latter...");
    }

 private String idClientForOrder66 (HelloMessage message){
        return message.getName().split(" ")[0];

 }

}
