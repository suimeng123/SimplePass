package com.lx.simplepass.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.lx.simplepass.R;
import com.lx.simplepass.model.FoodDetail;
import com.lx.simplepass.utils.ImageLoaderUtil;
import com.lx.simplepass.views.GlideRoundTransform;

import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class CookListAdapter extends CommonAdapter {

    public CookListAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        FoodDetail detail = (FoodDetail) o;
        TextView title = holder.getView(R.id.item_title);
        TextView type = holder.getView(R.id.item_type);
        ImageView icon = holder.getView(R.id.item_icon);

        title.setText(detail.getName());
        type.setText(detail.getCtgTitles());
        ImageLoaderUtil.loadTransformImgForUrl(mContext, detail.getThumbnail(), icon, new GlideRoundTransform(mContext, 4));
    }
}
