/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package login;

import common.APIManager;
import common.CommonConfig;
import entities.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import utils.APIEndpointsEnums;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class make the request the Token Login endpoint of the test-API.k6.io
 *
 * @author Adriana Olivera
 * @version 1.0
 */

public class TokenLogin {

    private User user;
    private Response response;
    private String token;

    /**
     * Constructor when it is neccesary to use the defined user from the CommonConfig.properties file
     */
    public TokenLogin() {
        CommonConfig config = ConfigFactory.create(CommonConfig.class);
        user = User.builder().username(config.username()).password(config.password()).build();

    }

    /**
     * Constructor when it is neccesary to use an other user
     *  @param user that is going to login
     */
    public TokenLogin(User user) {
        sendRequest(user);
    }

    public void login() {
        sendRequest(user);
    }

    /**
     * Method for send the request to the API
     * @param user that is going to login
     */
    private void sendRequest(User user) {
        String endpoint = APIEndpointsEnums.TOKEN_LOGIN.getApiEndpoint();
        this.response = APIManager.getInstance().postPublic(endpoint, ContentType.JSON, user);
        this.token = response.getBody().jsonPath().getString("access");
    }

    /**
     * Method for update the token value that it is in the CommonConfig.properties file
     */
    public void updateDefaultToken() {
        if (token != null) {
            Properties properties = new Properties();
            try {
                String filePath = "src/test/resources/properties/CommonConfig.properties";
                properties.load(new FileReader(filePath));
                properties.setProperty("token", token);
                properties.store(new FileWriter(filePath), "Token update on");
            } catch (IOException e) {
            }
        }
    }

    /**
     * @return response of the token login request
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @return the access token of the response of the token login request
     */
    public String getToken() {
        return token;
    }

    /**
     * @return the refresh token of the response of the token login request
     */
    public String getRefreshToken() {
        return response.getBody().jsonPath().getString("refresh");
    }
}
