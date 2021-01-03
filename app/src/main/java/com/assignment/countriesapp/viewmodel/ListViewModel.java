package com.assignment.countriesapp.viewmodel;

import android.util.Log;

import com.assignment.countriesapp.di.DaggerApiComponent;
import com.assignment.countriesapp.model.CountriesService;
import com.assignment.countriesapp.model.CountryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> country=new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadErr=new MutableLiveData<>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<>();
    @Inject
    public CountriesService service;
    public CompositeDisposable disposable=new CompositeDisposable();

    public ListViewModel() {
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchData();
    }

    private void fetchData() {
        loading.setValue(true);
        disposable.add(
            service.getCountries()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                        @Override
                        public void onSuccess(List<CountryModel> countryModels) {
                            countryLoadErr.setValue(false);
                            loading.setValue(false);
                            country.setValue(countryModels);
                        }

                        @Override
                        public void onError(Throwable e) {
                            countryLoadErr.setValue(true);
                            loading.setValue(false);
                            Log.e(this.getClass().toString(),"Error "+e);
                            e.printStackTrace();
                        }
                    })
            );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}






