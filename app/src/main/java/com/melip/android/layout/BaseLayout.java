package com.melip.android.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.common.base.CaseFormat;
import com.melip.android.adapter.ScreenObjBunchDtoListAdapter;
import com.melip.android.listener.event.ChangeActivityEventListener;
import com.melip.android.utils.MaDtoUtils;
import com.melip.android.utils.MaStringUtils;
import com.melip.common.dto.screen.ScreenDto;
import com.melip.common.dto.screen.ScreenObjDto;
import com.melip.common.dto.screen.ScreenObjGrpDto;

public class BaseLayout extends AbstractLayout {

    @Override
    protected void onCreate(Bundle savedInstancesState) {

        // 親クラスのonCreate処理実行
        super.onCreate(savedInstancesState);

        // 遷移元画面からScreenDtoを取得
        ScreenDto screenDto = (ScreenDto) getIntent().getSerializableExtra("screenDto");
        setLayoutAlias(screenDto.getLayoutAlias());

        // LayoutAliasで指定されたレイアウトをContentViewに設定する
        String[] layoutNames = {"Activity", screenDto.getLayoutAlias()};
        String layoutName = MaStringUtils.getLowerSnakeFromUpperCamels(layoutNames);
        setContentView(getLayoutIdentifierFromName(layoutName));

        // 各ScreenObjGrpDtoをその多重度に応じた処理でViewに設定する
        for(ScreenObjGrpDto tmpScreenObjGrpDto : screenDto.getScreenObjGrpDtoList()) {
            String layoutObjGrpAlias = tmpScreenObjGrpDto.getLayoutObjGrpAlias();
            String multiplicity = tmpScreenObjGrpDto.getMultiplicity();

            // 多重度 == sin(シングル)の場合
            if (MaStringUtils.equals("sin", multiplicity)) {

                // 各ScreenObjDtoをViewに設定する
                for (ScreenObjDto tmpScreenObjDto : MaDtoUtils.getSingleScreenObjDtoList(tmpScreenObjGrpDto)){
                    String[] idNames = {layoutObjGrpAlias, tmpScreenObjDto.getLayoutObjAlias()};
                    String idName = MaStringUtils.getLowerSnakeFromUpperCamels(idNames);
                    View view = getViewFromName(idName);
                    mapScreenObjDtoToView(tmpScreenObjDto, view);
                }
            }

            // 多重度 == multi(マルチ)の場合
            else if (MaStringUtils.equals("multi", multiplicity)) {

                // ScreenBunchDtoList用のAdapterを生成する
                ScreenObjBunchDtoListAdapter adapter =
                        new ScreenObjBunchDtoListAdapter(this, tmpScreenObjGrpDto);

                // 生成したAdapterを、LayoutObjGrpAliasで指定されたListViewに設定する
                String idName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,layoutObjGrpAlias);
                ListView listView = (ListView) getViewFromName(idName);
                listView.setAdapter(adapter);

                // Clickableなオブジェクトの場合Click時のリスナを設定する
                if(MaDtoUtils.screenObjGrpDtoIsClickable(tmpScreenObjGrpDto)){
                    ChangeActivityEventListener onChangeActivityEventListener =
                            new ChangeActivityEventListener(BaseLayout.this);
                    listView.setOnItemClickListener(onChangeActivityEventListener);
                }
            }
        }
    }
}
