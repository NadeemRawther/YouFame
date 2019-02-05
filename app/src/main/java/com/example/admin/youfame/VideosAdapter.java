package com.example.admin.youfame;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.api.services.youtube.YouTube;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private Listener listener;
    public interface Listener {
        public void onClick(int position, String Ways);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public VideosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(cv);
    }
    public void onBindViewHolder(ViewHolder holder, final int position) {
       final CardView cardView = holder.cardView;
       final Context context = cardView.getContext();
       final EditText edittext = (EditText) cardView.findViewById(R.id.editText2);
       final TextView textView1 = (TextView) cardView.findViewById(R.id.textView5);
       final TextView textView2 = (TextView) cardView.findViewById(R.id.textView8);
       final TextView textView3 = (TextView) cardView.findViewById(R.id.textView7);
       final TextView textView4 = (TextView) cardView.findViewById(R.id.textView6);
    VideosDatabase youpostDatabase = new VideosDatabase(cardView.getContext());
    SQLiteDatabase db = youpostDatabase.getReadableDatabase();
    Cursor cursor = db.query("VID", new String[]{"NAME"}, "_id = ?", new String[]{Integer.toString(position)}, null, null, null);
    if( cursor.moveToFirst()) {
        String nadeem = cursor.getString(0);
        edittext.setText(nadeem);
    }
    else {
        edittext.setText("");

    }
    //final String Ways = edittext.getText().toString();
     Button button = (Button) cardView.findViewById(R.id.button);
     button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
     if (listener != null) {
         final String Ways = edittext.getText().toString();
         final String view = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id=" + Ways + "&key=AIzaSyBPwXrfJNYvOWzmjE0E7KBiz72blVUHMZU";
         listener.onClick(position, Ways);
         RequestQueue queue = Volley.newRequestQueue(context);
         JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, view, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

     try {
         JSONArray items = response.getJSONArray("items");
         JSONObject statistics = items.getJSONObject(0).getJSONObject("statistics");
         Long Views = statistics.getLong("viewCount");
         Long Likes = statistics.getLong("likeCount");
         Long Dislikes = statistics.getLong("dislikeCount");
         Long comment = statistics.getLong("commentCount");
         textView1.setText(Views.toString());
         textView2.setText(Likes.toString());
         textView3.setText(Dislikes.toString());
         textView4.setText(comment.toString());
         }
     catch (JSONException e) {
         e.printStackTrace();
         }
         }
                    },
         new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView1.setText("No network");
                        }
                    });
         queue.add(stringRequest);
     }
         }
     });
    }
    public int getItemCount() {
        return 5;
    }
}


