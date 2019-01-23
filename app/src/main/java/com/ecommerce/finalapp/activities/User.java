package com.ecommerce.finalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ecommerce.finalapp.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ecommerce.finalapp.controller.AppControl;
import com.ecommerce.finalapp.controller.IApi;
import com.ecommerce.finalapp.response.SignInResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User extends AppCompatActivity {
    EditText nameText, emailText, addressText;
    ImageView i1,i2;
    Button editButton, okButton;

    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nameText=findViewById(R.id.nameText);
        emailText=findViewById(R.id.emailText);
        addressText=findViewById(R.id.addressText);
        editButton=findViewById(R.id.editButton);
        okButton=findViewById(R.id.okButton);

        SharedPreferences sharedPreferences= getSharedPreferences("package com.example.projectfinal;", Context.MODE_PRIVATE);
        final int id= sharedPreferences.getInt("user",0);
        //final String pass= sharedPreferences.getString("pass","");
        // Toast.makeText(this, ""+sharedPreferences.getInt("user",0), Toast.LENGTH_SHORT).show();


        OkHttpClient client = new OkHttpClient.Builder().build();

        final Retrofit retrofit= new Retrofit.Builder().baseUrl("http://allstore.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
        IApi iApi = retrofit.create(IApi.class);
        iApi.getuser(id).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                String name= response.body().getName();
                String email = response.body().getEmailId();
                String address= response.body().getAddress();
                pass = response.body().getPassword();

                nameText.setText(name);

                emailText.setText(email);
                addressText.setText(address);
                nameText.setEnabled(false);
                emailText.setEnabled(false);
                addressText.setEnabled(false);



            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {


            }  });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.setVisibility(1);
                nameText.setEnabled(true);
                emailText.setEnabled(true);
                addressText.setEnabled(true);
                String name= nameText.getText().toString();
                String Email=emailText.getText().toString();
                String address=addressText.getText().toString();
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        OkHttpClient client = new OkHttpClient.Builder().build();

                        final Retrofit retrofit= new Retrofit.Builder().baseUrl("http://allstore.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(client).build();
                        IApi iApi = retrofit.create(IApi.class);
                        SignInResponse signInResponse= new SignInResponse();
                        signInResponse.setAddress(nameText.getText().toString());
                        signInResponse.setEmailId(emailText.getText().toString());
                        signInResponse.setName(addressText.getText().toString());
                        signInResponse.setPassword(pass);
                        signInResponse.setUserId(id);


                        //Toast.makeText(Registration.this, ""+signInRequest.getName(), Toast.LENGTH_SHORT).show();
                        iApi.edit(signInResponse).enqueue(new Callback<SignInResponse>() {
                            @Override
                            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                                //System.out.println("Success" + response.body().getUserId());
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(User.this, "updated the details", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(User.this, "Error in upddation", Toast.LENGTH_SHORT).show();

                                } }

                            @Override
                            public void onFailure(Call<SignInResponse> call, Throwable t) {
                                System.out.println("error"+ t);



                            }
                        });


                    }
                });




            }
        });

    }


}