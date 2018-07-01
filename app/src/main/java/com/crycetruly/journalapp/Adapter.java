package com.crycetruly.journalapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ContactsAdapter> {
List<Entry> entryList=new ArrayList<>();
private Context context;
    private static final String TAG = "Adapter";
    public Adapter(List<Entry> contactList, Context context) {
        this.entryList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactsAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view= inflater.inflate(R.layout.singleentry,parent,false);

        return new ContactsAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter holder, final int position) {
holder.title.setText(entryList.get(position).getTitle());
holder.grateful.setText(entryList.get(position).getGrateful());
holder.done.setText(entryList.get(position).getDone());

holder.mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(context,DetailsActivity.class);
     int id=entryList.get(position).getId();
        Log.d(TAG, "onClick:item at "+id);

        Intent intent=new Intent(context,DetailsActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);



    }
});

    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }


    public static class ContactsAdapter extends RecyclerView.ViewHolder{
private View mView;
private TextView title,grateful,done;
        public ContactsAdapter(View itemView) {
            super(itemView);
            mView=itemView;

            title=mView.findViewById(R.id.titleview);
            grateful=mView.findViewById(R.id.grateful);
            done=mView.findViewById(R.id.done);

        }
    }
}
