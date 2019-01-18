package com.lx.simplepass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.base.library.lib.pullrefresh.PullToRefreshBase;
import com.base.library.lib.pullrefresh.PullToRefreshListView;
import com.base.library.utils.MMKVUtil;
import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.CookListAdapter;
import com.lx.simplepass.app.Constants;
import com.lx.simplepass.app.HttpUrl;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.FoodDetail;
import com.lx.simplepass.model.FoodTotal;
import com.lx.simplepass.model.FoodType;
import com.lx.simplepass.utils.PullRefreshUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/17.
 */

public class CookActivity extends BaseActivity {
    private PullToRefreshListView mLvList;
    private CookListAdapter adapter;
    private List<FoodDetail> mDatas = new ArrayList<>();
    private PullRefreshUtils pu;
    private int pageNo = 1;

    private HashMap<String, Object> cookParams = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        setHeaderVisiable(true);
        setTitleText("菜谱列表");
    }

    @Override
    public void initLayout() {
        mLvList = findViewById(R.id.lv_list);
    }

    @Override
    public void initData() {
        Object value = MMKVUtil.getValue(Constants.KEY_COOK_CATEGORY, MMKVUtil.TYPE_STRING);
        if (value == null) {
            getCookCategory();
        }
        adapter = new CookListAdapter(mContext, mDatas, R.layout.listitem_food);
        mLvList.setAdapter(adapter);

        (pu = new PullRefreshUtils()).initListViewHead(mLvList,true);
        pu.setOnMoreLoadListener(new PullRefreshUtils.OnMoreLoadListener() {
            @Override
            public void onMoreLoad() {
                pageNo ++;
                cookParams.put("page", pageNo);
                getCookList(1);
            }
        });
        mLvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                cookParams.put("page", pageNo);
                getCookList(0);
            }
        });
        initCookParams();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLvList.setRefreshing();
            }
        }, 500);

        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= 0) {
                    return;
                }
                FoodDetail foodDetail = mDatas.get(position - 1);
                startActivity(new Intent(mContext, CookDetailActivity.class).putExtra("foodDetail", foodDetail));
            }
        });
    }

    /** 初始化食谱参数 **/
    private void initCookParams() {
        cookParams.put("key", Constants.SHAREDSDK_KEY);
        cookParams.put("cid", "");
        cookParams.put("name", "");
        cookParams.put("page", pageNo);
        cookParams.put("size", Constants.PAGE_SIZE);
    }

    /** 1是加载更多 0是下拉刷新 **/
    private void getCookList(int type) {
        http3Util.doGet(HttpUrl.GET_COOK_FOOD_LIST_URL, cookParams, FoodTotal.class, type + 2);
    }

    /**
     * 获取菜谱分类
     **/
    private void getCookCategory() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.SHAREDSDK_KEY);
        http3Util.doGet(HttpUrl.GET_COOK_CATEGORY_URL, params, FoodType.class, 1);
    }

    @Override
    public void resultData(int tag, Bundle bundle) {
        switch (tag) {
            case 1:
                FoodType foodType = (FoodType) bundle.get(parser.getDataName());
                MMKVUtil.setValue(Constants.KEY_COOK_CATEGORY, foodType);
                break;
            case 2:
                // 下拉
                mLvList.onRefreshComplete();
                mDatas.clear();
                setDatas(bundle);
                break;
            case 3:
                // 加载更多
                setDatas(bundle);
                break;
            default:
                break;
        }
    }

    private void setDatas(Bundle bundle) {
        FoodTotal foodTotal = (FoodTotal) bundle.get(parser.getDataName());
        if (foodTotal != null && !foodTotal.getList().isEmpty()) {
            mDatas.addAll(foodTotal.getList());
            if (foodTotal.getList().size() < Constants.PAGE_SIZE) {
                pu.OnHasNotMoreData();
            } else {
                pu.onMoreLoadComplete();
            }
        } else {
            pu.onMoreLoadComplete();
        }
        adapter.notifyDataSetChanged();

        if (!bundle.getBoolean(parser.getStateName())) {
            ToastUtil.showToast(mContext, (String) bundle.get(parser.getMessageName()));
        }
    }
}
