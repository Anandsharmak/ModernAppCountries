package com.assignment.countriesapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    Single<List<CountryModel>> getCountries();
}
