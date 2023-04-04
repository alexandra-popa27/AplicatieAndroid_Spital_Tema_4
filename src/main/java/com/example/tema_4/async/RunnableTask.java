package com.example.tema_4.async;

import android.os.Handler;

import java.util.concurrent.Callable;

public class RunnableTask<R>implements  Runnable{

    private final Callable<R> asyncOperation;
    private final Handler handler;
    private final Callback<R> mainThreadOperation;

    public RunnableTask(Handler handler, Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        this.asyncOperation = asyncOperation;
        this.handler = handler;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        try {
            R result= asyncOperation.call();
            handler.post(new HandlerMessage<>(result,mainThreadOperation));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
