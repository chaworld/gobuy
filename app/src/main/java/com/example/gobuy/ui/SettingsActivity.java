package com.example.gobuy.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gobuy.R;

public class SettingsActivity extends AppCompatActivity {

    // 1. 宣告所有需要互動的 UI 元件
    private ImageButton buttonBack;
    private SeekBar seekBarVolume;
    private TextView textViewVolumeProgress;
    private Switch switchVibration;
    private Switch switchHide;
    private Spinner spinnerLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonBack = findViewById(R.id.buttonBack);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        textViewVolumeProgress = findViewById(R.id.textViewVolumeProgress);
        switchVibration = findViewById(R.id.switchVibration);
        switchHide = findViewById(R.id.switchHide);
        spinnerLanguage = findViewById(R.id.spinnerLanguage);

        setupListeners();
        setupLanguageSpinner();
        updateProgressText(seekBarVolume.getProgress());
    }

    private void setupListeners() {
        // 返回按鈕
        buttonBack.setOnClickListener(v -> finish());

        // 音量 SeekBar
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateProgressText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { /* 暫不處理 */ }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { /* 暫不處理 */ }
        });

        // 震動 Switch
        switchVibration.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "開啟" : "關閉";
            Toast.makeText(this, "震動已" + status, Toast.LENGTH_SHORT).show();
        });

        // 隱藏 Switch
        switchHide.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "開啟" : "關閉";
            Toast.makeText(this, "隱藏功能已" + status, Toast.LENGTH_SHORT).show();
        });
    }

    // 調整語言 Spinner
    private void setupLanguageSpinner() {
        // 請確保您在 strings.xml 中使用的名稱是 language_options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);
    }

    // 更新音量文字
    private void updateProgressText(int progress) {
        // 請確保您在 strings.xml 中有定義 settings_volume_format
        String progressText = String.format(getString(R.string.settings_volume_format), progress, seekBarVolume.getMax());
        textViewVolumeProgress.setText(progressText);
    }
}