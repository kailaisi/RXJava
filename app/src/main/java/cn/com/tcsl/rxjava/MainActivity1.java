package cn.com.tcsl.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity1 extends AppCompatActivity {
    public Subscriber<String> subscriber;
    Observable<Drawable> observable;
    int[] drawables = new int[]{R.mipmap.code0,R.mipmap.code1,R.mipmap.code2};


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
                creatObservable();
            }
        });
        createSubscribe();
    }

    private void createSubscribe() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, s);
                mHello.setText(s);
            }
        };
    }

    private void creatObservable() {
        observable = Observable.just(drawables[0],drawables[1],drawables[2]).map(new Func1<Integer, Drawable>() {
            @Override
            public Drawable call(Integer integer) {
                return getTheme().getDrawable(integer);
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onNext(Drawable drawable) {
                        mIv.setImageDrawable(drawable);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    // observable.subscribe(subscriber);

    private String getHello() {
        return "Hello RxAndroid";
    }

    private String getHello1() {
        return "Hello RxAndroid 1";
    }
}
