package pers.yan.video.common.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 基础service类
 *
 * @author likaiyan
 * @date 2020/11/20 1:55 下午
 */
public class BaseService<E, K> {

    private BaseMapper<E> mapper;

    public BaseService() {
    }

}
