package com.bb.android.criminalintent.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bb.android.criminalintent.R;
import com.bb.android.criminalintent.model.PictureUtils;

import java.io.File;

public class DetailPhotoFragment extends DialogFragment {

    private static final String PHOTO_FILE = "file";
    private ImageView mImageView;

    public static DetailPhotoFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_FILE, file);
        DetailPhotoFragment fragment = new DetailPhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        File file = (File) getArguments().getSerializable(PHOTO_FILE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, null);

        mImageView = (ImageView) v.findViewById(R.id.crime_detailed_photo);

        Bitmap bitmap = PictureUtils.getScaledBitmap(file.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
