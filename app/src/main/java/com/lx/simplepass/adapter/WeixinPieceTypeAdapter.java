package com.lx.simplepass.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.lx.simplepass.R;
import com.lx.simplepass.model.WeixinPieceType;

import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class WeixinPieceTypeAdapter extends CommonAdapter {

    public WeixinPieceTypeAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        WeixinPieceType type = (WeixinPieceType) o;
        TextView text = holder.getView(R.id.tv_title);
        text.setText(type.getName());
    }
}
