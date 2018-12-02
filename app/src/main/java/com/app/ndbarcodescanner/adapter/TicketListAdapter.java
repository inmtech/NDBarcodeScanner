package com.app.ndbarcodescanner.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.ndbarcodescanner.R;
import com.app.ndbarcodescanner.database.Ticket;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.NoteListViewHolder> {

    private Context mContext;
    private List<Ticket> arrayListNote;
    private List<Ticket> contactListFiltered;

    public TicketListAdapter(Context mContext, List<Ticket> arrayListNote) {
        this.mContext = mContext;
        this.arrayListNote = arrayListNote;
        this.contactListFiltered = arrayListNote;
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.raw_list, parent, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteListViewHolder holder, int position) {
        final Ticket noteData = contactListFiltered.get(position);
        holder.txtviewNoteTitle.setText("" + noteData.getTicketCode());
        holder.tvNo.setText(""+Integer.parseInt(String.valueOf(position+1))+". ");
        if(noteData.isChecked())
        {
            holder.imgStatus.setImageResource(R.drawable.right);
        }
        else
        {
            holder.imgStatus.setImageResource(R.drawable.cross);
        }
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }


    public class NoteListViewHolder extends RecyclerView.ViewHolder {

        public TextView txtviewNoteTitle;
        public ImageView imgStatus;
        public CardView cardView;
        public TextView tvNo;

        public NoteListViewHolder(View itemView) {
            super(itemView);
            tvNo=itemView.findViewById(R.id.tvNo);
            txtviewNoteTitle = itemView.findViewById(R.id.txtviewNoteTitle);
            cardView = itemView.findViewById(R.id.card);
            imgStatus=itemView.findViewById(R.id.imgStatus);
        }
    }
}
