package com.example.red_ragnar.testing;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.support.annotation.MainThread;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bpc.modulesdk.errors.RestErrorHandler;
import com.bpc.modulesdk.rest.RestServiceFactory;
import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.bpc.modulesdk.rest.dto.response.RatesResponse;
import com.bpc.modulesdk.security.SharedPreferencesHelper;

import java.util.List;

import app.Testing.FirstChoiceActivity;
import app.Testing.NewFirstChoice;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.example.red_ragnar.testing.R.id.fab;
import static com.example.red_ragnar.testing.R.layout.activity_first_choice;

public class MainActivity extends AppCompatActivity {
    Button FirstChoice;
    Button SecondChoice;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model mainModel = new Model();
        FirstChoice = (Button)  findViewById(R.id.firstChoice);
        SecondChoice = (Button)  findViewById(R.id.secondChoice);

        FirstChoice.setOnClickListener(firstChoiceOnClick);
        SecondChoice.setOnClickListener(secondChoiceOnClick);
        //queryRates();
    }
    View.OnClickListener firstChoiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this,NewFirstChoice.class);
            startActivity(intent);
        }
    };
    View.OnClickListener secondChoiceOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(MainActivity.this, SecondChoiceActivity.class);
            startActivity(intent);
        }
    };
}