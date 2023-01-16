package steps.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginBasicErrorSteps {
    private String endpoint;
    private JSONObject requestParams = new JSONObject();
    private Response response;
    // Pico Container

    @Given("that I am on login endpoint")
    public void shouldGetLoginBasicEndpoint() {
        endpoint = "https://test-api.k6.io/auth/basic/login/";
    }

    @When("I send an invalid user {string} or {string}")
    public void shouldEnterUsernameAndPassword(String loginn, String password) {
        requestParams.put("username", loginn);
        requestParams.put("password", password);
        response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString())
                .when()
                .post(endpoint);
    }

    @Then("I should get an error message {string}")
    public void shouldIGetAllDataOfTheUser(String message) {
        System.out.println("There is a " + message);
        assertEquals(400, response.statusCode());
        assertEquals("[Incorrect username or password.]",
                response.getBody().jsonPath().getString("non_field_errors"));
    }
}
