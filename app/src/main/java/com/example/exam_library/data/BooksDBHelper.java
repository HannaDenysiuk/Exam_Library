package com.example.exam_library.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BooksDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "library.db"; // назва бд
    private static final int DB_VERSION = 1; // версия базы данных
    public static final String TABLE = "cars"; // название таблицы в бд
    // names of columns of table
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_PUBLISHER = "publisher";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PAGES = "pages";

    public BooksDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_AUTHOR + " TEXT, " + COLUMN_TITLE +
                " TEXT, " + COLUMN_GENRE + " TEXT, " + COLUMN_PUBLISHER + " TEXT, "
                + COLUMN_YEAR + " INTEGER, " + COLUMN_PAGES + " INTEGER);");
        // добавление начальных данных
        for (Book book: Book.defaultCollection()
        ) {
            sqLiteDatabase.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_AUTHOR + ", "
                    + COLUMN_TITLE +  ", " + COLUMN_GENRE + ", "+ COLUMN_PUBLISHER + ", "
                    + COLUMN_YEAR + ", "+ COLUMN_PAGES + ") " +
                    "VALUES ('" + book.getAuthor() +"', '" +  book.getTitle() + "', '" +
                     book.getGenre() + "', '"+  book.getPublishingHouse() + "', "+
                    book.getYear() + ", " + book.getPages() + ");"
            );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public Book getBookById(long id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE +
                " WHERE " + COLUMN_ID + "=?;", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Book book = new Book();
        book.setAuthor(cursor.getString(1));
        book.setTitle(cursor.getString(2));
        book.setGenre(cursor.getString(3));
        book.setPublishingHouse(cursor.getString(4));
        book.setYear(cursor.getInt(5));
        book.setPages(cursor.getInt(6));
        return book;
    }
}
