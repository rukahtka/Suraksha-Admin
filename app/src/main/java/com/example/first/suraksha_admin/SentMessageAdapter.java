package com.example.first.suraksha_admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 9/10/17.
 */

public class SentMessageAdapter extends RecyclerView.Adapter<SentMessageAdapter.DataHolder> {
    Context mContext;
    ArrayList<ProblemSet> mArrayList;

    public SentMessageAdapter(Context mContext, ArrayList<ProblemSet> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.lay_views_in_recyclerview,null);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        holder.mProblemText.setText(mArrayList.get(position).getProblem()+"\n"+mArrayList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView mProblemText;
        public DataHolder(View itemView) {
            super(itemView);
            mProblemText= (TextView) itemView.findViewById(R.id.problenCollegeID);;
        }
    }
}
