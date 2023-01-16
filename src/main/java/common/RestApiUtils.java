/**
 * Copyright (c) 2023 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package common;

import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

/**
 * This class is to return the base utils that the project needs.
 *
 * @author Maria Hurtado
 * @version 1.0
 */
public final class RestApiUtils {

    private RestApiUtils() {
    }

    public static RequestSpecification createBaseRequest() {
        CommonConfig configuration = ConfigFactory.create(CommonConfig.class);
        return given()
                .when()
                .baseUri(configuration.baseUrl());
    }
}
