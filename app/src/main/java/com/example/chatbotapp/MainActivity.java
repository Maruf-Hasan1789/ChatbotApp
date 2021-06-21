package com.example.chatbotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private EditText textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview1);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter a text", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(textView.getText().toString());
            }
        });
    }
    private void postData(String text)
    {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http:192.168.56.1:5000/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        API_Service retrofitAPI = retrofit.create(API_Service.class);
        ChatModel chatModel=new ChatModel();
        chatModel.setChatBotReply(text);
        Call<ChatModel>call=retrofitAPI.getchat(chatModel.getChatBotReply().toString());
        call.enqueue(new Callback<ChatModel>() {
            @Override
            public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                ChatModel responseModel=response.body();
                String reply=responseModel.getChatBotReply();
                textView.setText(reply);
            }


            @Override
            public void onFailure(Call<ChatModel> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

}