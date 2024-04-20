package com.kekwy.mcolc;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// 自定义物品
//	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

	private MinecraftServer server;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		// 服务器启动后获取服务器实例
		ServerLifecycleEvents.SERVER_STARTED.register(server -> this.server = server);
		// 注册用于接收 Web 请求的自定义网络插件
		// 创建并启动一个简单的 HTTP 服务器，监听指定端口
		try {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8081), 0);
			httpServer.createContext("/inventory", exchange -> {
				// 解析请求头中的玩家 UUID
				String playerUUID = exchange.getRequestHeaders().getFirst("Player-UUID");
				// 获取玩家背包中的物品信息
				JsonObject inventoryData = getPlayerInventoryData(playerUUID);
				// 将物品信息作为 JSON 数据返回给请求方
				String response = inventoryData.toString();
				exchange.sendResponseHeaders(200, response.getBytes().length);
				exchange.getResponseBody().write(response.getBytes());
				exchange.close();
			});
			httpServer.setExecutor(Executors.newCachedThreadPool());
			httpServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
// 对修改箱子的一些尝试
////		Registry.register(Registries.ITEM, new Identifier())
//		LOGGER.info("Hello Fabric world!");
//		ServerWorld world = server.getOverworld();
//		BlockPos chestPos = new BlockPos(100, 64, 200);
//		BlockEntity blockEntity = world.getBlockEntity(chestPos);
//		if (blockEntity instanceof ChestBlockEntity chest) {
//
//			// 修改箱子的物品栏
//			ItemStack newItemStack = new ItemStack(Blocks.OAK_PLANKS, 45);
//			chest.setStack(2, newItemStack); // 根据需要设置位置
//		}
	}

	// 获取玩家背包中的物品信息
	private JsonObject getPlayerInventoryData(String playerUUID) {
		JsonObject inventoryData = new JsonObject();
		if (server != null) {
			PlayerEntity player = server.getPlayerManager().getPlayer(playerUUID);
			if (player != null) {
				JsonObject inventoryItems = new JsonObject();
				for (int i = 0; i < player.getInventory().size(); i++) {
					ItemStack stack = player.getInventory().getStack(i);
					if (!stack.isEmpty()) {
						inventoryItems.addProperty(Integer.toString(i), stack.getItem().getTranslationKey());
					}
				}
				inventoryData.addProperty("player", player.getName().getString());
				inventoryData.add("items", inventoryItems);
			}
		}
		return inventoryData;
	}

}