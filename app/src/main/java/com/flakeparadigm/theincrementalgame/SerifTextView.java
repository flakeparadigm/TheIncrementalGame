package com.flakeparadigm.theincrementalgame;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class SerifTextView extends TextView {

    public SerifTextView(Context context) {
        super(context);
        setTypeface(Typeface.SERIF);
    }
    public SerifTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.SERIF);
    }
    public SerifTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTypeface(Typeface.SERIF);
    }
}