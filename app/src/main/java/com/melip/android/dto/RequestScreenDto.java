package com.melip.android.dto;

import com.melip.common.dto.common.AttrDto;
import com.melip.common.dto.screen.ScreenObjBunchDto;

import java.io.Serializable;

/**
 * Created by j-nakashima on 2015/01/28.
 */
public class RequestScreenDto implements Serializable{

    /** 言語区分のフィールド名 */
    public static final String FIELD_LANG_DIV = "langDiv";
    /** 遷移先スクリーンIDのフィールド名 */
    public static final String FIELD_TARGET_SCREEN_ID = "targetScreenId";
    /** 地域IDのフィールド名 */
    public static final String FIELD_REGION_ID = "regionId";
    /** 施設グループIDのフィールド名 */
    public static final String FIELD_FACILITY_GRP_ID = "facilityGrpId";
    /** 施設IDのフィールド名 */
    public static final String FIELD_FACILITY_ID = "facilityId";

    /** 言語区分 */
    private String langDiv;
    /** 遷移先スクリーンID */
    private Integer targetScreenId;
    /** 地域ID */
    private Integer regionId;
    /** 施設グループID */
    private Integer facilityGrpId;
    /** 施設ID */
    private Integer facilityId;


    public RequestScreenDto (Integer targetScreenId, ScreenObjBunchDto screenObjBunchDto){
        this.targetScreenId = targetScreenId;
        this.regionId = screenObjBunchDto.getRegionId();
        this.facilityGrpId = screenObjBunchDto.getFacilityGrpId();
        this.facilityId = screenObjBunchDto.getFacilityId();
    }

    public RequestScreenDto(AttrDto attrDto){
        this.targetScreenId = attrDto.getTargetScreenId();
        this.regionId = attrDto.getRegionId();
        this.facilityGrpId = attrDto.getFacilityGrpId();
        this.facilityId = attrDto.getFacilityId();
    }

    public RequestScreenDto(){

    }


    /**
     * 言語区分を取得します。
     *
     * @return 言語区分
     */
    public String getLangDiv(){
        return langDiv;
    }

    /**
     * 言語区分を設定します。
     *
     * @param langDiv 言語区分
     */
    public void setLangDiv(String langDiv){
        this.langDiv = langDiv;
    }

    /**
     * 遷移先スクリーンIDを取得します。
     *
     * @return 遷移先スクリーンID
     */
    public Integer getTargetScreenId(){
        return targetScreenId;
    }

    /**
     * 遷移先スクリーンIDを設定します。
     *
     * @param targetScreenId 遷移先スクリーンID
     */
    public void setTargetScreenId(Integer targetScreenId){
        this.targetScreenId = targetScreenId;
    }
    /**
     * 地域IDを取得します。
     *
     * @return 地域ID
     */
    public Integer getRegionId(){
        return regionId;
    }

    /**
     * 地域IDを設定します。
     *
     * @param regionId 地域ID
     */
    public void setRegionId(Integer regionId){
        this.regionId = regionId;
    }

    /**
     * 施設グループIDを取得します。
     *
     * @return 施設グループID
     */
    public Integer getFacilityGrpId(){
        return facilityGrpId;
    }

    /**
     * 施設グループIDを設定します。
     *
     * @param facilityGrpId 施設グループID
     */
    public void setFacilityGrpId(Integer facilityGrpId){
        this.facilityGrpId = facilityGrpId;
    }

    /**
     * 施設IDを取得します。
     *
     * @return 施設ID
     */
    public Integer getFacilityId(){
        return facilityId;
    }

    /**
     * 施設IDを設定します。
     *
     * @param facilityId 施設ID
     */
    public void setFacilityId(Integer facilityId){
        this.facilityId = facilityId;
    }
}
