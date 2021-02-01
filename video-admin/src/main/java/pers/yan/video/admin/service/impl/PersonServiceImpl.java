package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.PersonMapper;
import pers.yan.video.admin.dao.VideoGroupMapper;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.PersonRoleDto;
import pers.yan.video.admin.pojo.dto.UpdatePersonParam;
import pers.yan.video.admin.pojo.entity.*;
import pers.yan.video.admin.service.PersonService;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/9/2 9:47 上午
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Override
    public PageDto<Person> getPersons(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Person> query = new QueryWrapper<>();
        if (keyword != null) {
            query.like("name", keyword);
        }
        Page<Person> page = new Page<>(pageNum, pageSize);
        this.page(page, query);
        PageDto<Person> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public void updatePerson(UpdatePersonParam updatePersonParam) {
        Person person = new Person();
        BeanUtils.copyProperties(updatePersonParam, person);
        this.updateById(person);
    }

    @Override
    public void addActor(PersonRoleDto personRoleDto) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(personRoleDto, actor);
        this.getBaseMapper().addActor(actor);
    }

    @Override
    public void deleteActor(PersonRoleDto personRoleDto) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(personRoleDto, actor);
        this.getBaseMapper().deleteActor(actor);
    }

    @Override
    public List<VideoGroup> getVideoGroupByActor(Integer personId) {
        return this.getBaseMapper().getVideoGroupByActorId(personId);
    }

    @Override
    public List<Person> getActorByVideoGroupId(Integer groupId) {
        return this.getBaseMapper().getActorByVideoGroup(groupId);
    }

    @Override
    public void addWriter(PersonRoleDto personRoleDto) {
        Writer writer = new Writer();
        BeanUtils.copyProperties(personRoleDto, writer);
        this.getBaseMapper().addWriter(writer);
    }

    @Override
    public void deleteWriter(PersonRoleDto personRoleDto) {
        Writer writer = new Writer();
        BeanUtils.copyProperties(personRoleDto, writer);
        this.getBaseMapper().deleteWriter(writer);
    }

    @Override
    public List<VideoGroup> getVideoGroupByWriter(Integer personId) {
        return this.getBaseMapper().getVideoGroupByWriterId(personId);
    }

    @Override
    public List<Person> getWriterByVideoGroupId(Integer groupId) {
        return this.getBaseMapper().getWriterByVideoGroup(groupId);
    }

    @Override
    public void addDirector(PersonRoleDto personRoleDto) {
        Director director = new Director();
        BeanUtils.copyProperties(personRoleDto, director);
        this.getBaseMapper().addDirector(director);
    }

    @Override
    public void deleteDirector(PersonRoleDto personRoleDto) {
        Director director = new Director();
        BeanUtils.copyProperties(personRoleDto, director);
        this.getBaseMapper().deleteDirector(director);
    }

    @Override
    public List<VideoGroup> getVideoGroupByDirector(Integer personId) {
        return this.getBaseMapper().getVideoGroupByDirectorId(personId);
    }

    @Override
    public List<Person> getDirectorByVideoGroupId(Integer groupId) {
        return this.getBaseMapper().getDirectorByVideoGroup(groupId);
    }
}
