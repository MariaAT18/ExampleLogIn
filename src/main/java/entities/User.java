/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * This class is the representation of a user of the test-API.k6.io
 *
 * @author Libertad Tolaba
 * @version 1.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Getter @Setter
    private String username;
    @Getter @Setter
    @JsonProperty("first_name")
    private String firstName;
    @Getter @Setter
    @JsonProperty("last_name")
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
}