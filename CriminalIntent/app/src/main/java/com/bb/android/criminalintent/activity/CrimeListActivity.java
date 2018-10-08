package com.bb.android.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.bb.android.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}