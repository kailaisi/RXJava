package cn.com.tcsl.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import cn.com.tcsl.rxjava.Utils.HttpMethod;
import cn.com.tcsl.rxjava.Utils.ProgressSubscriber;
import cn.com.tcsl.rxjava.Utils.Subject;
import cn.com.tcsl.rxjava.service.SubscriberOnNextListener;
import rx.Subscriber;

public class MainActivity6 extends AppCompatActivity {

    SubscriberOnNextListener getTopMovieOnNext;
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
        RxView.clicks(mTest).subscribe(this::getMovie);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
            }
        });
        getTopMovieOnNext=new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                mHello.setText(subjects.toString());
            }
        };
    }


    private void getMovie() {
        HttpMethod.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getTopMovieOnNext,this),0,10);
    }

    private void getMovie(Void v) {
        HttpMethod.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(getTopMovieOnNext,this),0,10);
    }

}
