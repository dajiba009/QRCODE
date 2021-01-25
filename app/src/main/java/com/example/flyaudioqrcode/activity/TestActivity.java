package com.example.flyaudioqrcode.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.flyaudioqrcode.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_qrcode);
    }
}
