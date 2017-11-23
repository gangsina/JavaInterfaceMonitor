package com.bentengwu.entity;

/**
 * @Author <a href="bentengwu@163.com">thender.xu</a>
 * @Date 2017/11/23 16:41.
 */
public class UrlMonitorEntity {
    /**
     * The base url for interface;
     * eg :
     *  When unbroken url is http://www.baidu.com/a/b?key=xxx
     *  The url value is a/b
     *  The {@see unbrokenUrl} is http://www.baidu.com/a/b?key=xxx
     */
    private String url;
    /**
     * The client ip. This is not the real ip in some particular case;
     */
    private String ip;
    /**
     * The unbroken url;
     */
    private String unbrokenUrl;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUnbrokenUrl() {
        return unbrokenUrl;
    }

    public void setUnbrokenUrl(String unbrokenUrl) {
        this.unbrokenUrl = unbrokenUrl;
    }
}
