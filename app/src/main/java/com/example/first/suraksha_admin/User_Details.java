package com.example.first.suraksha_admin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 14/10/17.
 */

public class User_Details extends Fragment {
    TextView mNameText,mCollegeIDText,mAddressText,mMobileNoText,mParentNoText,mYearText,mBranchText,mDivText;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.lay_useralldetails,null);
        mNameText= (TextView) view.findViewById(R.id.nameText);
        mCollegeIDText= (TextView) view.findViewById(R.id.collegeIDText);
        mAddressText= (TextView) view.findViewById(R.id.addressText);
        mMobileNoText= (TextView) view.findViewById(R.id.mobileNoText);
        mParentNoText= (TextView) view.findViewById(R.id.parentMobileNoText);
        mYearText= (TextView) view.findViewById(R.id.yearText);
        mBranchText= (TextView) view.findViewById(R.id.branchText);
        mDivText= (TextView) view.findViewById(R.id.divText);
        imageView= (ImageView) view.findViewById(R.id.imageView);
        Bundle bundle=getArguments();
       Message message= (Message) bundle.getSerializable("data");
        mNameText.setText(message.getName());
        mCollegeIDText.setText(message.getCollegeID());
        mAddressText.setText(message.getAddress());
        mMobileNoText.setText(message.getMobileNo());
        mParentNoText.setText(message.getParentMobileNo());
        mBranchText.setText(message.getBranch());
        mYearText.setText(message.getYear());
        mDivText.setText(message.getDiv());
        if(message.getName().equals("Jojo")){
            imageView.setVisibility(View.VISIBLE);
            String str=message.getImage();
            byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }
        return view;
    }
}
