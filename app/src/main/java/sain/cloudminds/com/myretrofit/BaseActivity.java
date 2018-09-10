package sain.cloudminds.com.myretrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * created by sain on 9/10/18
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG ="BaseActivity" ;
    /**
     *
     */
    private CompositeSubscription sCompositeSubscription ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(sCompositeSubscription == null || sCompositeSubscription.isUnsubscribed()) {
            sCompositeSubscription = new CompositeSubscription();
        }
    }
    public void addSubscriptin(Subscription subscription) {
        sCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sCompositeSubscription != null) {
            sCompositeSubscription.unsubscribe();
        }
    }
}
