package com.gmail.nf.project.jddca.noticefilm;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.UpcomingMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ApiService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        packageName = "com.gmail.nf.project.jddca.noticefilm",
        resourceDir = "res")
public class UpcomingMovieTest {

    private UpcomingMovieServiceImpl service;
    private MainActivity activity;
    private String key;
    private String lang;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        service = new UpcomingMovieServiceImpl(RetrofitService.getRetrofit());
        key = ApiService.getApiKey(activity.getApplicationContext());
        lang = ApiService.getLocales(activity.getApplicationContext());
    }

    @Test
    public void getActivity_shouldReturnNotEmptyActivity() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void getUpcomingFilmsList_shouldReturnNotEmptyList() throws Exception {
        Observable<UpcomingMovie> observable = service.getUpcomingMovie(key, lang);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(UpcomingMovie::getResults)
                .subscribe(films -> assertNotNull(films.get(0)));
    }

    @Test
    public void getUpcomingFilmsList_shouldReturnEqualFirstItem() throws Exception {
        Observable<UpcomingMovie> observable = service.getUpcomingMovie(key, lang);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(UpcomingMovie::getResults)
                .subscribe(films -> assertEquals(films.get(0).getTitle(), "Guardians of the Galaxy Vol. 2"));
    }

}
