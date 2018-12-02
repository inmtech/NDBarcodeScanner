package com.app.ndbarcodescanner.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoNote {

    @Insert
    public void addTicket(List<Ticket> note);

    @Query("SELECT * FROM ticket")
    LiveData<List<Ticket>> getAllTicket();

    @Query("UPDATE ticket SET checked =:entry WHERE ticket_code =:code")
    void updateTicket(String code,boolean entry);

    @Query("SELECT * FROM ticket WHERE ticket_code =:ticketCode AND checked =:isChecked")
    boolean isValiedTicket(String ticketCode,boolean isChecked);

    @Query("SELECT COUNT(checked) FROM ticket WHERE checked = 1")
    int getNumberOfRows();
}
