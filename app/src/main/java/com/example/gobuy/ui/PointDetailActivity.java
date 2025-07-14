package com.example.gobuy.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gobuy.R;

public class PointDetailActivity extends AppCompatActivity {

    // 定義用來傳遞資料的「鑰匙 (Key)」，設為 public static final 讓其他類別也能安全地使用
    public static final String EXTRA_STORE_NAME = "EXTRA_STORE_NAME";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_TIME = "EXTRA_TIME";
    public static final String EXTRA_POINTS = "EXTRA_POINTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_detail);

        // 找到所有 TextView
        TextView detailStoreName = findViewById(R.id.detailStoreName);
        TextView detailDate = findViewById(R.id.detailDate);
        TextView detailTime = findViewById(R.id.detailTime);
        TextView detailPoints = findViewById(R.id.detailPoints);

        // 從啟動這個 Activity 的 Intent 中取出資料
        String storeName = getIntent().getStringExtra(EXTRA_STORE_NAME);
        String date = getIntent().getStringExtra(EXTRA_DATE);
        String time = getIntent().getStringExtra(EXTRA_TIME);
        int points = getIntent().getIntExtra(EXTRA_POINTS, 0); // 0 是預設值

        // 將資料設定到 TextView 上
        detailStoreName.setText(storeName);
        detailDate.setText("交易日期: " + date);
        detailTime.setText("交易時間: " + time);

        String pointsText = (points >= 0) ? "+" + points : String.valueOf(points);
        detailPoints.setText(pointsText);
    }
}