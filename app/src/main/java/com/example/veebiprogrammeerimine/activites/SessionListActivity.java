package com.example.veebiprogrammeerimine.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.veebiprogrammeerimine.R;
import com.example.veebiprogrammeerimine.adapters.SessionListAdapter;
import com.example.veebiprogrammeerimine.classes.ApiClient;
import com.example.veebiprogrammeerimine.interfaces.ApiInterface;
import com.example.veebiprogrammeerimine.responses.SessionResponse;
import com.example.veebiprogrammeerimine.responses.UserResponse;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionListActivity extends AppCompatActivity {
    ListView lvsSessions;
    Context context;
    Activity activity;
    Button btnNewSession;
    ApiInterface apiInterface;
    String userId;
    SessionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = "";
        setContentView(R.layout.activity_session_list);

        context = SessionListActivity.this;
        activity = SessionListActivity.this;

        apiInterface = ApiClient.getMainClient().create(ApiInterface.class);

        btnNewSession = findViewById(R.id.bNewSession);

        btnNewSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Start new Session", Toast.LENGTH_SHORT).show();
                SessionResponse session = new SessionResponse();
                session.setStatus("started");
                session.setUser_id(userId);
                Call<SessionResponse> callSession = apiInterface.createSession(session);
                callSession.enqueue(new Callback<SessionResponse>() {
                    @Override
                    public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                        if(response.isSuccessful()) {
                            GetAllSessions();
                        }
                    }

                    @Override
                    public void onFailure(Call<SessionResponse> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        lvsSessions = findViewById(R.id.lvSessions);

        Call<ArrayList<UserResponse>> callUsers = apiInterface.getUsers();
        callUsers.enqueue(new Callback<ArrayList<UserResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UserResponse>> call, Response<ArrayList<UserResponse>> response) {
                if(response.isSuccessful()) {
                    ArrayList<UserResponse> users = response.body();

                    if(users.size() > 0) {
                        UserResponse user = users.get(0);
                        userId = user.getId();
                        GetAllSessions();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserResponse>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void GetAllSessions() {
        Call<ArrayList<SessionResponse>> call = apiInterface.getSession(userId);
        call.enqueue(new Callback<ArrayList<SessionResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SessionResponse>> call, Response<ArrayList<SessionResponse>> response) {
                if(response.isSuccessful()) {
                    ArrayList<SessionResponse> lstSessions = response.body();
                    adapter = new SessionListAdapter(context, lstSessions);
                    lvsSessions.setAdapter(adapter);
                    Iterator i = lstSessions.iterator();
                    while(i.hasNext()) {
                        SessionResponse  session = (SessionResponse) i.next();
                        btnNewSession.setVisibility(View.VISIBLE);
                        if(session.getStatus().equals("started") || session.getStatus().equals("paused")) {
                            btnNewSession.setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                } else {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SessionResponse>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}