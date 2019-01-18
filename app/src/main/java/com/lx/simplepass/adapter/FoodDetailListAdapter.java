package com.lx.simplepass.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.lx.simplepass.R;
import com.lx.simplepass.model.FoodDetailItem;
import com.lx.simplepass.utils.ImageLoaderUtil;
import com.lx.simplepass.views.GlideRoundTransform;

import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/18.
 */

public class FoodDetailListAdapter extends CommonAdapter {

    public FoodDetailListAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        FoodDetailItem item = (FoodDetailItem) o;

        TextView title = holder.getView(R.id.txt_title);
        ImageView icon = holder.getView(R.id.iv_icon);

        title.setText(item.getStep());
        ImageLoaderUtil.loadTransformImgForUrl(mContext, item.getImg(), icon, new GlideRoundTransform(mContext, 4));
    }
}
