package com.example.foro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.foro.Db.Publication;

import java.util.List;

public class PublicationAdapter extends BaseAdapter {

    private Context context;
    private List<Publication> publications;

    public PublicationAdapter(Context context, List<Publication> publications) {
        this.context = context;
        this.publications = publications;
    }

    @Override
    public int getCount() {
        return publications.size();
    }

    @Override
    public Object getItem(int position) {
        return publications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return publications.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_publication, parent, false);
        }

        Publication pub = publications.get(position);

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtMessage = convertView.findViewById(R.id.txtMessage);
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtTitle.setText(pub.getTitle());
        txtMessage.setText(pub.getMessage());
        txtDate.setText("Publicado por: " + pub.getUserName() + " | " + pub.getTime());

        return convertView;
    }

}
