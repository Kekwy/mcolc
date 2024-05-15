package com.kekwy.mcolc.mapper;

import com.kekwy.mcolc.model.PlayerDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerDetailsMapper {
    boolean insert(PlayerDetails playerDetails);
}
