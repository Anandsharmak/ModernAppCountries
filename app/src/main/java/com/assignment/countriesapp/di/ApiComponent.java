package com.assignment.countriesapp.di;

import com.assignment.countriesapp.model.CountriesApi;
import com.assignment.countriesapp.model.CountriesService;
import com.assignment.countriesapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = ApiModule.class)
public interface ApiComponent {
    void inject(CountriesService service);

    void inject(ListViewModel viewModel);
}
