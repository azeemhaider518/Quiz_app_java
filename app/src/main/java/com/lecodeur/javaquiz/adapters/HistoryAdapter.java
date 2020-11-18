package com.lecodeur.javaquiz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecodeur.javaquiz.R;
import com.lecodeur.javaquiz.model.History;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {



    private Context context;
    private LayoutInflater layoutInflater;
    private List<History> historyList;


    public HistoryAdapter(List<History> historyList, Context context) {

        this.historyList = historyList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.tvName.setText(historyList.get(position).name);
        holder.tvDate.setText(historyList.get(position).date);
        holder.tvScore.setText("Score:"+historyList.get(position).score);


    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvScore)
        TextView tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}