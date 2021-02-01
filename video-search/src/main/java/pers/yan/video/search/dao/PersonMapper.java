package pers.yan.video.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.yan.video.search.pojo.entity.Person;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/10/12 11:43 上午
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 根据videoGroupId获取相关人员信息
     * @param groupId 视频id
     * @return 人员列表
     */
    @Select("select p.* from " +
            " (select w.person_id from writer w where w.group_id = #{groupId} " +
            " union all " +
            " select d.person_id from director d where d.group_id = #{groupId} " +
            " union all " +
            " select a.person_id from actor a where a.group_id = #{groupId}) t " +
            " left join person p on t.person_id = p.id")
    List<Person> getPersonByVideoGroupId(int groupId);

}