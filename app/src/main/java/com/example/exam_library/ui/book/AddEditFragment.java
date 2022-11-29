package com.example.exam_library.ui.book;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.exam_library.R;
import com.example.exam_library.data.Book;
import com.example.exam_library.data.BooksDBHelper;
import com.example.exam_library.databinding.FragmentAddEditBinding;

public class AddEditFragment extends Fragment {
    private FragmentAddEditBinding binding;
    private BooksDBHelper dbHelper;
    private EditText etAuthor;
    private EditText etTitle;
    private EditText etGenre;
    private EditText etPublisher;
    private EditText etYear;
    private EditText etPages;

    public AddEditFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new BooksDBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        etAuthor = binding.tvAuthor;
        etTitle = binding.tvTitle;
        etGenre = binding.tvGenre;
        etPublisher = binding.tvPublisher;
        etYear = binding.tvYear;
        etPages = binding.tvPages;

        if (getArguments() != null){
            if (getArguments().containsKey("bookId")){
                long id = getArguments().getLong("bookId");
                Book book = dbHelper.getBookById(id);
                etAuthor.setText(book.getAuthor());
                etTitle.setText(book.getTitle());
                etGenre.setText(book.getGenre());
                etPublisher.setText(book.getPublishingHouse());
                etYear.setText(String.valueOf(book.getYear()));
                etPages.setText(String.valueOf(book.getPages()));
            }
        }
        return view;
    }
}