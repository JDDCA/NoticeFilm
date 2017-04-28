//package com.gmail.nf.project.jddca.noticefilm.presenter;
//
//import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;
//import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieService;
//import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieServiceImpl;
//import com.gmail.nf.project.jddca.noticefilm.model.utils.ApiService;
//import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
//import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
//import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragment2Impl;
//
//import java.util.Random;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import lombok.experimental.PackagePrivate;
//
//@PackagePrivate
//public class GeneratePresenter implements Presenter {
//
//    private final String TAG = getClass().getSimpleName();
//
//    private MovieView view;
//    private GenerateMovieService service;
//    private GenerateFragment2Impl generateFragmentImpl;
//
//    public GeneratePresenter(MovieView view) {
//        service = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
//        this.view = view;
//    }
//
//    public void initSpinner() {
////        GenerateMovieService service = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
//        service.getGenres(ApiService.getApiKey(generateFragmentImpl.getContext()), ApiService.getLocales(generateFragmentImpl.getContext()))
//                .subscribeOn(Schedulers.io()).take(1)
//                .map(genres -> genres.getGenres().get(new Random().nextInt(genres.getGenres().size())))
//                .subscribe(
//                        genre -> service.getPages(Integer.toString(genre.getId()),
//                                ApiService.getApiKey(generateFragmentImpl.getContext()),
//                                ApiService.getLocales(generateFragmentImpl.getContext()))
//                                .take(1)
//                                .subscribe(
//                                        pages -> service.getPage(Integer.toString(genre.getId()),
//                                                ApiService.getApiKey(generateFragmentImpl.getContext()),
//                                                ApiService.getLocales(generateFragmentImpl.getContext()),RetrofitService.INCLUDE_ABULT,
//                                                new Random().nextInt(pages.getTotalPages()))
//                                                .take(1)
//                                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
//                                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribe(view::showMovie, view::showError)
//                                        , view::showError)
//                        , view::showError);
//    }
//
//    public void adviceFilm(Genre genre) {
//        if (genre.getId() != RetrofitService.RANDOM_FILM) {
//            getFilm(genre.getId());
//        } else {
//            getRandomFilm(ApiService.getApiKey(generateFragmentImpl.getContext()), ApiService.getLocales(generateFragmentImpl.getContext()));
//        }
//    }
//
//
//    private void getRandomFilm(String key, String lang) {
////        GenerateMovieService service = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
//        service.getGenres(key, lang)
//                .subscribeOn(Schedulers.io()).take(1)
//                .map(genres -> genres.getGenres().get(new Random().nextInt(genres.getGenres().size())))
//                .subscribe(
//                        genre -> service.getPages(Integer.toString(genre.getId()), key, lang).take(1)
//                                .subscribe(
//                                        pages -> service.getPage(Integer.toString(genre.getId()), key, lang,RetrofitService.INCLUDE_ABULT,
//                                                new Random().nextInt(pages.getTotalPages())).take(1)
//                                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
//                                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
//                                                .observeOn(AndroidSchedulers.mainThread())
//                                                .subscribe(view::showMovie, view::showError)
//                                        , view::showError)
//                        , view::showError);
//    }
//
//    private void getFilm(Integer id) {
////        GenerateMovieService service = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
//        service.getPages(Integer.toString(id), ApiService.getApiKey(generateFragmentImpl.getContext()), ApiService.getLocales(generateFragmentImpl.getContext()))
//                .subscribeOn(Schedulers.io()).take(1)
//                .subscribe(
//                        pages -> service.getPage(Integer.toString(id),
//                                ApiService.getApiKey(generateFragmentImpl.getContext()),
//                                ApiService.getLocales(generateFragmentImpl.getContext()),RetrofitService.INCLUDE_ABULT,
//                                new Random().nextInt(pages.getTotalPages())).take(1)
//                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
//                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(view::showMovie, view::showError)
//                        , view::showError);
//    }
//
//}
