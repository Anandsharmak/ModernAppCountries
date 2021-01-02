package com.assignment.countriesapp.viewmodel;

import com.assignment.countriesapp.model.CountryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> country=new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadErr=new MutableLiveData<>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<>();

    public void refresh() {
        fetchData();
    }

    private void fetchData() {
        CountryModel country1 = new CountryModel("India", "Delhi", "");
        CountryModel country2 = new CountryModel("Nepal", "Kathmandu", "");
        CountryModel country3 = new CountryModel("Bang", "kL", "");

        List<CountryModel> list = new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country3);
        country.setValue(list);
        countryLoadErr.setValue(false);
        loading.setValue(false);
    }
}