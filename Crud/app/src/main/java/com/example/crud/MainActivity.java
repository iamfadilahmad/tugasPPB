package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;

import android.content.ContentValues;
import android.content.DialogInterface;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;

    private Button btnNRP, btnSave, btnShow, btnShowAll, btnUpdate, btnDelete;

    private String nrp;

    private ListView listView;

    private final DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edit_name);

        btnNRP = findViewById(R.id.btn_addnrp); btnNRP.setOnClickListener(view -> nrpdialog());
        btnSave = findViewById(R.id.btn_savedata); btnSave.setOnClickListener(this);
        btnShow = findViewById(R.id.btn_show); btnShow.setOnClickListener(this);
        btnShowAll = findViewById(R.id.btn_showAll); btnShowAll.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btn_update); btnUpdate.setOnClickListener(this);
        btnDelete = findViewById(R.id.btn_delete); btnDelete.setOnClickListener(this);

        listView = findViewById(R.id.listView_data);
    }

    private void nrpdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.nrp_dialog_layout, null);
        builder.setView(dialogView);
        final EditText dialogEditText = dialogView.findViewById(R.id.nrp_dialog_editText);

        builder.setPositiveButton("OK", (dialog, which)->{
            String getnrp = dialogEditText.getText().toString();
            nrp = getnrp;
        });

        builder.setNegativeButton("Cancel", (dialog,which)-> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addData(){
        Boolean add = this.DB.insertData(name.getText().toString(), this.nrp);
        try{
            this.checkResult(add);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void getData(){
        String getName = name.getText().toString();
        String getNrp = this.nrp;

        try{
            if(getName != null || (getName != null && getNrp != null)){
                String[] result = this.DB.checkname(getName);
                Toast.makeText(this, result[0] + " " + result[1], Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void updateData(){
        Boolean update = this.DB.updateName(name.getText().toString(), this.nrp);
        try{
            this.checkResult(update);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void deleteData(){
        Boolean delete = this.DB.deleteData(name.getText().toString());
        try{
            this.checkResult(delete);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void checkResult(Boolean result){
        String[] output;
        if(result){
            output = this.DB.checkname(name.getText().toString());
            Toast.makeText(this, output[0]+ " "+ output[1], Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void showAll(){
        String[] output = this.DB.showAll();
        if (output != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.mhs_list_layout, R.id.textViewName,output);
            listView.setAdapter(adapter);
        }
    }
//    btnSave = findViewById(R.id.btn_savedata); btnSave.setOnClickListener(this);
//    btnShow = findViewById(R.id.btn_show); btnShow.setOnClickListener(this);
//    btnShowAll = findViewById(R.id.btn_showAll); btnShowAll.setOnClickListener(this);
//    btnUpdate = findViewById(R.id.btn_update); btnUpdate.setOnClickListener(this);
//    btnDelete = findViewById(R.id.btn_delete); btnDelete.setOnClickListener(this);

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.btn_savedata){
            this.addData();
        } else if (view.getId() == R.id.btn_show) {
            this.getData();
        } else if (view.getId() == R.id.btn_showAll) {
            this.showAll();
        } else if (view.getId() == R.id.btn_update) {
            this.updateData();
        } else if (view.getId() == R.id.btn_delete) {
            this.deleteData();
        }
    }
}