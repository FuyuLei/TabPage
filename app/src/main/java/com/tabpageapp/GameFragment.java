package com.tabpageapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private TextView mTxtResult;
    private ImageView mImgViewComPlay;
    private ImageButton mImgBtnScissors, mImgBtnStone, mImgBtnPaper;
    public int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImgViewComPlay = (ImageView) getView().findViewById(R.id.imgViewComPlay);
        mTxtResult = (TextView) getView().findViewById(R.id.txtResult);
        mImgBtnScissors = (ImageButton) getView().findViewById(R.id.imgBtnScissors);
        mImgBtnStone = (ImageButton) getView().findViewById(R.id.imgBtnStone);
        mImgBtnPaper = (ImageButton) getView().findViewById(R.id.imgBtnPaper);

        mImgBtnScissors.setOnClickListener(imgBtnScissorsOnClick);
        mImgBtnStone.setOnClickListener(imgBtnStoneOnClick);
        mImgBtnPaper.setOnClickListener(imgBtnPaperOnClick);

        ((MainActivity) getActivity()).sendToResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
    }

    private View.OnClickListener imgBtnScissorsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            miCountSet++;

            // 1 – 剪刀, 2 – 石頭, 3 – 布.
            if (iComPlay == 1) {
                mImgViewComPlay.setImageResource(R.drawable.scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
                miCountDraw++;
                ((MainActivity)getActivity()).changeIcon(false, false, true);
            } else if (iComPlay == 2) {
                mImgViewComPlay.setImageResource(R.drawable.stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
                miCountComWin++;
                ((MainActivity)getActivity()).changeIcon(false, true, false);
            } else {
                mImgViewComPlay.setImageResource(R.drawable.paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
                miCountPlayerWin++;
                ((MainActivity)getActivity()).changeIcon(true, false, false);
            }
            ((MainActivity) getActivity()).sendToResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
        }
    };

    private View.OnClickListener imgBtnStoneOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            miCountSet++;

            // 1 – 剪刀, 2 – 石頭, 3 – 布.
            if (iComPlay == 1) {
                mImgViewComPlay.setImageResource(R.drawable.scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
                miCountPlayerWin++;
                ((MainActivity)getActivity()).changeIcon(true, false, false);
            } else if (iComPlay == 2) {
                mImgViewComPlay.setImageResource(R.drawable.stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
                miCountDraw++;
                ((MainActivity)getActivity()).changeIcon(false, false, true);
            } else {
                mImgViewComPlay.setImageResource(R.drawable.paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
                miCountComWin++;
                ((MainActivity)getActivity()).changeIcon(false, true, false);
            }
            ((MainActivity) getActivity()).sendToResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
        }
    };

    private View.OnClickListener imgBtnPaperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            // 決定電腦出拳.
            int iComPlay = (int) (Math.random() * 3 + 1);
            miCountSet++;

            // 1 – 剪刀, 2 – 石頭, 3 – 布.
            if (iComPlay == 1) {
                mImgViewComPlay.setImageResource(R.drawable.scissors);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_lose));
                miCountComWin++;
                ((MainActivity)getActivity()).changeIcon(false, true, false);
            } else if (iComPlay == 2) {
                mImgViewComPlay.setImageResource(R.drawable.stone);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_win));
                miCountPlayerWin++;
                ((MainActivity)getActivity()).changeIcon(true, false, false);
            } else {
                mImgViewComPlay.setImageResource(R.drawable.paper);
                mTxtResult.setText(getString(R.string.result) +
                        getString(R.string.player_draw));
                miCountDraw++;
                ((MainActivity)getActivity()).changeIcon(false, false, true);
            }
            ((MainActivity) getActivity()).sendToResult(miCountSet, miCountPlayerWin, miCountComWin, miCountDraw);
        }
    };

}
