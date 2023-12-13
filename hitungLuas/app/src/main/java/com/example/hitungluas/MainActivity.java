package com.example.hitungluas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText vPanjang = findViewById(R.id.editPanjang);
        EditText vLebar = findViewById(R.id.editLebar);
        Button hitung = findViewById(R.id.btnHitung);

//        public void hitungluas(){
//            String panjang = vPanjang.getText().toString();
//            String lebar = vLebar.getText().toString();
//
//        }
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String panjang = vPanjang.getText().toString();
                String lebar = vPanjang.getText().toString();

                double p = Double.parseDouble(panjang);
                double l = Double.parseDouble(lebar);
                double luas = p*l;
                Toast.makeText(MainActivity.this, "Luas adalah : "+luas, Toast.LENGTH_SHORT).show();
            }
        });


    }
    }

