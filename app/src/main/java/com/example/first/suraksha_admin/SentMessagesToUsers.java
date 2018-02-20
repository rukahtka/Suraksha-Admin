package com.example.first.suraksha_admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hp on 10/10/17.
 */


public class SentMessagesToUsers extends Fragment {
    RecyclerView mRecyclerView;
    SentMessageAdapter sentMessageAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ProblemSet> problemArrayList;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.resolved_layout,null);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        db=new SentMessageDataBaseOpenHelper(getContext(),"db_problemDataBase",null,1).getWritableDatabase();
        problemArrayList=new ArrayList<>();
        sentMessageAdapter=new SentMessageAdapter(getContext(),problemArrayList);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(sentMessageAdapter);
        populate();
        return view;
    }




    public void populate() {

        problemArrayList.clear();

        Cursor c = db.query("SentMessageTable", null, null, null, null, null, null);

        while( c.moveToNext() ) {
            problemArrayList.add(
                    new ProblemSet(
                            c.getString(0),c.getString(1)
                    )
            );
        }
        sentMessageAdapter.notifyDataSetChanged();
    }
}