package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        new getContacts().execute();
    }

    private class getContacts extends AsyncTask<Void, Void,Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0){
            HttpHandler hh = new HttpHandler();

            String url = "https://gist.githubusercontent.com/Baalmart/8414268/raw/43b0e25711472de37319d870cb4f4b35b1ec9d26/contacts";
            String jsonString = hh.makeServiceCall(url);

            Log.e("Main", "Response from URL : " + jsonString);
            if(jsonString!=null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray contacts = jsonObject.getJSONArray("contacts");

                    for(int i=0;i<contacts.length();i++){
                        JSONObject cont = contacts.getJSONObject(i);

                        String id = cont.getString("id");
                        String name = cont.getString("name");
                        String address = cont.getString("address");
                        String email = cont.getString("email");
                        String gender = cont.getString("gender");

                        JSONObject phone = cont.getJSONObject("phone");

                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        contactList.add(contact);
                    }
                }catch (final JSONException e){
                    Log.e("Main", e.getMessage());
                    runOnUiThread(()->Toast.makeText(getApplicationContext(),"Check Logs", Toast.LENGTH_SHORT).show());
                }
            } else {
                Log.e("Main", "Couldn't get JSON from server");
                runOnUiThread(()->Toast.makeText(getApplicationContext(),"Check Logs", Toast.LENGTH_SHORT).show());
            }

            return  null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            ListAdapter listAdapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.item_list,
                    new String[]{"id", "name", "email", "mobile"}, new int[]{R.id.tvid, R.id.tv_name, R.id.tv_email, R.id.tv_number});
            listView.setAdapter(listAdapter);
        }
    }
}