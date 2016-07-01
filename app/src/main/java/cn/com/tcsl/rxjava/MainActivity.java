package cn.com.tcsl.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public Subscriber<String> subscriber;
    Observable<String> observable;
    int drawables = R.mipmap.code0;


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
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String str="";
                String macSerial="";
                try {
                    Process pp = Runtime.getRuntime().exec(
                            "cat /sys/class/net/wlan0/address ");
                    InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                    LineNumberReader input = new LineNumberReader(ir);
                    for (; null != str;) {
                        str = input.readLine();
                        if (str != null) {
                            macSerial = str.trim();// 去空格
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (macSerial == null || "".equals(macSerial)) {
                    try {
                        subscriber.onNext(loadFileAsString("/sys/class/net/eth0/address")
                                .toUpperCase().substring(0, 17));
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
                subscriber.onNext(macSerial);
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String drawable) {
                        mHello.setText(drawable);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public static String loadFileAsString(String fileName) throws Exception {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }
    public static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }
    // observable.subscribe(subscriber);

    private String getHello() {
        return "Hello RxAndroid";
    }

    private String getHello1() {
        return "Hello RxAndroid 1";
    }
}
