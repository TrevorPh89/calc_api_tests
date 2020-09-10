package tests.get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import rest.Endpoints;
import rest.dto.Operation;
import tests.BaseTest;
import tests.data_providers.SubtractionDataProvider;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasKey;

@Slf4j
public class Get_subtraction_test extends BaseTest {

    @Test(dataProvider = "integer-data-provider", dataProviderClass = SubtractionDataProvider.class)
    public void subtractionIntNumbersTest(long val1, long val2) {
        long expectedResult = val1 - val2;
        Response response = RestAssured.given()
                .queryParam("val1", val1)
                .queryParam("val2", val2).log().all()
                .get(Endpoints.getCompute, Operation.SUB.getValue());

        response.then().statusCode(200).log().all();

        assertThat(response).isNotNull();
        response.then().body(matchesJsonSchemaInClasspath(props.getProperty("json_schemes.path")));
        response.then().body("$", hasKey("result"));

        long actualResult = ((Number) response.then().extract().path("result")).longValue();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(dataProvider = "noInt-data-provider", dataProviderClass = SubtractionDataProvider.class)
    public void subtractionNoIntNumbersTest(Object val1, Object val2) {
        Response response = RestAssured.given()
                .queryParam("val1", val1)
                .queryParam("val2", val2).log().all()
                .get(Endpoints.getCompute, Operation.SUB.getValue());

        response.then().statusCode(400).log().all();
    }

    @Test(dataProvider = "excessInt-data-provider", dataProviderClass = SubtractionDataProvider.class)
    public void subtractionExcessIntTest(Object val1, Object val2) {
        Response response = RestAssured.given()
                .queryParam("val1", val1)
                .queryParam("val2", val2).log().all()
                .get(Endpoints.getCompute, Operation.SUB.getValue());

        response.then().statusCode(400).log().all();
    }

}
