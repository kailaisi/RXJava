package cn.com.tcsl.rxjava.Utils;

import rx.functions.Func1;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class HttpResult<T> {
    private int count;
    private int start;
    private int total;
    private String title;
    T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getData() {
        return subjects;
    }

    public void setData(T data) {
        this.subjects = data;
    }
}
