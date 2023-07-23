package com.shixun.sduvideoapi.controller;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.*;
import com.shixun.sduvideoapi.domain.entity.AdminInfo;
import com.shixun.sduvideoapi.domain.entity.UserInfo;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.mapper.AdminInfoMapper;
import com.shixun.sduvideoapi.service.AdminInfoService;
import com.shixun.sduvideoapi.utils.MD5Util;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminInfo")
@CrossOrigin
public class AdminInfoController {
    @Autowired
    private AdminInfoService adminInfoService;
    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdminInfo> login(@RequestBody AdminInfoDTO dto){
        AdminInfo adminInfo = adminInfoService.login(dto);
        if(adminInfo != null){
            return Result.success(adminInfo);
        }
        return Result.error("登录失败！");
    }

    @PostMapping("/getAdminInfo")
    @ApiOperation("获取管理员信息")
    public Result<AdminInfo> getAdminInfo(@RequestBody AdminInfoDTO dto){
        AdminInfo adminInfo = adminInfoService.getAdminInfo(dto);
        if(adminInfo!=null){
            return Result.success(adminInfo);
        }
        return Result.error("查询信息失败");
    }

    @PostMapping("/selectAllVideos")
    @ApiOperation("查看所有视频")
    public Object selectAllVideos(@RequestBody Map map){
        VideoPageDTO videoPageDTO = adminInfoService.selectAllVideos(map);
        return Result.success(videoPageDTO);
    }

    @PostMapping("/selectAllUsers")
    @ApiOperation("查看所有用户")
    public Object selectAllUsers(@RequestBody Map map){
        UserPageDTO userPageDTO = adminInfoService.selectAllUsers(map);
        return Result.success(userPageDTO);
    }

    @PostMapping("/examineVideo")
    @ApiOperation("审核视频")
    public Object examineVideo(@RequestBody ExamineVideoDTO dto){
        boolean b = adminInfoService.examineVideo(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @PostMapping("/deleteUserById")
    @ApiOperation("根据id删除单个用户")
    public Object deleteUserById(@RequestBody UserInfoDTO dto){
        boolean b = adminInfoService.deleteUserById(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @PostMapping("/deleteUserByList")
    @ApiOperation("根据id删除多个用户")
    public Object deleteUserByList(@RequestBody List<Integer> dtoList){
        Integer row=adminInfoMapper.deleteUserByList(dtoList);
        if(row>0){
            return Result.success(row);
        }else{
            return Result.error("删除了0个用户！");
        }
    }

    @PostMapping("/updateUserById")
    @ApiOperation("根据id更新用户手机号和邮箱")
    public Object updateUserById(@RequestBody UserInfoDTO dto){
        boolean b = adminInfoService.updateUserById(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @PostMapping("/classifyVideo")
    @ApiOperation("根据视频vid来修改视频类别tid")
    public Object classifyVideo(@RequestBody ClassifyVideoDTO dto){
        boolean b = adminInfoService.classifyVideo(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }
    @PostMapping("/insertVideoType")
    @ApiOperation("增加视频类别")
    public Object insertVideoType(@RequestBody VideoTypeDTO dto){
        boolean b = adminInfoService.insertType(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @PostMapping("/updateVideoType")
    @ApiOperation("修改视频类别")
    public Object updateVideoType(@RequestBody VideoTypeDTO dto){
        boolean b = adminInfoService.updateType(dto);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }

    @PostMapping("/deleteVideoType")
    @ApiOperation("删除视频类别")
    public Object deleteVideoType(@RequestBody VideoType videotype){
        boolean b = adminInfoService.deleteType(videotype);
        if(b){
            return Result.success("操作成功");
        }else{
            return Result.error("操作失败");
        }
    }
}
