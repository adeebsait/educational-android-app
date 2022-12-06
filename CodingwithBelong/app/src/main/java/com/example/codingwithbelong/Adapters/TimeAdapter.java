package com.example.codingwithbelong.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codingwithbelong.Model.LineGraphData;
import com.example.codingwithbelong.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private ArrayList<LineGraphData> dataset;
    private Context context;

    public TimeAdapter(Context context){
        this.context=context;
    }

    public void setData(ArrayList<LineGraphData> dataset){
        this.dataset=dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date=new Date(dataset.get(position).getDate());
        long time=dataset.get(position).getTotalTime();
        String t=String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))
        );
        holder.date.setText("Date: "+date);
        holder.time.setText("Time: "+t);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, time;

        ViewHolder(View view){
            super(view);
            date=view.findViewById(R.id.date);
            time=view.findViewById(R.id.time);
        }

    }

}
