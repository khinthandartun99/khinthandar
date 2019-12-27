package com.example.loginretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginretrofit.api.ApiInterface;
import com.example.loginretrofit.api.ApiUtil;
import com.example.loginretrofit.model.RegisterData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    EditText userName,email,password,passwordConfirm;
    Button buttonRegister;
    TextView txtresponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        passwordConfirm=findViewById(R.id.confirm_password);

        buttonRegister=findViewById(R.id.register);

        txtresponse=findViewById(R.id.response);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }
    public void register(){
        String name=userName.getText().toString().trim();
        String userEmail=email.getText().toString().trim();
        String userPassword=password.getText().toString().trim();
        String passwordConfirmmation=passwordConfirm.getText().toString().trim();
        apiInterface= ApiUtil.getApi();

        Call<RegisterData> call=apiInterface.registerUser(name,userEmail,userPassword,passwordConfirmmation);

        call.enqueue(new Callback<RegisterData>() {
            @Override
            public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                if (response.isSuccessful()){
                    RegisterData registerData=response.body();
                    txtresponse.setText(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterData> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
