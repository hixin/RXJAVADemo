package sain.cloudminds.com.myretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sain.cloudminds.com.myretrofit.movie.MovieActivity;
import sain.cloudminds.com.myretrofit.welfare.WelFareActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.movie_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MovieActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.award_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WelFareActivity.class);
                startActivity(intent);
            }
        });
    }
}
