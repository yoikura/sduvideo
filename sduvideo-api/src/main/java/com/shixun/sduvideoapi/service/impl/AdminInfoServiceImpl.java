
package com.shixun.sduvideoapi.service.impl;

import com.shixun.sduvideoapi.domain.dto.*;
import com.shixun.sduvideoapi.domain.entity.AdminInfo;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.mapper.AdminInfoMapper;
import com.shixun.sduvideoapi.service.AdminInfoService;
import com.shixun.sduvideoapi.utils.passwordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Autowired
    AdminInfoMapper adminInfoMapper;

    @Override
    public AdminInfo login(AdminInfoDTO dto) {
        AdminInfo adminInfo = adminInfoMapper.AdminLogin(dto);
        return adminInfo;

    }

    @Override
    public AdminInfo getAdminInfo(AdminInfoDTO dto) {
        return adminInfoMapper.getAdminById(dto);
    }
    @Override
    public VideoPageDTO selectAllVideos(Map map) {
        Integer page = (Integer) map.get("page");
        Integer size = (Integer) map.get("size");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page - 1) * size);
        params.put("size", size);
        params.put("vname", map.get("vname"));
        List<VideoInfo> videoInfos = adminInfoMapper.selectAllVideos(params);
        VideoPageDTO videoPageDTO = new VideoPageDTO();
        videoPageDTO.setPageList(videoInfos);
        int count = adminInfoMapper.selectAllCount(map);
        videoPageDTO.setCount(count);
        System.out.println(videoPageDTO);
        return videoPageDTO;
    }

    @Override
    public boolean examineVideo(ExamineVideoDTO dto) {
        Integer row = adminInfoMapper.examineVideo(dto);
        return row>=1?true:false;
    }

    @Override
    public UserPageDTO selectAllUsers(Map map) {
        Integer page = (Integer) map.get("page");
        Integer size = (Integer) map.get("size");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page", (page - 1) * size);
        params.put("size", size);
        params.put("uname", map.get("uname"));
        List<UserInfo> userInfos = adminInfoMapper.selectAllUsers(params);
        UserPageDTO userPageDTO = new UserPageDTO();
        userPageDTO.setPageList(userInfos);
        int count = adminInfoMapper.selectAllUsersCount();
        userPageDTO.setCount(count);

        return userPageDTO;
    }

    @Override
    public boolean deleteUserById(UserInfoDTO dto) {
        Integer row = adminInfoMapper.deleteUserById(dto);
        return row >= 1;
    }

    @Override
    public Integer deleteUserByList(List<Integer> dtoList) {
        Integer row = adminInfoMapper.deleteUserByList(dtoList);
        return row;
    }

    @Override
    public boolean updateUserById(UserInfoDTO dto) {
        Integer row = adminInfoMapper.updateUserById(dto);
        return row>=1;
    }

    @Override
    public boolean classifyVideo(ClassifyVideoDTO dto) {
        Integer row = adminInfoMapper.classifyVideo(dto);
        return row>=1;
    }

    @Override
    public boolean insertType(VideoTypeDTO dto) {
        Integer row = adminInfoMapper.insertType(dto);
        return row>=1;
    }

    @Override
    public boolean updateType(VideoTypeDTO dto) {
        Integer row = adminInfoMapper.updateType(dto);
        return row>=1;
    }

    @Override
    public boolean deleteType(VideoType videotype) {
        Integer row = adminInfoMapper.deleteType(videotype);
        return row>=1;
    }


}

