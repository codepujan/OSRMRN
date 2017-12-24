package com.rnosrm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pujanpaudel on 12/19/17.
 */

public class MapViewWrapper extends View {
    public MapViewWrapper(Context context, ViewGroup group) {
        super(context);
        inflate(context,R.layout.activity_main,group);

    }
}
