package com.example.zhanghaochong.bottomnavigationbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.OnClick;

/**
 * Created by AmberWang on 17/3/5.
 */

public class EmailFragment extends Fragment {

    private ImageButton cancelButton, sendButton;
    private EditText emailAddress, emailContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.email, container, false);
        //setContentView (R.layout.email);
        initializeVars(v);
        sendButton.setOnClickListener(new View.OnClickListener(){
            // @Override
            public void onClick(View view){
                onSendClick(view);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener(){
            // @Override
            public void onClick(View view){
                onCancelClick(view);
            }
        });
        return v;
    }

    private void initializeVars(View v){
        cancelButton = (ImageButton) v.findViewById(R.id.cancelButton);
        sendButton = (ImageButton) v.findViewById(R.id.sendButton);
        //emailAddress = (EditText) v.findViewById(R.id.email_addr);
        //emailAddress = (EditText) v.findViewById(R.id.email_address);

    }

    public void onCancelClick(View view) {

        String yourCancelResponse = "Cancel email";
        //emailContent.setText("");

        Toast.makeText(EmailFragment.this.getActivity(), yourCancelResponse, Toast.LENGTH_SHORT).show();
        //once cancel, navigate back to the main page
        WorkoutFragment f = new WorkoutFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

    }

    public void onSendClick(View view) {

        String yourSendResponse = "Edit email";

        Toast.makeText(EmailFragment.this.getActivity(), yourSendResponse, Toast.LENGTH_SHORT).show();

        if(view.getId()==R.id.sendButton) {
            Log.i("Send email","");
            //String content = emailContent.getText().toString();
           // String address = emailAddress.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.setData(Uri.parse("mailto:"));
            //email.setType("message/rfc822");
            email.setType("text/plain");
             email.setType("text/plain");
            //email.putExtra(Intent.EXTRA_EMAIL, address);
            // email.putExtra(Intent.EXTRA_TEXT, address);
            email.putExtra(Intent.EXTRA_SUBJECT, "Subject: Feedback from your patient");

            try{
                startActivity(Intent.createChooser(email, "Send mail ..."));
                //getActivity().finish();
                Log.i("Finished sending email", "");
                WorkoutFragment f = new WorkoutFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
            }catch(android.content.ActivityNotFoundException ex){
              Toast.makeText(EmailFragment.this.getActivity(), "No email client installed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
