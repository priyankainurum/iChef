package com.ichef.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ichef.android.R;
import com.ichef.android.activity.FoodDetail;
import com.ichef.android.activity.HomePageActivity;
import com.ichef.android.activity.Login;
import com.ichef.android.activity.MyReward;
import com.ichef.android.responsemodel.homefood.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class HomeFoodAdapter extends RecyclerView.Adapter<HomeFoodAdapter.ViewHolder> {
    private Context ctx;
    private List<Result> mlist;
    private ArrayList<Result> slist;
    LayoutInflater inflater;

    String id;

    public HomeFoodAdapter(Context context, ArrayList<Result> list) {
        mlist = list;
        ctx = context;
        inflater = LayoutInflater.from(context);
        this.slist = new ArrayList<Result>();
        this.slist.addAll(mlist);
    }

    @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_food, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {

    // holder.drivername.setText(mlist.get(position).getFirstName()+" "+mlist.get(position).getLastName());
     holder.drivername.setText("Name of Food item");
     holder.branch.setText("Name of restaurent/chef");
     holder.vehicletype.setText("N150.00");
     id =mlist.get(position).getIdUserPK();

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, FoodDetail.class);
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
    public TextView drivername,branch,vehicletype;


    public ViewHolder(View itemView) {
        super(itemView);

        this.drivername = (TextView) itemView.findViewById(R.id.name);
        this.branch = (TextView) itemView.findViewById(R.id.resname);
        this.vehicletype = (TextView) itemView.findViewById(R.id.price);


    }
}
}
