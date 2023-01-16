/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package utils;

import common.APIManager;
import common.Constants;
import entities.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This class generate a random user with the paramether that there are in the
 * newuser.properties file
 *
 * @author Adriana Olivera
 * @version 1.0
 */
public class RegisterUser {

    private static final int RANDOM_LIMIT = 100000;
    private Response response;
    private String propertiesPath = "src/test/resources/properties/newuser.properties";
    private User user;

    /**
     * Constructor when it is neccesary to create a new user with parameters from the newuser.propeties
     * file
     */
    public RegisterUser() {
        Properties userProperties = readProperties();
        this.user = generateDefautUser(userProperties);
        sendRequest();
        editProperties(userProperties);
    }

    /**
     * Constructor when the user is sent as a parameter
     */
    public RegisterUser(User newUser) {
        this.user = newUser;
        sendRequest();
    }
    /**
     * Method for send the request to the API
     * @param user that is going to login
     */
    private void sendRequest() {
        String endpoint = APIEndpointsEnums.CREATE_USER.getApiEndpoint();
        this.response = APIManager.getInstance().postPublic(endpoint, ContentType.JSON, user);
    }

    /**
     * Method for generate a new user with the values from the newuser.propeties file
     */
    public User generateDefautUser(Properties userProperties) {
        Random random = new Random();
        int userNumber = random.nextInt(RANDOM_LIMIT);
        String userName = userProperties.getProperty("username") + userNumber;
        User newUser = User.builder().username(userName)
                                .firstName(userProperties.getProperty("first_name"))
                                .lastName(userProperties.getProperty("last_name"))
                                .email(userName + userProperties.getProperty("email"))
                                .password(userProperties.getProperty("password")).build();
        return newUser;
    }

    /**
     * Method for read the newuser.propeties file
     */
    private Properties readProperties() {
        Properties userProperties = new Properties();
        try {
            FileInputStream file = new FileInputStream(propertiesPath);
            userProperties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userProperties;
    }

    /**
     * Method for update the newuser.propeties file
     * @param userProperties
     */
    private void editProperties(Properties userProperties) {
        int userNumber = Integer.parseInt(userProperties.getProperty("number")) + 1;
        userProperties.setProperty("number", userNumber + "");
        try {
            userProperties.store(new FileWriter(propertiesPath), "Update user information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return response of the create user request
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @return the used user as a input of the request
     */
    public User getUser() {
        return user;
    }


}
