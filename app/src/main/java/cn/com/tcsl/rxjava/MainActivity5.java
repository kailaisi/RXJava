package cn.com.tcsl.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.tcsl.rxjava.Utils.HttpMethod;
import cn.com.tcsl.rxjava.Utils.Subject;
import cn.com.tcsl.rxjava.service.MovieService;
import cn.com.tcsl.rxjava.service.SubscriberOnNextListener;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity5 extends AppCompatActivity {

    private TextView mHello;
    private Button mTest;
    private ImageView mIv;


    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHello = (TextView) findViewById(R.id.hello);
        mTest = (Button) findViewById(R.id.test);
        mIv = (ImageView) findViewById(R.id.iv);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
            }
        });
    }

    private void getMovie() {
        Subscriber<List<Subject>> subscriber=new Subscriber<List<Subject>>() {
                   @Override
                   public void onCompleted() {
                       Log.i(TAG,"Get Top Movie Completed");
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.i(TAG,"Get Top Movie Error");
                       mHello.setText(e.getMessage());
                   }

                   @Override
                   public void onNext(List<Subject> movieEntity) {
                       mHello.setText(movieEntity.toString());
                   }
               };
        HttpMethod.getInstance().getTopMovie(subscriber,0,2);
    }
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);

}
