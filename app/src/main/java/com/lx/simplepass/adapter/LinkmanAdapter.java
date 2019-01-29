package com.lx.simplepass.adapter;

import android.content.Context;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.library.adapter.CommonAdapter;
import com.base.library.adapter.ViewHolder;
import com.base.library.utils.ToastUtil;
import com.lx.simplepass.R;
import com.lx.simplepass.fragment.LinkmanFragment;
import com.lx.simplepass.fragment.MessageDialogFragment;
import com.lx.simplepass.model.Linkman;
import com.lx.simplepass.utils.CommUtils;
import com.lx.simplepass.utils.LinkManUtil;
import com.lx.simplepass.views.CommDialog;

import java.util.List;

/**
 * 常用联系人适配器
 * com.lx.simplepass.adapter
 * SimplePass
 * Created by lixiao2
 * 2019/1/26.
 */

public class LinkmanAdapter extends CommonAdapter {
    private List mDatas;
    private LinkmanFragment.DelCallBack delClick;

    public LinkmanAdapter(Context context, List datas, int layoutId, LinkmanFragment.DelCallBack delClick) {
        super(context, datas, layoutId);
        mDatas = datas;
        this.delClick = delClick;
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        final Linkman item = (Linkman) o;
        TextView label = holder.getView(R.id.tv_label);
        TextView name = holder.getView(R.id.tv_name);
        TextView title = holder.getView(R.id.tv_title);
        View line = holder.getView(R.id.v_line);
        View line2 = holder.getView(R.id.v_line2);
        View line3 = holder.getView(R.id.v_line3);
        if (!TextUtils.isEmpty(item.getName())) {
            int len = item.getName().length();
            label.setText(item.getName().substring(len - 1, len));
        }else {
            label.setText("空");
        }
        int random = CommUtils.getRandomNumber();
        switch (random) {
            case 0:
                label.setBackgroundResource(R.drawable.oval_1_type);
                break;
            case 1:
                label.setBackgroundResource(R.drawable.oval_2_type);
                break;
            case 2:
                label.setBackgroundResource(R.drawable.oval_3_type);
                break;
            case 3:
                label.setBackgroundResource(R.drawable.oval_4_type);
                break;
            default:
                break;
        }
        name.setText(item.getName() + "    " + item.getNumber());
        title.setText(item.getSortKey());
        if (holder.getPosition() >= 1 && holder.getPosition() < (mDatas.size() - 1)) {
            if (((Linkman)mDatas.get(holder.getPosition() - 1)).getSortKey().equals(item.getSortKey())) {
                // 如果上一个的首字母与当前首字母一致 那么隐藏当前首字母
                title.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            } else {
                title.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
            }
        }

        if (holder.getPosition() <= mDatas.size() - 1) {
            if (holder.getPosition() == mDatas.size() - 1) {
                // 最后一个
                line2.setVisibility(View.GONE);
                line3.setVisibility(View.VISIBLE);
            } else {
                if (!((Linkman)mDatas.get(holder.getPosition() + 1)).getSortKey().equals(item.getSortKey())) {
                    line2.setVisibility(View.GONE);
                    line3.setVisibility(View.VISIBLE);
                } else {
                    line2.setVisibility(View.VISIBLE);
                    line3.setVisibility(View.GONE);
                }
            }
        }

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialogFragment fragment = new MessageDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", item);
                fragment.setArguments(bundle);
                fragment.show(((FragmentActivity)mContext).getSupportFragmentManager(),"MessageDialogFragment");
            }
        });

        name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dialog = new CommDialog.Builder(mContext).setTip("确认删除该联系人？").setSureClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            LinkManUtil.delLinkman(mContext, item.getContactId());
                            ToastUtil.showToast(mContext, "删除成功");
                            delClick.success();
                            dialog.dismiss();
                        } catch (RemoteException e) {
                            ToastUtil.showToast(mContext, "删除失败");
                            delClick.fail();
                            e.printStackTrace();
                        } catch (OperationApplicationException e) {
                            ToastUtil.showToast(mContext, "删除失败");
                            delClick.fail();
                            e.printStackTrace();
                        }
                    }
                }).create();
                dialog.show();
                return true;
            }
        });
    }

    private CommDialog dialog = null;
}
