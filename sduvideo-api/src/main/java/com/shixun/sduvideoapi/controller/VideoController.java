package com.shixun.sduvideoapi.controller;

import com.shixun.sduvideoapi.domain.common.Result;
import com.shixun.sduvideoapi.domain.dto.CommentDTO;
import com.shixun.sduvideoapi.domain.dto.SetFavoriteVideoDTO;
import com.shixun.sduvideoapi.domain.dto.SetLikeVideoDTO;
import com.shixun.sduvideoapi.domain.entity.VideoFavorite;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.domain.entity.VideoLike;
import com.shixun.sduvideoapi.domain.entity.VideoType;
import com.shixun.sduvideoapi.domain.vo.CommentVO;
import com.shixun.sduvideoapi.domain.vo.VideoInfoVO;
import com.shixun.sduvideoapi.domain.vo.VideoVO;
import com.shixun.sduvideoapi.mapper.VideoMapper;
import com.shixun.sduvideoapi.service.VideoService;
import com.shixun.sduvideoapi.utils.OSSUtil;
import com.shixun.sduvideoapi.utils.VideoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoService videoService;

    @PostMapping("/getVideoType")
    @ApiOperation("获取视频类型")
    public Result<Object> getVideoType(){
        List<VideoType> videoType = videoMapper.getVideoType();
        return Result.success(videoType);
    }

    @PostMapping("/getVideoByType")
    @ApiOperation("根据类型获取视频列表")
    public Result<Object> getVideoByType(@RequestBody VideoType videoType){
        List<VideoVO> videoInfos = videoMapper.selectByType(videoType);
        return Result.success(videoInfos);
    }

    @PostMapping("/getVideoById")
    public Result<Object> getVideoById(@RequestBody Map map){
        VideoInfoVO videoInfo = videoMapper.selectById(map);
        return Result.success(videoInfo);
    }

    @PostMapping("/setFavoriteVideo")
    @ApiOperation("收藏或取消收藏")
    public Object setFavoriteVideo(@RequestBody SetFavoriteVideoDTO dto){
        dto.setCreateTime(new Date());
        int result = videoService.setFavoriteScene(dto);
        if(result == 0){
            return Result.error("操作失败");
        }else{
            return Result.success("操作成功");
        }
    }

    @PostMapping("/setLikeVideo")
    @ApiOperation("点赞或取消点赞")
    public Object setLikeVideo(@RequestBody SetLikeVideoDTO dto){
        int result = videoService.setLikeScene(dto);
        if(result == 0){
            return Result.error("操作失败");
        }else{
            return Result.success("操作成功");
        }
    }

    @PostMapping("/selectLike")
    public Object selectLike(@RequestBody SetLikeVideoDTO dto){
        VideoLike videoLike = videoMapper.selectLike(dto);
        Map<String, String> map = new HashMap<>();
        if(videoLike == null){
            map.put("isLiked","false");
        }else{
            map.put("isLiked","true");
        }
        return map;
    }

    @PostMapping("/selectFavorite")
    public Object selectFavorite(@RequestBody SetFavoriteVideoDTO dto){
        VideoFavorite videoFavorite = videoMapper.selectFavorite(dto);
        Map<String, String> map = new HashMap<>();
        if(videoFavorite == null){
            map.put("isFavorite","false");
        }else{
            map.put("isFavorite","true");
        }
        return map;
    }

    @PostMapping("/comment")
    @ApiOperation("视频评论")
    public Object comment(@RequestBody CommentDTO dto){
        dto.setCreateTime(new Date());
        int result = videoMapper.insertComment(dto);
        if(result == 0){
            return Result.error("评论失败");
        }else{
            return Result.success("评论成功");
        }
    }

    @PostMapping("/rank")
    @ApiOperation("排行榜")
    public Object rank(){
        List<VideoVO> rankVideo = videoMapper.rank();
        return Result.success(rankVideo);
    }

    @PostMapping("/search")
    @ApiOperation("排行榜")
    public Object search(@RequestBody Map map){
        List<VideoInfoVO> vos = videoMapper.search(map);
        return Result.success(vos);
    }


    @PostMapping("/play")
    @ApiOperation("播放")
    public Object play(@RequestBody Map map){
        map.put("createTime",new Date());
        System.out.println(map);
        Boolean OK = videoService.play(map);

        if(OK){
            return Result.success();
        }else {
            return Result.error("播放失败");
        }
    }

    @PostMapping("/recommend")
    @ApiOperation("推荐")
    public Object recommend(){
        List<VideoInfoVO> recommends = videoMapper.recommend();
        if(recommends != null){
            return Result.success(recommends);
        }else{
            return Result.error("返回失败");
        }
    }


    @PostMapping("/getVideoCommentById")
    @ApiOperation("评论")
    public Object getVideoCommentById(@RequestBody Map map){
        List<CommentVO> videoComment = videoMapper.getVideoCommentById(map);
        if(videoComment != null){
            return Result.success(videoComment);
        }else{
            return Result.error("返回失败");
        }
    }

    /**
     * 上传视频
     */
    @RequestMapping("/addVideo")
//    @CrossOrigin(origins = "*", maxAge = 3600)
    public Object uploadVideo(@RequestParam("imgFile") MultipartFile imgFile, @RequestParam("videoFile") MultipartFile videoFile, VideoInfo videoInfo, HttpServletRequest req) throws Exception {
        OSSUtil ossClient = new OSSUtil();
        Map<String, Object> m = new HashMap<>();
        if (imgFile.isEmpty()) {
            m.put("error", "上传文件不能为空");
        }
        System.out.println(videoInfo);
        if (!((MultipartFile) imgFile).isEmpty()) {
            try {
                //将文件视频和封面
                String ad = ossClient.uploadVideo(videoFile);
                String co= ossClient.uploadImg2Oss(imgFile);
                //获取文件的URl地址  以便前台  显示
                String address = ossClient.getImgUrl(ad);
                String cover = ossClient.getImgUrl(co);
                videoInfo.setAddress(address);
                videoInfo.setCover(cover);
                videoInfo.setCreateTime(new Date());
                videoInfo.setDuration((int) (VideoUtil.getDurationBackMillis(videoFile)/1000));
                //修改民宿视频Vr
                videoMapper.insertVideo(videoInfo);
                return Result.success();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.error("文件不能为空");
    }

}
