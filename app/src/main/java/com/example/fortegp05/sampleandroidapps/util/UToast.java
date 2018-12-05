package com.example.fortegp05.sampleandroidapps.util;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

public class UToast {
    public static void normalShow(Context context, String txt) {
        show(context, txt, Color.CYAN);
    }

    public static void abnormalShow(Context context, String txt) {
        show(context, txt, Color.MAGENTA);
    }

    public static void show(Context context, String txt, int color) {
        Toast toast = Toast.makeText(context, txt, Toast.LENGTH_LONG);
        toast.getView().setBackgroundColor(color);
        toast.show();
    }
}
