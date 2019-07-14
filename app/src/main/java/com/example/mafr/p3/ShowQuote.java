package com.example.mafr.p3;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowQuote extends android.support.v4.app.Fragment {

    private Controller controller;
    TextView tvQuote;
    TextView tvWiki;
    TextView tvAuthor;
    Button btnNext;
    ImageView imageView;
    ImageView ivWikiBack;
    boolean first = true;
    boolean changedPlace = false;
    int turn  = 1;

    public ShowQuote() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_quote, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {

        imageView = view.findViewById(R.id.ivStart);
        ObjectAnimator fadeinPic = ObjectAnimator.ofFloat(imageView, "alpha",  0f, 1f);
        fadeinPic.setDuration(500);
        fadeinPic.start();

        ivWikiBack = view.findViewById(R.id.ivWikiBack);
        tvWiki = view.findViewById(R.id.tvWiki);
        tvQuote = view.findViewById(R.id.tvQuote);
        tvAuthor = view.findViewById(R.id.tvAuthor);
        btnNext = view.findViewById(R.id.btnNext);
        tvWiki.animate()
                .translationYBy(450)
                .setDuration(1)
                .start();
        fadeinAndout();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeinAndout();
            }
        });

        tvAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(changedPlace)){
                    tvWiki.setAlpha(1f);
                changedPlace = true;
                ObjectAnimator fadeOutQuote = ObjectAnimator.ofFloat(tvQuote, "textSize",  30, 22);
                fadeOutQuote.setDuration(1000);
                fadeOutQuote.start();
                tvQuote.animate()
                        .translationYBy(-500)
                        .setDuration(1000)
                        .start();
                tvWiki.setText("Den här artikeln handlar om bibelöversättaren och helgonet Hieronymus. För den nederländske konstnären med samma namn, se artikeln Hieronymus Bosch.");
                tvAuthor.animate()
                        .translationYBy(-650)
                        .setDuration(1000)
                        .start();
                tvWiki.animate()
                        .translationYBy(-950)
                        .setDuration(1000)
                        .start();

                ObjectAnimator fadeInBack = ObjectAnimator.ofFloat(ivWikiBack, "alpha",  0f, .7f);
                fadeInBack.setDuration(1000);
                fadeInBack.start();

            }
            }
        });

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }





    public void fadeinAndout(){

        //FADE OUT
        ObjectAnimator fadeOutQuote = ObjectAnimator.ofFloat(tvQuote, "alpha",  1f, 0f);
        fadeOutQuote.setDuration(1000);

        ObjectAnimator fadeOutAuthor = ObjectAnimator.ofFloat(tvAuthor, "alpha",  1f, 0f);
        fadeOutAuthor.setDuration(1000);

        ObjectAnimator fadeoutBack = ObjectAnimator.ofFloat(ivWikiBack, "alpha", 1f, 0f);
        fadeoutBack.setDuration(1000);

        ObjectAnimator fadeoutWiki = ObjectAnimator.ofFloat(tvWiki, "alpha", 1f, 0f);
        fadeoutWiki.setDuration(1000);

        //FADE IN
        ObjectAnimator fadeInQuote = ObjectAnimator.ofFloat(tvQuote, "alpha",  0f, 1f);
        fadeInQuote.setDuration(1000);

        ObjectAnimator fadeInAuthor = ObjectAnimator.ofFloat(tvAuthor, "alpha",  0f, 1f);
        fadeInAuthor.setDuration(1000);

        if(first){
            AnimatorSet s = new AnimatorSet();
            s.play(fadeInAuthor).after(fadeInQuote);

            s.start();
            first = false;
        }
        else{
            if(changedPlace){
                AnimatorSet sa = new AnimatorSet();
                sa.play(fadeOutAuthor).with(fadeOutQuote).with(fadeoutBack).with(fadeoutWiki);
                sa.start();

                fadeOutAuthor.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) { }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        reset();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) { }

                    @Override
                    public void onAnimationRepeat(Animator animation) { }
                });
            }
            else{
                AnimatorSet sa = new AnimatorSet();
                sa.play(fadeOutAuthor).with(fadeOutQuote);
                sa.start();

                fadeOutAuthor.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) { }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        reset();
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) { }

                    @Override
                    public void onAnimationRepeat(Animator animation) { }
                });

            }
        }
    }


    public void reset() {
        //FADE IN
        tvQuote.setText("new text" + turn + "new text" + turn + "new text" + turn + "new text" + turn+ "new text" + turn + "new text" + turn + "new text" + turn+ "new text" + turn + "new text" + turn + "new text" + turn);
        tvAuthor.setText("new Author" + turn);
        turn++;


        ObjectAnimator fadeInQuote = ObjectAnimator.ofFloat(tvQuote, "alpha", 0f, 1f);
        fadeInQuote.setDuration(1000);


        ObjectAnimator fadeInAuthor = ObjectAnimator.ofFloat(tvAuthor, "alpha", 0f, 1f);
        fadeInAuthor.setDuration(1000);



        AnimatorSet sa = new AnimatorSet();
        sa.play(fadeInQuote).before(fadeInAuthor);
        sa.start();

        if (changedPlace) {

            tvQuote.setTextSize(30);
            tvQuote.animate()
                    .translationYBy(500)
                    .setDuration(1)
                    .start();
            tvAuthor.animate()
                    .translationYBy(650)
                    .setDuration(1)
                    .start();
            tvWiki.animate()
                    .translationYBy(950)
                    .setDuration(1)
                    .start();
            changedPlace = false;
        }
    }


}
