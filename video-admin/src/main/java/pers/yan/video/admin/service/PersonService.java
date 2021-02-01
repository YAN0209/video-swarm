package pers.yan.video.admin.service;

import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.PersonRoleDto;
import pers.yan.video.admin.pojo.dto.UpdatePersonParam;
import pers.yan.video.admin.pojo.entity.Person;
import pers.yan.video.admin.pojo.entity.VideoGroup;

import java.util.List;

/**
 * 人员（演员、导演..）
 * @author likaiyan
 * @date 2020/9/1 4:07 下午
 */
public interface PersonService {

    /**
     * 搜索人员
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageDto<Person> getPersons(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 更新人员信息
     *
     * @param updatePersonParam
     */
    void updatePerson(UpdatePersonParam updatePersonParam);

    /**
     * 添加演员
     *
     * @param personRoleDto
     */
    void addActor(PersonRoleDto personRoleDto);

    /**
     * 删除演员
     *
     * @param personRoleDto
     */
    void deleteActor(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByActor(Integer personId);

    List<Person> getActorByVideoGroupId(Integer groupId);

    /**
     * 添加作家
     *
     * @param personRoleDto
     */
    void addWriter(PersonRoleDto personRoleDto);

    /**
     * 删除作家
     *
     * @param personRoleDto
     */
    void deleteWriter(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByWriter(Integer personId);

    List<Person> getWriterByVideoGroupId(Integer groupId);

    /**
     * 添加导演
     *
     * @param personRoleDto
     */
    void addDirector(PersonRoleDto personRoleDto);

    /**
     * 删除导演
     *
     * @param personRoleDto
     */
    void deleteDirector(PersonRoleDto personRoleDto);

    List<VideoGroup> getVideoGroupByDirector(Integer personId);

    List<Person> getDirectorByVideoGroupId(Integer groupId);



}