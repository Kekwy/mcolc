package com.kekwy.mcolc;

import com.google.gson.JsonObject;
import com.kekwy.mcolc.util.PlayerDetailsUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class HttpListener {

    private final MinecraftServer minecraftServer;
    private HttpServer httpServer;

    public HttpListener(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(27272), 0);
            httpServer.createContext("/player", this::handlePlayer);
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

    private void handlePlayer(HttpExchange exchange) {
        try {
            Map<String, String> queryParams = getQueryParams(exchange.getRequestURI());
            String name = queryParams.getOrDefault("name", "");
            String uuid = queryParams.getOrDefault("uuid", "");

            // 获取玩家背包中的物品信息
            JsonObject inventoryData = PlayerDetailsUtil.getPlayerDetails(minecraftServer, uuid, name);

            // 设置响应头中的 Content-Type
            exchange.getResponseHeaders().set("Content-Type", "application/json");

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
