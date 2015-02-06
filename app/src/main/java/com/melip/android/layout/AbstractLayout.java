package com.melip.android.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.common.base.CaseFormat;
import com.melip.android.AppController;
import com.melip.android.R;
import com.melip.android.utils.JsonParseUtils;
import com.melip.android.utils.MaDtoUtils;
import com.melip.android.utils.MaStringUtils;
import com.melip.common.dto.common.AttrDto;
import com.melip.common.dto.screen.ScreenDto;
import com.melip.common.dto.screen.ScreenObjBunchDto;
import com.melip.common.dto.screen.ScreenObjDto;
import com.melip.common.dto.screen.ScreenObjGrpDto;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j-nakashima on 2015/01/16.
 */
public class AbstractLayout extends Activity {

    /* DEF_TYPE */
    private static final String DEF_TYPE_ID = "id";
    private static final String DEF_TYPE_LAYOUT = "layout";

    /* LayoutAlias */
    private String mLayoutAlias;

    /* NetworkImage用のImageLoader */
    private ImageLoader mImageLoader = AppController.getInstance().getImageLoader();

    // 画面遷移時のパラメータ定義
    public static final String TRANSFER_SCREEN_OBJ = "transferScreenObj";

    // RequestQue;
    protected RequestQueue mQueue;

    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }

    public void setLayoutAlias(String layoutAlias){
        this.mLayoutAlias = layoutAlias;
    }

    public String getLayoutAlias(){
        return this.mLayoutAlias;
    }


    public String getListLayoutName(ScreenObjGrpDto screenObjGrpDto){
        String[] names = {"Activity", this.mLayoutAlias, screenObjGrpDto.getLayoutObjGrpAlias()};
        String name = MaStringUtils.getLowerSnakeFromUpperCamels(names);
        return name;
    }







    /**
     * Id名からIdを取得する処理。
     * @param name
     * @return
     */
    public int getIdentifierFromName (String name){
        return getResources().getIdentifier(name, DEF_TYPE_ID, getPackageName());
    }

    /**
     * Id名からLayoutResIdを取得する処理。
     * @param name
     * @return int
     */
    public int getLayoutIdentifierFromName (String name){
        return getResources().getIdentifier(name, DEF_TYPE_LAYOUT, getPackageName());
    }

    /**
     * Id名からViewを取得する処理。
     * @param name
     * @return View
     */
    public View getViewFromName (String name){
        return findViewById(getIdentifierFromName(name));
    }

    /**
     * ScreenObjDtoをViewに設定する処理。
     * @param screenObjDto
     * @param view
     */
    public void mapScreenObjDtoToView(ScreenObjDto screenObjDto, View view){

        String layoutObjType = screenObjDto.getLayoutObjType();

        if (StringUtils.equals("txt", layoutObjType)){
            AttrDto attrDto = MaDtoUtils.getSingleAttrDto(screenObjDto);
            TextView textView = (TextView) view;
            textView.setText(attrDto.getAttrVal());
        }

        if (StringUtils.equals("img", layoutObjType)){
            AttrDto attrDto = MaDtoUtils.getSingleAttrDto(screenObjDto);
            NetworkImageView networkImageView = (NetworkImageView) view;
            networkImageView.setImageUrl(attrDto.getAttrVal(), mImageLoader);
        }

        if (StringUtils.equals("list", layoutObjType)){
            LinearLayout linearLayout = (LinearLayout) view;
            for (AttrDto tmpAttrDto : screenObjDto.getAttrDtoList()){
                mapAttrDtoToView(tmpAttrDto, linearLayout);
            }
        }
    }

    /**
     * AttrDtoをViewに設定する処理。
     * @param attrDto AttrDto
     * @param view View
     */
    public void mapAttrDtoToView(AttrDto attrDto, View view) {

        LinearLayout linearLayout = (LinearLayout) view;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.list_item, null);

        // ヘッダを設定する
        TextView header = (TextView) contentView.findViewById(R.id.headerCell);
        header.setText(attrDto.getAttrGrpNm());

        // コンテンツを設定する
        LinearLayout contentCell = (LinearLayout) contentView.findViewById(R.id.contentCell);
        String attrGrpType = attrDto.getAttrGrpType();
        if ("txt".equals(attrGrpType)) {
            TextView textView = new TextView(this);
            textView.setText(attrDto.getAttrVal());
            contentCell.addView(textView);
        } else if ("imgp".equals(attrGrpType)) {
            NetworkImageView networkImageView = new NetworkImageView(this);
            networkImageView.setImageUrl(attrDto.getAttrVal(), mImageLoader);
            contentCell.addView(networkImageView);
        }

        linearLayout.addView(contentView);
    }







}
