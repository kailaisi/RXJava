package cn.com.tcsl.rxjava.service;

import java.util.List;

import cn.com.tcsl.rxjava.MovieEntity;
import cn.com.tcsl.rxjava.Utils.HttpResult;
import cn.com.tcsl.rxjava.Utils.Subject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public interface MovieService {
  /*  @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);*/
  @GET("top250")
  Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
