package pers.yan.video.admin.pojo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/11 9:34 下午
 */
@Data
public class AddVideoGroupParam {

    /**
     * 视频组名
     */
    @NotBlank
    private String name;

    /**
     * 评分
     */
    private Float rate;

    /**
     * imdbId
     */
    private String imdbId;

    /**
     * 别名
     */
    private String alias;

    /**
     * 封面
     */
    private String poster;

    /**
     * 视频码
     */
    private String code;

    /**
     * 锁定 0未锁定 1已锁定
     */
    private boolean lockTag;

}
