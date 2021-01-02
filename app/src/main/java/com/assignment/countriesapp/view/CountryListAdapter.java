package com.assignment.countriesapp.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.countriesapp.R;
import com.assignment.countriesapp.model.CountryModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder> {
    List<CountryModel> list;

    public CountryListAdapter(List<CountryModel> list) {
        this.list = list;
    }

    public void updateList(List<CountryModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.getCapitalName().setText(list.get(position).getCapital());
        holder.getCountryName().setText(list.get(position).getName());
        //holder.getCountryImage().setBackground(list.get(position).getFlag());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CountryListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.country_image)
        ImageView countryImage;
        @BindView(R.id.country_name)
        TextView countryName;
        @BindView(R.id.capital_name)
        TextView capitalName;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImage=itemView.findViewById(R.id.country_image);
            countryName=itemView.findViewById(R.id.country_name);
            capitalName=itemView.findViewById(R.id.capital_name);
        }

        public ImageView getCountryImage() {
            return countryImage;
        }

        public TextView getCountryName() {
            return countryName;
        }

        public TextView getCapitalName() {
            return capitalName;
        }
    }
}
