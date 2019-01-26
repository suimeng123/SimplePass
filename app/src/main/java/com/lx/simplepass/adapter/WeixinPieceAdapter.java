package com.lx.simplepass.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.lx.simplepass.R;
import com.lx.simplepass.model.WeixinPieceItem;
import com.lx.simplepass.utils.ImageLoaderUtil;
import com.lx.simplepass.views.GlideRoundTransform;
import com.lx.simplepass.views.GlideRoundTransformRadius;

import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/25.
 */

public class WeixinPieceAdapter extends CommonAdapter {

    public WeixinPieceAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        WeixinPieceItem item = (WeixinPieceItem) o;

        TextView title = holder.getView(R.id.tv_title);
        TextView content = holder.getView(R.id.tv_content);
        ImageView icon = holder.getView(R.id.iv_icon);

        title.setText(item.getTitle());
        content.setText(item.getPubTime());
        ImageLoaderUtil.loadTransformImgForUrl(mContext, item.getThumbnails(), icon, new GlideRoundTransformRadius(mContext, new int[]{4, 4, 0, 0}));
    }
}
