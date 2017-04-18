package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.lang.UScript;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by AmberWang on 17/3/5.
 */
//This fragment is to manage and view the account information of the user, including text info edition and photo upload
public class AccountFragment extends Fragment implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView imageToUpload;
    private Button editBtn;


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

        imageToUpload = (ImageView) v.findViewById(R.id.accountImage);

        editBtn = (Button) v.findViewById(R.id.info_update);

        name_text = (EditText)v.findViewById(R.id.name_edit);
        phone_text = (EditText) v.findViewById(R.id.phone_edit);
        email_text = (EditText) v.findViewById(R.id.email_edit);
        pt_text = (EditText) v.findViewById(R.id.pt_edit);
        birth_text = (EditText) v.findViewById(R.id.birth_edit);
        pswd_text = (EditText) v.findViewById(R.id.pswd_edit);

        new pullUserInfo().execute("http://colab-sbx-pvt-14.oit.duke.edu:8000/exercises/userinfo/");

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
                User.phone = phone_text.getText().toString();
                User.birth = birth_text.getText().toString();
                User.pt = pt_text.getText().toString();
                new updateUserInfo().execute("http://colab-sbx-pvt-14.oit.duke.edu:8000/exercises/userinfo/");
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

    public class pullUserInfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("method", "GET")
                        .appendQueryParameter("username", User.username)
                        .appendQueryParameter("password", User.password);

                String query = builder.build().getEncodedQuery();

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result != null) {
                jsonParser(result);
            }
            else{
                Toast.makeText(getActivity().getBaseContext(), "No user information available", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void jsonParser(String result){
        try {
            System.out.println(result);
            JSONObject parentObject = new JSONObject(result);
            User.phone = parentObject.getString("mobile");
            User.pt = parentObject.getString("pt");
            User.birth = parentObject.getString("birthday");

            name_text.setText(User.username);
            name_text.setKeyListener(null);
            phone_text.setText(User.phone);
            email_text.setText("hz132@duke.edu");
            pt_text.setText(User.pt);
            birth_text.setText(User.birth);
            pswd_text.setText(User.password);
            pswd_text.setKeyListener(null);

            return;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return;
    }

    public class updateUserInfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("method", "POST")
                        .appendQueryParameter("username", User.username)
                        .appendQueryParameter("password", User.password)
                        .appendQueryParameter("mobile", User.phone)
                        .appendQueryParameter("pt", User.pt)
                        .appendQueryParameter("birthday", User.birth);

                String query = builder.build().getEncodedQuery();

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.equals("Success")){
                Toast.makeText(getActivity().getBaseContext(), "Update successful", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity().getBaseContext(), "Fail", Toast.LENGTH_LONG).show();
            }
        }
    }

}
