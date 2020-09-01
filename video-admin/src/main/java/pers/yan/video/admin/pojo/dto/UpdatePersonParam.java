package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/13 8:59 下午
 */
@Data
public class UpdatePersonParam extends AddPersonParam{

    /**
     * 主键
     */
    @NotNull
    private Integer id;

}
