package com.example.signup_login_library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    Paint textPaint;

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);

    }

    private void parseAttributes(Context context, AttributeSet attrs) {

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public  void changedesign(int textcolor, int background_shape,String font_path) {
        //set text color
        setTextColor(getResources().getColor(textcolor));
        //set background
        setBackgroundResource(background_shape);
        //set text in single line
        setSingleLine(true);
        //set font
        if(font_path!=null) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), font_path);
            setTypeface(tf);
        }

    }
    public void changeType(String name){
        if(name.equals("password")||name.equals("confirm password"))
        {
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }
    }

}