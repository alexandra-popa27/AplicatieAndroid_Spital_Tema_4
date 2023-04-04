package com.example.tema_4.async;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {
    private final Executor executor=Executors.newCachedThreadPool();
    private final Handler handler=new Handler(Looper.getMainLooper());

    public <R> void executeAsync(Callable<R> asyncOperation,Callback <R> mainThreadOperation){
        executor.execute(new RunnableTask<>(handler,asyncOperation,mainThreadOperation));
    }
}

