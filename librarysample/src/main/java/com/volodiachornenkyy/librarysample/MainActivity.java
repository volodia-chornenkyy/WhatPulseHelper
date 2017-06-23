package com.volodiachornenkyy.librarysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.volodiachornenkyy.whatpulse_library.WhatPulseClient;
import com.volodiachornenkyy.whatpulse_library.WhatPulseErrors;
import com.volodiachornenkyy.whatpulse_library.pulses.WhatPulsePulse;
import com.volodiachornenkyy.whatpulse_library.pulses.WhatPulsePulsesApi;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseException;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUser;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUserApi;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    TextView tvResults;

    WhatPulseUserApi userApi;
    WhatPulsePulsesApi pulsesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.input);
        tvResults = (TextView) findViewById(R.id.results);

        WhatPulseClient whatPulseClient = new WhatPulseClient();
        userApi = whatPulseClient.getUserApi();
        pulsesApi = whatPulseClient.getPulsesApi();

        findViewById(R.id.load_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResults.setText("");
                SingleObserver<WhatPulseUser> subscriber = new SingleObserver<WhatPulseUser>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onSuccess(WhatPulseUser whatPulseUser) {
                        tvResults.setText(whatPulseUser.toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (throwable instanceof WhatPulseException) {
                            tvResults.setText(WhatPulseErrors.define(throwable.getMessage()).name());
                        } else {
                            tvResults.setText(throwable.getMessage());
                        }
                    }
                };
                userApi.getUser(getInputValue())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });

        findViewById(R.id.load_pulses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResults.setText("");
                SingleObserver<List<WhatPulsePulse>> subscriber = new SingleObserver<List<WhatPulsePulse>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onSuccess(List<WhatPulsePulse> whatPulseUser) {
                        tvResults.setText(whatPulseUser.toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (throwable instanceof WhatPulseException) {
                            tvResults.setText(WhatPulseErrors.define(throwable.getMessage()).name());
                        } else {
                            tvResults.setText(throwable.getMessage());
                        }
                    }
                };
                pulsesApi.getUserPulses(getInputValue())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    public String getInputValue() {
        return etInput.getText().toString();
    }
}