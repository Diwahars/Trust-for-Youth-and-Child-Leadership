package com.nkana.app.adapter;

import android.support.v4.app.Fragment;

/**
 * Created by chokkar
 */
public class TabPagerItem {

    private final CharSequence mTitle;
    private final Fragment mFragment;

    public TabPagerItem(CharSequence title, Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public CharSequence getTitle() {
        return mTitle;
    }
}
