package com.channelsoft.alps.activity.to;

/**
 * 路线信息
 * Created by yuanshun on 2015/1/14.
 */
public class Route {
    private String name;//路线名
    private int imageId;//图片信息
    private int totle;//在线人数

    public Route() {
    }

    public Route(String name, int imageId, int totle) {
        this.name = name;
        this.imageId = imageId;
        this.totle = totle;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTotle() {
        return totle;
    }
}
