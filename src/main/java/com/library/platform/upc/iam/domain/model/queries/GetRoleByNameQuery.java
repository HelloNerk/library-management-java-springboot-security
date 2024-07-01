package com.library.platform.upc.iam.domain.model.queries;


import com.library.platform.upc.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
