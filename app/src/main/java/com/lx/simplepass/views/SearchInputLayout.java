package com.lx.simplepass.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lx.simplepass.R;

/**
 * com.lx.simplepass.views
 * SimplePass
 * Created by lixiao2
 * 2019/1/21.
 */

public class SearchInputLayout extends LinearLayout {
    private Context mContext;
    private EditText edtSearch;
    public SearchInputLayout(Context context) {
        this(context, null);
    }

    public SearchInputLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchInputLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SearchInputLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_input, this, false);
        edtSearch = view.findViewById(R.id.edt_search);
        View line = new View(mContext);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        line.setBackgroundColor(mContext.getResources().getColor(R.color.c_e0));
        line.setLayoutParams(params);
        addView(view);
        addView(line);
    }

    public EditText getEdtSearch() {
        if (edtSearch != null) {
            return edtSearch;
        }
        return null;
    }
}
