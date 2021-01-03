package com.assignment.countriesapp;

import com.assignment.countriesapp.model.CountriesService;
import com.assignment.countriesapp.model.CountryModel;
import com.assignment.countriesapp.viewmodel.ListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.when;

public class ListViewModelTest {
    @Mock
    CountriesService service;
    @InjectMocks
    ListViewModel listViewModel=new ListViewModel();
    @Rule
    public InstantTaskExecutorRule rule=new InstantTaskExecutorRule();
    @Before
    public  void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    @Before
    public void setupRxSchedulers(){
        Scheduler immediate =new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
        RxJavaPlugins.setOnSingleSubscribe(null);
    }
    @Test
    public void getCountriesSuccess(){
        List<CountryModel> list=new ArrayList<CountryModel>();
        list.add(new CountryModel("India","Delhi",""));
        Single<List<CountryModel>> singleList=Single.just(list);

        when(service.getCountries()).thenReturn(singleList);

        listViewModel.refresh();
        Assert.assertEquals(1,listViewModel.country.getValue().size());
        Assert.assertEquals(listViewModel.countryLoadErr.getValue(),false);
        Assert.assertEquals(listViewModel.loading.getValue(),false);
    }

    @Test
    public void getCountriesError(){
        Single<List<CountryModel>> exception=Single.error(new Throwable());
        when(service.getCountries()).thenReturn(exception);
        try {
            listViewModel.refresh();
        }catch (Exception e){
        }
        Assert.assertEquals(listViewModel.countryLoadErr.getValue(),true);
        Assert.assertEquals(listViewModel.loading.getValue(),false);
    }
}
