package com.demon.virtualwork.widget;

import com.demon.virtualwork.util.Log;
import com.demon.virtualwork.util.TextUtils;
import javafx.scene.control.TextField;


/**
 * @author DeMon
 * Date 2021/5/27.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
public class NumTextField extends TextField {
    private static final String TAG = "NumTextField";

    @Override
    public void replaceText(int start, int end, String text) {
        Log.i(TAG, "replaceText=" + text);
        if (TextUtils.isDigitsOnly(text)) {
            super.replaceText(start, end, text);
        }
    }


    @Override
    public void replaceSelection(String replacement) {
        Log.i(TAG, "replaceSelection=" + replacement);
        if (TextUtils.isDigitsOnly(replacement)) {
            super.replaceSelection(replacement);
        }
    }
}
