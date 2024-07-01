package com.library.platform.upc.iam.interfaces.rest.transform;


import com.library.platform.upc.iam.domain.model.entities.Role;
import com.library.platform.upc.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
