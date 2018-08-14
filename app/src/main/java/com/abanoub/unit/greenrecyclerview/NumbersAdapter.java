package com.abanoub.unit.greenrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>{


    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class NumberViewHolder extends RecyclerView.ViewHolder{

        public NumberViewHolder(View itemView) {
            super(itemView);
        }
    }
}
