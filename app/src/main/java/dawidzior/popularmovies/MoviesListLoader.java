package dawidzior.popularmovies;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

import dawidzior.popularmovies.model.Movie;
import dawidzior.popularmovies.utils.JsonParser;
import dawidzior.popularmovies.utils.NetworkUtils;

public class MoviesListLoader extends AsyncTaskLoader<List<Movie>> {

    private String sortBy;

    public MoviesListLoader(Context context, Bundle bundle) {
        super(context);
        sortBy = bundle.getString(MainActivity.SORT_BY_KEY);
    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> moviesList = null;
        try {
            String requestJson = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildTheMovieDbUrl(sortBy));
            moviesList = JsonParser.getMoviesFromJson(requestJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesList;
    }
}
