package com.mythio.movii.model.movie;

import com.google.gson.annotations.SerializedName;
import com.mythio.movii.model.cast.Credits;
import com.mythio.movii.model.collection.Collection;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.video.VideoResponse;

import java.util.ArrayList;

public class MovieTmdb {

    @SerializedName("belongs_to_collection")
    private Collection collection;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("videos")
    private VideoResponse videoResponse;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("runtime")
    private Integer runtime;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("imdb_id")
    private String imdb;

    @SerializedName("genres")
    private ArrayList<Genre> genres = new ArrayList<>();

    @SerializedName("recommendations")
    private MovieResponse recommendations;

    @SerializedName("credits")
    private Credits credits;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public MovieResponse getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(MovieResponse recommendations) {
        this.recommendations = recommendations;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }
}
