package com.zengfull.myfragme.util.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by asus on 2015/12/21.
 */
public class AsyncHttpClient {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private AsyncHttpClient(){
    }

    public static void sendRequest(Context context, AsyncHttpRequest request){
        if(context != null) {
            request.onPreExecute();
            threadPool.submit(new RequestHandle(request));
        }
    }

     static class RequestHandle implements  Runnable{

        private AsyncHttpRequest asyncHttpRequest;
        private Handler mHandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 asyncHttpRequest.onPostExecute(msg.obj);
             }
         };

        public RequestHandle(AsyncHttpRequest asyncHttpRequest){
            this.asyncHttpRequest = asyncHttpRequest;
        }
         
        @Override
        public void run() {
            asyncHttpRequest.doInBackground();
            Message message = mHandler.obtainMessage();
            message.sendToTarget();
        }
    }
}

