package com.kekwy.mcolc.controller;

import com.kekwy.mcolc.controller.api.PlayerAPI;
import com.kekwy.mcolc.model.AuthenticationDetails;
import com.kekwy.mcolc.model.PlayerDetails;
import com.kekwy.mcolc.model.vo.ResponseBody;
import com.kekwy.mcolc.service.PlayerService;
import com.kekwy.mcolc.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PlayerController implements PlayerAPI {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public ResponseEntity<ResponseBody> details() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationDetails details = (AuthenticationDetails) authentication.getDetails();

        String name = details.getGameRoleDetails().getName();
        String id = details.getGameRoleDetails().getId();

        PlayerDetails playerDetails = playerService.getPlayerDetails(name, id);

//        if (playerDetails == null) {} // TODO

        ResponseBuilder builder = new ResponseBuilder();
        builder.code(0).payload(playerDetails).build();
        return ResponseEntity.ok(builder.build());
    }

    @Override
    public ResponseEntity<Resource> avatar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationDetails details = (AuthenticationDetails) authentication.getDetails();

        String id = details.getGameRoleDetails().getId();

        Resource avatar = playerService.getPlayerAvatar(id);
        if (avatar != null) {
            return ResponseEntity.ok()
//                    .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                    .contentType(MediaType.IMAGE_PNG).body(avatar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
