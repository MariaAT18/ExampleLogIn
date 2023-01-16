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
 * This class is the representation of a crocodile of the test-API.k6.io
 *
 * @author Jucumaris Team
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crocodile {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String sex;
    @Getter @Setter
    @JsonProperty("date_of_birth")
    private String dateBirth;
    @Getter @Setter
    private Integer age;
}