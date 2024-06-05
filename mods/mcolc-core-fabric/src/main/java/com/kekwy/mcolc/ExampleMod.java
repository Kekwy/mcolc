package com.kekwy.mcolc;

import com.google.gson.JsonObject;
import com.kekwy.mcolc.util.PlayerDetailsUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class ExampleMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("Mcolc Core");
    public static final String QUEUE_NAME = "mcolc.db.queue";
    public static final String EXCHANGE_NAME = "mcolc.db.direct";
    public static final String ROUTING_KEY = "mcolc.db.key";

    // 自定义物品
//	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

    private Connection connection;
    private Channel channel;
    private HttpListener httpListener;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // 服务器启动后获取服务器实例
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            httpListener = new HttpListener(server);
            httpListener.start();
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            httpListener.stop();
        });
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            try {
                channel.close();
                connection.close();
            } catch (IOException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        });
        // 监听玩家下线事件
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            JsonObject playerDetails = PlayerDetailsUtil.getPlayerDetails(player);
            try {
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, playerDetails.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // 监听玩家开箱子事件
        UseBlockCallback.EVENT.register(new ChestEventHandler());

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("mcolc");
        factory.setPassword("mcolc");

        // 建立连接
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        // 注册用于接收 Web 请求的自定义网络插件
        // 创建并启动一个简单的 HTTP 服务器，监听指定端口

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


}