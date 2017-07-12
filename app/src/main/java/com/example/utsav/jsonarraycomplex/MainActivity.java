package com.example.utsav.jsonarraycomplex;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.utsav.jsonarraycomplex.adapter.UserDataAdapter;
import com.example.utsav.jsonarraycomplex.model.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<UserResponse> userResponseArrayList = new ArrayList<>();
    private RecyclerView rvUserData;
    public static final String URL = "https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUserData = (RecyclerView) findViewById(R.id.rv_userdata);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rvUserData.setLayoutManager(layoutManager);
        rvUserData.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        UserTask userTask = new UserTask();
        userTask.execute(URL);
    }

    public class UserTask extends AsyncTask<String, Void, ArrayList<UserResponse>> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Loading", "utsav");
        }

        @Override
        protected ArrayList<UserResponse> doInBackground(String... params) {
            try {
                String json = getJsonString(params[0]);
                Type listType = new TypeToken<ArrayList<UserResponse>>() {}.getType();

                return userResponseArrayList = new GsonBuilder().create().fromJson(json, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<UserResponse> userResponses) {
            super.onPostExecute(userResponses);
            progressDialog.dismiss();
            UserDataAdapter userDataAdapter = new UserDataAdapter(userResponses);
            rvUserData.setAdapter(userDataAdapter);
        }
    }

    private String getJsonString(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
