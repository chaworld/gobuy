package com.example.gobuy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gobuy.R;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewHeader;
    private ImageButton buttonGoToPoints, buttonGoToSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化 UI 元件
        imageViewHeader = findViewById(R.id.ibgobuy);
        buttonGoToPoints = findViewById(R.id.ibpoint);
        buttonGoToSettings = findViewById(R.id.ibsetting);

        // 3. 執行所有點擊事件的設定
        setupClickListeners();
    }

    // 專門用來設定所有點擊事件的方法
    private void setupClickListeners() {
        //gobuy圖示返回登入事件
        imageViewHeader.setOnClickListener(v -> navigateToLogin());

        //功能按鈕的跳轉事件
        buttonGoToSettings.setOnClickListener(v -> {
            // 跳轉到 SettingsActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        buttonGoToPoints.setOnClickListener(v -> {
            // 跳轉到 PointsActivity
            Intent intent = new Intent(MainActivity.this, PointsActivity.class);
            startActivity(intent);
        });
    }

    // 專門負責返回登入頁的共用方法
    private void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        // 清除所有在 MainActivity 之上的 Activity，確保返回行為乾淨
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}