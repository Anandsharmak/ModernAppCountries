package com.assignment.countriesapp.model;

import com.assignment.countriesapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {

    public static CountriesService instance;
    @Inject
    public CountriesApi api;

    private CountriesService() {
        DaggerApiComponent.create().inject(this);
    }

    public static CountriesService getInstance(){
        if(instance==null)
            instance=new CountriesService();
        return instance;
    }
    public Single<List<CountryModel>> getCountries(){
        return api.getCountries();
    }
}
