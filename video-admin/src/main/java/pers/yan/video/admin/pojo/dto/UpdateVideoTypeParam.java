package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/13 10:22 下午
 */
@Data
public class UpdateVideoTypeParam extends AddVideoTypeParam {

    /**
     * 主键
     */
    @NotNull
    private Integer id;

}
