package com.kekwy.mcolc.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

public class PlayerDetailsUtil {

    private static JsonArray getItems(List<ItemStack> list) {
        JsonArray array = new JsonArray();
        for (ItemStack itemStack : list) {
            if (itemStack.isEmpty()) {
                array.add(JsonNull.INSTANCE);
            } else {
                JsonObject object = new JsonObject();
                object.addProperty("key", itemStack.getTranslationKey());
                object.addProperty("name", itemStack.getName().getString()); // 从服务端仅能获取英文名
                object.addProperty("damage", itemStack.getDamage());
                object.addProperty("maxDamage", itemStack.getMaxDamage());
                object.addProperty("count", itemStack.getCount());
                array.add(object);
            }
        }
        return array;
    }

    public static JsonObject getPlayerDetails(PlayerEntity player) {

        JsonObject playerDetails = new JsonObject();

        playerDetails.addProperty("uuid", player.getUuidAsString());

        playerDetails.addProperty("name", player.getName().getString());

        // 获取生命值
        playerDetails.addProperty("health", player.getHealth());

        // 获取饱食度
        playerDetails.addProperty("hunger", player.getHungerManager().getFoodLevel());

        // 获取经验值
        JsonObject experience = new JsonObject();

        experience.addProperty("level", player.experienceLevel);
        experience.addProperty("progress", player.experienceProgress);

        playerDetails.add("experience", experience);

        // TODO: 获取当前BUFF
        playerDetails.addProperty("buff", "");

        // 获取背包数据
        PlayerInventory inventory = player.getInventory();
        JsonObject inventoryData = new JsonObject();

        JsonArray hotBarList = getItems(inventory.main.subList(0, 9));
        JsonArray mainList = getItems(inventory.main.subList(9, inventory.main.size()));
        JsonArray armorList = getItems(inventory.armor);
        JsonArray offHandList = getItems(inventory.offHand);

        inventoryData.add("main", mainList);
        inventoryData.add("armor", armorList);
        inventoryData.add("offHand", offHandList);
        inventoryData.add("hotBar", hotBarList);

        playerDetails.add("inventory", inventoryData);

        return playerDetails;
    }

}
