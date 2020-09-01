package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/11 9:35 下午
 */
@Data
public class UpdateVideoGroupParam extends AddVideoGroupParam {

    /**
     * 主键
     */
    @NotNull
    private Integer id;

}
