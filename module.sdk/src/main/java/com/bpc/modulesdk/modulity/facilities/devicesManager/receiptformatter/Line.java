package com.bpc.modulesdk.modulity.facilities.devicesManager.receiptformatter;

import android.support.annotation.StringRes;

import com.bpc.modulesdk.BaseApp;

/**
 * Created by Masloed on 30.05.2017.
 */

public class Line {

    public enum TextSize {STANDART, LARGE}

    public enum Align {CENTER, LEFT, RIGNT}

    private String text = "";
    private TextSize textSize = TextSize.STANDART;
    private Align align = Align.LEFT;

    public Line(String text) {
        this.text = text;
    }

    public Line(String text, Align align) {
        this.text = text;
        this.align = align;
    }

    public Line(@StringRes int textID, Align align) {
        this(BaseApp.getContext().getString(textID), align);
    }

    public Line(String text, Align align, TextSize textSize) {
        this.text = text;
        this.textSize = textSize;
        this.align = align;
    }

    public Line(@StringRes int textID, Align align, TextSize textSize) {
        this(BaseApp.getContext().getString(textID), align, textSize);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextSize getTextSize() {
        return textSize;
    }

    public void setTextSize(TextSize textSize) {
        this.textSize = textSize;
    }

    public Align getAlign() {
        return align;
    }

    public void setAlign(Align align) {
        this.align = align;
    }
}
