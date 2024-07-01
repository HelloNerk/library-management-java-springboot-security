package com.library.platform.upc.iam.interfaces.rest.transform;


import com.library.platform.upc.iam.domain.model.aggregates.User;
import com.library.platform.upc.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
