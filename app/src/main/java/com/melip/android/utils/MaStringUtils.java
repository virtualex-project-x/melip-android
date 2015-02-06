package com.melip.android.utils;

import com.google.common.base.CaseFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Created by j-nakashima on 2015/01/30.
 */
public class MaStringUtils extends StringUtils {

    /** インスタンス化せずに使用するためprivateとする。 */
    private MaStringUtils(){};

    /**
     * 複数のUpperCamelCaseの文字列をSnakeCaseに連結して返却する処理。
     * @param args UpperCamelCaseの文字列
     * @return String
     */
    public static String getLowerSnakeFromUpperCamels(String[] args){

        if( args == null || args.length == 0){
            return null;
        }

        StringBuilder sbr = new StringBuilder(
                CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, args[0]));

        for (int index = 1; index < args.length; index++) {
            sbr.append("_");
            sbr.append(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, args[index]));
        }

        return sbr.toString();
    };

}
