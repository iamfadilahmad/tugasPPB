package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.String;
import java.lang.Integer;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private TextView result;
    private Button add, sub, div, multi, clear;

    private String num1string, num2string;

    private Float num1int, num2int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.edit_num1);
        num2 = findViewById(R.id.edit_num2);

        result = findViewById(R.id.tv_result);

        add = findViewById(R.id.btn_add);
        sub = findViewById(R.id.btn_substract);
        div = findViewById(R.id.btn_divide);
        multi = findViewById(R.id.btn_multiply);
        clear = findViewById(R.id.btn_clear);




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fadd();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fsub();
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fdiv();
            }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fmulti();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fclear();
            }
        });

    }

    private void fadd(){
        num1int = Float.parseFloat(num1.getText().toString());
        num2int = Float.parseFloat(num2.getText().toString());
        float count = num1int + num2int;
        result.setText(String.valueOf(count));
    }

    private void fsub(){
        num1int = Float.parseFloat(num1.getText().toString());
        num2int = Float.parseFloat(num2.getText().toString());
        float count = num1int - num2int;
        result.setText(String.valueOf(count));
    }
    private void fdiv(){
        num1int = Float.parseFloat(num1.getText().toString());
        num2int = Float.parseFloat(num2.getText().toString());
        float count;
        if(num2int != 0){
            count = num1int/num2int;
        }
        else{
            Toast.makeText(this,"Invalid divider", Toast.LENGTH_LONG).show();
            return;
        }
        result.setText(String.valueOf(count));

    }
    private void fmulti(){
        num1int = Float.parseFloat(num1.getText().toString());
        num2int = Float.parseFloat(num2.getText().toString());
        float count = num1int * num2int;
        result.setText(String.valueOf(count));
    }
    private void fclear(){
        num1.getText().clear();
        num2.getText().clear();
        result.setText("");

    }
}