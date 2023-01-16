/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package utils;

/**
 * This class is enum with all the endpoints of the test-API.k6.io
 *
 * @author Jucumaris Team
 * @version 1.0
 */
public enum APIEndpointsEnums {
    BASIC_LOGIN("/auth/basic/login/"),
    TOKEN_LOGIN("/auth/token/login/"),
    PUBLIC_LIST_CROCODILES("/public/crocodiles/"),
    PUBLIC_CROCODILE("/public/crocodiles/{id}/"),
    CREATE_USER("/user/register/"),
    LOGOUT("/auth/cookie/logout/"),
    PRIVATE_LIST_CROCODILES("/my/crocodiles/"),
    PRIVATE_CROCODILE("/my/crocodiles/{id}/");

    private String apiEndpoint;

    APIEndpointsEnums(String endpoint) {
        this.apiEndpoint = endpoint;
    }

    public String getApiEndpoint() {
        return this.apiEndpoint;
    }

    @Override
    public String toString() {
        return apiEndpoint;
    }
}