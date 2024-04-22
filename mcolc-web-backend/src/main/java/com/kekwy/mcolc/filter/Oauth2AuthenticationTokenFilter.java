package com.kekwy.mcolc.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kekwy.mcolc.model.AuthenticationDetails;
import com.kekwy.mcolc.model.GameRoleDetails;
import com.kekwy.mcolc.service.GameRoleService;
import com.kekwy.mcolc.util.ResponseBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class Oauth2AuthenticationTokenFilter extends OncePerRequestFilter {

    private ApplicationContext applicationContext;

    private ObjectMapper objectMapper;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        String authService = request.getHeader("AuthorizationService");
        String roleName = request.getHeader("RoleName");
        // 存在 token
        if (authToken != null) {
            // 访问认证服务器获取游戏角色信息
            GameRoleService service = (GameRoleService) applicationContext.getBean(authService + "GameRoleService");
            List<GameRoleDetails> gameRoleDetailsList = service.getGameRoleDetailsList(authToken);
            if (!gameRoleDetailsList.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
                GameRoleDetails gameRoleDetails = null;
                if (gameRoleDetailsList.size() > 1) {   // 当前游戏账户下有多个角色
                    if (roleName != null) {             // 若用户已经选择了角色，则从其账户下找到用户选择的角色
                        for (GameRoleDetails details : gameRoleDetailsList) {
                            if (details.getRoleName().equals(roleName)) {
                                gameRoleDetails = details;
                                break;
                            }
                        }
                    }
                    if (gameRoleDetails == null) {      // 需要用户手动选择一个游戏角色
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/json");
                        ResponseBuilder builder = new ResponseBuilder()
                                .code(1).message("你有多个角色").payload(gameRoleDetailsList);
                        response.getWriter().write(objectMapper.writeValueAsString(builder.build()));
                        return;
                    }
                } else { // 只有一个角色
                    gameRoleDetails = gameRoleDetailsList.get(0);
                }
                UsernamePasswordAuthenticationToken token = // 在上下文中添加认证信息
                        new UsernamePasswordAuthenticationToken(authToken, null);
                token.setDetails(new AuthenticationDetails(gameRoleDetails, service,
                        new WebAuthenticationDetailsSource().buildDetails(request)));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}