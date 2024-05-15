package com.kekwy.mcolc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryMapper {

    String selectItemUuid(@Param("uuid") String uuid, @Param("slot") String slot, @Param("index") int index);

    boolean exists(@Param("uuid") String uuid, @Param("slot") int slot, @Param("index") int index);

    boolean insert(@Param("uuid") String uuid, @Param("slot") int slot,
                   @Param("index") int index, @Param("itemUuid") String itemUuid);

    boolean update(@Param("uuid") String uuid, @Param("slot") int slot,
                   @Param("index") int index, @Param("itemUuid") String itemUuid);
}
