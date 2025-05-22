package com.example.mybtl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class MovieDAO {
    // Phương thức để lấy tất cả movies
    public static ArrayList<Movie> getAll(Context context) {
        ArrayList<Movie> movies = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Movies", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int image = cursor.getInt(2);
            String content = cursor.getString(3);
            String category = cursor.getString(4);
            int trailer = cursor.getInt(5);
            String premiere = cursor.getString(6);
            int price = cursor.getInt(7);

            Movie movie = new Movie(id, name, image, content, category, trailer, premiere, price);
            movies.add(movie);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return movies;
    }

    // Phương thức lọc phim theo thể loại
    public static ArrayList<Movie> filterByCategory(Context context, String category) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        ArrayList<Movie> allMovies = getAll(context);

        for (Movie movie : allMovies) {
            if (movie.getCategory().equalsIgnoreCase(category)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
}
