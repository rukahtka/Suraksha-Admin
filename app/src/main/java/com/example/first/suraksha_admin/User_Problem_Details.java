package com.example.first.suraksha_admin;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by hp on 14/10/17.
 */

public class User_Problem_Details extends Fragment {
    TextView mNameText,mCollegeIDText,mAddressText,mMobileNoText,mParentNoText,mYearText,mBranchText,mDivText,mLocation,mProblemText,txt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lay_user_problem_details,null);
        mNameText= (TextView) view.findViewById(R.id.nameText);
        mCollegeIDText= (TextView) view.findViewById(R.id.collegeIDText);
        mAddressText= (TextView) view.findViewById(R.id.addressText);
        mMobileNoText= (TextView) view.findViewById(R.id.mobileNoText);
        mParentNoText= (TextView) view.findViewById(R.id.parentMobileNoText);
        mYearText= (TextView) view.findViewById(R.id.yearText);
        mBranchText= (TextView) view.findViewById(R.id.branchText);
        mDivText= (TextView) view.findViewById(R.id.divText);
        mLocation= (TextView) view.findViewById(R.id.locationText);
        mProblemText= (TextView) view.findViewById(R.id.problemText);

        Bundle bundle=getArguments();
        final Message message= (Message) bundle.getSerializable("data");
        mNameText.setText(message.getName());
        mCollegeIDText.setText(message.getCollegeID());
        mAddressText.setText(message.getAddress());
        mMobileNoText.setText(message.getMobileNo());
        mParentNoText.setText(message.getParentMobileNo());
        mBranchText.setText(message.getBranch());
        mYearText.setText(message.getYear());
        mDivText.setText(message.getDiv());

        mProblemText.setText(message.getProblem());
        registerForContextMenu(mMobileNoText);
        registerForContextMenu(mParentNoText);

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetOnMap mapFragment=new GetOnMap();
                Bundle bundle1=new Bundle();
                bundle1.putSerializable("data",message);
                mapFragment.setArguments(bundle1);
                getFragmentManager().beginTransaction().replace(R.id.content_frame,mapFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId()== mMobileNoText.getId()){
            menu.add(0,1,0,"MESSAGE to "+mMobileNoText.getText().toString());
            menu.add(0,2,1,"CALL to "+mMobileNoText.getText().toString());
        }
        if(v.getId()==mParentNoText.getId()){
            menu.add(1,3,0,"MESSAGE to "+mParentNoText.getText().toString());
            menu.add(1,4,1,"CALL to "+mParentNoText.getText().toString());
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==1){
            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.lay_sendmessagedialogbox);
            txt = (TextView) dialog.findViewById(R.id.dialogBoxTextView);

            Button btn = (Button) dialog.findViewById(R.id.dialogBoxSendBtn);
            dialog.setTitle("Send Message");
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txt.length()!=0){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(mMobileNoText.getText().toString(),null,txt.getText().toString(),null,null);
                        Toast.makeText(getContext(), "Sending Message to "+mMobileNoText.getText().toString()+".....", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else if(item.getItemId()==2){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mMobileNoText.getText().toString()));
            startActivity(callIntent);
        }

        else if(item.getItemId() == 3){
            final Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.lay_sendmessagedialogbox);
            txt = (TextView) dialog.findViewById(R.id.dialogBoxTextView);

            Button btn = (Button) dialog.findViewById(R.id.dialogBoxSendBtn);
            dialog.setTitle("Send Message");
            dialog.show();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txt.length()!=0){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(mParentNoText.getText().toString(),null,txt.getText().toString(),null,null);
                        Toast.makeText(getContext(), "Sending Message to "+mParentNoText.getText().toString()+".....", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else if(item.getItemId() ==4){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mParentNoText.getText().toString()));
            startActivity(callIntent);
        }


        return super.onContextItemSelected(item);
    }
}
