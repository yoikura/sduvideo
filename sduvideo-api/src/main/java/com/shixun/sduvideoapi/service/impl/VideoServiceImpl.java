package com.shixun.sduvideoapi.service.impl;

import com.shixun.sduvideoapi.domain.dto.SetLikeVideoDTO;
import com.shixun.sduvideoapi.domain.dto.SetFavoriteVideoDTO;
import com.shixun.sduvideoapi.mapper.VideoMapper;
import com.shixun.sduvideoapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int setFavoriteScene(SetFavoriteVideoDTO dto){
        int result = 0;
        if(!dto.isFavorite()){
            result = videoMapper.insertFavoriteVideo(dto);
            videoMapper.addFavorite(dto);
        }else{
            result = videoMapper.deleteFavoriteVideo(dto);
            videoMapper.subFavorite(dto);
        }
        return result;
    }

    @Override
    public int setLikeScene(SetLikeVideoDTO dto){
        int result = 0;
        if(!dto.isLike()){
            result = videoMapper.insertLikeVideo(dto);
            videoMapper.addLike(dto);
        }else{
            result = videoMapper.deleteLikeVideo(dto);
            videoMapper.subLike(dto);
        }
        return result;
    }

    @Override
    public Boolean play(Map map) {

        int i = videoMapper.insertHistory(map);

        int j =videoMapper.insertVideoPlayNum(map);

        return (i>0 && j>0);
    }
}
