package com.example.myapplication_b;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<BookData> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_title;
        public TextView TextView_content;
        public ImageView ImageView_title;

        public MyViewHolder(View v){
            super(v);
            TextView_title=v.findViewById(R.id.TextView_title);
            TextView_content=v.findViewById(R.id.TextView_content);
            ImageView_title=v.findViewById(R.id.ImageView_title);
        }
    }
    public MyAdapter(List<BookData> myDataset){
        mDataset=myDataset;

    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RelativeLayout v=(RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_books,parent,false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        BookData books=mDataset.get(position);
        holder.TextView_title.setText(books.getTitle());
        holder.TextView_content.setText(books.getDescription());

        Uri uri=Uri.parse(books.getUrlToimage());
        holder.ImageView_title.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mDataset==null?0:mDataset.size();
    }

}
