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

    public int selectedPosition = -1;

    public WeixinPieceTypeAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        WeixinPieceType type = (WeixinPieceType) o;
        TextView text = holder.getView(R.id.tv_title);
        text.setText(type.getName());
        if (selectedPosition == holder.getPosition()) {
            text.setBackgroundResource(R.drawable.bg_weixin_piece_type_item_selected);
            text.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            text.setBackgroundResource(R.drawable.bg_weixin_piece_type_item);
            text.setTextColor(mContext.getResources().getColor(R.color.c_33));
        }
    }
}
