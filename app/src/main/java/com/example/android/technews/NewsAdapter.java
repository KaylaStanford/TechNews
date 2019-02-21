package com.example.android.technews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news){
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
            News news = getItem(position);

            TextView webTitle = listItemView.findViewById(R.id.title_text_view);
            TextView sectionName = listItemView.findViewById(R.id.section_text_view);
            TextView webPublicationDate = listItemView.findViewById(R.id.date_text_view);
            TextView webUrl = listItemView.findViewById(R.id.webUrl_text_view);
            TextView author = listItemView.findViewById(R.id.author_text_view);


            webTitle.setText(news.getWebTitle());
            sectionName.setText(news.getSectionName());
            webPublicationDate.setText(news.getWebPublicationDate());
            webUrl.setText(news.getWebUrl());
            if (news.getAuthor() == null){
                author.setVisibility(View.GONE);
            } else {
                author.setText(news.getAuthor());
            }

        return listItemView;
   }

   public void setItems(List<News> news) {
        this.clear();
        this.addAll(news);
        notifyDataSetChanged();
   }
}
