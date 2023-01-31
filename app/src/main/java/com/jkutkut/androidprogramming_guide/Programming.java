package com.jkutkut.androidprogramming_guide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Programming#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Programming extends Fragment {

    private static final String ARG_CODE = "code";
    private static final String ARG_FONT_SIZE = "fontSize";

    public static final int DEFAULT_FONT_SIZE = 40;

    private String code;
    private int fontSize;

    public Programming() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param code String with the wanted code.
     * @param fontSize Font size of the text.
     * @return A new instance of fragment programming.
     */
    public static Programming newInstance(String code, int fontSize) {
        Programming fragment = new Programming();
        Bundle args = new Bundle();
        args.putString(ARG_CODE, code);
        args.putInt(ARG_FONT_SIZE, fontSize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getString(ARG_CODE);
            fontSize = getArguments().getInt(ARG_FONT_SIZE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programming, container, false);

        TextView txtvCode = view.findViewById(R.id.txtvCode);
        txtvCode.setText(code);
        txtvCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        return view;
    }
}