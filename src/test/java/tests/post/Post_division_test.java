package tests.post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import rest.Endpoints;
import rest.dto.Operation;
import rest.dto.post.PostComputeRequest;
import tests.BaseTest;
import tests.data_providers.DivisionDataProvider;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasKey;

@Slf4j
public class Post_division_test extends BaseTest {


    @Test(dataProvider = "integer-data-provider", dataProviderClass = DivisionDataProvider.class)
    public void divisionTestPositiveOperands(long val1, long val2) {
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.DIV);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        if (val2 == 0) {
            response.then().statusCode(400).log().all();
        } else {
            double expectedResult = (double) val1 / (double) val2;
            response.then().statusCode(200).log().all();

            assertThat(response).isNotNull();
            response.then().body(matchesJsonSchemaInClasspath(props.getProperty("json_schemes.path")));
            response.then().body("$", hasKey("result"));

            double actualResult = ((Number) response.then().extract().path("result")).doubleValue();
            assertThat(actualResult).isEqualTo(expectedResult);
        }
    }

    @Test(dataProvider = "noInt-data-provider", dataProviderClass = DivisionDataProvider.class)
    public void divisionNoIntNumbersTest(Object val1, Object val2) {
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.DIV);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        response.then().statusCode(400).log().all();
    }

    @Test(dataProvider = "excessInt-data-provider", dataProviderClass = DivisionDataProvider.class)
    public void divisionExcessIntTest(long val1, long val2) {
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.DIV);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        response.then().statusCode(400).log().all();
    }


}
