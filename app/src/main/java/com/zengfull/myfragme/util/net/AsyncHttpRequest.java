package com.zengfull.myfragme.util.net;

/**
 * Created by asus on 2015/12/21.
 */
public abstract class AsyncHttpRequest<T> {
    /**
     * 请求前
     */
    protected void onPreExecute() {
    }

    /**
     * 请求中
     * @return
     */
    public abstract T doInBackground();

    /**
     * 请求结果
     * @param result
     */
    protected void onPostExecute(T result) {
    }
}
