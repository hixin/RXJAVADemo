package sain.cloudminds.com.myretrofit.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import rx.functions.Action1;
import sain.cloudminds.com.myretrofit.BaseActivity;
import sain.cloudminds.com.myretrofit.MovieApplication;
import sain.cloudminds.com.myretrofit.R;
import sain.cloudminds.com.myretrofit.http.Fault;

/**
 * created by sain on 9/10/18
 */
public class MovieActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MovieActivity";
    private RecyclerView mRecyclerView;
    private MovieLoader mMovieLoader;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_layout);
        mMovieLoader = new MovieLoader();
        initView();
    }

    @Override
    public void onClick(View v) {

    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.movie_list);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new MovieDecoration());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);
        getMovieList();
    }


    private void getMovieList() {
        mMovieLoader.getMovie(0, 10).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mMovieAdapter.setMovies(movies);
                mMovieAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, "error message:" + throwable.getMessage());
                if (throwable instanceof Fault) {
                    Fault fault = (Fault) throwable;
                    if (fault.getErrorCode() == 404) {

                    }
                }
            }
        });
    }

    public static class MovieDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;
        private Paint mTextPaint;
        private Bitmap mIcon;
        private float mFlagLeft;

        public MovieDecoration() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mTextPaint = new Paint();
            mTextPaint.setColor(Color.WHITE);
            mTextPaint.setTextSize(50);
            mIcon = BitmapFactory.decodeResource(MovieApplication.getContext().getResources(), R.mipmap.rank);
            mFlagLeft = 50;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(0, 0, 0, 20);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            Rect rect = new Rect();
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = parent.getChildAt(i);
                float top = view.getTop();
                int index = parent.getChildAdapterPosition(view);
                String text = String.valueOf(index + 1);
                mTextPaint.getTextBounds(text, 0, text.length(), rect);
                int width = (int) mTextPaint.measureText(text);//文字宽
                int height = rect.height();//文字高

                c.drawBitmap(mIcon, mFlagLeft, top, mPaint);
                float textX = mFlagLeft + mIcon.getWidth() / 2 - width / 2;
                float textY = top + mIcon.getHeight() / 2 + height / 2;
                c.drawText(text, textX, textY, mTextPaint);
            }
        }
    }
}
