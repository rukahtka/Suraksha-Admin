package com.example.first.suraksha_admin;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class AllUserDetails_Fragment extends Fragment {
    RecyclerView mRecyclerView;
    MessagaeAdapter messageAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Message> problemArrayList;
    String url;
    RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lay_alluserdetailsfragment,null);
        url="http://akthakur0422.000webhostapp.com/fetchData.php";
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rproblemFragmentRecyclerView);
        problemArrayList=new ArrayList<>();
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        messageAdapter=new MessagaeAdapter(getContext(),problemArrayList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(messageAdapter);
        requestQueue= Volley.newRequestQueue(getContext());

        messageAdapter.setOnRecyclerViewClickListener(new MessagaeAdapter.OnRecyclerViewItenClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",problemArrayList.get(position));
                User_Details user_details=new User_Details();
                user_details.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.content_frame,user_details).addToBackStack(null).commit();
            }
        });
        getAllUsers();
        return view;
    }


    private void getAllUsers() {


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
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
                        if(jsonObject.getString("name").equals("Jojo")){
                            m.setImage(jsonObject.getString("image"));
                        }
                        m.setMobileNo(jsonObject.getString("mobileNo"));
                        m.setParentMobileNo(jsonObject.getString("parentNo"));
                        m.setAddress(jsonObject.getString("address"));
                        m.setProblem(null);
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
