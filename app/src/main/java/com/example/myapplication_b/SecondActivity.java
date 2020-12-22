package com.example.myapplication_b;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    RequestQueue queue;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btn = (Button) findViewById(R.id.searchBtn);
        mRecyclerview =findViewById(R.id.my_recycler_view);



        mRecyclerview.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        queue= Volley.newRequestQueue(this);
        getBooks();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView searchText = (TextView) findViewById(R.id.searchText);
                //final TextView searchResult = (TextView) findViewById(R.id.searchResult);
                String keyword = searchText.getText().toString();

                Intent intent = new Intent(SecondActivity.this, SubActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
                //searchResult.setText(str);


            }
        });
    }
    public void getBooks(){
        String url="http://book.interpark.com/api/bestSeller.api?key=6CB2E130395017E95BF3FF7D5C4230DCF4CAA251DE9491B6DB19657DC3BB96EA&categoryId=100";

        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray arrayBooks = jsonObj.getJSONArray("item");
                            List<BookData> books = new ArrayList<>();

                            for (int i = 0, j = arrayBooks.length(); i < j ;i++){
                                JSONObject obj = arrayBooks.getJSONObject(i);
                                BookData bookData = new BookData();
                                bookData.setTitle(obj.getString("title"));
                                bookData.setUrlToimage(obj.getString("coverLargeUrl"));
                                bookData.setDescription(obj.getString("description"));
                                books.add(bookData);
                            }
                            BookData bookData = new BookData();

                            books.add(bookData);
                            mAdapter=new MyAdapter(books);
                            mRecyclerview.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

}

//내가참고한 사이트:https://medium.com/@nanyoung18/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%8A%A4%ED%8A%9C%EB%94%94%EC%98%A4-%EA%B8%B0%EC%B4%88-4-%EB%89%B4%EC%8A%A4-%EC%95%B1-%EB%A7%8C%EB%93%A4%EA%B8%B0-2-7e9ab008630c