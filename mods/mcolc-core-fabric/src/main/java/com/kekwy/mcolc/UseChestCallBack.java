package com.kekwy.mcolc;

import com.kekwy.mcolc.util.ChestUtil;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;


public class UseChestCallBack implements UseBlockCallback {

    public static final String QUEUE_NAME = "mcolc.db.chest.queue";
    public static final String EXCHANGE_NAME = "mcolc.db.chest.direct";
    public static final String ROUTING_KEY = "mcolc.db.chest.key";


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient()) {
            return ActionResult.PASS;
        }
        BlockPos pos = hitResult.getBlockPos();
        // 判断当前方块是否是箱子
        if (world.getBlockState(pos).getBlock() != Blocks.CHEST) {
            return ActionResult.PASS;
        }
        // 判断箱子该箱子是否属于当前玩家
        Set<String> owners = ChestUtil.getOwners(world, pos);
        if (owners.isEmpty()) {
            return ActionResult.PASS;
        } else if (!owners.contains(player.getName().getString())) {
            player.sendMessage(Text.of("该箱子已上锁，你没有权限打开。"), true);
            return ActionResult.FAIL;
        }
        // 获取箱子实体对象，为打开的箱子界面添加监听器，监听玩家存取箱子的事件
        ChestBlockEntity chestEntity = (ChestBlockEntity) world.getBlockEntity(pos);

        player.openHandledScreen(chestEntity);

        player.currentScreenHandler.addListener(new ScreenHandlerListener() {
            @Override
            public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
                System.out.println("Slot " + slotId + " has been updated. New item: " + stack);
            }

            @Override
            public void onPropertyUpdate(ScreenHandler handler, int property, int value) {

            }

        });

        return ActionResult.SUCCESS;
    }

}
