package com.example.gobuy.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.widget.Toast;

import java.util.Calendar;


import com.example.gobuy.R;

public class LoginActivity extends AppCompatActivity {
    private Spinner spinnerNationality;
    private EditText editTextPhone, editEmailAddress, editPassword, editBirthday;
    private CheckBox checkNotrobot;
    private Button buttonClear, buttonConfirm;
    private ImageView imageLogo;
    private TextView tvNationality, tvAccount, tvPassword, tvBirthday, tvVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //-----------------綁定元件--------------------------
        spinnerNationality = findViewById(R.id.spinnerNationality);
        editTextPhone = findViewById(R.id.editTextPhone);
        editEmailAddress = findViewById(R.id.editEmailAddress);
        editPassword = findViewById(R.id.editPassword);
        editBirthday = findViewById(R.id.editBirthday);
        checkNotrobot = findViewById(R.id.checkNotrobot);
        buttonClear = findViewById(R.id.buttonClear);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        imageLogo = findViewById(R.id.imageLogo);
        tvNationality = findViewById(R.id.tvNationality);
        tvAccount = findViewById(R.id.tvAccount);
        tvPassword = findViewById(R.id.tvPassword);
        tvBirthday = findViewById(R.id.tvBirthday);
        tvVerify = findViewById(R.id.tvVerify);

        //------------------建立一個ArrayAdapter------------------
        //android.R.layout.simple_spinner_item 是 Android 內建的、最簡單的單行文字佈局
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nationalities_array, android.R.layout.simple_spinner_item);

        //指定下拉選單展開時，每個選項要使用的佈局，同樣使用 Android 內建的佈局
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //將這個設定好的 adapter 套用到我們的 spinnerNationality 元件上
        spinnerNationality.setAdapter(adapter);

        //------------------設定監聽------------------------
        //CheckBox：啟用/禁用「確認送出」按鈕
        checkNotrobot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonConfirm.setEnabled(isChecked);
            }
        });
        //清除按鈕：清空所有輸入欄
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPhone.setText("");
                editEmailAddress.setText("");
                editPassword.setText("");
                editBirthday.setText("");
            }
        });
        //生日輸入框：彈出日期選擇器
        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得當前的系統日期，作為 DatePickerDialog 的預設日期
                final java.util.Calendar calendar = java.util.Calendar.getInstance();
                int currentYear = calendar.get(java.util.Calendar.YEAR);
                int currentMonth = calendar.get(java.util.Calendar.MONTH);
                int currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH);

                //建立一個 DatePickerDialog(第一個參數是 context (通常是 this),第二個參數是 OnDateSetListener，當使用者選好日期按下確定後會被觸發)
                DatePickerDialog datePickerDialog = new DatePickerDialog(LoginActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //在這裡處理使用者選擇好的日期
                                String selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
                                editBirthday.setText(selectedDate);
                            }
                        }, currentYear, currentMonth, currentDay); //後面三個參數是預設顯示的年、月、日

                //顯示 DatePickerDialog
                datePickerDialog.show();
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得所有輸入框的文字內容 .trim()=>去除前後空格
                String phone = editTextPhone.getText().toString().trim();
                String email = editEmailAddress.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String birthday = editBirthday.getText().toString().trim();

                //驗證邏輯(檢查是否有任何一個欄位是空的)
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(birthday)) {
                    Toast.makeText(LoginActivity.this, "所有欄位皆為必填", Toast.LENGTH_SHORT).show();
                }else{
                    // 建立一個 Intent，指定要從 (LoginActivity.this) 跳轉到 (MainActivity.class)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    // 執行這個 Intent
                    startActivity(intent);

                    // 呼叫 finish() 將登入頁面從堆疊中移除，這樣使用者在主功能頁按下返回鍵時，才不會又回到登入頁
                    finish();
                }
            }
        });
    }
}