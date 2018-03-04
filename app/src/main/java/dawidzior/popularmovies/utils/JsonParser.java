package dawidzior.popularmovies.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dawidzior.popularmovies.model.Movie;

public class JsonParser {
    private static final String RESULTS = "results";
    private static final String TITLE = "title";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POSTER = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    public static List<Movie> getMoviesFromJson(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(RESULTS);

        ArrayList<Movie> popularMovieList = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonMovieObject = jsonArray.getJSONObject(i);
            Movie popularMovie = new Movie();
            popularMovie.setTitle(jsonMovieObject.optString(TITLE));
            popularMovie.setOriginalTitle(jsonMovieObject.optString(ORIGINAL_TITLE));
            popularMovie.setOverview(jsonMovieObject.optString(OVERVIEW));
            popularMovie.setPoster(TextUtils
                    .substring(jsonMovieObject.optString(POSTER), 1, jsonMovieObject.optString(POSTER).length()));
            popularMovie.setUserRating(jsonMovieObject.getDouble(VOTE_AVERAGE));
            popularMovie.setReleaseDate(jsonMovieObject.optString(RELEASE_DATE));

            popularMovieList.add(popularMovie);
        }
        return popularMovieList;
    }
}
