package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.viewholder> {
ArrayList<thing> things ;

    public recycleradapter(ArrayList<thing> things) {
        this.things = things;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new viewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thing , viewGroup , false ))  ;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
      thing thing = things.get(i);
      viewholder.name.setText(thing.getName());
      viewholder.age.setText(thing.getAge());
    }

    @Override
    public int getItemCount() {
        return things.size();
    }

    class   viewholder extends  RecyclerView.ViewHolder {
 TextView name , age ;
      public viewholder(@NonNull View itemView) {
          super(itemView);
          name = itemView.findViewById(R.id.nameid);
          age = itemView.findViewById(R.id.ageid);
      }
  }

}
