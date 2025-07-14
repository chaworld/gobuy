package com.example.gobuy.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gobuy.R;
import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointViewHolder> {

    private List<PointItem> pointItems;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PointItem item);
    }
    //讓 Activity 可以設定監聽器
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //接收資料列表
    public PointsAdapter(List<PointItem> pointItems, Context context) {
        this.pointItems = pointItems;
        this.context = context;
    }

    //ViewHolder：用來存放單一列表項目的 View
    public static class PointViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStoreName, textViewDate, textViewTime, textViewPointsValue;
        public PointViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStoreName = itemView.findViewById(R.id.textViewStoreName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewPointsValue = itemView.findViewById(R.id.textViewPointsValue);
        }
    }

    //onCreateViewHolder：當 RecyclerView 需要新的 ViewHolder 時呼叫
    @NonNull
    @Override
    public PointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 載入 list_item_point.xml 這個版面
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_point, parent, false);
        return new PointViewHolder(view);
    }

    //onBindViewHolder：將資料綁定到 ViewHolder 上
    @Override
    public void onBindViewHolder(@NonNull PointViewHolder holder, int position) {
        // 取得目前位置的資料
        PointItem currentItem = pointItems.get(position);

        // 設定文字
        holder.textViewStoreName.setText(currentItem.getStoreName());
        holder.textViewDate.setText(currentItem.getTransactionDate());
        holder.textViewTime.setText(currentItem.getTransactionTime());

        // 格式化點數文字(讓正數的點在顯示時，自動在前面加上一個 + 號，變成 +650，而負數或零則維持原樣。)
        int points = currentItem.getPointsChange();
        String pointsText = (points > 0) ? "+" + points : String.valueOf(points);
        holder.textViewPointsValue.setText(pointsText);

        if (currentItem.getStoreName().isEmpty()) {
            // 如果是空白資料，就把所有 TextView 都設為空白
            holder.textViewStoreName.setText("");
            holder.textViewDate.setText("");
            holder.textViewTime.setText("");
            holder.textViewPointsValue.setText("");
        }

        // 處理一紅一白的效果
        if (position % 2 != 0) {
            // 奇數行
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            // 偶數行
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_red));
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(pointItems.get(position));
            }
        });
    }

    // getItemCount：回傳資料的總數
    @Override
    public int getItemCount() {
        return pointItems.size();
    }
}