package com.shixun.sduvideoapi.service.impl;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.FollowDTO;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.mapper.UserInfoMapper;
import com.shixun.sduvideoapi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean send(String tel, String code) {
        try{
            //配置阿里云
            Config config = new Config()
                    .setAccessKeyId("LTAI5tAq2f3wHEPm5BQdALHn") //您的AccessKey ID
                    .setAccessKeySecret("EYEl4lbHJWkugV0pqIraogF5iaYlna"); // 您的AccessKey Secret
            // 访问的域名
            config.endpoint = "dysmsapi.aliyuncs.com";

            com.aliyun.dysmsapi20170525.Client client =  new com.aliyun.dysmsapi20170525.Client(config);

            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(tel)
                    .setSignName("sduvideo")
                    .setTemplateCode("SMS_461795980")
                    .setTemplateParam("{\"code\":\""+code+"\"}");
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse response = client.sendSms(sendSmsRequest);
            SendSmsResponseBody body = response.getBody();
            System.out.println(body.getMessage());
            return "OK".equals(body.getMessage());
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Result<UserInfo> login(HttpServletRequest request, Map<String, String> map) {
        if(request.getSession().getAttribute("user")!=null){
            return Result.error("已经登录");
        }
        //获取手机号
        String tel = map.get("tel");
        //获取验证码
        String code = map.get("code");
        //从Redis中获取验证码
        String codeFromRedis = redisTemplate.opsForValue().get(tel);
        if (codeFromRedis != null && codeFromRedis.equals(code)){
            UserInfo user = userInfoMapper.getByTel(map);
            request.getSession().setAttribute("user",tel);
            if (user == null){
                //当前用户为新用户，自动完成注册
                user = new UserInfo();
                user.setTel(tel);
                user.setUserName(tel);
                userInfoMapper.insert(user);
                UserInfo user1 = userInfoMapper.getByTel(map);
                redisTemplate.opsForSet().add("user", user1.getUid().toString());
                redisTemplate.delete(tel);
                return Result.success(user1);
            }else {
                redisTemplate.opsForSet().add("user", user.getUid().toString());
                redisTemplate.delete(tel);
                return Result.success(user);
            }
        }
        return Result.error("登录失败！");
    }

    @Override
    public int addUser(Map map) {
        UserInfo userinfo = userInfoMapper.getByEmail((String) map.get("email"));
        if(userinfo != null)
            return 0;
        return userInfoMapper.addUser(map);

    }

    public Result<String> logout(HttpServletRequest request) {
        Long userId = (Long)request.getSession().getAttribute("user");
        if(userId == null){
            return Result.error("未登录");
        }else {
            redisTemplate.opsForSet().remove(userId.toString());
            request.getSession().removeAttribute("user");
            return Result.success("退出成功！");
        }
    }

    @Override
    public Integer insertAttention(FollowDTO followDTO) {
        Integer integer = userInfoMapper.insertAttention(followDTO);
        return integer;
    }
    @Override
    public Integer updateUserInfoAttentionEd(FollowDTO followDTO) {
        Integer integer1 = userInfoMapper.updateUserInfoAttentionEd(followDTO);
        return integer1;
    }
    @Override
    public Integer updateUserInfoAttentionIng(FollowDTO followDTO) {
        return userInfoMapper.updateUserInfoAttentionIng(followDTO);
    }

    public Boolean attention(FollowDTO followDTO) {
        Integer ok1 = insertAttention(followDTO);
       Integer ok2  = updateUserInfoAttentionEd(followDTO);
        Integer ok3 = updateUserInfoAttentionIng((followDTO));
        return (ok1>0 && ok2>0 && ok3>0);
    }

    @Override
    public Boolean deleteAttention(FollowDTO followDTO) {
        Integer ok1 = deleteInsertAttention(followDTO);
        Integer ok2  = dUpdateUserInfoAttentionEd(followDTO);
        Integer ok3 = dUdateUserInfoAttentionIng((followDTO));
        return (ok1>0 && ok2>0 && ok3>0);
    }

    @Override
    public Integer deleteInsertAttention(FollowDTO followDTO) {
        return userInfoMapper.DinsertAttention(followDTO);
    }

    @Override
    public Integer dUpdateUserInfoAttentionEd(FollowDTO followDTO) {
        return userInfoMapper.DupdateUserInfoAttentionEd(followDTO);
    }

    @Override
    public Integer dUdateUserInfoAttentionIng(FollowDTO followDTO) {
        return userInfoMapper.DupdateUserInfoAttentionIng(followDTO);
    }
}
