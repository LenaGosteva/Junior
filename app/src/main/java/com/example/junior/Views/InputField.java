package com.example.junior.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.junior.R;
import com.example.junior.databinding.ViewInputFieldBinding;


public class InputField extends LinearLayout {

    ViewInputFieldBinding binding;

    public InputField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public InputField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public InputField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {

        binding = ViewInputFieldBinding.inflate(LayoutInflater.from(getContext()), this, true);
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.InputField, 0, 0);
        binding.textInputOfField.setText("title");
        try {
            String title = attributes.getString(R.styleable.InputField_title);
            binding.textOfField.setHint(title);

            int inputType = attributes.getInt(R.styleable.InputField_android_inputType, InputType.TYPE_NULL);
            binding.textInputOfField.setInputType(inputType);
        } finally {
            attributes.recycle();
        }
    }

    public String getText() {
        return binding.textInputOfField.getText().toString();
    }

    public void setText(String str) {
        binding.textInputOfField.setText(str);
    }public void setHint(String str) {
        binding.textOfField.setHint(str);
    }

    public void setListenerOfChanges(TextWatcher watcher){
        binding.textInputOfField.addTextChangedListener(watcher);
    }
}