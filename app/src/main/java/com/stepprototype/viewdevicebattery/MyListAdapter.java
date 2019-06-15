package com.stepprototype.viewdevicebattery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<DeviceInfo> {


    private Context mContext;
    private int mResource;

    private TextView mDeviceNameTextView;
    private TextView mBattPercentageTextView;
    private ProgressBar mBattPercentageProgressBar;
    private int mDevicePercentage;

    public MyListAdapter(Context context, int resource, ArrayList<DeviceInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String deviceName = getItem(position).deviceInfo;
        String batteryPercentage = getItem(position).batteryPercentage.toString();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        mDeviceNameTextView = (TextView) convertView.findViewById(R.id.deviceName_text_view);
        mBattPercentageTextView = (TextView) convertView.findViewById(R.id.percentage_text_view);
        mBattPercentageProgressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        mDeviceNameTextView.setText(deviceName);
        mBattPercentageTextView.setText(batteryPercentage + "%");
        mBattPercentageProgressBar.setProgress(Integer.parseInt(batteryPercentage));

        return convertView;

    }
}
