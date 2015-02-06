package com.melip.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.melip.android.dto.RequestScreenDto;
import com.melip.android.layout.AbstractLayout;
import com.melip.android.listener.AbstractErrorListener;
import com.melip.android.listener.json.ScreenDtoJsonListener;
import com.melip.android.utils.CreateRequestUtils;

public class MainActivity extends AbstractLayout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /* ScreenDtoJSON 呼び出し処理 */

        // URLを作成する
        RequestScreenDto requestScreenDto = new RequestScreenDto();
        requestScreenDto.setLangDiv("ja");
        requestScreenDto.setTargetScreenId(5);
        requestScreenDto.setRegionId(2);
        requestScreenDto.setFacilityGrpId(null);
        requestScreenDto.setFacilityId(null);
        String url = CreateRequestUtils.createScreenObjRequest(requestScreenDto);

        // リクエストを生成する
        ScreenDtoJsonListener jsonObjectListener = new ScreenDtoJsonListener(this);
        AbstractErrorListener abstractErrorListener = new AbstractErrorListener();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, jsonObjectListener, abstractErrorListener);

        // リクエストをQUEUEに設定する
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(jsonObjectRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
