package com.melip.android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.common.base.CaseFormat;
import com.melip.android.AppController;
import com.melip.android.R;
import com.melip.android.dto.RequestScreenDto;
import com.melip.android.layout.AbstractLayout;
import com.melip.android.utils.MaDtoUtils;
import com.melip.common.dto.common.AttrDto;
import com.melip.common.dto.screen.ScreenObjBunchDto;
import com.melip.common.dto.screen.ScreenObjDto;
import com.melip.common.dto.screen.ScreenObjGrpDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by j-nakashima on 2015/01/23.
 */
public class ScreenObjBunchDtoListAdapter extends BaseAdapter {

    private AbstractLayout mActivity;
    private ImageLoader mImageLoader;
    private ScreenObjGrpDto mScreenObGrpDto;
    private LayoutInflater inflater;

    public ScreenObjBunchDtoListAdapter(AbstractLayout activity, ScreenObjGrpDto screenObjGrpDto){
        this.mActivity = activity;
        this.mImageLoader = activity.getImageLoader();
        this.mScreenObGrpDto = screenObjGrpDto;
    }

    /**
     * ViewHolder クラス
     */
    private static class ViewHolder{

        // エイリアスとViewのマップオブジェクト
        private Map<String, View> viewHolderMap = new HashMap<>();

        // コンストラクタ
        ViewHolder (View view, ScreenObjBunchDto screenBunchDto, String packageName){
            for (ScreenObjDto tmpScreenObjDto : screenBunchDto.getScreenObjDtoList()){
                String name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tmpScreenObjDto.getLayoutObjAlias());
                int tmpResourceId = view.getResources().getIdentifier(name, "id", packageName);
                viewHolderMap.put(tmpScreenObjDto.getLayoutObjAlias(), view.findViewById(tmpResourceId));
            }
        }

        // エイリアスからViewを取得する
        View getView(String viewObjAlias){
            return viewHolderMap.get(viewObjAlias);
        }

        // ViewHolderを初期化する
        void initialize(){
            for(String key : viewHolderMap.keySet()){
                if (viewHolderMap.get(key) instanceof LinearLayout){
                    ((LinearLayout)viewHolderMap.get(key)).removeAllViews();
                }
            }
        }


    }


    @Override
    public int getCount() {
        return mScreenObGrpDto.getScreenObjBunchDtoList().size();
    }

    @Override
    public Object getItem(int location) {
        return mScreenObGrpDto.getScreenObjBunchDtoList().get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView (int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        ScreenObjBunchDto targetScreenObjBunchDto = mScreenObGrpDto.getScreenObjBunchDtoList().get(position);

        // inflaterオブジェクトの再利用判定
        if(inflater == null) {
            inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // convertViewオブジェクトの再利用判定
        if(convertView == null) {
            // ViewとViewHolderを生成し、ViewHolderをTagにセットする
            String listLayoutName = mActivity.getListLayoutName(mScreenObGrpDto);
            int listLayoutId =  mActivity.getLayoutIdentifierFromName(listLayoutName);
            convertView = inflater.inflate(listLayoutId, null);
            viewHolder = new ViewHolder(convertView, targetScreenObjBunchDto, mActivity.getPackageName());
            convertView.setTag(R.string.viewHolderTag, viewHolder);
        } else {
            // TagからViewHolderを取得し初期化する
            viewHolder = (ViewHolder) convertView.getTag(R.string.viewHolderTag);
            viewHolder.initialize();
        }

        // Clickableなオブジェクトにはタグを付ける
        if (MaDtoUtils.screenObjGrpDtoIsClickable(this.mScreenObGrpDto)) {
            RequestScreenDto requestScreenDto = new RequestScreenDto(this.mScreenObGrpDto.getTargetScreenId(), targetScreenObjBunchDto);
            convertView.setTag(R.string.requestScreenDtoTag, requestScreenDto);
        }

        for(ScreenObjDto tmpDto : targetScreenObjBunchDto.getScreenObjDtoList()){

            // Viewオブジェクトの取得
            View targetView = viewHolder.getView(tmpDto.getLayoutObjAlias());

            // LayoutObjTypeの取得
            String layoutObjType = tmpDto.getLayoutObjType();

            // ViewオブジェクトとLayoutObjTypeの相関チェック
            // TODO チェック処理実装


            // オブジェクトが文字列の場合
            // TODO 定数クラス使用
            if ("txt".equals(layoutObjType)) {
                AttrDto attrDto = MaDtoUtils.getSingleAttrDto(tmpDto);
                TextView textView = (TextView) targetView;
                textView.setText(attrDto.getAttrVal());
            }

            // オブジェクトが画像の場合
            // TODO 定数クラス使用
            else if ("img".equals(layoutObjType)) {
                AttrDto attrDto = MaDtoUtils.getSingleAttrDto(tmpDto);
                NetworkImageView networkImageView = (NetworkImageView) targetView;
                networkImageView.setImageUrl(attrDto.getAttrVal(), mImageLoader);
            }

            // オブジェクトがリストの場合
            else if ("list".equals(layoutObjType)) {
                LinearLayout linearLayout = (LinearLayout) targetView;
                for (AttrDto tmpAttrDto : tmpDto.getAttrDtoList()){
                    mActivity.mapAttrDtoToView(tmpAttrDto, linearLayout);
                }
            }
        }
        return convertView;
    }

}
