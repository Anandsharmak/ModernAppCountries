package com.assignment.countriesapp.view;

import android.text.Layout;
import android.util.Log;
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
    String TAG="CountryListAdapter";
    public CountryListAdapter(List<CountryModel> list) {
        this.list = list;
    }

    public void updateList(List<CountryModel> list) {
        Log.d(TAG,"Update list called "+this.list.size()+"new size"+list.size());
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
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
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

        void bind(CountryModel countryModel){
            Utils.getImage(countryImage,countryModel.getFlag(),
                    Utils.getProgressDrawable(countryImage.getContext()));
            countryName.setText(countryModel.getName());
            capitalName.setText(countryModel.getCapital());
        }
    }
}
