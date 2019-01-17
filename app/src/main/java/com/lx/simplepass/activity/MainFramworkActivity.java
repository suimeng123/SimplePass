package com.lx.simplepass.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.library.utils.MMKVUtil;
import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.adapter.MainFragmentPagerAdapter;
import com.lx.simplepass.app.Constants;
import com.lx.simplepass.app.HttpUrl;
import com.lx.simplepass.base.BaseActivity;
import com.lx.simplepass.base.BaseFragment;
import com.lx.simplepass.fragment.FirstPageFragment;
import com.lx.simplepass.model.Provinces;
import com.lx.simplepass.views.BottomTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFramworkActivity extends BaseActivity {
    private static final String TAG = MainFramworkActivity.class.getSimpleName();
    private BottomTabLayout btl;
    private ViewPager vp;
    private TextView mTvTitle;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initLayout() {
        btl = findViewById(R.id.bl_tab);
        vp = findViewById(R.id.vp_content);
        mTvTitle = findViewById(R.id.txt_title);

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Provinces> lists = (ArrayList<Provinces>) MMKVUtil.getValue("city", MMKVUtil.TYPE_JSONARRAY, Provinces.class);
                ToastUtil.showToast(mContext, lists.size() + "---");
            }
        });
    }

    @Override
    public void initData() {
        btl.setResources(Constants.names, Constants.imgs, Constants.sImgs);
        btl.setItemClickListener(new BottomTabLayout.ItemClickListener() {
            @Override
            public void onclick(int index) {
                vp.setCurrentItem(index);
                mTvTitle.setText(getResources().getText(Constants.names[index]));
            }
        });

        Bundle bundle1 = new Bundle();
        bundle1.putString("title", getResources().getString(R.string.first_tab));
        Fragment f1 = BaseFragment.getInstance(FirstPageFragment.class, bundle1);
        Bundle bundle2 = new Bundle();
        bundle2.putString("title", getResources().getString(R.string.second_tab));
        Fragment f2 = BaseFragment.getInstance(FirstPageFragment.class, bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("title", getResources().getString(R.string.three_tab));
        Fragment f3 = BaseFragment.getInstance(FirstPageFragment.class, bundle3);
        Bundle bundle4 = new Bundle();
        bundle4.putString("title", getResources().getString(R.string.four_tab));
        Fragment f4 = BaseFragment.getInstance(FirstPageFragment.class, bundle4);
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);

        vp.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                btl.views.get(position).performClick();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        String citys = (String) MMKVUtil.getValue(Constants.KEY_CITYS, MMKVUtil.TYPE_STRING);
        if (citys == null || TextUtils.isEmpty(citys)) {
            getCitys();
        }
    }

    /** 获取城市信息 **/
    public void getCitys() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", Constants.SHAREDSDK_KEY);
        http3Util.doGet(HttpUrl.GET_CITY_URL,params,null,1);
    }


    @Override
    public void resultData(int tag, Bundle bundle) {
        switch (tag) {
            case 1:
                // 获取城市列表信息
                Boolean isSuccess = (Boolean) bundle.get(parser.getStateName());
                if (isSuccess) {
                    MMKVUtil.setValue(Constants.KEY_CITYS, bundle.get(parser.getDataName()).toString());
                } else {
                    ToastUtil.showToast(mContext, bundle.get(parser.getMessageName()).toString());
                }
                break;
            default:
                break;
        }
    }
}
