package cn.shaojiel.junit5.annotation;

import cn.shaojiel.junit5.registration.WebClient;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoCloseTest {

    @AutoClose // <1>
    WebClient webClient = new WebClient(); // <2>

    String serverUrl = // specify server URL ...
            "https://localhost";

    @Test
    void getProductList() {
        // Use WebClient to connect to web server and verify response
        assertEquals(200, webClient.get(serverUrl + "/products").getResponseStatus());
    }
}
