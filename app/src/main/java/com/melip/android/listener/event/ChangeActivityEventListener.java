package com.melip.android.listener.event;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.melip.android.R;
import com.melip.android.dto.RequestScreenDto;
import com.melip.android.listener.AbstractErrorListener;
import com.melip.android.listener.json.ScreenDtoJsonListener;
import com.melip.android.utils.CreateRequestUtils;

/**
 * Created by j-nakashima on 2015/01/29.
 */
public class ChangeActivityEventListener implements AdapterView.OnItemClickListener {

    private Context mContext;
    private RequestQueue mRequestQueue;
    private String url;

    public ChangeActivityEventListener(Context context){
        this.mContext = context;
        this.mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

        // 選択されたViewに付与されたRequestScreenDtoからURLを生成する
        RequestScreenDto requestScreenDto = (RequestScreenDto) view.getTag(R.string.requestScreenDtoTag);
        this.url = CreateRequestUtils.createScreenObjRequest(requestScreenDto);

        // 生成されたURLを確認し、問題がなければテストJSONデータに基づく画面遷移を実施する
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle("URL");
        alertDialogBuilder.setMessage(this.url);
        alertDialogBuilder.setNegativeButton("NG",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // RequestQueueにScreenObj取得用のリクエストを設定する
                        ScreenDtoJsonListener jsonObjectListener = new ScreenDtoJsonListener(mContext);
                        AbstractErrorListener abstractErrorListener = new AbstractErrorListener();
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, jsonObjectListener, abstractErrorListener);
                        mRequestQueue.add(jsonObjectRequest);
                    }
                });
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
