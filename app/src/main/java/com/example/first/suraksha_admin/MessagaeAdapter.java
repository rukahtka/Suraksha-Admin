package com.example.first.suraksha_admin;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 10/10/17.
 */

public class MessagaeAdapter extends RecyclerView.Adapter<MessagaeAdapter.DataHolder> {

    interface OnRecyclerViewItenClickListener {
        public void onClick(View view, int position);
    }

    Context mContext;
    ArrayList<Message> messageArrayList;

    OnRecyclerViewItenClickListener mOnRecyclerViewItenClickListener;
    int mode;

    public MessagaeAdapter(Context mContext, ArrayList<Message> messageArrayList) {
        this.mContext = mContext;
        this.messageArrayList = messageArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lay_views_in_recyclerview, null);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        holder.txt1.setText("CollegeID: " + messageArrayList.get(position).getCollegeID() + "\n" + "Name: " + messageArrayList.get(position).getName());
        if (messageArrayList.get(position).getProblem() != null) {
            holder.txt1.append("\n" + "Message: " + messageArrayList.get(position).getProblem());
            if(messageArrayList.get(position).getProblem().trim() == new String("I need help").trim()){
                holder.txt1.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt1;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public DataHolder(View itemView) {
            super(itemView);
            txt1 = (TextView) itemView.findViewById(R.id.problenCollegeID);
             itemView.setOnClickListener(this);
        }

   @Override
        public void onClick(View v) {
            mOnRecyclerViewItenClickListener.onClick(v,getPosition());
        }

    }
    public void setOnRecyclerViewClickListener(OnRecyclerViewItenClickListener onRecyclerViewClickListener){
        mOnRecyclerViewItenClickListener=onRecyclerViewClickListener;
    }


    }


