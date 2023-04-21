package com.swj.tp02numbergamerefectoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.swj.tp02numbergamerefectoring.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // 사용자가 맞출 정답값
    int com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Random rnd = new Random();
        com = rnd.nextInt(501) + 500; // 500 ~ 1000

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnRetry.setOnClickListener(view -> {
            binding.btn.setVisibility(View.VISIBLE);
            binding.btnRetry.setVisibility(View.GONE);
            com = rnd.nextInt(501) + 500; // 500 ~ 1000
            binding.tv.setText("결과값");
        });

        binding.btn.setOnClickListener(view -> {
            // 사용자가 입력한 숫자를 가지고 있는 EditText에게 숫자 모양 글씨 받아오기
            // String --> int 변환
            int num = 0;
            if(!binding.et.getText().toString().equals("")) {
                num = Integer.parseInt(binding.et.getText().toString());
            } else return;

            if(num < 500 || num > 1000) Toast.makeText(this, "500 ~ 1000 사이의 숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
            else if(num > com) binding.tv.setText(num + "보다 작습니다.");
            else if(num < com) binding.tv.setText(num + "보다 큽니다.");
            else {
                binding.tv.setText("축하합니다. 정답입니다.");
                binding.btn.setVisibility(View.GONE);
                binding.btnRetry.setVisibility(View.VISIBLE);
            }
            binding.et.setText("");
        });
    }
}