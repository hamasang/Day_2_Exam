package com.example.user.day_2_exam;

import android.Manifest;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Gota.OnRequestPermissionsBack {

    Intent intent;
    Button button;
    SpeechRecognizer mRecongizer;
    boolean power = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        new Gota.Builder(this).withPermissions(Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO)
                .requestId(1)
                .setListener(this)
                .check();
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");
        mRecongizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecongizer.setRecognitionListener(listener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(power == false) {
                    mRecongizer.startListening(intent);
                    Toast.makeText(MainActivity.this, "녹음이 시작되었습니다.", Toast.LENGTH_SHORT).show();
                    power = true;

                }

            }
        });
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle results) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[(mResult.size())];
            mResult.toArray(rs);
            TextView tv = findViewById(R.id.stt);
            tv.setText(rs[0].toString());
            if(power == true) {
                Toast.makeText(MainActivity.this, "녹음이 끝났습니다.", Toast.LENGTH_SHORT).show();
                power = false;
            }

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };

    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isGranted(Manifest.permission.RECORD_AUDIO) && gotaResponse.isGranted(Manifest.permission.INTERNET)){

        }
    }
}
