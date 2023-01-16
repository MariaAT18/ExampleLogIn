import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Login {
    @Test
    public void verifyBasicUserLogin() {
        String BASE_URL = "https://test-api.k6.io";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "MariaHurtadoAT18");
        requestParams.put("password", "maria123");
        given().
                body(requestParams.toString()).contentType(ContentType.JSON).
                when().
                post(BASE_URL + "/auth/basic/login/").
                then().
                assertThat().
                statusCode(200).
                body("username", Matchers.equalTo("MariaHurtadoAT18"));
    }
}
