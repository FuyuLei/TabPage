package com.tabpageapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tabpageapp.R;

public class GameResultFragment extends Fragment {

    private EditText mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;

    public GameResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_game_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mEdtCountSet = (EditText) getView().findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) getView().findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) getView().findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) getView().findViewById(R.id.edtCountDraw);
    }

    public void showResult(int miCountSet,int miCountPlayerWin,int miCountComWin,int miCountDraw) {
        mEdtCountSet.setText(Integer.toString(miCountSet));
        mEdtCountPlayerWin.setText(Integer.toString(miCountPlayerWin));
        mEdtCountComWin.setText(Integer.toString(miCountComWin));
        mEdtCountDraw.setText(Integer.toString(miCountDraw));
    }
}