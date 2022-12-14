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
import tests.data_providers.MultiplicationDataProvider;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasKey;

@Slf4j
public class Post_multiplication_test extends BaseTest {


    @Test(dataProvider = "integer-data-provider", dataProviderClass = MultiplicationDataProvider.class)
    public void multiplicationTestPositiveOperands(long val1, long val2) {
        long expectedResult = val1 * val2;
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.MUL);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        response.then().statusCode(200).log().all();

        assertThat(response).isNotNull();
        response.then().body(matchesJsonSchemaInClasspath(props.getProperty("json_schemes.path")));
        response.then().body("$", hasKey("result"));

        long actualResult = ((Number) response.then().extract().path("result")).longValue();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test(dataProvider = "noInt-data-provider", dataProviderClass = MultiplicationDataProvider.class)
    public void multiplicationNoIntNumbersTest(Object val1, Object val2) {
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.MUL);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        response.then().statusCode(400).log().all();
    }

    @Test(dataProvider = "excessInt-data-provider", dataProviderClass = MultiplicationDataProvider.class)
    public void multiplicationExcessIntTest(long val1, long val2) {
        PostComputeRequest request = new PostComputeRequest().withVal1(val1).withVal2(val2).withOperation(Operation.MUL);
        Response response = RestAssured.given().contentType(ContentType.JSON).body(request).log().all()
                .post(Endpoints.postCompute);

        response.then().statusCode(400).log().all();
    }
}
