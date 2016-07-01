package cn.com.tcsl.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity3 extends AppCompatActivity {
    public Subscriber<String> subscriber;
    Observable<Drawable> observable;
    Student[] students=new Student[3];


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
        initvalue();
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createSubscribe();
            }
        });
        createSubscribe();
    }

    private void initvalue() {
        ArrayList<String> course=new ArrayList<>();
        course.add("xing");
        course.add("ming");
        students[0]=new Student("wu",course);
        students[1]=students[2]=new Student("jinx",course);
    }

    private void createSubscribe() {
        Observable.from(students).flatMap(new Func1<Student, Observable<String>>() {
            @Override
            public Observable<String> call(Student s) {
                return Observable.from(s.getCourses());
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,s);
            }
        });
    }

}
