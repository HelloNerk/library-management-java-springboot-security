package com.library.platform.upc.iam.domain.services;



import com.library.platform.upc.iam.domain.model.entities.Role;
import com.library.platform.upc.iam.domain.model.queries.GetAllRolesQuery;
import com.library.platform.upc.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
