package com.assignment.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {
    public static String BASE_URL="https://raw.githubusercontent.com";
    public static CountriesService instance;
    public CountriesApi api= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi.class);

    private CountriesService() {
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
