package com.example.formpenilaian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText editNama, editTugas, editEas, editEts, editFP;

    private TextView tvNama, tvAngka, tvHuruf;

    private Button proses, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNama = findViewById(R.id.edit_nama);
        editTugas = findViewById(R.id.edit_nilaitugas);
        editEts = findViewById(R.id.edit_nilaiets);
        editEas = findViewById(R.id.edit_nilaieas);
        editFP = findViewById(R.id.edit_nilaifp);

        tvNama = findViewById(R.id.tv_nama);
        tvAngka = findViewById(R.id.tv_nilaiangka);
        tvHuruf = findViewById(R.id.tv_nilaihuruf);

        proses = findViewById(R.id.btn_process);
        clear = findViewById(R.id.btn_clear);

        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proses_nilai();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanse();
            }
        });
    }

    private void proses_nilai(){
        String namamhs = editNama.getText().toString();
        
        String huruftemp = "";


        int tugas = Integer.parseInt(editTugas.getText().toString());
        int ets = Integer.parseInt(editEts.getText().toString());
        int eas = Integer.parseInt(editEas.getText().toString());
        int fp = Integer.parseInt(editFP.getText().toString());

        double hasil = tugas * 0.1 + ets * 0.3 + eas * 0.3 + fp * 0.3;
        if(hasil <= 100 && hasil > 85){
            huruftemp="A";
        } else if (hasil<=85 && hasil>70) {
            huruftemp="B";
        } else if (hasil<=70 && hasil>60) {
            huruftemp="C";
        } else if (hasil<= 60 && hasil > 50) {
            huruftemp="D";
        }else{
            huruftemp="F";
        }

        tvNama.setText("Nama Mahasiswa : "+ namamhs);
        tvAngka.setText("Nilai Akhir : "+ Double.toString(hasil));
        tvHuruf.setText("Skor : "+huruftemp);
    }

    private void cleanse(){
        editNama.getText().clear();
        editTugas.getText().clear();
        editEts.getText().clear();
        editEas.getText().clear();
        editFP.getText().clear();

        tvNama.setText("");
        tvHuruf.setText("");;
        tvAngka.setText("");
    }
}