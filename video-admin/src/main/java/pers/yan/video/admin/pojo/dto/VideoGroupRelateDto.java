package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 视频组 类型 标签 关联 传输类
 * @author likaiyan
 * @date 2020/6/14 10:22 上午
 */
@Data
public class VideoGroupRelateDto {

    /**
     * 视频组id
     */
    @NotNull
    private Integer groupId;

    /**
     * 视频类型 视频标签 id
     */
    @NotNull
    private Integer relateId;

}
