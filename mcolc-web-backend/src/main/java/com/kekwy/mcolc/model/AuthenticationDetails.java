package com.kekwy.mcolc.model;

import com.kekwy.mcolc.service.GameRoleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Data
@AllArgsConstructor
public class AuthenticationDetails {
    private GameRoleDetails gameRoleDetails;
    private GameRoleService gameRoleService;
    private WebAuthenticationDetails webAuthenticationDetails;
}
