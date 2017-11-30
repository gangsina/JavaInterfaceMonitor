package com.bentengwu.service;

import com.bentengwu.RootObject;
import com.bentengwu.utillib.String.StrUtils;
import com.bentengwu.utillib.number.NumberUtils;
import com.bentengwu.utillib.validate.ValidateUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.bentengwu.nannytoolforredissupport.*;
import redis.clients.jedis.Jedis;

/**
 * 所有控制配置设置在本业务类中完成.
 * @Author <a href="bentengwu@163.com">thender.xu</a>
 * @Date 2017/11/30 13:36.
 */
public class ConfigSetService extends RootObject{
    //--日志
    protected final static Logger logger = LoggerFactory.getLogger(ConfigSetService.class);


    //--配置
    //单位时间的键
    protected final String per_seconds = "long:limit:${bizzType}:per";
    //阀值. 单位时间内,单个接口允许被访问的次数
    protected final String limit_per_intf_times = "long:limit:${bizzType}:per:${realOptVal}";
    //阀值. 单位时间内,单个接口被单个用户访问的次数阀值
    protected final String limit_per_intf_session_times = "long:limit:${bizzType}:userper:${realOptVal}";

    //--默认值
    //单位时间的长度.默认60S.
    protected int perSeconds = 60;
    //阀值. 单位时间内,单个接口允许被访问的次数. 数据缓存到SDK,每30S重新从REDIS中读取配置
    protected int limitPerIntfTimes = Integer.MAX_VALUE;
    //阀值. 单位时间内,单个接口被单个用户访问的次数阀值. 数据缓存到应用,每30S重新从REDIS中读取配置
    protected int limitPerIntfSessionTimes = Integer.MAX_VALUE;


    //--内存业务支持
    protected RAbstractService rAbstractService;

    //--构造器
    //默认构造器
    public ConfigSetService() {

    }

    //指定内存数据源的构造器
    public ConfigSetService(RAbstractService rAbstractService) {
        this.rAbstractService = rAbstractService;
    }

    //指定默认配置的构造器
    public ConfigSetService(int perSeconds, int limitPerIntfTimes, int limitPerIntfSessionTimes) {
        this.perSeconds = perSeconds;
        this.limitPerIntfTimes = limitPerIntfTimes;
        this.limitPerIntfSessionTimes = limitPerIntfSessionTimes;
    }

    //指定默认配置和内存数据源的构造器
    public ConfigSetService(int perSeconds, int limitPerIntfTimes, int limitPerIntfSessionTimes, RAbstractService rAbstractService) {
        this.perSeconds = perSeconds;
        this.limitPerIntfTimes = limitPerIntfTimes;
        this.limitPerIntfSessionTimes = limitPerIntfSessionTimes;
        this.rAbstractService = rAbstractService;
    }

    //--getter/setter
    public int getPerSeconds() {
        return perSeconds;
    }

    public void setPerSeconds(int perSeconds) {
        this.perSeconds = perSeconds;
    }

    public int getLimitPerIntfTimes() {
        return limitPerIntfTimes;
    }

    public void setLimitPerIntfTimes(int limitPerIntfTimes) {
        this.limitPerIntfTimes = limitPerIntfTimes;
    }

    public int getLimitPerIntfSessionTimes() {
        return limitPerIntfSessionTimes;
    }

    public void setLimitPerIntfSessionTimes(int limitPerIntfSessionTimes) {
        this.limitPerIntfSessionTimes = limitPerIntfSessionTimes;
    }

    public RAbstractService getrAbstractService() {
        return rAbstractService;
    }

    public void setrAbstractService(RAbstractService rAbstractService) {
        this.rAbstractService = rAbstractService;
    }

    //--基础业务接口
    //格式化per_seconds的键名称.
    public String ftKeyPerSeconds(final String bizzType) {
        return StrUtils.replaceEach(this.per_seconds, new String[]{"${bizzType}"}, new String[]{bizzType });
    }

    //格式化limit_per_intf_times的键名称.
    public String ftKeyLimitPerIntfTimes(final String bizzType,final String realOptVal) {
        return StrUtils.replaceEach(this.limit_per_intf_times, new String[]{"${bizzType}","${realOptVal}"}, new String[]{bizzType ,realOptVal});
    }

    //格式化limit_per_intf_session_times的键名称.
    public String ftKeyLimitPerIntfSessionTimes(final String bizzType,final String realOptVal) {
        return StrUtils.replaceEach(this.limit_per_intf_session_times, new String[]{"${bizzType}","${realOptVal}"}, new String[]{bizzType ,realOptVal});
    }

    //--具体特性 业务模型

    /**
     * 设置单个会话在单位时间内允许访问接口的上限值
     * @param bizzType 业务类型
     * @param realOptVal 接口值
     */
    public void setLimitPerIntfSessionTimes(final String bizzType, final String realOptVal, final int times) {
        logger.debug("Set Limit Per Interface Session Times limit : {},{},{}",bizzType,realOptVal,times);
        ValidateUtils.validateParams(bizzType,realOptVal,times);
        rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                return jedis.set(ftKeyLimitPerIntfSessionTimes(bizzType, realOptVal),times+"");
            }
        });
    }

    /**
     * 读取单个会话在单位时间内允许访问接口的上限值
     * @param bizzType 业务类型
     * @param realOptVal 接口值
     */
    public int getLimitPerIntfSessionTimes(final String bizzType, final String realOptVal) {
        ValidateUtils.validateParams(bizzType,realOptVal);
        Object retInt = rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                return jedis.get(ftKeyLimitPerIntfSessionTimes(bizzType, realOptVal));
            }
        });

        return NumberUtils.toInteger(retInt, getLimitPerIntfTimes());
    }

    /**
     * 设置单位时间内,接口允许访问的上限阀值
     * @param bizzType 业务类型
     * @param realOptVal 接口值
     */
    public void setLimitPerIntfTimes(final String bizzType, final String realOptVal, final int times) {
        logger.debug("Set Limit Per Interface Times limit : {},{},{}",bizzType,realOptVal,times);
        ValidateUtils.validateParams(bizzType,realOptVal,times);
        rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                return jedis.set(ftKeyLimitPerIntfTimes(bizzType,realOptVal),times+"");
            }
        });
    }

    /**
     * 读取单位时间接口允许访问的上限值
     * @param bizzType 业务类型
     * @param realOptVal 接口值
     */
    public int getLimitPerIntfTimes(final String bizzType, final String realOptVal) {
        ValidateUtils.validateParams(bizzType,realOptVal);
        Object retInt = rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                return jedis.get(ftKeyLimitPerIntfTimes(bizzType,realOptVal));
            }
        });

        return NumberUtils.toInteger(retInt, getLimitPerIntfTimes());
    }

    //设置具体业务类型的单位时间值.
    public void setPerSeconds(final String bizzType,final int seconds) {
        logger.debug("Set Per Seconds {} --> {}",bizzType,seconds);
        ValidateUtils.validateParams(bizzType,seconds);
        rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                String key = ftKeyPerSeconds(bizzType);
                return jedis.set(key, seconds+"");
            }
        });
    }


    /**
     * @param bizzType 业务类型
     * @return 对应业务类型的单位时间.默认
     */
    public int getPerSeconds(final String bizzType) {
        Object retInt = rAbstractService.execute(new JedisExecuter() {
            @Override
            public Object execute(Jedis jedis) {
                String key = ftKeyPerSeconds(bizzType);
                return jedis.get(key);
            }
        });

        return NumberUtils.toInteger(retInt, getPerSeconds());
    }
}
