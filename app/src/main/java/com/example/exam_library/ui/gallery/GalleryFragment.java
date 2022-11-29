package com.example.exam_library.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exam_library.R;
import com.example.exam_library.data.BooksDBHelper;
import com.example.exam_library.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    private BooksDBHelper dbHelper;
    private SimpleCursorAdapter cursorAdapter;
    private ListView listView;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dbHelper = new BooksDBHelper(getContext());
        listView = binding.listGallery;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               viewBook(l);
            }
        });
        refreshListData();
        return root;
    }

    private void viewBook(long l) {
        Bundle bundle = new Bundle();
        bundle.putLong("bookId", l);
        NavHostFragment.findNavController(GalleryFragment.this)
                .navigate(R.id.action_nav_gallery_to_addEditFragment, bundle);
    }

    private void refreshListData() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor bookCursor = database.rawQuery("SELECT * FROM " +
                BooksDBHelper.TABLE + ";", null);
        String[] dbFields = {BooksDBHelper.COLUMN_ID,
                BooksDBHelper.COLUMN_AUTHOR, BooksDBHelper.COLUMN_TITLE, BooksDBHelper.COLUMN_GENRE,
        BooksDBHelper.COLUMN_PUBLISHER, BooksDBHelper.COLUMN_YEAR, BooksDBHelper.COLUMN_PAGES};
        int[] dbFieldsViews = {R.id.tvId, R.id.tv_author, R.id.tv_title, R.id.tv_genre,
                R.id.tv_publisher, R.id.tv_year, R.id.tv_pages};
         cursorAdapter= new SimpleCursorAdapter(getContext(), R.layout.book_item, bookCursor,
                dbFields, dbFieldsViews, 0);
        listView.setAdapter(cursorAdapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}