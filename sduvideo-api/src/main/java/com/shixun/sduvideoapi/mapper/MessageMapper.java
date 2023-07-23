package com.shixun.sduvideoapi.mapper;

import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {
    List<MessageVO> getAllMessage(Map map);
    Integer addMessage(Map map);
    List<UserInfo> selectToUser(Map map);
}
