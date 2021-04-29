package com.ichef.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ichef.android.R;
import com.ichef.android.activity.FoodDetail;
import com.ichef.android.responsemodel.homefood.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private Context ctx;
    private List<Result> mlist;
    private ArrayList<Result> slist;
    LayoutInflater inflater;

    String id;

    public ReviewAdapter(Context context, ArrayList<Result> list) {
        mlist = list;
        ctx = context;
        inflater = LayoutInflater.from(context);
        this.slist = new ArrayList<Result>();
        this.slist.addAll(mlist);
    }

    @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.listreview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {

    // holder.drivername.setText(mlist.get(position).getFirstName()+" "+mlist.get(position).getLastName());
     holder.name.setText("Name of Customer");
     holder.more.setText("Feedback of customer as per he has typed after received his meal");
    // holder.amount.setText("N150.00");
     id =mlist.get(position).getIdUserPK();

/*
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, FoodDetail.class);
            ctx.startActivity(intent);
        }
    });
*/


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
    public TextView name,more,amount;


    public ViewHolder(View itemView) {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.cusname);
        this.more = (TextView) itemView.findViewById(R.id.txreview);
      //  this.amount = (TextView) itemView.findViewById(R.id.amountcart);


    }
}
}
