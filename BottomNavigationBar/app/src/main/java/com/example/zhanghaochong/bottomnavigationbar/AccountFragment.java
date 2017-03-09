package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by zhanghaochong on 17/3/5.
 */

public class AccountFragment extends Fragment implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    //private static final int RESULT_OK = 1;
    ImageView imageToUpload;
    Button uploadBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account, container, false);
        imageToUpload = (ImageView) v.findViewById(R.id.accountImage);

        uploadBtn = (Button) v.findViewById(R.id.uploadBtn);

        imageToUpload.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.accountImage:
               /* final ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
                if(zoomOut) {
                    Toast.makeText(v.getContext(), "NORMAL SIZE!", Toast.LENGTH_LONG).show();
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageView.setAdjustViewBounds(true);
                    zoomOut =false;
                }else{
                    Toast.makeText(v.getContext(), "FULLSCREEN!", Toast.LENGTH_LONG).show();
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    zoomOut = true;
                }*/
                break;
            case R.id.uploadBtn:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
        }
    }

}
