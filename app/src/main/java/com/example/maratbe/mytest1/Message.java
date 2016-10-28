package com.example.maratbe.mytest1;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

/**
 * Created by maratbe on 10/14/2016.
 */

public class Message
{
    private String name;
    private String text;
    private String type;
    private String picture;
    private int color;

    public Message(String name, String text, String type, String picture, int color)
    {
        setName(name);
        setText(text);
        setType(type);
        setPicture(picture);
        setColor(color);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

    public int getColor() {
        return color;
    }
}
