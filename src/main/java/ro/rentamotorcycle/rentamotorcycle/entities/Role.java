package ro.rentamotorcycle.rentamotorcycle.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER("User"),
    ADMIN("Admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role fromString(String name) {
        for (Role role : Role.values()) {
            if (role.name.equalsIgnoreCase(name)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
