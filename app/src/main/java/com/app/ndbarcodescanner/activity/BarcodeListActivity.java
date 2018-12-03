package com.app.ndbarcodescanner.activity;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.app.ndbarcodescanner.adapter.*;
import com.app.ndbarcodescanner.R;
import com.app.ndbarcodescanner.database.MyAppDatabase;
import com.app.ndbarcodescanner.database.Ticket;
import com.app.ndbarcodescanner.util.EqualSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BarcodeListActivity extends AppCompatActivity {

    public static MyAppDatabase myAppDatabase;
    private RecyclerView recyclerviewNoteList;
    private List<Ticket> arrayListNote=new ArrayList<>();
    public TicketListAdapter noteListAdapter;
    private TextView tvStaus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_list);
        getSupportActionBar().hide();
        tvStaus=findViewById(R.id.tvStaus);

        recyclerviewNoteList = findViewById(R.id.recyclerviewNoteList);
        recyclerviewNoteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerviewNoteList.addItemDecoration(new EqualSpacingItemDecoration(30));
        noteListAdapter=new TicketListAdapter(this,arrayListNote);
        recyclerviewNoteList.setAdapter(noteListAdapter);
        MyAppDatabase.getInstance(this).daoNote().getAllTicket().observe(this, new Observer<List<Ticket>>() {
            @Override
            public void onChanged(@Nullable List<Ticket> notes) {
                arrayListNote.clear();
                arrayListNote.addAll(notes);
                noteListAdapter.notifyDataSetChanged();
                tvStaus.setText("Total Ticket = "+arrayListNote.size()+" Ticket Varified ="+MyAppDatabase.getInstance(BarcodeListActivity.this).daoNote().getNumberOfRows());
            }
        });
    }
}
