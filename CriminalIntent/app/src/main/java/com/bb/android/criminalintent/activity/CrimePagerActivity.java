package com.bb.android.criminalintent.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bb.android.criminalintent.R;
import com.bb.android.criminalintent.fragment.CrimeFragment;
import com.bb.android.criminalintent.model.Crime;
import com.bb.android.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.bb.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mJumpToFirstButton;
    private Button mJumpToLastButton;

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        mJumpToLastButton = (Button) findViewById(R.id.jump_to_last);
        mJumpToFirstButton = (Button) findViewById(R.id.jump_to_first);

        mCrimes = CrimeLab.get(this).getCrimes();

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                mJumpToFirstButton.setVisibility(View.VISIBLE);
                mJumpToLastButton.setVisibility(View.VISIBLE);
                checkItem();
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });



        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }



        mJumpToFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
                checkItem();
            }
        });


        mJumpToLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(99);
                checkItem();
            }
        });
    }

    private void checkItem() {

        if (mViewPager.getCurrentItem() == 0) {
            mJumpToFirstButton.setVisibility(View.INVISIBLE);
            mJumpToLastButton.setVisibility(View.VISIBLE);
        }

        if (mViewPager.getCurrentItem() == 99) {
            mJumpToLastButton.setVisibility(View.INVISIBLE);
            mJumpToFirstButton.setVisibility(View.VISIBLE);
        }
    }
}
