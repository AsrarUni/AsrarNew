package com.example.mersad.asrar.Activities;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.R;

import wiadevelopers.com.library.DivarUtils;
import wiadevelopers.com.library.MaskdEditText.MaskedEditText;


public class Edit_Password_Activity extends AppCompatActivity {

    Typeface custom_font;
    MaskedEditText edt_current, edt_newPass, edt_newPassCopy;
    TextView tv_current, tv_newPass, tv_newPassCopy;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        initialize();
    }

    private void initialize() {

        find_views();
        change_notification_color();
        set_typefaces();
//        setup_toolbar();
//        setup_navigation();
        set_Listeners();


    }

    private void set_Listeners() {

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Real_Pass = DivarUtils.readDataFromStorage(Constant.IS_HAS_PATTERN, null);
                String Entered_Pass = edt_current.getRawText().toString().trim();
                String choosed_pass = edt_newPass.getRawText().toString().trim();
                String choosed_pass_copy = edt_newPassCopy.getRawText().toString().trim();

                if (Entered_Pass.length() > 3 && choosed_pass.length() > 3 && choosed_pass_copy.length() > 3) {
                    if (Entered_Pass.equals(Real_Pass)) {

                        if (choosed_pass.equals(choosed_pass_copy)) {

                            DivarUtils.writeDataInStorage(Constant.IS_HAS_PATTERN, choosed_pass);
                            Toast.makeText(Edit_Password_Activity.this, "عملیات تغییر رمز با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {

                            Toast.makeText(Edit_Password_Activity.this, "لطفا تکرار کلمه عبور جدید را با دقت وارد کنید", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        Toast.makeText(Edit_Password_Activity.this, "لطفا رمز صحیح فعلی را با دقت وارد کنید", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(Edit_Password_Activity.this, "لطفا تمامی فیلد ها را پر کنید", Toast.LENGTH_SHORT).show();

                }
                ;

            }
        });

    }

    private void find_views() {

        edt_current = findViewById(R.id.EditPassword_edt_current);
        edt_newPass = findViewById(R.id.EditPassword_edt_new);
        edt_newPassCopy = findViewById(R.id.EditPassword_edt_new_copy);

        tv_current = findViewById(R.id.EditPassword_tv_current);
        tv_newPass = findViewById(R.id.EditPassword_tv_new);
        tv_newPassCopy = findViewById(R.id.EditPassword_tv_new_copy);

        btn_save = findViewById(R.id.EditPassword_btn_Save);

    }

    private void change_notification_color() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = Edit_Password_Activity.this.getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(ContextCompat.getColor(Edit_Password_Activity.this, R.color.useful_dark));

        }

    }

    private void set_typefaces() {

        custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/koodak.ttf");

        tv_current.setTypeface(custom_font);
        tv_newPass.setTypeface(custom_font);
        tv_newPassCopy.setTypeface(custom_font);
        btn_save.setTypeface(custom_font);

    }


}
