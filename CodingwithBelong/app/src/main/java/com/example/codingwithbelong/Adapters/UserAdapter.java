package com.example.codingwithbelong.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.codingwithbelong.Model.User;
import com.example.codingwithbelong.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    ArrayList<User> studentDataList;
    Context context;
    int i = 4;
    public UserAdapter(Context context,ArrayList<User> studentDataList) {
        this.context = context;
        this.studentDataList=studentDataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User data=studentDataList.get(position);
       holder.rank.setText(""+i);
       i++;
        Glide.with(context).load(data.getPhoto())
                .placeholder(R.drawable.peson)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        holder.name.setText(data.getName());
        holder.score.setText(""+data.getScore());
        holder.time.setText(""+calculateTime(data.getTime()));
    }



    @Override
    public int getItemCount() {
        return studentDataList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rank,name,score,time;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            rank=itemView.findViewById(R.id.rankTv);
            image =itemView.findViewById(R.id.itemImage);
            name = itemView.findViewById(R.id.userNameTv);
            score = itemView.findViewById(R.id.scoreTv);
            time = itemView.findViewById(R.id.timeTv);

        }
    }
    public String calculateTime(int time)
    {
        String tTime = "";
        if(time < 60)
        {
            tTime = "0"+":"+"0"+":"+time;
        }else if(time < 3600)
        {
            int minutes = time / 60 ;
            int second = time % 60;
            tTime = "0"+":"+minutes+":"+second;
        }else{
            int hour = time / 3600;
            int s = time % 3600;
            int minutes = s / 60 ;
            int second = s % 60;
            tTime = hour+":"+minutes+":"+second;
        }

        return  tTime;
    }
}
