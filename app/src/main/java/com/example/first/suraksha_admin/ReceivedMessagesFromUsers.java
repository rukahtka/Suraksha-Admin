package com.example.first.suraksha_admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hp on 10/10/17.
 */

public class ReceivedMessagesFromUsers extends Fragment {

    RecyclerView mRecyclerView;
    MessagaeAdapter messageAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Message> problemArrayList;
    String url;
    RequestQueue requestQueue;

    @Nullable

    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.unresolved_layout,container,false);
        url="https://akthakur0422.000webhostapp.com/fetchproblemsofusers.php";
        mRecyclerView= (RecyclerView) view.findViewById(R.id.problemrecycler);
        problemArrayList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        messageAdapter=new MessagaeAdapter(getContext(),problemArrayList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(messageAdapter);
        requestQueue= Volley.newRequestQueue(getContext());
      //  registerForContextMenu(mRecyclerView);
        getAllUsers();
        messageAdapter.setOnRecyclerViewClickListener(new MessagaeAdapter.OnRecyclerViewItenClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",problemArrayList.get(position));
                User_Problem_Details user_details=new User_Problem_Details();
                user_details.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.content_frame,user_details).addToBackStack(null).commit();
            }
        });

        return view;
    }

   /* @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"Mark as solvedd");
        menu.add(0,2,1,"Go to location");
        super.onCreateContextMenu(menu, v, menuInfo);
    }*/

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1){
            Toast.makeText(getContext(), "you selected: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId()==2){
            Toast.makeText(getContext(), "you selected: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    private void getAllUsers() {


        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray=response.getJSONArray("result");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Message m=new Message();
                        m.setCollegeID(jsonObject.getString("collegeID"));
                        m.setName(jsonObject.getString("name"));
                        m.setBranch(jsonObject.getString("branch"));
                        m.setYear(jsonObject.getString("year"));
                        m.setDiv(jsonObject.getString("division"));
                       m.setAddress(jsonObject.getString("address"));
                        m.setProblem(jsonObject.getString("problem"));
                        m.setMobileNo(jsonObject.getString("mobileNo"));
                        m.setParentMobileNo(jsonObject.getString("parentNo"));
                        m.setLat(jsonObject.getDouble("lattitude"));
                        m.setLng(jsonObject.getDouble("longitude"));
                        problemArrayList.add(m);
                        messageAdapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("Successful","Value parsed");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Unsuccessful","Value not parsed"+error);
            }
        });

        requestQueue.add(jsonObjectRequest);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));




    }



}
