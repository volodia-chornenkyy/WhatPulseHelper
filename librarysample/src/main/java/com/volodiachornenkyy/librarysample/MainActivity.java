package com.volodiachornenkyy.librarysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.volodiachornenkyy.whatpulse_library.WhatPulseClient;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUser;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUserApi;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    TextView tvResults;

    WhatPulseUserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.input);
        tvResults = (TextView) findViewById(R.id.results);

        WhatPulseClient whatPulseClient = new WhatPulseClient();
        userApi = whatPulseClient.getUserApi();

        findViewById(R.id.load_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResults.setText("");
                SingleObserver<WhatPulseUser> subscriber = new SingleObserver<WhatPulseUser>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("TEST onSubscribe");
                    }

                    @Override
                    public void onSuccess(WhatPulseUser whatPulseUser) {
                        tvResults.setText(whatPulseUser.toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        tvResults.setText(throwable.getMessage());
                    }
                };
                userApi.getUser(getInputValue())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber);
            }
        });
    }

    public String getInputValue() {
        return etInput.getText().toString();
    }
}