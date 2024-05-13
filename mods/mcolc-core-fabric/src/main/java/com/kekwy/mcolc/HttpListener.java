package com.kekwy.mcolc;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.*;
import java.util.concurrent.Executors;

public class HttpListener {

    private final MinecraftServer minecraftServer;
    private HttpServer httpServer;

    public HttpListener(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(27272), 0);
            httpServer.createContext("/inventory", this::inventoryHandler);
            httpServer.setExecutor(Executors.newCachedThreadPool());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(10);
    }

    private void inventoryHandler(HttpExchange exchange) {
        try {
            Map<String, String> queryParams = getQueryParams(exchange.getRequestURI());
            String name = queryParams.getOrDefault("name", "");
            String uuid = queryParams.getOrDefault("uuid", "");

            // 获取玩家背包中的物品信息
            JsonObject inventoryData = getPlayerDetails(uuid, name);
            // 将物品信息作为 JSON 数据返回给请求方
            if (inventoryData == null) {
                exchange.sendResponseHeaders(403, 0);
                exchange.close();
            } else {
                String response = inventoryData.toString();
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonArray getItems(DefaultedList<ItemStack> list) {
        JsonArray array = new JsonArray();
        for (ItemStack itemStack : list) {
            if (itemStack.isEmpty()) {
                array.add(JsonNull.INSTANCE);
            } else {
                JsonObject object = new JsonObject();
                object.addProperty("key", itemStack.getTranslationKey());
//                object.addProperty("name", itemStack.getName().getString()); 从服务端仅能获取英文名
                object.addProperty("damage", itemStack.getDamage());
                object.addProperty("maxDamage", itemStack.getMaxDamage());
                object.addProperty("count", itemStack.getCount());
                array.add(object);
            }
        }
        return array;
    }

    private JsonObject getPlayerDetails(String uuid, String name) {
        ServerPlayerEntity player = minecraftServer.getPlayerManager().getPlayer(name);
        if (player == null || !player.getUuidAsString().equals(uuid)) {
            return null;
        }
        JsonObject playerDetails = new JsonObject();

        // 获取生命值
        playerDetails.addProperty("health", player.getHealth());

        // 获取饱食度
        playerDetails.addProperty("hunger", player.getHungerManager().getFoodLevel());

        // TODO: 获取当前BUFF
        playerDetails.addProperty("buff", "");

        // 获取背包数据
        PlayerInventory inventory = player.getInventory();
        JsonObject inventoryData = new JsonObject();

        JsonArray mainList = getItems(inventory.main);
        JsonArray armorList = getItems(inventory.armor);
        JsonArray offHandList = getItems(inventory.offHand);

        inventoryData.add("main", mainList);
        inventoryData.add("armor", armorList);
        inventoryData.add("offHand", offHandList);

        playerDetails.add("inventory", inventoryData);

        return playerDetails;
    }

    private static Map<String, String> getQueryParams(URI uri) {
        Map<String, String> queryParams = new HashMap<>();
        String query = uri.getQuery();
        if (query != null) {
            Arrays.stream(query.split("&"))
                    .map(param -> param.split("="))
                    .forEach(pair -> queryParams.put(pair[0], pair[1]));
        }
        return queryParams;
    }

}
