package com.shixun.sduvideoapi.mapper;

import com.shixun.sduvideoapi.domain.dto.*;
import com.shixun.sduvideoapi.domain.entity.AdminInfo;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminInfoMapper{
    AdminInfo AdminLogin(AdminInfoDTO dto);

    AdminInfo getAdminById(AdminInfoDTO dto);

    Integer selectAllCount(Map map);
    Integer selectAllUsersCount();

    List<UserInfo> selectAllUsers(Map map);
    List<VideoInfo> selectAllVideos(Map map);

    List<UserInfo> selectAllUsers();

    Integer examineVideo(ExamineVideoDTO dto);

    Integer deleteUserById(UserInfoDTO dto);

    Integer deleteUserByList(List<Integer> dtoList);

    Integer updateUserById(UserInfoDTO dto);

    Integer classifyVideo(ClassifyVideoDTO dto);

    Integer insertType(VideoTypeDTO dto);

    //修改视频类别
    Integer updateType(VideoTypeDTO dto);

    //删除视频类别(将删除标记改为1）
    Integer deleteType(VideoType videotype);
}
