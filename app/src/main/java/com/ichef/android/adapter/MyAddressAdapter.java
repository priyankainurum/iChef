package com.ichef.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ichef.android.MainActivity;
import com.ichef.android.R;
import com.ichef.android.activity.EditAddress;
import com.ichef.android.activity.FoodDetail;
import com.ichef.android.responsemodel.homefood.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {
    private Context ctx;
    private List<Result> mlist;
    private ArrayList<Result> slist;
    LayoutInflater inflater;

    String id;

    public MyAddressAdapter(Context context, ArrayList<Result> list) {
        mlist = list;
        ctx = context;
        inflater = LayoutInflater.from(context);
        this.slist = new ArrayList<Result>();
        this.slist.addAll(mlist);
    }

    @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_address, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {

    // holder.drivername.setText(mlist.get(position).getFirstName()+" "+mlist.get(position).getLastName());
     holder.name.setText("Home Location");
     holder.locationaddress.setText("ABC colony,xyz Nagar,city,state,country,pin");
    holder.phone.setText("Contatc Detail,09874651230");

     id =mlist.get(position).getIdUserPK();

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, EditAddress.class);
            ctx.startActivity(intent);
        }
    });
    holder.edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, EditAddress.class);
            ctx.startActivity(intent);
        }
    });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mlist.clear();
        if (charText.length() == 0) {
            mlist.addAll(slist);
        }
        else
        {
            for (Result wp : slist)
            {
                if (wp.getFirstName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name,locationaddress,phone;
    ImageView edit;


    public ViewHolder(View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.nameaddress);
        this.locationaddress = (TextView) itemView.findViewById(R.id.locationaddress);
        this.phone = (TextView) itemView.findViewById(R.id.phoneno);
        this.edit = (ImageView) itemView.findViewById(R.id.edit);


    }
}
}
