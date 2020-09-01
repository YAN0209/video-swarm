package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author likaiyan
 * @date 2020/6/13 8:57 下午
 */
@Data
public class AddPersonParam {

    /**
     * 姓名
     */
    @NotBlank
    private String name;

    /**
     * 0未知 1男 2女
     */
    private Integer sex;

    /**
     * 所在地
     */
    private String homePlace;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 别名
     */
    private String alias;

    /**
     * imdbId
     */
    private String imdbId;

}
