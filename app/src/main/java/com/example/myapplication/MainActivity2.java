package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView rv ;
    Boolean end = false ;
    recycleradapter rva =  new recycleradapter(things); ;
    Boolean isloading = false ;
   // String key = null ;
    ProgressBar prgrsbr;
    DocumentSnapshot key = null ;
   public static ArrayList<thing> things = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv = findViewById(R.id.recyclerView);
        prgrsbr = findViewById(R.id.prgrsbrid);
        rv.setAdapter(rva);
        rv.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this) ;
       lm.setReverseLayout(true);
        rv.setLayoutManager(lm);
        things.clear();

addshit();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager =  (LinearLayoutManager)  rv.getLayoutManager();
                int items = linearLayoutManager.getItemCount() ;
                int lastitem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if(items <lastitem+3 ){
                    if (!isloading){

                        isloading=true ;
                        addshit();



                    }

                }
            }
        });


    }






  /*  private final EventListener<QuerySnapshot> eventListener = (value , error) -> {
        if (error != null ) {
            return ;
        }




        if (value != null ){
            final Handler handler = new Handler();
            prgrsbr.setVisibility(View.VISIBLE);
            for (DocumentChange documentChange : value.getDocumentChanges()){
                if(documentChange.getType() == DocumentChange.Type.ADDED ){
                    things.add(new thing(documentChange.getDocument().getString("name" ), documentChange.getDocument().getString("age"))) ;
                    key = documentChange.getDocument().getId();

                }
            }

            rva.notifyDataSetChanged();
            isloading=false ;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    prgrsbr.setVisibility(View.INVISIBLE);
                }
            }, 500);


        }
    } ;*/
    
    public void addshit(){
        get().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
               if (!isloading) {things.clear();}
               for (DocumentChange dc : value.getDocumentChanges()){
                   if(dc.getType() == DocumentChange.Type.ADDED){
                       things.add(new thing(dc.getDocument().getString("name" ), dc.getDocument().getString("age"))) ;
                       key =  dc.getDocument();
                   }



               }
               rva.notifyDataSetChanged();
               rva.notifyItemRangeInserted(things.size() , things.size());
               if(!isloading){
                   rv.smoothScrollToPosition(0);}
                isloading=false ;
             //  rv.smoothScrollToPosition(0);
            }

        });


    }
    
  /*  public void addshit(){
        get().addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                final Handler handler = new Handler();
                prgrsbr.setVisibility(View.VISIBLE);
                for (DocumentSnapshot documentChange : value.getDocuments()){

                        things.add(new thing(documentChange.getString("name" ), documentChange.getString("age"))) ;
                        key =  documentChange;


                }

                rva.notifyDataSetChanged();
                isloading=false ;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prgrsbr.setVisibility(View.INVISIBLE);
                    }
                }, 200);
            }
        }) ;
        
    }*/

    public Query get(){
        if (key== null ) {

            return FirebaseFirestore.getInstance().collection("thing").orderBy("timestamp"  , Query.Direction.DESCENDING ).limit(10) ;
        }
        else {
            return FirebaseFirestore.getInstance().collection("thing").orderBy("timestamp", Query.Direction.DESCENDING ).startAfter(key).limit(10);
        }
    }
    


}












































































        /*

        rva.notifyDataSetChanged();
        addshit();

rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager linearLayoutManager =  (LinearLayoutManager)  rv.getLayoutManager();
        int items = linearLayoutManager.getItemCount() ;
        int lastitem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        if(items<lastitem+3  ){
if (!isloading){

    isloading=true ;
    addshit();



}

        }
    }
});

    }
    public void addshit(){
        prgrsbr.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()) {
                    if (!snap.child("name").getValue().toString().equals("")) {
                        things.add( new thing(snap.child("name").getValue().toString(), snap.child("age").getValue().toString())   );
                        rva.notifyDataSetChanged();
                        key = snap.getKey();
                    }

                }

                isloading=false ;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prgrsbr.setVisibility(View.INVISIBLE);
                    }
                }, 500);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public Query get(){
        if (key== null ) {

            return FirebaseDatabase.getInstance().getReference().child("thing").orderByKey().limitToFirst(10);
        }
        else {
            return FirebaseDatabase.getInstance().getReference().child("thing").orderByKey().startAfter(key).limitToFirst(10);
        }
    }


}*/