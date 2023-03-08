package com.joey.template.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author Joey
 */

@Getter
public enum RoleEnum {
    /**
     * admin
     */
    ROLE_ADMIN("ROLE_ADMIN", "ADMIN"),
    /**
     * user
     */
    ROLE_USER("ROLE_ADMIN", "USER");
    @JsonValue
    private final String name;
    private final String desc;

    RoleEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}