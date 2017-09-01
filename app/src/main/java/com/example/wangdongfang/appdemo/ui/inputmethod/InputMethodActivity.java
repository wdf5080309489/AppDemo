package com.example.wangdongfang.appdemo.ui.inputmethod;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.wangdongfang.appdemo.R;

import java.lang.reflect.Method;

public class InputMethodActivity extends AppCompatActivity implements View.OnTouchListener, KeyboardView.OnKeyboardActionListener {

    private EditText edtName;
    private EditText edtPwd;
    private KeyboardView keyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_method);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtPwd = (EditText) findViewById(R.id.edt_pwd);
        keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        keyboardView.setEnabled(true);
        keyboardView.setKeyboard(new Keyboard(this, R.xml.digitals));
        keyboardView.setOnKeyboardActionListener(this);

        hideSysInput(edtName);
        hideSysInput(edtPwd);
        edtName.setOnTouchListener(this);
        edtPwd.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        keyboardView.setVisibility(View.VISIBLE);
        return false;
    }

    private void hideSysInput(EditText editText) {
        try {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                    boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(editText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {
        System.out.println("onKey code = " + i);
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
