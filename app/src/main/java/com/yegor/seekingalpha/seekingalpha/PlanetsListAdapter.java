package com.yegor.seekingalpha.seekingalpha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

/**
 * Adapter for mail list view
 */
class PlanetsListAdapter extends RecyclerView.Adapter<PlanetViewHolder> {

    private Context mContext;
    private List<Planet> mPlanets = new LinkedList<>();
    private ItemSelectedListener mListener;

    PlanetsListAdapter(Context context, ItemSelectedListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_element, parent, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanetViewHolder holder, int position) {
        final Planet planet = mPlanets.get(position);
        holder.name.setText(planet.name);
        holder.description.setText(planet.description);
        Picasso.with(mContext)
                .load(planet.URL)
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error)
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemSelected(planet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlanets.size();
    }

    void addItems(List<Planet> planets) {
        mPlanets.addAll(planets);
        notifyDataSetChanged();
    }

    interface ItemSelectedListener {

        void onItemSelected(Planet planet);
    }
}
