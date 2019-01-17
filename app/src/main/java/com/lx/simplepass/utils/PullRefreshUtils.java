package com.lx.simplepass.utils;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lx.simplepass.R;
import com.base.library.lib.pullrefresh.PullToRefreshBase;
import com.base.library.lib.pullrefresh.PullToRefreshExpandableListView;
import com.base.library.lib.pullrefresh.PullToRefreshListView;

public class PullRefreshUtils {
	public static void initHead(PullToRefreshBase pull){
		pull.setPullLabel("下拉刷新");
		pull.setRefreshingLabel("刷新中...");
		pull.setReleaseLabel("松手后刷新");
	}
	
	public void hideFooterLine(){
		if(line != null) {
			line.setVisibility(View.GONE);
		}
	}
	
	View emptyView;
	View footer,lay_foot;
	View footerProgress;
	TextView footerTxt;
	ListView listView;
	View line;
	
	public void initListView(final ListView listView, boolean toLoadMore){
		listView.setTag(true);
		this.listView = listView;
		emptyView = View.inflate(listView.getContext(), R.layout.activity_empty, null);
		listView.setEmptyView(emptyView);
		
		if(toLoadMore){
			footer = View.inflate(listView.getContext(), R.layout.activity_list_more, null);
			line = footer.findViewById(R.id.line1);
			lay_foot = footer.findViewById(R.id.lay_foot);
			footer.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					 if((Boolean)listView.getTag()){
	                     onMoreLoading();
	                     if(ol != null){ol.onMoreLoad();}
	                 }
				}
			});
			
			footerProgress = footer.findViewById(R.id.pb_more);
			footerTxt = (TextView) footer.findViewById(R.id.txt_load_more);
			listView.addFooterView(footer);
			listView.setOnScrollListener(new OnScrollListener() {
				boolean flag = false;
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {
					if(!footer.isClickable()) {return;}
					if(firstVisibleItem != 0 && visibleItemCount+firstVisibleItem >= totalItemCount-1){
	                    if(!flag && (Boolean)listView.getTag()&&!footerIsHide){
	                        flag = true;
	                        onMoreLoading();
	                        if(ol != null){ol.onMoreLoad();}
	                    }
	                }else{
	                	flag = false;
	                }
				}
			});
		}
	}
	
	
	
	public void initListViewHead(final PullToRefreshListView pull,boolean toLoadMore){
		initHead(pull);
		initListView(pull.getRefreshableView(), toLoadMore);
	}
	
	private boolean footerIsHide = false;
	public void hideFoot(){
		footerIsHide = true;
		this.footer.setVisibility(View.GONE);
		this.footer.setPadding(0, -footer.getMeasuredHeight() - 1000, 0, 0);
	}
	
	/**
	 * 没有更多内容了
	 */
	public void OnHasNotMoreData(){
		if(footerIsHide){return;}
		footer.setClickable(false);
		lay_foot.setBackgroundColor(listView.getContext().getResources().getColor(R.color.color_eee));
		footerProgress.setVisibility(View.GONE);
		footerTxt.setText("没有更多");
		footerTxt.setTextColor(listView.getContext().getResources().getColor(R.color.color_bbb));
//		listView.setOnScrollListener(null);//清楚监听
//		footer.setOnClickListener(null);
	}
	
	public void onMoreLoadComplete(){
		if(footerIsHide){return;}
		footerProgress.setVisibility(View.GONE);
		footerTxt.setText("加载更多");
		listView.setTag(true);
		footer.setClickable(true);
	}
	
	private void onMoreLoading(){
		if(footerIsHide){return;}
		footerProgress.setVisibility(View.VISIBLE);
		footerTxt.setText("加载中...");
		listView.setTag(false);
	}
	
	private OnMoreLoadListener ol;
	public void setOnMoreLoadListener(OnMoreLoadListener ol){
		this.ol = ol;
	}
	
	public interface OnMoreLoadListener{
		public void onMoreLoad();
	}
	
	public void initExpandableListViewHead(final PullToRefreshExpandableListView pull){
		initHead(pull);
		emptyView = View.inflate(pull.getContext(), R.layout.activity_empty, null);
		pull.setEmptyView(emptyView);
	}
	
	public static View getEmptyView(Context c){
		return View.inflate(c, R.layout.activity_empty, null);
	}
}
