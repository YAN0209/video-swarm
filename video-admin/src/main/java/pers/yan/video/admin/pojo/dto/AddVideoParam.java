package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/6/11 9:42 下午
 */
@Data
public class AddVideoParam {

    /**
     * 名字
     */
    @NotBlank
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 视频组id
     */
    @NotEmpty
    private Integer groupId;

    /**
     * 锁定 0未锁定 1已锁定 默认0
     */
    private boolean lockTag;

}
