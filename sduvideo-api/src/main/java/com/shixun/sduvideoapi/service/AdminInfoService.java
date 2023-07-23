package com.shixun.sduvideoapi.service;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.*;
import com.shixun.sduvideoapi.domain.entity.AdminInfo;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;

import java.util.List;
import java.util.Map;

public interface AdminInfoService  {
    public AdminInfo login(AdminInfoDTO dto);

    public AdminInfo getAdminInfo(AdminInfoDTO dto);

    public VideoPageDTO selectAllVideos(Map map);

    boolean examineVideo(ExamineVideoDTO dto);

    boolean deleteUserById(UserInfoDTO dto);

    Integer deleteUserByList(List<Integer> dto);

    boolean updateUserById(UserInfoDTO dto);

    public UserPageDTO selectAllUsers(Map map);

    boolean classifyVideo(ClassifyVideoDTO dto);

    boolean insertType(VideoTypeDTO dto);

    boolean updateType(VideoTypeDTO dto);

    boolean deleteType(VideoType videotype);



}
