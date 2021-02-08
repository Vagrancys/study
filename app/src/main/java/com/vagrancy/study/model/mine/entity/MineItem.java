package com.vagrancy.study.model.mine.entity;

/**
 * @author Vagrancy
 * @date 2021/2/8
 * Github: https:github.com/Vagrancys
 * Email:18050829067@163.com
 * Description: 个人中心item
 */
public class MineItem {
    private int icon;
    private String text;

    public MineItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
