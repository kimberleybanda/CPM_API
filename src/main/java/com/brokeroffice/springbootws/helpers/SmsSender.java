package com.brokeroffice.springbootws.helpers;




import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class SmsSender {




    public static void smsSender(String message, String recipientPhoneNumber) throws IOException, UnirestException {
        // Create the JSON dynamically
        JSONObject json = new JSONObject();
        json.put("integrationAppId", "kim");
        json.put("phone", recipientPhoneNumber);
        json.put("message", message);

        // Set timeouts
        Unirest.setTimeouts(0, 0);

        // Send the POST request
        HttpResponse<String> response = Unirest.post("http://34.198.175.216:8045/v1/api/sms_integration")
                .header("Content-Type", "application/json")
                .body(json.toString())
                .asString();

        // Optionally, handle the response
        if (response.getStatus() == 200) {
            System.out.println("SMS sent successfully!");
        } else {
            System.out.println("Failed to send SMS. Response: " + response.getBody());
        }
    }
}
