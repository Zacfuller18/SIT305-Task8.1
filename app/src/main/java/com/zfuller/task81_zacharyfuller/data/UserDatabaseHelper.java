package com.zfuller.task81_zacharyfuller.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.zfuller.task81_zacharyfuller.model.User;
import com.zfuller.task81_zacharyfuller.util.UserUtil;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    public UserDatabaseHelper(@Nullable Context context) {
        super(context, UserUtil.DATABASE_NAME, null, UserUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + UserUtil.TABLE_NAME + "(" + UserUtil.USERID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + UserUtil.USERNAME + " TEXT , " + UserUtil.PASSWORD + " TEXT , " + UserUtil.FULLNAME + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserUtil.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long InsertUser (User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserUtil.FULLNAME, user.getFullName());
        contentValues.put(UserUtil.USERNAME, user.getUsername());
        contentValues.put(UserUtil.PASSWORD, user.getPassword());

        long newRowId = db.insert(UserUtil.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public Integer GetUser(String userName, String userPassword)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserUtil.TABLE_NAME, new String[]{UserUtil.USERID}, UserUtil.USERNAME + "=? and " + UserUtil.PASSWORD + "=?", new String[]{userName, userPassword}, null, null, null);

        Integer userId = null;
        if(cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }

        cursor.close();
        db.close();
        return userId;
    }
}
