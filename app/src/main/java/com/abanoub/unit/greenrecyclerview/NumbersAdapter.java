package com.abanoub.unit.greenrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>{

    private static int viewHolderCount;

    private int mNumberItems;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener{
        void onListItemClick(int id);
    }

    /**
     * Constructor for NumbersAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberItems Number of items to display in list
     */
    public NumbersAdapter(int numberItems, ListItemClickListener onClickListener){
        this.mNumberItems = numberItems;
        viewHolderCount = 0;
        mOnClickListener = onClickListener;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        NumberViewHolder numberViewHolder = new NumberViewHolder(view);

        numberViewHolder.viewHolderIndex.setText("ViewHolder index:" + viewHolderCount);
        int color = MaterialColor.getBackgroundForViewHolder(context, viewHolderCount);
        numberViewHolder.itemView.setBackgroundColor(color);
        viewHolderCount++;
        Log.d(this.toString(), "count :" + viewHolderCount);
        return numberViewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.numberListItem.setText(String.valueOf(position));
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    /**
     * Cache of the children views for a list item.
     */
    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView numberListItem;

        // Will display the index of view holder
        TextView viewHolderIndex;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         * {@link NumbersAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);

            numberListItem = itemView.findViewById(R.id.tv_number);

            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance);

            itemView.setOnClickListener(this);
        }

        /**
         * Called whenever a user clicks on an item in the list.
         * @param view The View that was clicked
         */
        @Override
        public void onClick(View view) {
            int id = getAdapterPosition();
            mOnClickListener.onListItemClick(id);
        }
    }
}
