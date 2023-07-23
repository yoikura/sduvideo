package com.shixun.sduvideoapi.mapper;


import com.shixun.sduvideoapi.domain.dto.CommentDTO;
import com.shixun.sduvideoapi.domain.dto.SetLikeVideoDTO;
import com.shixun.sduvideoapi.domain.dto.SetFavoriteVideoDTO;
import com.shixun.sduvideoapi.domain.entity.VideoFavorite;
import com.shixun.sduvideoapi.domain.entity.VideoInfo;
import com.shixun.sduvideoapi.domain.entity.VideoLike;
import com.shixun.sduvideoapi.domain.entity.VideoType;
import com.shixun.sduvideoapi.domain.vo.CommentVO;
import com.shixun.sduvideoapi.domain.vo.VideoInfoVO;
import com.shixun.sduvideoapi.domain.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {

    List<VideoVO> selectByType(VideoType videoType);

    VideoInfoVO selectById(Map map);

    List<VideoInfoVO> recommend();

    List<CommentVO> getVideoCommentById(Map map);

    List<VideoType> getVideoType();

    int insertFavoriteVideo(SetFavoriteVideoDTO dto);

    int deleteFavoriteVideo(SetFavoriteVideoDTO dto);

    int insertLikeVideo(SetLikeVideoDTO dto);

    int deleteLikeVideo(SetLikeVideoDTO dto);

    int insertComment(CommentDTO dto);

    List<VideoVO> rank();

    int insertHistory(Map map);
    int insertVideoPlayNum(Map map);

    int addFavorite(SetFavoriteVideoDTO dto);
    int subFavorite(SetFavoriteVideoDTO dto);
    VideoFavorite selectFavorite(SetFavoriteVideoDTO dto);
    VideoLike selectLike(SetLikeVideoDTO dto);
    int addLike(SetLikeVideoDTO dto);
    int subLike(SetLikeVideoDTO dto);

    int insertVideo(VideoInfo videoInfo);

    List<VideoInfoVO> search(Map map);

}
