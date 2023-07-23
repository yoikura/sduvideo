package com.shixun.sduvideoapi.service;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.FollowDTO;
import com.shixun.sduvideoapi.domain.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserInfoService {
    public Result<UserInfo> login(HttpServletRequest request, Map<String, String> map);
    public boolean send(String tel, String code);

    public Integer insertAttention(FollowDTO followDTO);
    public Integer updateUserInfoAttentionEd(FollowDTO followDTO);

    public Integer updateUserInfoAttentionIng(FollowDTO followDTO);

    Boolean attention(FollowDTO followDTO);

    Boolean deleteAttention(FollowDTO followDTO);

    int addUser(Map map);

    public Integer deleteInsertAttention(FollowDTO followDTO);
    public Integer dUpdateUserInfoAttentionEd(FollowDTO followDTO);

    public Integer dUdateUserInfoAttentionIng(FollowDTO followDTO);

    public Result<String> logout(HttpServletRequest request);
}
