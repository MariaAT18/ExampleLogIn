import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class MyFirstRestAssured {
    public static void main(String args[]) {
        //getCocodrileStatusOk();
        //getListCocodrilesId();
        //getStatusCode();
        postLogin();
    }
    public static void getCocodrileStatusOk() {
        int statusCode= given().when().get("https://test-api.k6.io/public/crocodiles/3/").getStatusCode();
        System.out.println("The response status is "+statusCode);
    }
    public static void getListCocodrilesId() {
        Response res = (Response) given().when()
                .get("https://test-api.k6.io/public/crocodiles/3/")
                .then().assertThat().statusCode(200)
                .extract().response();
        JsonPath response = res.jsonPath();
        System.out.println(response.toString());
    }
    public static void getStatusCode() {
        Response response = RestAssured.get("https://test-api.k6.io/public/crocodiles/3/");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
    }

    public static void postLogin() {
        //RestAssured.baseURI ="https://test-api.k6.io/auth/basic";
        String BASE_URL ="https://test-api.k6.io";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "MariaHurtadoAT18");
        requestParams.put("password", "maria123");
        //request.body(requestParams.toJSONString());
        //request.body(requestParams.);

        // no Response response = RestAssured.get("https://test-api.k6.io/auth/basic/login/");
        /* Response response = request.put("/login/");
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
        System.out.println(response.getStatusLine());
         */
        // https://test-api.k6.io/auth/basic/login/
        Response response = given().
                body(requestParams.toString()).contentType(ContentType.JSON).
                when().
                post(BASE_URL + "/auth/basic/login/").
                then().extract().response()
                ;


        System.out.println(response.statusCode());
        System.out.println(response.statusCode());
    }
}
