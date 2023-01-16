package steps.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginBasicSteps {
    private String endpoint;
    private Response response;
    @Given("that I am on login basic endpoint")
    public void shouldGetLoginBasicEndpoint() {
        endpoint = "https://test-api.k6.io/auth/basic/login/";
    }

    @When("^I send a username and password$")
    public void shouldEnterUsernameAndPassword() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "MariaHurtadoAT18");
        requestParams.put("password", "maria123");
        response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .when()
                .post(endpoint);
    }

    @Then("^I get all data of the user$")
    public void shouldIGetAllDataOfTheUser() {
        assertEquals(200, response.statusCode());
        assertNotNull(response.getBody().jsonPath().getString("id"));
        assertNotNull(response.getBody().jsonPath().getString("username"));
        assertNotNull(response.getBody().jsonPath().getString("first_name"));
        assertNotNull(response.getBody().jsonPath().getString("last_name"));
        assertNotNull(response.getBody().jsonPath().getString("email"));
    }
}
