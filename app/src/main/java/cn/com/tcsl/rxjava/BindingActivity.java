package cn.com.tcsl.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.design.widget.RxAppBarLayout;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxBind的效果测试
 * Created by Administrator on 2016/7/8 0008.
 */
public class BindingActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mRxbindingEtUsualApproach;
    private EditText mRxbindingEtReactiveApproach;
    private TextView mRxbindingTvShow;
    private FloatingActionButton mRxbindingFab;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_binding);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRxbindingEtUsualApproach = (EditText) findViewById(R.id.rxbinding_et_usual_approach);
        mRxbindingEtReactiveApproach = (EditText) findViewById(R.id.rxbinding_et_reactive_approach);
        mRxbindingTvShow = (TextView) findViewById(R.id.rxbinding_tv_show);
        mRxbindingFab = (FloatingActionButton) findViewById(R.id.rxbinding_fab);
        initToolbar();
        initFab();
        RxTextView.textChanges(mRxbindingEtUsualApproach).subscribe(mRxbindingTvShow::setText);
        RxTextView.textChanges(mRxbindingEtReactiveApproach).map(new Func1<CharSequence, String>() {
            @Override
            public String call(CharSequence charSequence) {
                return new StringBuilder(charSequence).reverse().toString();
            }
        }).subscribe(mRxbindingTvShow::setText);
    }

    /**
     * 初始化fab
     */
    private void initFab() {

      /*  RxView.clicks(mRxbindingFab).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Snackbar.make(findViewById(android.R.id.content),"点击fab",Snackbar.LENGTH_SHORT).show();
            }
        });*/
        Observable<Void> share =  RxView.clicks(mRxbindingFab).share();
        Subscription buttonSub = share.subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Snackbar.make(findViewById(android.R.id.content), "点击fab", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化标题数据
     */
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        RxToolbar.itemClicks(mToolbar).subscribe(this::onToolbarItemClicked);
        RxToolbar.navigationClicks(mToolbar).subscribe(this::onToolbarNavigationClicked);

    }
    // 点击Toolbar的项
    private void onToolbarItemClicked(MenuItem menuItem) {
        String m = "点击\"" + menuItem.getTitle() + "\"";
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }
    // 浏览点击
    private void onToolbarNavigationClicked(Void v) {
        Toast.makeText(this, "浏览点击", Toast.LENGTH_SHORT).show();
    }
}
