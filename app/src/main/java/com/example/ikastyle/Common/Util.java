package com.example.ikastyle.Common;

import java.util.Locale;

public class Util {
    /*
     * 端末の設定が日本語なら2、それ以外なら1を返す
     */
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
