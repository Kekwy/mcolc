package com.kekwy.mcolc.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ChestUtil {

    /**
     * 获取该箱子的所有者（们）
     */
    public static Set<String> getOwners(World world, BlockPos pos) {
        Set<String> res = new HashSet<>();
        // 获取箱子正前方的告示牌 TODO: 处理大型箱子的情况
        BlockState chestState = world.getBlockState(pos); // 获取箱子的方块状态
        Direction facingDirection = chestState.get(Properties.HORIZONTAL_FACING);
        BlockPos adjacentPos = pos.offset(facingDirection);
        if (world.getBlockState(adjacentPos).isIn(BlockTags.SIGNS)) {
            BlockEntity blockEntity = world.getBlockEntity(adjacentPos);
            if (blockEntity instanceof SignBlockEntity signEntity) {
                Text[] messages = signEntity.getFrontText().getMessages(false);
                // 确定该告示牌上文本表示箱子的所有者
                if (messages.length > 1 && messages[0].getString().equals("[private]")) {
                    for (int i = 1; i < messages.length; i++) {
                        res.add(messages[i].getString());
                    }
                }
            }
        }
        return res;
    }

}
