package com.example.nadavlotan.gameofthrones;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<FamilyMember> mDataSet;

    public ListAdapter(List<FamilyMember> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custome_view_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(this.mDataSet.get(i).getName());
        myViewHolder.imageView.setImageResource(mDataSet.get(i).getPicture());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * Sets my own customized viewHolder
     **/
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.familyName);
            imageView = itemView.findViewById(R.id.familyPic);
        }
    }
}

