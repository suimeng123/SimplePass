package com.lx.simplepass.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.FoodDetailListAdapter;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.FoodDetail;
import com.lx.simplepass.model.FoodDetailItem;
import com.lx.simplepass.model.FoodRecipe;
import com.lx.simplepass.utils.ImageLoaderUtil;
import com.lx.simplepass.views.GlideRoundTransform;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜谱详情
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class CookDetailActivity extends BaseActivity {

    private FoodDetail foodDetail;

    private TextView mTvTitle;
    private TextView mTvTip;
    private View header;
    private ListView mLvList;

    private FoodDetailListAdapter adapter;
    private ArrayList<FoodDetailItem> lists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodDetail = getIntent().getParcelableExtra("foodDetail");
        setContentView(R.layout.activity_cook_detail);
        setHeaderVisiable(true);
    }

    @Override
    public void initLayout() {
        mLvList = findViewById(R.id.lv_list);
        header = LayoutInflater.from(mContext).inflate(R.layout.header_food_detail, null);
        mLvList.addHeaderView(header);
        mTvTitle = header.findViewById(R.id.txt_title);
        mTvTip = header.findViewById(R.id.txt_tip);
    }

    @Override
    public void initData() {
        if (foodDetail != null) {
            setTitleText(foodDetail.getName());
            FoodRecipe recipe = foodDetail.getRecipe();
            if (recipe != null) {
                mTvTip.setText(recipe.getSumary());
                mTvTitle.setText(Html.fromHtml(getIngredientsString(recipe.getIngredients())));
            }
            if (!TextUtils.isEmpty(recipe.getMethod())) {
                List list = new ArrayList(JSON.parseArray(recipe.getMethod(), FoodDetailItem.class));
                if (!list.isEmpty()) {
                    lists.addAll(list);
                }
            }
        }

        adapter = new FoodDetailListAdapter(mContext, lists, R.layout.listitem_food_detail);
        mLvList.setAdapter(adapter);
    }

    private String getIngredientsString(String ingredients) {
        String tip = "暂无配料信息";
        if (TextUtils.isEmpty(ingredients) || ingredients.length() < 4) {
            return tip;
        }
        String strs = ingredients.substring(2, ingredients.length() - 2).replace("\"", "");
        String aar[] = strs.split(",");
        if (aar == null || aar.length == 0) {
            return tip;
        }
        StringBuffer sb = new StringBuffer();
        int i = 1;
        for(String str : aar) {
            String[] a = str.split("：");
            if (a == null || a.length < 2) {
                continue;
            }
            sb.append("<font color='#000'>" + a[0] + "：</font>");
            sb.append(a[1]);
            if (i < aar.length){
                sb.append("<br/><br/>");
            }
            i++;
        }
        return sb.toString();
    }
}
