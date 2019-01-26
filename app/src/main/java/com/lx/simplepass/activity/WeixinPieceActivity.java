package com.lx.simplepass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.base.library.lib.pullrefresh.PullToRefreshBase;
import com.base.library.lib.pullrefresh.PullToRefreshListView;
import com.base.library.utils.MMKVUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.WeixinPieceAdapter;
import com.lx.simplepass.adapter.WeixinPieceTypeAdapter;
import com.lx.simplepass.app.Constants;
import com.lx.simplepass.app.HttpUrl;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.model.WeiXinPieceList;
import com.lx.simplepass.model.WeixinPieceItem;
import com.lx.simplepass.model.WeixinPieceType;
import com.lx.simplepass.utils.PullRefreshUtils;
import com.lx.simplepass.views.SearchPopLayout;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.lx.simplepass.activity
 * SimplePass
 * Created by lixiao2
 * 2019/1/25.
 */

public class WeixinPieceActivity extends BaseActivity {

    private PullToRefreshListView mLvList;
    private WeixinPieceAdapter adapter;
    private List<WeixinPieceItem> lists = new ArrayList<>();
    private String cId = "37";
    private int pageNo = 1;
    private PullRefreshUtils pu;


    /** 自定义的pop搜索布局 选择的 **/
    private SearchPopLayout searchPop;
    private GridView mGVContent;
    private WeixinPieceTypeAdapter typeAdapter;
    private List<WeixinPieceType> types = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin_piece);
        setTitleText("微信精选");
        setRightText("分类");
        setRightTextClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPop.showOrHidePopWindow(getTitleLayout());
            }
        });
    }

    @Override
    public void initLayout() {
        mLvList = findViewById(R.id.lv_list);
    }

    @Override
    public void initData() {
        adapter = new WeixinPieceAdapter(mContext, lists, R.layout.listitem_weixin_piece);
        mLvList.setAdapter(adapter);


        (pu = new PullRefreshUtils()).initListViewHead(mLvList,true);
        pu.setOnMoreLoadListener(new PullRefreshUtils.OnMoreLoadListener() {
            @Override
            public void onMoreLoad() {
                pageNo ++;
                getListData(1);
            }
        });
        mLvList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                getListData(0);
            }
        });
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= 0) {
                    return;
                }
                WeixinPieceItem item = lists.get(position - 1);
                startActivity(new Intent(mContext, WeixinPieceDetailActivity.class).putExtra("item", item));
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLvList.setRefreshing();
            }
        }, 500);

        getListType();

        searchPop = new SearchPopLayout(this);
        searchPop.getInstance(R.layout.weixin_piece_type, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mGVContent = (GridView) searchPop.getView(R.id.gv_content);
        typeAdapter = new WeixinPieceTypeAdapter(mContext, types, R.layout.griditem_weixin_piece_type);
        mGVContent.setAdapter(typeAdapter);
        mGVContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeixinPieceType item = types.get(position);
                cId = item.getCid();
                pageNo = 1;
                getListData(0);
                searchPop.showOrHidePopWindow(getTitleLayout());
            }
        });

        setTypeInfo();
    }
    /** 设置微信精选分类信息 **/
    private void setTypeInfo() {
        if (MMKVUtil.getValue(Constants.KEY_WEIXIN_PIECE_CATEGORY, MMKVUtil.TYPE_STRING) != null) {
            List<WeixinPieceType> list = new ArrayList<>(JSON.parseArray(MMKVUtil.getValue(Constants.KEY_WEIXIN_PIECE_CATEGORY, MMKVUtil.TYPE_STRING).toString(), WeixinPieceType.class));
            if (list != null) {
                types.clear();
                types.addAll(list);
                typeAdapter.notifyDataSetChanged();
            }
        }
    }

    private void getListData(int type) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.SHAREDSDK_KEY);
        params.put("cid", cId);
        params.put("page", pageNo);
        params.put("size", Constants.PAGE_SIZE);
        http3Util.doGet(HttpUrl.GET_WEIXIN_PIECE_URL, params, WeiXinPieceList.class, type + 2);
    }

    /** 获取微信精选分类 **/
    private void getListType() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.SHAREDSDK_KEY);
        http3Util.doGet(HttpUrl.GET_WEIXIN_CATEGORY_URL, params, null, 1);
    }

    @Override
    public void resultData(int tag, Bundle bundle) {
        switch (tag) {
            case 1:
                Object types = bundle.get(parser.getDataName());
                if (types != null) {
                    MMKVUtil.setValue(Constants.KEY_WEIXIN_PIECE_CATEGORY, types.toString());
                    setTypeInfo();
                }
                break;
            case 2:
                WeiXinPieceList pieceList = (WeiXinPieceList) bundle.get(parser.getDataName());
                mLvList.onRefreshComplete();
                if (pieceList != null && pieceList.getList() != null) {
                    lists.clear();
                    lists.addAll(pieceList.getList());
                    adapter.notifyDataSetChanged();
                }
                break;
            case 3:
                WeiXinPieceList pieceList2 = (WeiXinPieceList) bundle.get(parser.getDataName());
                if (pieceList2 != null && pieceList2.getList() != null) {
                    lists.addAll(pieceList2.getList());
                    adapter.notifyDataSetChanged();
                    if (pieceList2.getTotal() == lists.size()) {
                        pu.OnHasNotMoreData();
                    } else {
                        pu.onMoreLoadComplete();
                    }
                } else {
                    pu.onMoreLoadComplete();
                }
                break;
            default:
                break;
        }
    }
}
