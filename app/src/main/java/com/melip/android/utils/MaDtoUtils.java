package com.melip.android.utils;

import com.melip.common.dto.common.AttrDto;
import com.melip.common.dto.screen.ScreenObjBunchDto;
import com.melip.common.dto.screen.ScreenObjDto;
import com.melip.common.dto.screen.ScreenObjGrpDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j-nakashima on 2015/02/03.
 */
public class MaDtoUtils {

    /**
     * ScreenObjGrpDtoの1件目のScreenObjBunchDtoのScreenObjDtoListを返却する処理。<br>
     * ScreenObjBunchDtoが2件以上存在した場合はExceptionを投げる。
     * @param screenObjGrpDto
     * @return
     */
    public static List<ScreenObjDto> getSingleScreenObjDtoList(ScreenObjGrpDto screenObjGrpDto){

        // ScreenObjBunchDtoListを取得
        ArrayList<ScreenObjBunchDto> screenObjBunchDtoList=
                (ArrayList) screenObjGrpDto.getScreenObjBunchDtoList();

        // TODO Error Handling
        if (screenObjBunchDtoList == null || screenObjBunchDtoList.size() == 0){
            // TODO screenObjBunchDto が存在しない場合のError
        } else if (screenObjBunchDtoList.size() > 1){
            // TODO screenObjBunchDto が複数件存在する場合のError
        }

        return screenObjBunchDtoList.get(0).getScreenObjDtoList();
    }

    public static AttrDto getSingleAttrDto(ScreenObjDto screenObjDto){

        // AttrDtoListを取得
        ArrayList<AttrDto> attrDtoList = (ArrayList) screenObjDto.getAttrDtoList();

        // TODO Error Handling
        if (attrDtoList == null || attrDtoList.size() == 0){
            // TODO screenObjBunchDto が存在しない場合のError
        } else if (attrDtoList.size() > 1){
            // TODO screenObjBunchDto が複数件存在する場合のError
        }

        return attrDtoList.get(0);



    }


    /**
     * ScreenObjGrpDtoがClickableか否か判定する処理。
     * @param screenObjGrpDto 対象のScreenObjGrpDto
     * @return
     */
    public static boolean screenObjGrpDtoIsClickable(ScreenObjGrpDto screenObjGrpDto){
        if (screenObjGrpDto.getTargetScreenId() != null){
            return true;
        } else {
            return false;
        }
    }

}
