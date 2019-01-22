package com.lx.simplepass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.base.library.lib.pullrefresh.PullToRefreshBase;
import com.base.library.lib.pullrefresh.PullToRefreshListView;
import com.base.library.utils.MMKVUtil;
import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.CookListAdapter;
import com.lx.simplepass.adapter.FoodTypeAdapter;
import com.lx.simplepass.app.Constants;
import com.lx.simplepass.app.HttpUrl;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.FoodDetail;
import com.lx.simplepass.model.FoodSecondType;
import com.lx.simplepass.model.FoodThreeType;
import com.lx.simplepass.model.FoodTotal;
import com.lx.simplepass.model.FoodType;
import com.lx.simplepass.utils.CommUtils;
import com.lx.simplepass.utils.PullRefreshUtils;
import com.lx.simplepass.views.SearchInputLayout;
import com.lx.simplepass.views.SearchPopLayout;

import org.w3c.dom.Text;

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
    private String cId = "";
    private String keyWord = "";

    /** 自定义的搜索布局 **/
    private SearchInputLayout silSearch;

    /** 列表请求参数集 **/
    private HashMap<String, Object> cookParams = new HashMap<>();

    /** 自定义的pop搜索布局 选择的 **/
    private SearchPopLayout searchPop;
    /** pop中两个列表适配器 **/
    private FoodTypeAdapter foodTypeAdapter, foodTypeAdapter2;

    /** 是否加载了菜谱分类数据 用来打开pop时判断是否已经填充了数据 **/
    private boolean isLoadTypeData = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);
        setHeaderVisiable(true);
        setTitleText("菜谱列表");
        setRightText("分类");
        setRightTextClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchPop != null) {
                    if (!isLoadTypeData) {
                        initSearchList();
                    }
                    searchPop.showOrHidePopWindow(getTitleLayout());
                }
            }
        });
        searchPop = new SearchPopLayout(this);
        searchPop.getInstance(R.layout.pop_cook_type, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        initSearchList();
    }

    @Override
    public void initLayout() {
        silSearch = findViewById(R.id.sil_search);
        mLvList = findViewById(R.id.lv_list);
        mLvList.requestFocus();
    }

    @Override
    public void initData() {

        CommUtils.searchInputListener(silSearch.getEdtSearch(), new CommUtils.IEditTextListener() {
            @Override
            public void getResult(String result) {
//                if (TextUtils.isEmpty(result)) {
//                    ToastUtil.showToast(mContext, "请输入要搜索的关键字");
//                    return;
//                }
                pageNo = 1;
                cookParams.put("page", pageNo);
                cookParams.put("name", result);
                getCookList(0);
            }
        });

        Object value = MMKVUtil.getValue(Constants.KEY_COOK_CATEGORY, MMKVUtil.TYPE_STRING);
        if (value == null || value.equals("") || value.toString().equals("$")) {
            getCookCategory();
        }

        adapter = new CookListAdapter(mContext, mDatas, R.layout.listitem_food);
        mLvList.setAdapter(adapter);

        (pu = new PullRefreshUtils()).initListViewHead(mLvList,true);
        pu.setOnMoreLoadListener(new PullRefreshUtils.OnMoreLoadListener() {
            @Override
            public void onMoreLoad() {
                pageNo ++;
                getCookList(1);
            }
        });
        mLvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                keyWord = silSearch.getEdtSearch().getText().toString();
                getCookList(0);
            }
        });
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLvList.setRefreshing();
            }
        }, 500);


    }

    List<FoodSecondType> list = null;
    List<FoodThreeType> list2 = null;



    /** 初始化搜索列表参数 **/
    private void initSearchList() {
        FoodType foodType = (FoodType) MMKVUtil.getValue(Constants.KEY_COOK_CATEGORY, MMKVUtil.TYPE_PARCELABLE, FoodType.class);
        if (foodType != null && searchPop != null) {
            if (foodType.getCategoryInfo() != null) {
                ((TextView) searchPop.getView(R.id.txt_title)).setText(foodType.getCategoryInfo().getName());
            }
            list = foodType.getChilds();
            list2 = null;
            ListView listView = (ListView) searchPop.getView(R.id.lv_list1);
            final ListView listView2 = (ListView) searchPop.getView(R.id.lv_list2);
            if (list != null) {
                foodTypeAdapter = new FoodTypeAdapter(mContext, list, R.layout.pop_listitem_food_type);
                listView.setAdapter(foodTypeAdapter);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == foodTypeAdapter.selectedPosition) {
                        // 同一个item 返回
                        return;
                    }
                    // 改变选择状态
                    FoodSecondType type = list.get(position);
                    type.getCategoryInfo().setiSelected(true);
                    if (foodTypeAdapter.selectedPosition != -1) {
                        list.get(foodTypeAdapter.selectedPosition).getCategoryInfo().setiSelected(false);
                    }
                    foodTypeAdapter.notifyDataSetChanged();
                    foodTypeAdapter.selectedPosition = position;


                    // 初始化下一级列表
                    list2 = type.getChilds();
                    foodTypeAdapter2 = new FoodTypeAdapter(mContext, list2, R.layout.pop_listitem_food_type);
                    listView2.setAdapter(foodTypeAdapter2);
                }
            });
            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == foodTypeAdapter2.selectedPosition) {
                        // 同一个item 返回
                        return;
                    }
                    // 同一个item 返回
                    FoodThreeType type = list2.get(position);
                    type.getCategoryInfo().setiSelected(true);
                    if (foodTypeAdapter2.selectedPosition != -1) {
                        list2.get(foodTypeAdapter2.selectedPosition).getCategoryInfo().setiSelected(false);
                    }
                    foodTypeAdapter2.notifyDataSetChanged();
                    foodTypeAdapter2.selectedPosition = position;

                    // 开始搜索
                    cId = type.getCategoryInfo().getCtgId();
                    getCookList(0);
                    searchPop.showOrHidePopWindow(getTitleLayout());
                }
            });
            isLoadTypeData = true;
        }

    }

    /** 初始化食谱参数 **/
    private void initCookParams() {
        cookParams.put("key", Constants.SHAREDSDK_KEY);
        cookParams.put("cid", cId);
        if (silSearch != null) {
            keyWord = silSearch.getEdtSearch().getText().toString().trim();
        }
        cookParams.put("name", keyWord);
        cookParams.put("page", pageNo);
        cookParams.put("size", Constants.PAGE_SIZE);
    }

    /** 1是加载更多 0是下拉刷新 **/
    private void getCookList(int type) {
        initCookParams();
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
        if (foodTotal != null && foodTotal.getList() != null) {
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
