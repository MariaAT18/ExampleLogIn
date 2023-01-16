/**
 * Copyright (c) 2023 Jala University.
 * <p>
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */

package common;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:properties/CommonConfig.properties"})
public interface CommonConfig extends Config {
    String baseUrl();
    String username();
    String password();
    String token();
}
