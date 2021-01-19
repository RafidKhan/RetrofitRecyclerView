package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.model.ResultsItem;
import com.example.myapplication.model.SearchPhotoModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>{

    Context context;
    List<ResultsItem> resultsItems;

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView userName, fullName, portfolioUrl;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.textview1);
            fullName = itemView.findViewById(R.id.textview2);
            portfolioUrl = itemView.findViewById(R.id.textview3);
            imageView= itemView.findViewById(R.id.imageView2);
        }
    }



    public ProgramAdapter(Context context, List<ResultsItem> resultsItems)
    {

        this.context= context;
        this.resultsItems = resultsItems;

    }

    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(BuildConfig.DEBUG ? MainActivity.DefaultInterceptors.getHttpBodyLoggingInterceptor() : MainActivity.DefaultInterceptors.getHttpNoneLoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        MyApiCall myApiCall = retrofit.create(MyApiCall.class);

        Call<SearchPhotoModel> call = myApiCall.getPhoto("cat", "Qb8NmJOpIwSGOUdh-cgnU4S6R6J8yeOND-y04HhTxqc", "1", "5");

        call.enqueue(new Callback<SearchPhotoModel>() {
            @Override
            public void onResponse(Call<SearchPhotoModel> call, Response<SearchPhotoModel> response) {

                String fetchimg= response.body().getResults().get(3).getUrls().getRegular();

                String firstName = response.body().getResults().get(3).getUser().getFirstName();
                String lastName = response.body().getResults().get(3).getUser().getLastName();

                String fullName = firstName+" "+lastName;

                String userName= response.body().getResults().get(3).getUser().getUsername();
                String portfolioUrl = response.body().getResults().get(3).getUser().getPortfolioUrl();

                //response.body().getResults();

                /*
                Glide.with(ProgramAdapter.this)
                        .load(fetchimg)
                        .fitCenter()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(fetchimg);
                 */


                holder.userName.setText(userName);
                holder.fullName.setText(fullName);
                holder.portfolioUrl.setText(portfolioUrl);
            }

            @Override
            public void onFailure(Call<SearchPhotoModel> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return resultsItems.size();
    }
}
