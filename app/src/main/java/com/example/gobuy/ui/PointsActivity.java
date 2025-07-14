package com.example.gobuy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.gobuy.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class PointsActivity extends AppCompatActivity implements PointsAdapter.OnItemClickListener{

    private RecyclerView recyclerViewPoints;
    private PointsAdapter pointsAdapter;
    private List<PointItem> pointItemList;
    private Spinner spinnerPointsRange;
    private ImageButton buttonBack;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);

        //初始化 RecyclerView
        recyclerViewPoints = findViewById(R.id.recyclerViewPoints);
        //初始化 SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        //初始化 Spinner
        spinnerPointsRange = findViewById(R.id.spinnerPointsRange);
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());

        //建立資料
        createSampleData();
        //設定 RecyclerView
        setupRecyclerView();
        //設定 Spinner
        setupPointsSpinner();
        // 設定下拉更新的監聽器
        setupSwipeRefreshListener();
    }

    private void setupRecyclerView() {
        // 建立 Adapter 並傳入資料
        pointsAdapter = new PointsAdapter(pointItemList, this);
        //Adapter 由 PointsActivity 本身來處理點擊事件。
        pointsAdapter.setOnItemClickListener(this);
        // 設定 RecyclerView 的佈局管理器
        recyclerViewPoints.setLayoutManager(new LinearLayoutManager(this));

        // 設定 Adapter
        recyclerViewPoints.setAdapter(pointsAdapter);
    }
    @Override
    public void onItemClick(PointItem item) {
        //建立一個 Intent，準備跳轉到 PointDetailActivity
        Intent intent = new Intent(this, PointDetailActivity.class);

        //使用 "putExtra" 方法，將被點擊項目的資料打包進 Intent
        intent.putExtra(PointDetailActivity.EXTRA_STORE_NAME, item.getStoreName());
        intent.putExtra(PointDetailActivity.EXTRA_DATE, item.getTransactionDate());
        intent.putExtra(PointDetailActivity.EXTRA_TIME, item.getTransactionTime());
        intent.putExtra(PointDetailActivity.EXTRA_POINTS, item.getPointsChange());

        //執行
        startActivity(intent);
    }

    // 新增：設定 SwipeRefreshLayout 的監聽器
    private void setupSwipeRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // 當下拉更新被觸發時執行的程式碼
            //更新
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                //一定要更新資料
                //打亂
                //建立一個新的臨時 List，只包含原始列表的前三個項目
                List<PointItem> subList = new ArrayList<>(pointItemList.subList(0, 3));

                //使用 Collections.shuffle() 來隨機打亂這個只含三筆資料的臨時 List
                java.util.Collections.shuffle(subList);

                //將打亂後的結果，依序放回原始 pointItemList 的前三個位置
                for (int i = 0; i < 3; i++) {
                    pointItemList.set(i, subList.get(i));
                }
                pointsAdapter.notifyDataSetChanged();
                //告訴 SwipeRefreshLayout 刷新已結束，隱藏旋轉圖示
                swipeRefreshLayout.setRefreshing(false);
            },1000); //延遲兩秒
        });
    }

    private void setupPointsSpinner() {
        // 建立 ArrayAdapter，並從資源檔讀取資料
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.points_range_options, // <<<--- 檢查重點：這裡的名稱是否和 strings.xml 中的 name 一致？
                android.R.layout.simple_spinner_item
        );

        //設定下拉選單的樣式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 將 adapter 套用到 spinner 元件上
        spinnerPointsRange.setAdapter(adapter);
    }
    private void createSampleData() {
        pointItemList = new ArrayList<>();
        // 根據要求建立三筆資料
        // 建立一個店家名稱的陣列
        String[] stores = {"吃飽屋", "草本屋", "夢想家"};

        // 迴圈三次，為每個店家產生一筆隨機資料
        for (String storeName : stores) {
            // 產生隨機日期 (年份 2024-2025, 月份 1-12, 日期 1-28)
            int year = 2024 + random.nextInt(2);
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(28);
            String randomDate = String.format(Locale.TAIWAN, "%d/%02d/%02d", year, month, day);

            // 產生隨機時間 (小時 0-23, 分鐘 0-59)
            int hour = random.nextInt(24);
            int minute = random.nextInt(60);
            String randomTime = String.format(Locale.TAIWAN, "%02d:%02d", hour, minute);

            // 產生隨機點數 (100 到 999 之間)
            int randomPoints = 100 + random.nextInt(900);

            // 將這筆包含隨機數值的資料加入列表
            pointItemList.add(new PointItem(storeName, randomDate, randomTime, randomPoints));
        }
        for (int i = 0; i <=20; i++) {
                pointItemList.add(new PointItem("", "", "",0));
        }
    }
}