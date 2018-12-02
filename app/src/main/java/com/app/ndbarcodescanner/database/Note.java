package com.app.ndbarcodescanner.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "note")
public class Note implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    /*@ColumnInfo(name = "created_date")
    @TypeConverters(Converters.class)
    private Date created_date;
*/

    @ColumnInfo(name = "created_date")
    private String created_date;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "color_code")
    private String colorcode;

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public Note() {
    }

    public Note(int id, String name, String description, String date, String colorcode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_date = date;
        this.colorcode = colorcode;
    }

    public Note(String name, String description, String date,String colorcode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_date = date;
        this.colorcode=colorcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.created_date);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.colorcode);
    }

    public Note(Parcel in) {
        this.id = in.readInt();
        long tmpCreated_date = in.readLong();
        this.created_date = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.colorcode=in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
