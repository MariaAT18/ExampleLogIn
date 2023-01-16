package steps.login;

import common.APIManager;
import common.Constants;
import entities.Crocodile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import login.TokenLogin;
import utils.APIEndpointsEnums;

import static common.Constants.BEARER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCrocodileSteps {
    private final TokenLogin tokenLogin;
    private Header authHeader;
    private String age;

    public CreateCrocodileSteps() {
        this.tokenLogin = new TokenLogin();
    }

    @Given("An authenticated session token")
    public void shouldLoginWithTokenAuthentication() {
        tokenLogin.login();
        authHeader = new Header("Authorization", BEARER + tokenLogin.getToken());
    }

    @When("I create a {string} crocodile {string} born in {string}")
    public void shouldTheUserCreateAMaleCrocodile(String sex, String name, String date) {
        String sexValue = sex.equals("male") ? "M" : "F";
        String endpoint = APIEndpointsEnums.PRIVATE_LIST_CROCODILES.getApiEndpoint();

        Crocodile crocodile = Crocodile.builder()
                .name(name)
                .sex(sexValue)
                .dateBirth(date)
                .build();
        // Create a crocodile
        Response response = APIManager.getInstance().postPrivate(endpoint, ContentType.JSON, authHeader, crocodile);
        assertEquals(Constants.STATUS_CREATED, response.statusCode());

        Response responseGet = APIManager.getInstance().getPrivate(endpoint
                        + response.getBody().jsonPath().getString("id"),
                ContentType.JSON, authHeader);
        age = response.getBody().jsonPath().getString("age");
    }

    @Then("the crocodile age should be {int} years old")
    public void shouldValidateTheAgeOfTheCrocodileInYears(int years) {
        assertEquals(years, Integer.valueOf(age));
    }}
