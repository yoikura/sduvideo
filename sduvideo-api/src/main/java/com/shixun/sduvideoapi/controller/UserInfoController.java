package com.shixun.sduvideoapi.controller;

import com.alibaba.druid.util.StringUtils;
import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.FollowDTO;
import com.shixun.sduvideoapi.domain.dto.UserInfoDTO;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.domain.vo.FavoriteVO;
import com.shixun.sduvideoapi.domain.vo.HistoryVO;
import com.shixun.sduvideoapi.domain.vo.VideoFollowVO;
import com.shixun.sduvideoapi.mapper.UserInfoMapper;
import com.shixun.sduvideoapi.utils.RandomUtil;
import com.shixun.sduvideoapi.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/userinfo")
@CrossOrigin
public class UserInfoController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostMapping("/sendMsg")
    @ApiOperation("发送验证码")
    public Result<String> sendMsg(@RequestBody UserInfoDTO dto){
        String tel = dto.getTel();
        if (!StringUtils.isEmpty(tel)){
            //生成随机验证码
            String code = RandomUtil.getSixBitRandom();
            System.out.println(code);
            //调用Api完成发送短信
            boolean isSend = userInfoService.send(dto.getTel(),code);
            if(isSend){
                redisTemplate.opsForValue().set(dto.getTel(),code,2, TimeUnit.MINUTES);
                return Result.success(code);
            }
        }
        return Result.error("发送失败");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserInfo> login(HttpServletRequest request, @RequestBody Map<String, String> map){
        return userInfoService.login(request,map);
    }

    @PostMapping("/loginByEmail")
    @ApiOperation("用户登录")
    public Result<UserInfo> loginByEmail(@RequestBody Map<String, String> map){
        UserInfo userInfo = userInfoMapper.loginByEmail(map);
        if(userInfo!=null){
            return Result.success(userInfo);
        }else {
            return Result.error("用户名或密码错误");
        }
    }

    @PostMapping("/addUser")
    @ApiOperation("注册用户")
    public Object addUser(@RequestBody Map map){
        int OK = userInfoService.addUser(map);
        if(OK > 0){
            return Result.success();
        }else{
            return Result.error("添加错误");
        }
    }

    @PostMapping("/logout")
    @ApiOperation("用户注销")
    public Result<String> logout(HttpServletRequest request){
        return userInfoService.logout(request);
    }

    @PostMapping("/getUserInfo")
    @ApiOperation("获取用户个人信息")
    public Result<UserInfo> getPersonalInfo(@RequestBody UserInfoDTO dto){
        UserInfo userInfo = userInfoMapper.getUserInfo(dto);
        return Result.success(userInfo);
    }

    @PostMapping("/updateUserInfo")
    @ApiOperation("修改用户个人信息")
    public Result<String> updateUserInfo(@RequestBody UserInfoDTO dto){
        Integer result = userInfoMapper.updateUserInfo(dto);
        if(result>0){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }
    }

    @PostMapping("/getFavoriteVideo")
    @ApiOperation("查询收藏视频")
    public Result<List<FavoriteVO>> getFavoriteVideo(@RequestBody UserInfoDTO dto){
        List<FavoriteVO> list = userInfoMapper.getFavoriteVideo(dto);
        return Result.success(list);
    }

    @PostMapping("/getMyVideo")
    @ApiOperation("查询收藏视频")
    public Result<List<VideoInfo>> getMyVideo(@RequestBody UserInfoDTO dto){
        List<VideoInfo> list = userInfoMapper.getMyVideo(dto);
        return Result.success(list);
    }

    @PostMapping("/attention")
    public Result<String> Attention(@RequestBody FollowDTO followDTO){


        followDTO.setCreateTime(new Date());
        FollowDTO selectFollow = userInfoMapper.selectFollow(followDTO);
        if(selectFollow != null)
            return Result.error("已关注不可重复关注");
        Boolean OK = userInfoService.attention(followDTO);
        if(OK)  {
            return Result.success();
        }else {
            return Result.error("已关注");
        }
    }


    @PostMapping("/deleteAttention")
    public Result<String> deleteAttention(@RequestBody FollowDTO followDTO){

        FollowDTO selectFollow = userInfoMapper.selectFollow(followDTO);
        if(selectFollow == null)
            return Result.error("不在关注行列，不可操作");
        Boolean OK = userInfoService.deleteAttention(followDTO);
        if(OK)  {
            return Result.success("取消成功");
        }else {
            return Result.error("取消失败");
        }
    }

    @PostMapping("/getAttention")
    @ApiOperation("查找关注的用户")
    public Result<Object> getAttention(@RequestBody UserInfoDTO dto){
        List<UserInfo> userInfos = userInfoMapper.selectAttention(dto);
        return  Result.success(userInfos);



    }

    @PostMapping("/getAttentionTo")
    @ApiOperation("查找谁关注我")
    public Result<Object> getAttentionTo(@RequestBody UserInfoDTO dto){
        List<UserInfo> userInfos = userInfoMapper.selectAttentionToMe(dto);
        return Result.success(userInfos);

    }

    @PostMapping("/selectHistory")
    @ApiOperation("查找历史记录")
    public Result<Object> selectHistory(@RequestBody Map map){
        List<HistoryVO> videoInfos = userInfoMapper.selectHistory(map);
        return Result.success(videoInfos);

    }

    @PostMapping("/selectAttend")
    public Object selectAttend(@RequestBody FollowDTO followDTO){
        FollowDTO follow = userInfoMapper.selectAttend(followDTO);
        Map<String, String> map = new HashMap<>();
        if(follow == null){
            map.put("isAttend","false");
        }else{
            map.put("isAttend","true");
        }
        return map;
    }

    @PostMapping("/getUserLikeNum")
    public Object getUserLikeNum(@RequestBody Map map){
        Integer num = userInfoMapper.getUserLikeNum(map);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("likeNum",num);
        return map1;
    }

    @PostMapping("/getUserPlayNum")
    public Object getUserPlayNum(@RequestBody Map map){
        Integer num = userInfoMapper.getUserPlayNum(map);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("playNum",num);
        return map1;
    }

    @PostMapping("/getUserWork")
    public Object getUserWork(@RequestBody Map map){
        List<VideoInfo> list = userInfoMapper.getUserWork(map);
        return Result.success(list);
    }

    @PostMapping("/getFollowedVideo")
    public Object getFollowedVideo(@RequestBody Map map){
        List<VideoFollowVO> list = userInfoMapper.getFollowedVideo(map);
        return Result.success(list);
    }

}
