package com.example.tema_4.database;

import android.content.Context;

import com.example.tema_4.async.AsyncTaskRunner;
import com.example.tema_4.async.Callback;

import java.util.List;
import java.util.concurrent.Callable;

public class CenterService {
    private final CenterDao centerDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public CenterService(Context context){
        this.centerDao=DatabaseManager.getInstance(context).getCenterDao();
        asyncTaskRunner=new AsyncTaskRunner();
    }

    public void insert(Center center, Callback<Center> activityThread){
        Callable<Center> insertOperation=new Callable<Center>() {
            @Override
            public Center call() throws Exception {
                if(center==null || center.getId()>0){
                    return null;
                }
                long id=centerDao.insert(center);
                if(id<0){
                    return null;
                }
                center.setId(id);
                return center;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation,activityThread);
    }

    public void getAll(Callback<List<Center>> activityThread){
        Callable<List<Center>> getAllOperation=new Callable<List<Center>>() {
            @Override
            public List<Center> call() {
                return centerDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation,activityThread);
    }
}
