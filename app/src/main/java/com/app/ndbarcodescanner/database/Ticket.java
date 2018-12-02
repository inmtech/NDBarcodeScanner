package com.app.ndbarcodescanner.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ticket")
public class Ticket  {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "ticket_code")
    private String ticketCode;

    public Ticket(String ticketCode, boolean checked) {
        this.ticketCode = ticketCode;
        this.checked = checked;
    }

    @ColumnInfo(name = "checked")
    private boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
