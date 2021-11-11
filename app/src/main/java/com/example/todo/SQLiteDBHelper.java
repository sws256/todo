package com.example.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper
{

    private SQLiteDB mDbHelper;

    public SQLiteDBHelper(Context context)
    {
        mDbHelper = SQLiteDB.getInstance(context);
    }

    public Boolean excuteQuery(String sql)
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getWritableDatabase();
            mDb.execSQL(sql);
            mDbHelper.close();
            return true;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "excuteQuery >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<SubjectInfo> loadSubjectList()
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT subjectName FROM SubjectList;";

            List<SubjectInfo> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    SubjectInfo info = new SubjectInfo();
                    info.setSubjectName(cursor.getString(0));
                    list.add(info);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getSubjectData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<LectureInfo> loadLectureList(String date)
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT subjectName, lectureName, isDone FROM LectureList WHERE startDate <= "+date+" AND endDate >= "+date+";";
            System.out.println(sql);
            List<LectureInfo> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    LectureInfo info = new LectureInfo(cursor.getString(0),cursor.getString(1),Boolean.valueOf(cursor.getString(2)));

                    list.add(info);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getLectureData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List<Assignment> loadAssignmentList(String date)
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT subjectName, assignmentName, isDone FROM AssignmentList WHERE startDate <= "+date+" AND endDate >= "+date+";";

            List<Assignment> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    Assignment info = new Assignment(cursor.getString(0),cursor.getString(1),Boolean.valueOf(cursor.getString(2)));

                    list.add(info);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getAssignmentData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List<ExamInfo> loadExamList(String date)
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT subjectName, examName FROM ExamList WHERE date == "+date+";";

            List<ExamInfo> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    ExamInfo info = new ExamInfo(cursor.getString(0),cursor.getString(1));

                    list.add(info);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getExamData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List<AlarmInfo> loadAlarmList()
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT * FROM AlarmList;";

            List<AlarmInfo> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    AlarmInfo info = new AlarmInfo(false,cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

                    list.add(info);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getAlarmData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List<String> loadFriendsList()
    {
        try
        {
            SQLiteDatabase mDb = mDbHelper.getReadableDatabase();
            String sql = "SELECT * FROM Friends;";

            List<String> list = new ArrayList();

            Cursor cursor = mDb.rawQuery(sql, null);
            if (cursor!=null)
            {
                // 칼럼의 마지막까지
                while( cursor.moveToNext() ) {

                    String id = cursor.getString(0);

                    list.add(id);
                }
            }
            cursor.close();
            mDbHelper.close();
            return list;
        }
        catch (SQLException mSQLException)
        {
            Log.e("DataAdapter", "getFriendsData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}