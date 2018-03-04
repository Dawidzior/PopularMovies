package dawidzior.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import dawidzior.popularmovies.model.Movie;
import dawidzior.popularmovies.utils.NetworkUtils;

public class DetailsActivity extends AppCompatActivity {

    public static final String MOVIE_BUNDLE_KEY = "MOVIE_BUNDLE_KEY";

    private Movie movie;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.details_poster_view)
    protected ImageView detailsPoster;

    @BindView(R.id.details_original_title)
    protected TextView originalTitle;

    @BindView(R.id.details_rating_bar)
    protected RatingBar ratingBar;

    @BindView(R.id.details_release_date)
    protected TextView releaseDate;

    @BindView(R.id.overview)
    protected TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Fix shared element transition blinking.

        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            movie = getIntent().getParcelableExtra(MOVIE_BUNDLE_KEY);
        } else {
            movie = savedInstanceState.getParcelable(MOVIE_BUNDLE_KEY);
        }

        setTitle(movie.getTitle());

        detailsPoster.setTransitionName(MainActivity.SHARED_ELEMENT);

        //w500 to maintain image quality.
        Picasso.with(this).load(NetworkUtils.IMAGE_BASE_URL + "w500/" + movie.getPoster()).into(detailsPoster);
        originalTitle.setText(movie.getOriginalTitle());
        ratingBar.setRating(Float.valueOf(movie.getUserRating().toString()));
        releaseDate.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(MOVIE_BUNDLE_KEY, movie);
        super.onSaveInstanceState(outState);
    }
}
