package tests;

import io.restassured.RestAssured;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected Properties props = new Properties();

    public BaseTest() {
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            //Rest Assured config
            RestAssured.baseURI = props.getProperty("api.uri");
            RestAssured.port = Integer.parseInt(props.getProperty("api.port"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
