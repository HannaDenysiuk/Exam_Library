package com.example.exam_library.ui.book;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        final Button submit = binding.btnSubmitAddEdit;
        final Button cancel = binding.btnCancelAddEdit;
        final Button delete = binding.btnDeleteAddEdit;

        cancel.setOnClickListener(v -> {
            goToGallery();
        });

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

                submit.setOnClickListener(v -> {
                    editBook(id);
                });
                //set visible if edit
                delete.setVisibility(View.VISIBLE);

                delete.setOnClickListener(v->{
                    dbHelper.databaseDelete(id);
                    goToGallery();
                });
            }
        } else {
            submit.setOnClickListener(v -> {
                addBook();
            });

            //set invisible if new Book
            delete.setVisibility(View.GONE);
        }
        return view;
    }

    private void addBook() {
        dbHelper.saveNewBook(new Book(etAuthor.getText().toString(), etTitle.getText().toString(),
                etGenre.getText().toString(), etPublisher.getText().toString(),
                Integer.parseInt(etYear.getText().toString()),
                Integer.parseInt(etPages.getText().toString())));
        goToGallery();
    }

    private void editBook(long id) {
        Book book = new Book(etAuthor.getText().toString(), etTitle.getText().toString(),
                etGenre.getText().toString(), etPublisher.getText().toString(),
                Integer.parseInt(etYear.getText().toString()),
                Integer.parseInt(etPages.getText().toString()));
                dbHelper.databaseUpdate(book, id);
        goToGallery();
    }
    private void goToGallery(){
        NavHostFragment.findNavController(AddEditFragment.this)
                .navigate(R.id.action_nav_add_edit_to_nav_gallery, null);
    }
}