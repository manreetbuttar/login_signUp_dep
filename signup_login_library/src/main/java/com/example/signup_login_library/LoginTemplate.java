package com.example.signup_login_library;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.ButtonModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LoginTemplate {
    static RelativeLayout mainLayout;
    static LinearLayout editTextField, forgotLayout, buttonLayout;
    static ImageView logo;
    static String[] editTexthint = {"email", "password", "confirm password"};
    static ArrayList<ButtonModel> temp = new ArrayList<>();
    static String[] buttonName = {"ok"};
    static String text, hint, password;
    static LinearLayout mainLayout_1;
    static ImageView logo_1;
    static CustomEditText edt;
    public static List<CustomEditText> allEds = new ArrayList<CustomEditText>();

    static CustomButton button;
    static TextView forgot;
    static int count = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceAsColor")
    public static void addLayout(Context context, Boolean logo, ArrayList<EditTextModel> numOfEdittext, Boolean forgot, String buttonName, LinearLayout layout, int edittextcolor, int edit_background, String edit_font) {
        if(layout!=null) {
            if (logo == true) {
                logo_1 = new ImageView(context);
                logo_1.setImageResource(R.drawable.eten_logo);
                layout.addView(logo_1);

            }
            for (int i = 0; i < numOfEdittext.size(); i++) {
                try {
                    addEditText(context, numOfEdittext.get(i).getName(), layout);
                    change_editText(edittextcolor, edit_background, edit_font, numOfEdittext.get(i).getName());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            addforgot(forgot, context, "Forgot Password", layout);
            addButton(context, buttonName, layout, 100);
        }
    }

    public static void set_Logo_design(int logo_src, int height, int width) {
        if (logo_1 != null) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            logo_1.setLayoutParams(params);
            logo_1.setImageResource(logo_src);
            logo_1.getLayoutParams().height = height;
            logo_1.getLayoutParams().width = width;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void addEditText(Context context, final String name, LinearLayout layout) throws NoSuchFieldException, IllegalAccessException {
        count++;
        edt = new CustomEditText(context);
        edt.setBackground(null);
        edt.setCursorVisible(true);
        //set cursor color
        Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
        f.setAccessible(true);
        f.set(edt, R.color.colorPrimaryDark);
        edt.setHint(name);
        layout.addView(edt);
        allEds.add(edt);
        if (count == 1) {
            ((LinearLayout.LayoutParams) edt.getLayoutParams()).setMargins(50, 100, 50, 0);
        } else {
            ((LinearLayout.LayoutParams) edt.getLayoutParams()).setMargins(50, 15, 50, 0);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void change_editText(int textColor, int button_background, String font_path,String password) {
        if (edt != null) {
            edt.changedesign(textColor, button_background, font_path);
            edt.changeType(password);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void change_button(int textColor, int button_background, String font_path) {
        if (button != null) {
            button.changedesign(textColor, button_background, font_path);
        }
    }
    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void addforgot(Boolean value, Context context, String name, LinearLayout linearLayout) {
        if (value == true) {
                forgot = new TextView(context);
                forgot.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                forgot.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                linearLayout.addView(forgot);
                forgot.setText(name);
                ((LinearLayout.LayoutParams) forgot.getLayoutParams()).setMargins(50, 30, 50, 0);

        }
    }
    public static void setforgot_design(Context context,int textcolor){
        if(forgot!=null) {
            if(textcolor!=0) {
                forgot.setTextColor(context.getResources().getColor(textcolor));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void addButton(final Context context, final String name, LinearLayout layout, int height) {
        button = new CustomButton(context);
        layout.addView(button);
        //button.setOnClickListener(this);
        ((LinearLayout.LayoutParams) button.getLayoutParams()).setMargins(50, 50, 50, 0);
        ((LinearLayout.LayoutParams) button.getLayoutParams()).height = height;
        button.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //itemListener.Clicked(v,name);
                Intent intent = new Intent("DynamicPage");
                intent.putExtra("BUTTON", name);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void Background(Context context, Boolean value,LinearLayout layout, int color, int picture) {
        if (value == true) {
            if(layout!=null) {
                if (picture != 0) {
                    layout.setBackgroundResource(picture);
                } else {
                    if (color != 0) {
                        layout.setBackgroundColor(context.getResources().getColor(color));
                    }
                }
            }
        }
    }

}
