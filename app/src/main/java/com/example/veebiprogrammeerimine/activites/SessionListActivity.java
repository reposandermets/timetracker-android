package com.example.veebiprogrammeerimine.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.veebiprogrammeerimine.R;
import com.example.veebiprogrammeerimine.adapters.SessionListAdapter;
import com.example.veebiprogrammeerimine.classes.ApiClient;
import com.example.veebiprogrammeerimine.interfaces.ApiInterface;
import com.example.veebiprogrammeerimine.responses.SessionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionListActivity extends AppCompatActivity {
    ListView lvsSessions;
    Context context;
    Activity activity;

    ApiInterface apiInterface;

    SessionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_list);

        context = SessionListActivity.this;
        activity = SessionListActivity.this;

        apiInterface = ApiClient.getMainClient().create(ApiInterface.class);

        lvsSessions = findViewById(R.id.lvSessions);
        // GetAllSessions();
        ArrayList<SessionResponse> sessions = new ArrayList<SessionResponse>();
        SessionResponse session = new SessionResponse();
        session.setStatus("Hello");
        sessions.add(session);
        /*
        SessionResponse session1 = new SessionResponse();
        session.setStatus("World");
        sessions.add(session1);
*/
        adapter = new SessionListAdapter(context, sessions);
        lvsSessions.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void GetAllSessions() {
        Call<ArrayList<SessionResponse>> call = apiInterface.getSession("85d766af-2d83-439d-a97f-400cac99015b");
        call.enqueue(new Callback<ArrayList<SessionResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SessionResponse>> call, Response<ArrayList<SessionResponse>> response) {
                if(response.isSuccessful()) {
                    ArrayList<SessionResponse> lstSessions = response.body();
                    adapter = new SessionListAdapter(context, lstSessions);
                    lvsSessions.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    // adapter.add(lstSessions);
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