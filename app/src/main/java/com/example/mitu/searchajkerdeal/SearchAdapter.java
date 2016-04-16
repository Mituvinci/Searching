package com.example.mitu.searchajkerdeal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mitu on 4/10/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<SearchDeals> mSearchDealses;
    private ArrayList<SearchCategories> mSearchCategories;
    private ArrayList<SearchMerchants> mSearchMerchantses;

    private static MyClickListener myClickListener;
    private Context mcontext;
    private int mdealId;

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView crazyDealImage;
        TextView dealId;
        TextView dealTitle;
        TextView dealPrice;
        TextView accountsTitle;

        public DataObjectHolder(View itemView) {
            super(itemView);

            crazyDealImage = (ImageView) itemView.findViewById(R.id.imageView_1);
            dealId = (TextView) itemView.findViewById(R.id.dealid);
            dealTitle = (TextView) itemView.findViewById(R.id.dealtitle);
            dealPrice = (TextView) itemView.findViewById(R.id.dealPrice);
            accountsTitle = (TextView) itemView.findViewById(R.id.accounttitle);


            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public SearchAdapter(ArrayList<SearchDeals > searchDealses,ArrayList<SearchCategories > searchCategories, Context applicationContext) {
        mSearchDealses = searchDealses;
        mSearchCategories = searchCategories;
       // mSearchMerchantses = searchMerchantses;
        mcontext = applicationContext;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder,final int position) {

       /* holder.dealId.setText(mSearchDealses.get(position).getDealId()+"");
        holder.dealTitle.setText(mSearchDealses.get(position).getDealTitle()+"");
        holder.dealPrice.setText(mSearchDealses.get(position).getDealPrice()+"");
        holder.dealId.setText(mSearchDealses.get(position).getDealId()+"");
        Picasso.with(mcontext).load(mSearchDealses.get(position).getmImage()).placeholder(R.drawable.placeholder).into(holder.crazyDealImage);
*/

        holder.dealTitle.setText(mSearchCategories.get(position).getCategory()+"");

    }

    @Override
    public int getItemCount() {
        /*if(mDataset != null) {
            return mDataset.size();
        } else {
            return 0;
        }*/
        return mSearchDealses.size();
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
