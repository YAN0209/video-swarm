package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/13 10:30 下午
 */
@Data
public class UpdateVideoTagParam extends AddVideoTagParam {

    /**
     * 主键
     */
    @NotNull
    private Integer id;

}
