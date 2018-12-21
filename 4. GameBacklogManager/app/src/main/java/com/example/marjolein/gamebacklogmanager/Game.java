package com.example.marjolein.gamebacklogmanager;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "game")
public class Game implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "gametitle")
    private String gameTitle;

    @ColumnInfo(name = "platform")
    private String gamePlatform;

    @ColumnInfo(name = "notes")
    private String gameNotes;

    @ColumnInfo(name = "status")
    private String gameStatus;

    @ColumnInfo(name = "date")
    private String gameDate;

    public Game(String gameTitle, String gamePlatform, String gameNotes, String gameStatus, String gameDate) {
        this.gameTitle = gameTitle;
        this.gamePlatform = gamePlatform;
        this.gameNotes = gameNotes;
        this.gameStatus = gameStatus;
        this.gameDate = gameDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public void setGamePlatform(String gamePlatform) {
        this.gamePlatform = gamePlatform;
    }

    public String getGameNotes() {
        return gameNotes;
    }

    public void setGameNotes(String gameNotes) {
        this.gameNotes = gameNotes;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameTitle='" + gameTitle + '\'' +
                ", gamePlatform='" + gamePlatform + '\'' +
                ", gameNotes='" + gameNotes + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                ", gameDate='" + gameDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte)0);
        }
        else{
            dest.writeByte((byte)1);
            dest.writeLong(this.id);
        }
        dest.writeString(this.gameTitle);
        dest.writeString(this.gamePlatform);
        dest.writeString(this.gameNotes);
        dest.writeString(this.gameStatus);
        dest.writeString(this.gameDate);
    }

    protected Game(Parcel in) {
        if(in.readByte() == 0){
            this.id = null;
        }else{
            id = in.readLong();
        }
        this.gameTitle = in.readString();
        this.gamePlatform = in.readString();
        this.gameNotes = in.readString();
        this.gameStatus = in.readString();
        this.gameDate = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
