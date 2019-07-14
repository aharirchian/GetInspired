package com.example.mafr.p3;

import android.media.Image;

public class Controller {
    private MainActivity mainActivity;
    private ShowQuote showQuote;
    private StartPage startPage;

    public Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        initializeStartPage();
        //initializeQuoteFragment();
    }

    private void initializeStartPage() {
        startPage = (StartPage)mainActivity.getFragment("StartPage");
        if(startPage == null) {
            startPage = new StartPage();
        }

        startPage.setController(this);
        mainActivity.setFragment(startPage);
    }


    public void initializeQuoteFragment() {
        showQuote = (ShowQuote)mainActivity.getFragment("ShowQuote");
        if(showQuote==null) {
            showQuote = new ShowQuote();
        }
        showQuote.setController(this);
        mainActivity.setFragment(showQuote);
    }
}
