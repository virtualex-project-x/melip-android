package com.melip.android.listener.json;


import android.content.Context;
import android.content.Intent;

import com.android.volley.Response;
import com.melip.android.utils.JsonParseUtils;
import com.melip.common.dto.screen.ScreenDto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by j-nakashima on 2015/01/29.
 */
public class ScreenDtoJsonListener implements Response.Listener<JSONObject>{

    private Context mContext;

    public ScreenDtoJsonListener(Context context){
        this.mContext = context;
    }

    @Override
    public void onResponse(JSONObject jsonObj){
        try {
            // JSONObjectをスクリーンに変換する
            ScreenDto screenDto = JsonParseUtils.toScreenDto(jsonObj);

            // 遷移先パッケージ名・クラス名を取取得する
            String className = "com.melip.android.layout.BaseLayout";
            String packageName = "com.melip.android";

            // 次画面のアクティビティ起動
            Intent intent = new Intent();
            intent.setClassName(packageName, className);
            intent.putExtra("screenDto", screenDto);
            mContext.startActivity(intent);

        } catch (JSONException e) {
            // TODO 未実装
            e.printStackTrace();
        }
    }
}
