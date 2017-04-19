package com.socc.android.soccapp.base.cstlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.*;
import com.socc.android.soccapp.R;

/**
 * Created by AppCreater01 on 2017-03-16.
 */
public class CustomTextLabelView extends LinearLayout {
    LinearLayout bg;
    EditText input;
    TextView label;

    public CustomTextLabelView(Context context) {
        super(context);
        initView();
    }
    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);

        View v = li.inflate(R.layout.text_labelview, this, false);
        addView(v);

    }
}
