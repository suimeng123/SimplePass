package com.lx.simplepass.adapter;

import android.content.Context;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.base.library.utils.ViewUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.model.CategoryInfo;
import com.lx.simplepass.model.FoodSecondType;
import com.lx.simplepass.model.FoodThreeType;

import org.w3c.dom.Text;

import java.util.List;

/**
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/21.
 */

public class FoodTypeAdapter extends CommonAdapter {

    public List mDatas;

    public FoodTypeAdapter(Context context, List datas, int layoutId) {
        super(context, datas, layoutId);
        mDatas = datas;
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        TextView tv = holder.getView(R.id.txt_title);
        if (o instanceof FoodSecondType) {
            FoodSecondType type = (FoodSecondType) o;
            tv.setText(type.getCategoryInfo().getName());
            setSelectState(type.getCategoryInfo(), tv);
        } else if (o instanceof FoodThreeType) {
            FoodThreeType type = (FoodThreeType) o;
            tv.setText(type.getCategoryInfo().getName());
            setSelectState(type.getCategoryInfo(), tv);
        }
    }

    private void setSelectState(CategoryInfo info, TextView tv) {
        if (info.isiSelected()) {
            ViewUtil.setTextViewDrawable(mContext, tv, 4, R.mipmap.type_selected);
            tv.setBackgroundColor(mContext.getResources().getColor(R.color.color_eee));
        } else {
            ViewUtil.setTextViewDrawable(mContext, tv, 4, 0);
            tv.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    public List getDatas() {
        return mDatas;
    }
}
