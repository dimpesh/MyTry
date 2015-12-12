package com.movie.app.mytry;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieObject {

    String title;
    String poster_path;
    String overview;
    String vote_average;
    String release_date;

public MovieObject()
{}

    public MovieObject(String title,String poster_path,String overview,String vote_average,String release_date)
    {
        this.title=title;
        this.poster_path=poster_path;
        this.overview=overview;
        this.vote_average=vote_average;
        this.release_date=release_date;
    }
}

