package com.bentengwu;

import java.io.Serializable;

/**
 * This is  all class 's  father
 * @Author <a href="bentengwu@163.com">thender.xu</a>
 * @Date 2017/11/23 16:03.
 */
public abstract class RootObject implements Serializable {
    //The bizz type
    public static enum BizzType{
        /**
         * Used for java interface . Supported to spring aop
         */
        service,
        /**
         * Configration for customer code for bizz
         */
        bizzcode,
        /**
         * The web url for bizz
         */
        url,
        /**
         * The client ip
         */
        ip,
        /**
         * The http session
         */
        session,
        /**
         * One process 's time interval.
         */
        timeInterval
    }
}
