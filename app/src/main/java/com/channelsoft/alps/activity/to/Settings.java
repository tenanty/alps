package com.channelsoft.alps.activity.to;

/**
 * Created by tenanty on 15/1/14.
 */
public class Settings {
    private String settingsName;//设置信息
    private String settingsValue;//设置值信息
    private int settingsImg;//图片信息

    public Settings(String settingsName, int settingsImg) {
        this.settingsName = settingsName;
        this.settingsImg = settingsImg;
    }

    public Settings(String settingsName, String settingsValue, int settingsImg) {
        this.settingsName = settingsName;
        this.settingsValue = settingsValue;
        this.settingsImg = settingsImg;
    }

    public String getSettingsName() {
        return settingsName;
    }

    public int getSettingsImg() {
        return settingsImg;
    }

    public String getSettingsValue() {
        return settingsValue;
    }
}
