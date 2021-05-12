package com.ichef.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ichef.android.R;
import com.ichef.android.activity.Feedback;
import com.ichef.android.activity.FoodDetail;
import com.ichef.android.responsemodel.bookmarklist.Result;

import java.util.ArrayList;
import java.util.List;


public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private Context ctx;
    private List<Result> mlist;
    private ArrayList<Result> slist;
    LayoutInflater inflater;

    String id;

    public BookmarkAdapter(Context context, ArrayList<Result> list) {
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
     holder.drivername.setText(mlist.get(position).getFoodItemID());
     holder.branch.setText(mlist.get(position).getDatecreated());
     holder.vehicletype.setText("N150.00");
    // id =mlist.get(position).getIdUserPK();

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, FoodDetail.class);
            ctx.startActivity(intent);
        }
    });
    holder.star.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ctx, Feedback.class);
            ctx.startActivity(intent);
        }
    });
    holder.bookmarkred.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.bookmarkred.setVisibility(View.GONE);
            holder.bookmarkwhite.setVisibility(View.VISIBLE);
        }
    });
    holder.bookmarkwhite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.bookmarkred.setVisibility(View.VISIBLE);
            holder.bookmarkwhite.setVisibility(View.GONE);
        }
    });
    holder.share.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Your text");
            ctx.startActivity(Intent.createChooser(share, "Share using"));
        }
    });

}

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView drivername,branch,vehicletype;
    LinearLayout star;
    ImageView bookmarkred,bookmarkwhite,share;

    public ViewHolder(View itemView) {
        super(itemView);

        this.drivername = (TextView) itemView.findViewById(R.id.name);
        this.branch = (TextView) itemView.findViewById(R.id.resname);
        this.vehicletype = (TextView) itemView.findViewById(R.id.price);
        this.star = itemView.findViewById(R.id.star);
        this.bookmarkwhite= itemView.findViewById(R.id.bookmarkwhite);
        this.bookmarkred= itemView.findViewById(R.id.bookmarkred);
        this.share= itemView.findViewById(R.id.share);

    }
}
}
