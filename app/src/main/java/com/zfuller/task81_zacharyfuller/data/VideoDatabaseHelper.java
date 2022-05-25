package com.zfuller.task81_zacharyfuller.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zfuller.task81_zacharyfuller.model.Video;
import com.zfuller.task81_zacharyfuller.util.UserUtil;
import com.zfuller.task81_zacharyfuller.util.VideoUtil;

import java.util.ArrayList;

public class VideoDatabaseHelper extends SQLiteOpenHelper {
    public VideoDatabaseHelper(@Nullable Context context) {
        super(context, VideoUtil.DATABASE_NAME, null, VideoUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_VIDEO_TABLE = "CREATE TABLE " + VideoUtil.TABLE_NAME + "(" + VideoUtil.VIDEOID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + VideoUtil.URL + " TEXT , " + UserUtil.USERID + " INTEGER, FOREIGN KEY (" + UserUtil.USERID + ") REFERENCES " + UserUtil.TABLE_NAME
                + "(" + VideoUtil.VIDEOID + ") )";
        sqLiteDatabase.execSQL(CREATE_VIDEO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VideoUtil.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long InsertVideo(Video video) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserUtil.USERID, video.getUserId());
        contentValues.put(VideoUtil.URL, video.getURL());

        long newRowId = db.insert(VideoUtil.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public ArrayList<Video> GetVideos(int userId) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + VideoUtil.VIDEOID + ", " + UserUtil.USERID + ", " + VideoUtil.URL + " FROM " + VideoUtil.TABLE_NAME + " WHERE " + UserUtil.USERID + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        ArrayList<Video> videos = new ArrayList<Video>();
        while (cursor.moveToNext()) {
            Video video = new Video(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
            videos.add(video);
        }
        cursor.close();
        db.close();
        return videos;
    }

}
