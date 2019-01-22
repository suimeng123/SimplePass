package com.lx.simplepass.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.lx.simplepass.R;
import com.lx.simplepass.activity.LookBigImgActivity;
import com.lx.simplepass.model.FoodDetail;
import com.lx.simplepass.model.FoodDetailItem;
import com.lx.simplepass.utils.ImageLoaderUtil;
import com.lx.simplepass.views.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/18.
 */

public class FoodDetailListAdapter extends CommonAdapter {

    private ArrayList mDatas;
    public FoodDetailListAdapter(Context context, ArrayList datas, int layoutId) {
        super(context, datas, layoutId);
        mDatas = datas;
    }

    @Override
    public void convert(final ViewHolder holder, Object o) {
        FoodDetailItem item = (FoodDetailItem) o;

        TextView title = holder.getView(R.id.txt_title);
        ImageView icon = holder.getView(R.id.iv_icon);

        title.setText(item.getStep());
        ImageLoaderUtil.loadTransformImgForUrl(mContext, item.getImg(), icon, new GlideRoundTransform(mContext, 4));

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, LookBigImgActivity.class).putExtra("position", holder.getPosition()).putParcelableArrayListExtra("data", mDatas));
            }
        });
    }
}
