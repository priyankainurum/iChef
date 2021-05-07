package com.ichef.android.retrofit;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ichef.android.R;


/**
 * Created by As148 on 7/28/2017.
 */

public class TransparentProgressDialog extends Dialog {

    private ProgressBar progressBar;
    private TextView progressText;

    public TransparentProgressDialog(Context context) {
        super(context, R.style.TransparentProgressDialog);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        setCancelable(true);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(context, R.color.offSteelGreen), PorterDuff.Mode.SRC_IN );

        progressText = new TextView(context);
        progressText.setText("Please Wait...");
        progressText.setTextColor(Color.GREEN);
        progressText.setTextSize(16.0f);

        layout.addView(progressBar, params);
        layout.addView(progressText,params);
        addContentView(layout, params);
    }

    @Override
    public void show() {
        super.show();
    }
}
