package com.example.ikastyle.Util;

import java.util.Locale;

public class Util {
    public static int getLanguageCode()
    {
        Locale local = Locale.getDefault();
        if(local.equals(Locale.JAPAN) || local.getLanguage().equals(Locale.JAPANESE)){
            return  2;
        }
        else {
            return 1;
        }
    }
}
