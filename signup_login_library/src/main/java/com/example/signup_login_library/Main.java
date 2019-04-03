package com.example.signup_login_library;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mylibrary.ButtonModel;
import com.example.mylibrary.Dailog;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends AppCompatActivity {
    RelativeLayout mainLayout;
    LinearLayout editTextField, forgotLayout, buttonLayout;
    ImageView logo;
    ArrayList<EditTextModel> edittextArrayList = new ArrayList<>();
    String[] editTexthint = {"email", "password", "confirm password"};
    ArrayList<ButtonModel> temp = new ArrayList<>();
    String[] buttonName = {"ok"};
    String text, hint,password;
    private static Context mContext;
    LinearLayout background;
    IntentFilter intentFilter;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background=(LinearLayout) findViewById(R.id.background);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ActionBar m_myActionBar=getSupportActionBar(); // to get activity actionbar
        //For hiding actionbar
        m_myActionBar.hide();
        for (String name : editTexthint) {
            EditTextModel model = new EditTextModel();
            model.setName(name);
            edittextArrayList.add(model);
        }
        for (String buttonname : buttonName) {
            ButtonModel buttonModel = new ButtonModel();
            buttonModel.setName(buttonname);
            temp.add(buttonModel);
        }
        LoginTemplate.addLayout(this,true,edittextArrayList,true,"Login",background,R.color.black,R.drawable.edittext_background,"Gotham-Font/GothamMedium.ttf");
        LoginTemplate.set_Logo_design(R.drawable.eten_logo,250,250);
        LoginTemplate.change_button(R.color.white,R.drawable.button_shape,"Gotham-Font/GothamMedium.ttf");
        LoginTemplate.setforgot_design(this,R.color.colorPrimary);
        LoginTemplate.Background(this,true,background,R.color.colorAccent,R.drawable.back_2);
        intentFilter = new IntentFilter("DynamicPage");

    }

    private BroadcastReceiver buttonClick = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String text1 = intent.getStringExtra("BUTTON");
            if(text1.equals("Login")){
                for (int i = 0; i < LoginTemplate.allEds.size(); i++) {
                    text =  LoginTemplate.allEds.get(i).getText().toString();
                    hint = edittextArrayList.get(i).getName();
                    if(hint.equals("password")){
                        if(!text.equals("")) {
                            password= LoginTemplate.allEds.get(i).getText().toString();
                            if(password.length()<5){
                                Dailog.showDialog(Main.this, "Weak password", "Please fill Strong Password ", temp);
                                break;
                            }
                        }
                    }
                    if (hint.equals("email")) {
                        if(!text.equals("")) {
                            Boolean check = isEmailValid( LoginTemplate.allEds.get(i).getText().toString());
                            if (!check) {
                                Dailog.showDialog(Main.this, "Wrong Email", "Please fill correct Email ", temp);
                                break;
                            }
                        }
                    }
                    if(hint.equals("confirm password")){
                        if(!text.equals("")) {
                            if(! LoginTemplate.allEds.get(i).getText().toString().equals(password)){
                                Dailog.showDialog(Main.this, "Password not match", "Please fill it correct ", temp);
                                break;
                            }

                        }
                    }
                    if (text.equals("")) {
                        Dailog.showDialog(Main.this, "Please fill it", "Empty " + edittextArrayList.get(i).getName(), temp);
                        break;
                    }

                }
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(buttonClick);
    }


    @Override
    protected void onResume() {
        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        LocalBroadcastManager.getInstance(this).registerReceiver(
                buttonClick, new IntentFilter("DynamicPage"));
        super.onResume();
    }

    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-z]+[\\w-]+\\.)+[a-z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }
}
//arrow.animate().rotation(arrow.getRotation() + 180);