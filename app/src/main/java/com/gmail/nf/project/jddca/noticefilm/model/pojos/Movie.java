package com.gmail.nf.project.jddca.noticefilm.model.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {

    // Проверяет является ли фильм категорией для взрослых
    @SerializedName("adult")
    @Expose
    private Boolean adult;

    // Путь к фоновому изображению
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    // Относиться ли фильм к колекции
    @SerializedName("belongs_to_collection")
    @Expose
    private Object belongsToCollection;

    // Бюджет фильма
    @SerializedName("budget")
    @Expose
    private Integer budget;

    // Жанр фильма
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    // Домашняя страница
    @SerializedName("homepage")
    @Expose
    private String homepage;

    // ID фильма
    @SerializedName("id")
    @Expose
    private Integer id;

    // ID фильма на iMDb
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;

    // Язык оригинала
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    // Название фильма на языке оригинала
    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    // Обзор фильма
    @SerializedName("overview")
    @Expose
    private String overview;

    // Оценка фильма
    @SerializedName("popularity")
    @Expose
    private Double popularity;

    // Путь к постеру
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    // Список компаний которые выпустили фильм
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> productionCompanies = null;

    // Страны в которых снимался фильм
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> productionCountries = null;

    // Дата релиза
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    // Кассовые сборы
    @SerializedName("revenue")
    @Expose
    private Integer revenue;

    // Безпонятия что это может быть :)
    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    // Язык перевода
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spokenLanguages = null;

    // Статус фильма
    @SerializedName("status")
    @Expose
    private String status;

    // Слоган фильма
    @SerializedName("tagline")
    @Expose
    private String tagline;

    // Заголовок фильма - Название фильма
    @SerializedName("title")
    @Expose
    private String title;

    // Наличие видео к фильму
    @SerializedName("video")
    @Expose
    private Boolean video;

    // Средняя оценка по количеству проголосовавших
    @SerializedName("vote_average")
    @Expose
    private Integer voteAverage;

    // Всего проголосовало за фильм
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

}
