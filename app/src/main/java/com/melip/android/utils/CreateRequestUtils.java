package com.melip.android.utils;

import com.melip.android.dto.RequestScreenDto;

/**
 * Created by j-nakashima on 2015/01/29.
 */
public class CreateRequestUtils {

    private final String URL = "http://10.0.2.2:8080/melip-webservices/resource/";


    /** インスタンス化せずに使用するためprivateとする。 */
    private CreateRequestUtils(){

    }

    public static String createScreenObjRequest(RequestScreenDto requestScreenDto){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://10.0.2.2:8080/melip-webservices/resource/screenDto?");
        // TODO 値を設定から取得するように修正する
        requestScreenDto.setLangDiv("ja");
        if(requestScreenDto.getLangDiv() != null) {
            urlBuilder.append(RequestScreenDto.FIELD_LANG_DIV);
            urlBuilder.append("=");
            urlBuilder.append(requestScreenDto.getLangDiv());
            urlBuilder.append("&");
        }
        if(requestScreenDto.getTargetScreenId() != null) {
            urlBuilder.append(RequestScreenDto.FIELD_TARGET_SCREEN_ID);
            urlBuilder.append("=");
            urlBuilder.append(requestScreenDto.getTargetScreenId());
            urlBuilder.append("&");
        }
        if(requestScreenDto.getRegionId() != null) {
            urlBuilder.append(RequestScreenDto.FIELD_REGION_ID);
            urlBuilder.append("=");
            urlBuilder.append(requestScreenDto.getRegionId());
            urlBuilder.append("&");
        }
        if(requestScreenDto.getFacilityGrpId() != null) {
            urlBuilder.append(RequestScreenDto.FIELD_FACILITY_GRP_ID);
            urlBuilder.append("=");
            urlBuilder.append(requestScreenDto.getFacilityGrpId());
            urlBuilder.append("&");
        }
        if(requestScreenDto.getFacilityId() != null) {
            urlBuilder.append(RequestScreenDto.FIELD_FACILITY_ID);
            urlBuilder.append("=");
            urlBuilder.append(requestScreenDto.getFacilityId());
        }
        return urlBuilder.toString();
    }

}
