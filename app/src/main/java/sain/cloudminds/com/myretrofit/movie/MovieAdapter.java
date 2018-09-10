package sain.cloudminds.com.myretrofit.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import sain.cloudminds.com.myretrofit.R;


/**
 * created by sain on 9/10/18
 */
public class MovieAdapter extends RecyclerView.Adapter{
    private List<Movie> mMovies;

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        MovieHolder movieHolder = (MovieHolder) holder;
        ImageLoader.getInstance().displayImage(movie.images.small, movieHolder.imageView);
        movieHolder.time.setText("上映时间："+movie.year + "年");
        movieHolder.title.setText(movie.title);
        movieHolder.subTitle.setText(movie.original_title);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView subTitle;
        public TextView time;
        public MovieHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
            title = itemView.findViewById(R.id.movie_title);
            subTitle = itemView.findViewById(R.id.movie_sub_title);
            time = itemView.findViewById(R.id.movie_time);

        }
    }

}
