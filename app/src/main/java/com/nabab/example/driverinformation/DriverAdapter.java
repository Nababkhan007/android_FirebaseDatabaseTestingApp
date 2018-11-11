package com.nabab.example.driverinformation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private Context context;
    private List<DriverInformation> driverInformationList;

    public DriverAdapter(Context context, List<DriverInformation> driverInformationList) {
        this.context = context;
        this.driverInformationList = driverInformationList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.driver_information_view_design, null);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DriverViewHolder driverViewHolder, int i) {
        final DriverInformation itemId = driverInformationList.get(i);
        driverViewHolder.viewFullNameTv.setText(itemId.getFirstName() +" "+ itemId.getLastName());
        driverViewHolder.viewAddressTv.setText(itemId.getAddress());
        driverViewHolder.viewEmailTv.setText(itemId.getEmail());
        driverViewHolder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = itemId.getMobileNumber();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+mobileNumber));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 3);
                    return;
                }
                if (callIntent.resolveActivity(context.getPackageManager())!= null){
                    context.startActivity(callIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverInformationList.size();
    }

    public class DriverViewHolder extends RecyclerView.ViewHolder{
        private TextView viewFullNameTv, viewAddressTv, viewEmailTv;
        private Button callBtn;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            viewFullNameTv = itemView.findViewById(R.id.viewFullNameTvId);
            viewAddressTv = itemView.findViewById(R.id.viewAddressTvId);
            viewEmailTv = itemView.findViewById(R.id.viewEmailTvId);
            callBtn = itemView.findViewById(R.id.callBtnId);
        }
    }
}
