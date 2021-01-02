package com.assignment.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.countriesapp.R;
import com.assignment.countriesapp.model.CountryModel;
import com.assignment.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.loading_error)
    TextView loadingError;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ListViewModel listViewModel;
    CountryListAdapter adapter=new CountryListAdapter(new ArrayList<CountryModel>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setAdapter(adapter);
        listViewModel= ViewModelProviders.of(this).get(ListViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listViewModel.refresh();

        observeViewModel();


    }

    private void observeViewModel() {
        listViewModel.country.observe(this, countryModels -> {
        if(countryModels!=null)
            adapter.updateList(countryModels);
        });

        listViewModel.countryLoadErr.observe(this, error -> {
            if(error!=null)
                loadingError.setVisibility(error? View.VISIBLE:View.GONE);
        });

        listViewModel.loading.observe(this, loading -> {
            if(loading!=null)
                loadingView.setVisibility(loading? View.VISIBLE:View.GONE);
        });
    }
}