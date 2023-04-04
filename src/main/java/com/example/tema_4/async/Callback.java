package com.example.tema_4.async;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
