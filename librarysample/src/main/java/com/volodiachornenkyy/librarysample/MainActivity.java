package com.volodiachornenkyy.librarysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.volodiachornenkyy.whatpulse_library.WhatPulseClient;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUser;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    TextView tvResults;

    WhatPulseUserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.input);
        tvResults = (TextView) findViewById(R.id.results);

        WhatPulseClient whatPulseClient = new WhatPulseClient();
        userService = whatPulseClient.getUserService();

        findViewById(R.id.load_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResults.setText("");
                userService.getUser(getInputValue()).enqueue(new Callback<WhatPulseUser>() {
                    @Override
                    public void onResponse(Call<WhatPulseUser> call, Response<WhatPulseUser> response) {
                        WhatPulseUser whatPulseUser = response.body();
                        tvResults.setText(whatPulseUser.toString());
                    }

                    @Override
                    public void onFailure(Call<WhatPulseUser> call, Throwable t) {
                        tvResults.setText(t.getMessage());
                    }
                });
            }
        });

    }

    public String getInputValue() {
        return etInput.getText().toString();
    }
}