package com.shixun.sduvideoapi.mapper;

import com.shixun.sduvideoapi.domain.dto.FollowDTO;
import com.shixun.sduvideoapi.domain.dto.UserInfoDTO;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.domain.vo.FavoriteVO;
import com.shixun.sduvideoapi.domain.vo.HistoryVO;
import com.shixun.sduvideoapi.domain.vo.VideoFollowVO;
import com.shixun.sduvideoapi.domain.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper {
    UserInfo selectById(Integer uid);
    UserInfo getUserInfo(UserInfoDTO userInfoDTO);
    UserInfo getByTel(Map map);
    Integer insert(UserInfo userInfo);
    Integer updateUserInfo(UserInfoDTO userInfoDTO);

    Integer insertAttention(FollowDTO followDTO);
    Integer updateUserInfoAttentionEd(FollowDTO followDTO);
    Integer updateUserInfoAttentionIng(FollowDTO followDTO);

    FollowDTO selectFollow(FollowDTO followDTO);


    List<FavoriteVO> getFavoriteVideo(UserInfoDTO userInfoDTO);

    List<VideoInfo> getMyVideo(UserInfoDTO userInfoDTO);


    Integer DinsertAttention(FollowDTO followDTO);
    Integer DupdateUserInfoAttentionEd(FollowDTO followDTO);
    Integer DupdateUserInfoAttentionIng(FollowDTO followDTO);

    List<UserInfo> selectAttention(UserInfoDTO userInfoDTO);
    List<UserInfo> selectAttentionToMe(UserInfoDTO userInfoDTO);

    List<HistoryVO>selectHistory(Map map);

    List<VideoFollowVO>getFollowedVideo(Map map);

    FollowDTO selectAttend(FollowDTO followDTO);

    Integer getUserLikeNum(Map map);
    Integer getUserPlayNum(Map map);

    List<VideoInfo> getUserWork(Map map);

    int addUser(Map map);

    UserInfo getByEmail(String tel);

    UserInfo loginByEmail(Map map);
}
