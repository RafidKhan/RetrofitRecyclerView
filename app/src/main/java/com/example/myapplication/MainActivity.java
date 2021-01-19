package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.ResultsItem;
import com.example.myapplication.model.SearchPhotoModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewResult;
    private TextView nameResult;

    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutManager;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageViewResult = findViewById(R.id.imageView);
        nameResult= findViewById(R.id.TVName);

        recyclerView = findViewById(R.id.rvProgram);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(BuildConfig.DEBUG ? DefaultInterceptors.getHttpBodyLoggingInterceptor() : DefaultInterceptors.getHttpNoneLoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        MyApiCall myApiCall = retrofit.create(MyApiCall.class);

        Call<SearchPhotoModel> call = myApiCall.getPhoto("cat", "Qb8NmJOpIwSGOUdh-cgnU4S6R6J8yeOND-y04HhTxqc", "1", "5");

        //RecycleSTart
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //RecycleEnd





        call.enqueue(new Callback<SearchPhotoModel>() {
            @Override
            public void onResponse(Call<SearchPhotoModel> call, Response<SearchPhotoModel> response) {
                if (response.isSuccessful()) {
                    String fetchimg= response.body().getResults().get(3).getUrls().getRegular();

                    String firstName = response.body().getResults().get(3).getUser().getFirstName();
                    String lastName = response.body().getResults().get(3).getUser().getLastName();

                    String fullName = firstName+" "+lastName;

                    String userName= response.body().getResults().get(3).getUser().getUsername();
                    String portfolioUrl = response.body().getResults().get(3).getUser().getPortfolioUrl();


                    String[] images ={fetchimg};

                   // Log.e("Photo", "Photo"+ fetchimg);

                   // imageViewResult.setImageURI(fetchimgInt);

                    //Log.e("ANDROID", "First Name: "+ response.body().getResults().get(0).getUser().getFirstName()+" "+response.body().getResults().get(0).getUser().getLastName());
                    //Log.e("ANDROID", "onResponse: "+ response.body().getResults().get(0).getId());


                    //Toast.makeText(this, response.body().getPhoto().getId().toString(), Toast.LENGTH_LONG).show();


                    Glide.with(MainActivity.this)
                            .load(fetchimg)
                            .fitCenter()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(imageViewResult);

                    nameResult.setText("User Name: "+userName+"\n"+"Name: "+fullName+"\n"+"Portfolio URL: "+portfolioUrl);




                }

            }

            @Override
            public void onFailure(Call<SearchPhotoModel> call, Throwable t) {

            }
        });


        programAdapter = new ProgramAdapter(this, (List<ResultsItem>) call);
        recyclerView.setAdapter(programAdapter);

    }

    public static class DefaultInterceptors {

        public static HttpLoggingInterceptor getHttpBodyLoggingInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return interceptor;
        }

        public static HttpLoggingInterceptor getHttpNoneLoggingInterceptor() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            return interceptor;
        }
    }
}