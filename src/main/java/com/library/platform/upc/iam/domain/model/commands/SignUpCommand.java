package com.library.platform.upc.iam.domain.model.commands;



import com.library.platform.upc.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
