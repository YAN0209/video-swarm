package pers.yan.video.portal.service;

/**
 * redis服务封装
 *
 * @author likaiyan
 * @date 2020/9/29 2:29 下午
 */
public interface RedisService {

    /**
     * 存储数据
     *
     * @param key   k
     * @param value v
     */
    void set(String key, String value);

    /**
     * 获取数据
     *
     * @param key k
     * @return json数据
     */
    String get(String key);

    /**
     * 设置过期时间
     *
     * @param key    k
     * @param expire 过期时间
     * @return boolean
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     *
     * @param key k
     */
    void remove(String key);

    /**
     * 获取自增id
     *
     * @param key   k
     * @param delta 增加量
     * @return id
     */
    String increment(String key, long delta);

}
