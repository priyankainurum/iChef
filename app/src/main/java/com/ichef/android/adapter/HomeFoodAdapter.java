package com.ichef.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ichef.android.R;
import com.ichef.android.activity.Feedback;
import com.ichef.android.activity.FoodDetail;
import com.ichef.android.activity.HomePageActivity;
import com.ichef.android.activity.Login;
import com.ichef.android.activity.MyReward;
import com.ichef.android.requestmodel.addtocartrequest.AddtoCartRequest;
import com.ichef.android.requestmodel.markbookmark.MarkBookmarkRequest;
import com.ichef.android.responsemodel.addtocartresponse.AddtoCartResponse;
import com.ichef.android.responsemodel.homefood.Result;
import com.ichef.android.responsemodel.markbookmark.MarkBookmarkResponse;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;
import com.ichef.android.utils.Prefrence;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFoodAdapter extends RecyclerView.Adapter<HomeFoodAdapter.ViewHolder> {
    private Context ctx;
    private List<com.ichef.android.responsemodel.homefood.Result> mlist;
    private ArrayList<com.ichef.android.responsemodel.homefood.Result> slist;
    LayoutInflater inflater;

    String token;

    public HomeFoodAdapter(Context context, ArrayList<com.ichef.android.responsemodel.homefood.Result> list) {
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
     holder.branch.setText("Name of restaurant/chef");
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
            markAsBookmark();
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

    private void markAsBookmark() {
        token= Prefrence.get(ctx, Prefrence.KEY_TOKEN);
        MarkBookmarkRequest request = new MarkBookmarkRequest();
        request.setUserID("60882de62fb1f752c8069701");
        request.setFoodItemID("60857625c9107863c4ef683e");



        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<MarkBookmarkResponse> resultCall = apiInterface.Callmarkbookmark("Bearer " + token,request);
        resultCall.enqueue(new Callback<MarkBookmarkResponse>() {

            @Override
            public void onResponse(Call<MarkBookmarkResponse> call, retrofit2.Response<MarkBookmarkResponse> response) {

                if (response.body().getStatus().equals(true)) {

                    Toast.makeText(ctx,  ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MarkBookmarkResponse> call, Throwable t) {
                Toast.makeText(ctx, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
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
