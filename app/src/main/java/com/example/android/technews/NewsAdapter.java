package com.example.android.technews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news){
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
            News news = getItem(position);

            Date dateObject = new Date();

            TextView webTitle = convertView.findViewById(R.id.title_text_view);
            TextView sectionName = convertView.findViewById(R.id.section_text_view);
            TextView webPublicationDate = convertView.findViewById(R.id.date_text_view);
            TextView dateView = convertView.findViewById(R.id.date_text_view);
            String formattedDate = formatDate(dateObject);
            dateView.setText(formattedDate);
            TextView webUrl = convertView.findViewById(R.id.webUrl_text_view);
            TextView author = convertView.findViewById(R.id.author_text_view);


            webTitle.setText(news.getWebTitle());
            sectionName.setText(news.getSectionName());
            webPublicationDate.setText(news.getWebPublicationDate());
            webUrl.setText(news.getWebUrl());
            if (news.getAuthor() == null){
                author.setVisibility(View.GONE);
            } else {
                author.setText(news.getAuthor());
            }




        return convertView;
   }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

   public void setItems(List<News> news) {
        this.clear();
        this.addAll(news);
        notifyDataSetChanged();
   }
}
