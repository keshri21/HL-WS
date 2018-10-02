package com.hlws.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_FIELD,
    ROLE_OFFICE,
    ROLE_PASSIVE_OFFICE,
    ROLE_OWNER,
    ROLE_CUSTOMER,
    ROLE_TRUCK_OWNER,
    ANONYMOUS;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
