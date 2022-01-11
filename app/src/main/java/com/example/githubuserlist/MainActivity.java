package com.example.githubuserlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.githubuserlist.adapter.UserAdapter;
import com.example.githubuserlist.model.ResponseUser;
import com.example.githubuserlist.model.UserModel;
import com.example.githubuserlist.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<UserModel> dataGithub = new ArrayList<>();
    RecyclerView rvUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() !=null){
            getSupportActionBar().setTitle(getString(R.string.search_user));
        }

        progressBar = findViewById(R.id.progressBar);

        //RV
        rvUser = findViewById(R.id.rv_search_user);

        //Layout
        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null){
            SearchView searchView = (SearchView) findViewById(R.id.sv_user);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.username));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    showProgress(true);
                    if(s != null){
                        getDataOnline(s);
                    } else {
                        Toast.makeText(MainActivity.this, "Please Insert Username", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            });
        }

    }

    private void showProgress(boolean state) {
        if(state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void getDataOnline(final String usernames){
        Call<ResponseUser> request = ApiClient.getApiService().getSearchUser(usernames);
        request.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    //Collecting Data
                    dataGithub = response.body().getItems();
                    //Set Adapter to Recycler View
                    rvUser.setAdapter(new UserAdapter(MainActivity.this,dataGithub));
                    showProgress(false);
                } else {
                    Toast.makeText(MainActivity.this,"Request Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });
    }
}