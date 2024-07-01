package com.library.platform.upc.iam.domain.services;


import com.library.platform.upc.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
