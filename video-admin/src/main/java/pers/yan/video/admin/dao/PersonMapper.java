package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import pers.yan.video.admin.pojo.entity.*;

import java.util.List;

/**
 * 人员
 *
 * @author likaiyan
 * @date 2020/9/1 4:55 下午
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 添加演员
     *
     * @param actor 演员
     */
    @Insert("insert into actor (group_id, person_id) values (#{actor.groupId}, #{actor.personId})")
    void addActor(@Param("actor") Actor actor);

    /**
     * 添加作家
     *
     * @param writer 作家
     */
    @Insert("insert into writer (group_id, person_id) values (#{writer.groupId}, #{writer.personId})")
    void addWriter(@Param("writer") Writer writer);

    /**
     * 添加导演
     *
     * @param director 导演
     */
    @Insert("insert into director (group_id, person_id) values (#{director.groupId}, #{director.personId})")
    void addDirector(@Param("director") Director director);

    /**
     * 删除演员
     *
     * @param actor 演员
     * @return 删除条数
     */
    @Delete("delete from actor where group_id = #{actor.groupId} and person_id = #{actor.person_id}")
    int deleteActor(@Param("actor") Actor actor);

    /**
     * 删除作家
     *
     * @param writer 作家
     * @return 删除条数
     */
    @Delete("delete from writer where group_id = #{writer.groupId} and person_id = #{writer.person_id}")
    int deleteWriter(@Param("writer") Writer writer);

    /**
     * 删除导演
     *
     * @param director 导演
     * @return 删除条数
     */
    @Delete("delete from director where group_id = #{director.groupId} and person_id = #{director.person_id}")
    int deleteDirector(@Param("director") Director director);

    /**
     * 根据演员id获取视频组
     * @param actorId 演员id
     * @return 视频组列表
     */
    @Select("select v.* from actor a left join video_group v on a.group_id = v.id where a.person_id = #{actorId}")
    List<VideoGroup> getVideoGroupByActorId(@Param("actorId") int actorId);

    /**
     * 根据视频组id获取演员
     * @param groupId 视频组id
     * @return 演员列表
     */
    @Select("select p.* from actor a left join person p on a.person_id = p.id where a.group_id = #{groupId}")
    List<Person> getActorByVideoGroup(@Param("groupId") int groupId);

    /**
     * 根据作家id获取视频组
     * @param writerId 作家id
     * @return 视频组列表
     */
    @Select("select v.* from writer w left join video_group v on w.groupId = v.id where w.personId = #{writerId}")
    List<VideoGroup> getVideoGroupByWriterId(@Param("writerId") int writerId);

    /**
     * 根据视频组id获取作家
     * @param groupId 视频组id
     * @return 作家列表
     */
    @Select("select p.* from writer w left join person p on w.personId = p.id where w.groupId = #{groupId}")
    List<Person> getWriterByVideoGroup(@Param("groupId") int groupId);

    /**
     * 根据导演id获取视频组
     * @param directorId 作家id
     * @return 视频组列表
     */
    @Select("select v.* from director d left join video_group v on d.groupId = v.id where d.personId = #{directorId}")
    List<VideoGroup> getVideoGroupByDirectorId(@Param("directorId") int directorId);

    /**
     * 根据视频组id获取导演
     * @param groupId 视频组id
     * @return 导演列表
     */
    @Select("select p.* from director d left join person p on d.personId = p.id where d.groupId = #{groupId}")
    List<Person> getDirectorByVideoGroup(@Param("groupId") int groupId);

}