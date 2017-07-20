package com.bpc.modulesdk.ui.widjets.stateindicator;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;


public class FontManager {

    private static final Map<String, Typeface> FONTS = new HashMap<String, Typeface>();

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesomewebfont.ttf";

    public static Typeface getTypeface(Context context, String fontName) {

        Typeface typeface = FONTS.get(fontName);

        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            FONTS.put(fontName, typeface);
        }

        return typeface;
    }

}