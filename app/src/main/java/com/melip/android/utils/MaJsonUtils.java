package com.melip.android.utils;

import org.json.JSONObject;

/**
 * Created by j-nakashima on 2015/02/02.
 */
public class MaJsonUtils {

    /** インスタンス化せずに使用するためprivateとする。 */
    private MaJsonUtils(){}

    /**
     * JSONObjectの要素をIntegerとして取得するメソッドです
     * @param jsonObj JSONObject
     * @param name 要素名
     * @return
     */
    public static Integer getInteger(JSONObject jsonObj, String name){
        if (jsonObj.isNull(name)){
            return null;
        } else {
            return jsonObj.optInt(name);
        }
    }

}
