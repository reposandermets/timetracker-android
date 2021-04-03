package com.example.veebiprogrammeerimine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veebiprogrammeerimine.R;
import com.example.veebiprogrammeerimine.activites.SessionListActivity;
import com.example.veebiprogrammeerimine.classes.ApiClient;
import com.example.veebiprogrammeerimine.interfaces.ApiInterface;
import com.example.veebiprogrammeerimine.responses.SessionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SessionListAdapter extends BaseAdapter {

    Context context;
    ArrayList<SessionResponse> lstSession;
    LayoutInflater inflater;

    public SessionListAdapter(Context _context, ArrayList<SessionResponse> _list) {
        context = _context;
        lstSession = _list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return lstSession.size();
    }

    @Override
    public Object getItem(int position) {

        return lstSession.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lstSession.indexOf(getItem(position));
        // return position;
        // return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SessionDataHolders holder = new SessionDataHolders();

        convertView = inflater.inflate(R.layout.list_item, null);
        holder.tvStatus = convertView.findViewById(R.id.tvStatus);
        // holder.tvMinutes = view.findViewById(R.id.tvMinutes);
        // holder.bPause = view.findViewById(R.id.bPause);
        // holder.bStart = view.findViewById(R.id.bStart);

        String status = lstSession.get(position).getStatus();
        holder.tvStatus.setText(status);
        // holder.tvMinutes.setText(String.valueOf(lstSession.get(position).getSeconds()));
/*
        SessionResponse session = lstSession.get(position);
        holder.bPause.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetSessionStatus(session, "paused");
                    }
                }
        );

        holder.bStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SetSessionStatus(session, "started");
                    }
                }
        );
*/
        return convertView;
    }

    private void SetSessionStatus(SessionResponse session, String status) {
        ApiInterface apiInterface = ApiClient.getMainClient().create(ApiInterface.class);
        session.setStatus(status);
        Call<SessionResponse> call = apiInterface.patchSession(session.getId(), session);
        call.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    ((SessionListActivity) context).GetAllSessions();
                } else {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public class SessionDataHolders {
        public TextView tvStatus;
        // public Button bPause, bStart;
       //  public TextView tvStatus, tvMinutes;
    }
}
