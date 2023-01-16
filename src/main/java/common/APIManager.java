/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package common;

import entities.Crocodile;
import entities.User;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import java.util.logging.Logger;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
/**
 * This class is for manage all the endpoints of the API
 *
 * @author Jucumaris Team
 * @version 1.0
 */
public final class APIManager {

    private static APIManager instance;
    private final Logger log;

    private APIManager() {
        CommonConfig configuration = ConfigFactory.create(CommonConfig.class);
        log = ATLogger.getInstance().getLogger();
        baseURI = configuration.baseUrl();
        log.info("Set baseURI to: " + baseURI);
    }

    /**
     * Method for get the unique instance for APIManager class
     * @return APIManager instance
     */
    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }
        return instance;
    }

    /**
     * Method for get the response for GET public endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @return response of the request
     */
    public Response getPublic(String endpoint, ContentType contentType) {
        log.info("Send public GET for: " + endpoint);
        return given().contentType(contentType).when().get(endpoint);
    }

    /**
     * Method for get the response for POST public endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param id Id to get the user
     * @return response of the request
     */
    public Response getPublicId(String endpoint, ContentType contentType, String id) {
        log.info("Send public GET for: " + endpoint.replace("{id}", id));
        return given().contentType(contentType).when().get(endpoint.replace("{id}", id));
    }

    /**
     * Method for get the response for GET private endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param header where there is the authentification
     * @return response of the request
     */
    public Response getPrivate(String endpoint, ContentType contentType, Header header) {
        log.info("Send private GET for: " + endpoint);
        return given().contentType(contentType).header(header).when().get(endpoint);
    }

    /**
     * Method for get the response for GET private endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param header where there is the authentification
     * @param id Id to get the private crocodile
     * @return response of the request
     */
    public Response getPrivateId(String endpoint, ContentType contentType, Header header, String id) {
        log.info("Send private GET for: " + endpoint.replace("{id}", id));
        return given().contentType(contentType).header(header).when().get(endpoint.replace("{id}", id));
    }

    /**
     * Method for get the response for POST public endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param user that is gonna send in the request
     * @return response of the request
     */
    public Response postPublic(String endpoint, ContentType contentType, User user) {
        log.info("Send public POST for: " + endpoint);
        return given().contentType(contentType).body(user).when().post(endpoint);
    }

    /**
     * Method for get the response for POST private endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param header where there is the authentification
     * @param crocodile that is going to be added
     * @return response of the request
     */
    public Response postPrivate(String endpoint, ContentType contentType, Header header, Crocodile crocodile) {
        log.info("Send private POST for: " + endpoint);
        return given().contentType(contentType).header(header).body(crocodile).when().post(endpoint);
    }

    /**
     * Method for get the response for PUT private endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param header where there is the authentification
     * @param crocodile that is going to be updated
     * @param id Id to update a crocodile
     * @return response of the request
     */
    public Response putPrivate(String endpoint, ContentType contentType, Header header, Crocodile crocodile,
                               String id) {
        log.info("Send private PUT for: " + endpoint.replace("{id}", id));
        return given().contentType(contentType).header(header).body(crocodile).when().put(endpoint.replace("{id}", id));
    }

    /**
     * Method for get the response for DELETE private endpoint
     * @param endpoint URL of the endpoint
     * @param contentType of the request
     * @param header where there is the authentification
     * @param id Id to delete a crocodile
     * @return response of the request
     */
    public Response deletePrivate(String endpoint, ContentType contentType, Header header, String id) {
        log.info("Send private DELETE for: " + endpoint.replace("{id}", id));
        return given().contentType(contentType).header(header).when().delete(endpoint.replace("{id}", id));
    }

}
