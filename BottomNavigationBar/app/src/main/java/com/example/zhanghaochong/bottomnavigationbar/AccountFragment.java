package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.User;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by AmberWang on 17/3/5.
 */
//This fragment is to manage and view the account information of the user, including text info edition and photo upload
public class AccountFragment extends Fragment implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    //private static final int RESULT_OK = 1;
    private ImageView imageToUpload;
    // private Button uploadBtn;
    private Button editBtn;
    // private Button regBtn;

    private EditText name_text;
    private EditText phone_text;
    private EditText email_text;
    private EditText pt_text;
    private EditText birth_text;
    private EditText pswd_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account, container, false);

        //mRef = new Firebase("https://tibaapplication.firebaseio.com/");
        User user = new User();
        imageToUpload = (ImageView) v.findViewById(R.id.accountImage);

        editBtn = (Button) v.findViewById(R.id.info_update);

        name_text = (EditText)v.findViewById(R.id.name_edit);
        phone_text = (EditText) v.findViewById(R.id.phone_edit);
        email_text = (EditText) v.findViewById(R.id.email_edit);
        pt_text = (EditText) v.findViewById(R.id.pt_edit);
        birth_text = (EditText) v.findViewById(R.id.birth_edit);
        pswd_text = (EditText) v.findViewById(R.id.pswd_edit);

        name_text.setText(user.getUsername());
        phone_text.setText(user.getPhone());
        email_text.setText(user.getEmail());
        pt_text.setText(user.getPt());
        birth_text.setText(user.getBirth());

        imageToUpload.setOnClickListener(this);
        editBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.accountImage:
                String choose = "Please choose a photo";
                Toast.makeText(AccountFragment.this.getActivity(), choose, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case R.id.info_update:
                String update = "Update your account information";
                Toast.makeText(AccountFragment.this.getActivity(), update, Toast.LENGTH_LONG).show();
                User user = new User();
                name_text.setText("");
                phone_text.setText("");
                email_text.setText("");
                pt_text.setText("");
                birth_text.setText("");
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
