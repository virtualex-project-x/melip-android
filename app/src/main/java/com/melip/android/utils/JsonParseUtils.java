package com.melip.android.utils;

import com.melip.common.dto.common.AttrDto;
import com.melip.common.dto.screen.ScreenDto;
import com.melip.common.dto.screen.ScreenObjBunchDto;
import com.melip.common.dto.screen.ScreenObjDto;
import com.melip.common.dto.screen.ScreenObjGrpDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSONオブジェクトをDTOにパースするUtilクラスです
 */
public class JsonParseUtils {

    /** インスタンス化せずに使用するためprivateとする。 */
    private JsonParseUtils(){

    }

    /**
     * JSONオブジェクトをスクリーンDTOにパースする処理。
 * @param jsonObj
     * @return ScreenDto
     * @throws JSONException
     */
    public static ScreenDto toScreenDto (JSONObject jsonObj) throws JSONException{

        // スクリーンDTOを生成
        ScreenDto screenDto = new ScreenDto();

        // レイアウトIDを設定
        screenDto.setLayoutId(MaJsonUtils.getInteger(jsonObj, ScreenDto.FIELD_LAYOUT_ID));

        // レイアウトエイリアスを設定
        screenDto.setLayoutAlias(jsonObj.getString(ScreenDto.FIELD_LAYOUT_ALIAS));

        // レイアウトタイプコードエイリアスを設定
        screenDto.setLayoutTypeCdAlias(jsonObj.getString(ScreenDto.FIELD_LAYOUT_TYPE_CD_ALIAS));

        // スクリーンIDを設定
        screenDto.setScreenId(MaJsonUtils.getInteger(jsonObj, ScreenDto.FIELD_SCREEN_ID));

        // スクリーンオブジェクトグループDTOリストを設定
        JSONArray jsonScreenObjDtoList = jsonObj.getJSONArray(ScreenDto.FIELD_SCREEN_OBJ_GRP_DTO_LIST);
        List<ScreenObjGrpDto> screenObjGrpDtoList = new ArrayList<>();
        for (int counter = 0; counter < jsonScreenObjDtoList.length(); counter++){
            screenObjGrpDtoList.add(toScreenObjGrpDto(jsonScreenObjDtoList.getJSONObject(counter)));
        }
        screenDto.setScreenObjGrpDtoList(screenObjGrpDtoList);

        return screenDto;
    }

    /**
     * JSONオブジェクトをスクリーンオブジェクトグループDTOにパースする処理。
     * @param jsonObj
     * @return ScreenObjGrpDto
     * @throws JSONException
     */
    public static ScreenObjGrpDto toScreenObjGrpDto (JSONObject jsonObj) throws JSONException{

        // スクリーンオブジェクトグループDTOを生成
        ScreenObjGrpDto screenObjGrpDto = new ScreenObjGrpDto();

        // レイアウトオブジェクトグループIDを設定
        screenObjGrpDto.setLayoutObjGrpId(MaJsonUtils.getInteger(jsonObj, ScreenObjGrpDto.FIELD_LAYOUT_OBJ_GRP_ID));

        // レイアウトオブジェクトグループエイリアスを設定
        screenObjGrpDto.setLayoutObjGrpAlias(jsonObj.getString(ScreenObjGrpDto.FIELD_LAYOUT_OBJ_GRP_ALIAS));

        // 多重度を設定
        screenObjGrpDto.setMultiplicity(jsonObj.getString(ScreenObjGrpDto.FIELD_MULTIPLICITY));

        // スクリーンオブジェクトグループIDを設定
        screenObjGrpDto.setScreenObjGrpId(MaJsonUtils.getInteger(jsonObj, ScreenObjGrpDto.FIELD_SCREEN_OBJ_GRP_ID));

        // スクリーンオブジェクトDTOリストリストを設定
        JSONArray jsonScreenObjBunchDtoList = jsonObj.getJSONArray(ScreenObjGrpDto.FIELD_SCREEN_OBJ_BUNCH_DTO_LIST);
        List<ScreenObjBunchDto> screenObjBunchDtoList = new ArrayList<>();
        for (int counter = 0; counter < jsonScreenObjBunchDtoList.length(); counter++){
            screenObjBunchDtoList.add(toScreenObjBunchDto(jsonScreenObjBunchDtoList.getJSONObject(counter)));
        }
        screenObjGrpDto.setScreenObjBunchDtoList(screenObjBunchDtoList);

        // 遷移先スクリーンIDを設定
        screenObjGrpDto.setTargetScreenId(MaJsonUtils.getInteger(jsonObj, ScreenObjGrpDto.FIELD_TARGET_SCREEN_ID));

        return screenObjGrpDto;
    }

    /**
     * JSONオブジェクトをスクリーンオブジェクトバンチDTOにパースする処理。
     * @param jsonObj
     * @return ScreenObjDto
     * @throws JSONException
     */
    public static ScreenObjBunchDto toScreenObjBunchDto(JSONObject jsonObj) throws JSONException{
        // スクリーンオブジェクトDTOリストを生成
        ScreenObjBunchDto screenObjBunchDto = new ScreenObjBunchDto();

        // 地域IDを設定
        screenObjBunchDto.setRegionId(MaJsonUtils.getInteger(jsonObj, ScreenObjBunchDto.FIELD_REGION_ID));

        // 施設グループIDを設定
        screenObjBunchDto.setFacilityGrpId(MaJsonUtils.getInteger(jsonObj, ScreenObjBunchDto.FIELD_FACILITY_GRP_ID));

        // 施設IDを設定
        screenObjBunchDto.setFacilityId(MaJsonUtils.getInteger(jsonObj, ScreenObjBunchDto.FIELD_FACILITY_ID));

        // スクリーンオブジェクトDTOリストを設定
        JSONArray jsonScreenObjDtoList = jsonObj.getJSONArray(ScreenObjBunchDto.FIELD_SCREEN_OBJ_DTO_LIST);
        List<ScreenObjDto> screenObjDtoList = new ArrayList<>();
        for (int counter = 0; counter < jsonScreenObjDtoList.length(); counter++){
            screenObjDtoList.add(toScreenObjDto(jsonScreenObjDtoList.getJSONObject(counter)));
        }
        screenObjBunchDto.setScreenObjDtoList(screenObjDtoList);

        return screenObjBunchDto;
    }

    /**
     * JSONオブジェクトをスクリーンオブジェクトDTOにパースする処理。
     * @param jsonObj
     * @return ScreenObjDto
     * @throws JSONException
     */
    public static ScreenObjDto toScreenObjDto (JSONObject jsonObj) throws JSONException{
        // スクリーンオブジェクトDTOを生成
        ScreenObjDto screenObjDto = new ScreenObjDto();

        // レイアウトオブジェクトIDを設定
        screenObjDto.setLayoutObjId(MaJsonUtils.getInteger(jsonObj, ScreenObjDto.FIELD_LAYOUT_OBJ_ID));

        // レイアウトオブジェクトエイリアスを設定
        screenObjDto.setLayoutObjAlias(jsonObj.getString(ScreenObjDto.FIELD_LAYOUT_OBJ_ALIAS));

        // レイアウトオブジェクトタイプを設定
        screenObjDto.setLayoutObjType(jsonObj.getString(ScreenObjDto.FIELD_LAYOUT_OBJ_TYPE));

        // スクリーンオブジェクトIDを設定
        screenObjDto.setScreenObjId(MaJsonUtils.getInteger(jsonObj, ScreenObjDto.FIELD_SCREEN_OBJ_ID));

        // 属性DTOリストを設定
        JSONArray jsonAttrDtoList = jsonObj.getJSONArray(ScreenObjDto.FIELD_ATTR_DTO_LIST);
        List<AttrDto> attrDtoList = new ArrayList<>();
        for (int counter = 0; counter < jsonAttrDtoList.length(); counter++){
            attrDtoList.add(toAttrDto( jsonAttrDtoList.getJSONObject(counter)));
        }
        screenObjDto.setAttrDtoList(attrDtoList);

        return screenObjDto;
    }

    /**
     * JSONオブジェクトを属性DTOにパースする処理。
     * @param jsonObj
     * @return AttDto
     * @throws JSONException
     */
    public static AttrDto toAttrDto (JSONObject jsonObj) throws JSONException{
        // 属性DTOを生成
        AttrDto attrDto = new AttrDto();

        // 地域IDを設定
        attrDto.setRegionId(MaJsonUtils.getInteger(jsonObj, AttrDto.FIELD_REGION_ID));

        // 施設グループIDを設定
        attrDto.setFacilityGrpId(MaJsonUtils.getInteger(jsonObj, AttrDto.FIELD_FACILITY_GRP_ID));

        // 施設IDを設定
        attrDto.setFacilityId(MaJsonUtils.getInteger(jsonObj, AttrDto.FIELD_FACILITY_ID));

        // 属性グループIDを設定
        attrDto.setAttrGrpId(MaJsonUtils.getInteger(jsonObj, AttrDto.FIELD_ATTR_GRP_ID));

        // 属性グループ種別を設定
        attrDto.setAttrGrpType(jsonObj.getString(AttrDto.FIELD_ATTR_GRP_TYPE));

        // 属性グループ名称を設定
        attrDto.setAttrGrpNm(jsonObj.getString(AttrDto.FIELD_ATTR_GRP_NM));

        // 属性グループ値を設定
        attrDto.setAttrVal(jsonObj.getString(AttrDto.FIELD_ATTR_VAL));

        // 属性グループコード値を設定
        attrDto.setAttrCdVal(jsonObj.getString(AttrDto.FIELD_ATTR_CD_VAL));

        // 遷移先スクリーンIDを設定
        attrDto.setTargetScreenId(MaJsonUtils.getInteger(jsonObj, AttrDto.FIELD_TARGET_SCREEN_ID));

        return attrDto;
    }

    public static <T> List<T> convertJsonArrayToArrayList (JSONArray jsonArray, List<T> abstractList) throws JSONException{
        List<T> returnList = new ArrayList<T>();

        for (int counter = 0; counter < jsonArray.length(); counter++){
            returnList.add((T)toScreenObjGrpDto(jsonArray.getJSONObject(counter)));
        }

        return returnList;
    }


}
