package com.wangpiece.ious.vo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc 视频播放相关参数
 * @date 2018-12-15 10:14
 */
public class VideoVO implements Serializable{

    private static final long serialVersionUID = 4159592366535553123L;
    /**
     * 视频名称
     */
    private String videoName;
    /**
     * 视频地址
     */
    private String videoSrc;
    /**
     * 视频封面图片地址
     */
    private String poster;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
