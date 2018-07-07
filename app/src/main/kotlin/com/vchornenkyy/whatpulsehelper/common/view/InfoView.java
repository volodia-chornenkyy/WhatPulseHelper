package com.vchornenkyy.whatpulsehelper.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vchornenkyy.whatpulsehelper.R;

public class InfoView extends RelativeLayout {

    private TextView tvContent;
    private TextView tvAction;

    public InfoView(Context context) {
        super(context);
        setup(context, null);
    }

    public InfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public InfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        tvAction.setOnClickListener(l);
    }

    public void setContent(@Nullable String content) {
        tvContent.setText(content);
    }

    private void setup(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.layout_info, this);
        TextView tvHeader = view.findViewById(R.id.tvHeader);
        tvContent = view.findViewById(R.id.tvValue);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvContent.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        tvAction = view.findViewById(R.id.tvAction);

        if (attrs != null) {
            final TypedArray attributes = getContext().obtainStyledAttributes(
                    attrs, R.styleable.InfoView, 0, 0);

            if (attributes.hasValue(R.styleable.InfoView_headerText)) {
                tvHeader.setText(attributes.getString(R.styleable.InfoView_headerText));
            } else {
                tvHeader.setVisibility(GONE);
            }

            if (attributes.hasValue(R.styleable.InfoView_contentText)) {
                tvContent.setText(attributes.getString(R.styleable.InfoView_contentText));
            } else {
                tvContent.setVisibility(GONE);
            }

            if (attributes.hasValue(R.styleable.InfoView_actionText)) {
                tvAction.setText(attributes.getString(R.styleable.InfoView_actionText));
            } else {
                tvAction.setText(R.string.dismiss);
            }

            attributes.recycle();
        }
    }

}
