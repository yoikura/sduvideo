package com.shixun.sduvideoapi.controller;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.vo.MessageVO;
import com.shixun.sduvideoapi.mapper.MessageMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageMapper messageMapper;

    @PostMapping("/getAllMessage")
    public Object getAllMessage(@RequestBody Map map){
        List<MessageVO> vos = messageMapper.getAllMessage(map);
        return Result.success(vos);
    }

    @PostMapping("/addMessage")
    public Object addMessage(@RequestBody Map map){
        map.put("createTime",new Date());
        Integer res = messageMapper.addMessage(map);
        if(res > 0){
            return Result.success("success");
        }else {
            return Result.error("error");
        }
    }

    @PostMapping("/selectToUser")
    public Object selectToUser(@RequestBody Map map){
        List<UserInfo> list = messageMapper.selectToUser(map);
        return Result.success(list);
    }
}
