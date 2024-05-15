package com.kekwy.mcolc.mapper;

import com.kekwy.mcolc.model.PlayerDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMapper {

    boolean exists(@Param("uuid") String uuid);

    boolean insert(PlayerDetails playerDetails);

    boolean update(PlayerDetails playerDetails);
}
