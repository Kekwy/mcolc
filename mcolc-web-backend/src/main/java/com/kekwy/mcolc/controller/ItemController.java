package com.kekwy.mcolc.controller;

import com.kekwy.mcolc.controller.api.ItemAPI;
import com.kekwy.mcolc.model.AuthenticationDetails;
import com.kekwy.mcolc.model.vo.ResponseBody;
import com.kekwy.mcolc.service.ItemService;
import com.kekwy.mcolc.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemController implements ItemAPI {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ResponseEntity<ResponseBody> inventory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationDetails details = (AuthenticationDetails) authentication.getDetails();

        String name = details.getGameRoleDetails().getName();
        String id = details.getGameRoleDetails().getId();

        Map<String, String> inventory = itemService.getInventory(name, id);

        ResponseBuilder builder = new ResponseBuilder();
        builder.code(0).payload(inventory).build();
        return ResponseEntity.ok(builder.build());
    }

    @Override
    public ResponseEntity<Resource> icon(String name) {
        Resource resource = itemService.getIcon(name);
        if (resource != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
