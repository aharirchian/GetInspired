package com.example.mafr.p3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */

public class StartPage extends android.support.v4.app.Fragment {
    private Controller controller;
    private ImageView ivStart;
    private Button btnStart;

    public StartPage() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {
        ivStart = view.findViewById(R.id.ivStart);
        btnStart = view.findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new ButtonListener());

        ObjectAnimator fadeInPictureStart = ObjectAnimator.ofFloat(ivStart, "alpha", 0f, 1f);
        ObjectAnimator fadeInButtonStart = ObjectAnimator.ofFloat(btnStart, "alpha", 0f, 1f);

        AnimatorSet as = new AnimatorSet();
        as.play(fadeInPictureStart).before(fadeInButtonStart);
        as.setDuration(2000);
        as.start();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

        private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            ObjectAnimator fadeout = ObjectAnimator.ofFloat(ivStart, "alpha", 1f, 0f);
            ObjectAnimator fadeout2 = ObjectAnimator.ofFloat(btnStart, "alpha", 1f, 0f);
            fadeout.setDuration(2000);
            fadeout.setDuration(2000);


            AnimatorSet sa = new AnimatorSet();
            sa.play(fadeout).with(fadeout2);
            sa.start();

            fadeout.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }
                @Override
                public void onAnimationEnd(Animator animation) {
                    controller.initializeQuoteFragment();
                }
                @Override
                public void onAnimationCancel(Animator animation) { }

                @Override
                public void onAnimationRepeat(Animator animation) { }
            });



        }
    }
}
