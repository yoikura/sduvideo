package com.shixun.sduvideoapi.service;

import com.shixun.sduvideoapi.domain.dto.SetLikeVideoDTO;
import com.shixun.sduvideoapi.domain.dto.SetFavoriteVideoDTO;

import java.util.Map;

public interface VideoService {
    int setFavoriteScene(SetFavoriteVideoDTO dto);

    int setLikeScene(SetLikeVideoDTO dto);

    Boolean play(Map map);
}
