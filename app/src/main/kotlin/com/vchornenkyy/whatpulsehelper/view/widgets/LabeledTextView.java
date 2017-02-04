package com.vchornenkyy.whatpulsehelper.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vchornenkyy.whatpulsehelper.R;

public class LabeledTextView extends LinearLayout {

    private TextView tvLabel;
    private TextView tvValue;

    public LabeledTextView(Context context) {
        super(context);
        setup(context, null);
    }

    public LabeledTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.layout_labeled_text, this);
        tvLabel = (TextView) view.findViewById(R.id.label);
        tvValue = (TextView) view.findViewById(R.id.value);

        if (attrs != null) {
            final TypedArray attributes = getContext().obtainStyledAttributes(
                    attrs, R.styleable.LabeledTextView, 0, 0);
            if (attributes.hasValue(R.styleable.LabeledTextView_labelText)) {
                setLabelText(attributes.getString(R.styleable.LabeledTextView_labelText));
            }

            if (attributes.hasValue(R.styleable.LabeledTextView_valueText)) {
                setValueText(attributes.getString(R.styleable.LabeledTextView_valueText));
            }
            attributes.recycle();
        }

        if (isInEditMode()) {
            tvValue.setText(R.string.placeholder_text);
        }
    }

    public void setValueText(String value) {
        if (tvValue != null) {
            tvValue.setText(value);
        }
    }

    public void setLabelText(String label) {
        if (tvLabel != null) {
            tvLabel.setText(label);
        }
    }
}
