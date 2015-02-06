package com.melip.android.listener;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by j-nakashima on 2015/01/29.
 */
public class AbstractErrorListener implements Response.ErrorListener{

    @Override
    public void onErrorResponse(VolleyError error) {
        // エラー処理記載
    }
}
