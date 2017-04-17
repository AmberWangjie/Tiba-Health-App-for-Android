package com.example.zhanghaochong.bottomnavigationbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Adapter.TaskAdapter;
import com.example.zhanghaochong.bottomnavigationbar.Data.Exercise;
import com.example.zhanghaochong.bottomnavigationbar.Data.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by zhanghaochong on 17/3/5.
 */

public class WorkoutFragment extends Fragment {

    private ArrayList<Task> mTasks = new ArrayList<>();
    private ArrayList<Exercise> mExercises = new ArrayList<>();
    private Button startBtn;
    private ListView myView;
    private TextView timerDescp;
    private int totalTime = 0;
    private TaskAdapter adapter;
    OnExercisePass onExercisePass;

    public final static String MESSAGE_ID = "com.example.zhanghaochong.bottomnavigationbar.id";
    public final static String CONSTANT_TASK = "com.example.zhanghaochong.bottomnavigationbar.task";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the fragment layout file
        View v = inflater.inflate(R.layout.workout, container, false);

        myView = (ListView)v.findViewById(android.R.id.list);
        new JSONTask().execute("http://colab-sbx-pvt-14.oit.duke.edu:8000/exercises/");
        System.out.println(mTasks.size());

        //local database


        onClickButtonListener(v);

        return v;
    }


    public class JSONTask extends AsyncTask<String, String, ArrayList<Exercise>> {

        @Override
        protected ArrayList<Exercise> doInBackground(String... params) {
            String finalJson = HttpConnection(params[0]);

            return JsonParser(finalJson);
        }

        @Override
        protected void onPostExecute(ArrayList<Exercise> result){
            super.onPostExecute(result);
            mExercises = result;
            onExercisePass.setExercise(getExercise());
            mTasks = getExercise().getmTask();
            timerDescp = (TextView) getActivity().findViewById(R.id.timerDescp);
            for(int i=0; i < mTasks.size(); i++){
                totalTime += Integer.parseInt(mTasks.get(i).getTime())/60000;
            }
            timerDescp.setText(totalTime + "min");
            if(mTasks.size() > 0){
                adapter = new TaskAdapter(getActivity(),mTasks);
                myView.setAdapter(adapter);
            }else{
                Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickButtonListener(View v) {
        startBtn = (Button) v.findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.zhanghaochong.bottomnavigationbar.DescriptionActivity");
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(CONSTANT_TASK, mTasks);
                Integer id = 0;

                intent.putExtras(bundle);
                intent.putExtra(MESSAGE_ID, id);

                startActivity(intent);
            }
        });
    }

    public Exercise getExercise(){
        Calendar c = Calendar.getInstance();

        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = sd.format(c.getTime());
        System.out.println("Current time: "+ formattedDate);
        int i = 0;
        while(i < mExercises.size() && !formattedDate.equals(mExercises.get(i).getExerciseDate())){
            i++;
        }

        return mExercises.get(i);
    }

    public String HttpConnection(String Url) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", "hz132")
                    .appendQueryParameter("password", "!Q@W#E$R");

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
            buffer.insert(0, "{\"exercises\":");
            buffer.append("}");

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

    public ArrayList<Exercise> JsonParser(String finalJson){

        try {
            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("exercises");
            ArrayList<Exercise> newExercises = new ArrayList<>();

            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                JSONArray childArray = finalObject.getJSONArray("tasks");
                ArrayList<Task> newTasks = new ArrayList<>();
                Exercise e = new Exercise();
                for (int j = 0; j < childArray.length(); j++) {
                    JSONObject childObject = childArray.getJSONObject(j);
                    Task t = new Task();

                    t.setName(childObject.getString("task_name"));
                    t.setDescription(childObject.getString("task_description"));
                    t.setTime(childObject.getString("task_duration"));
                    t.setId(childObject.getString("task_id"));
                    t.setAbstraction(childObject.getString("task_abstraction"));
                    newTasks.add(t);
                }

                e.setExerciseName(finalObject.getString("exercise_name"));
                e.setExerciseDate(finalObject.getString("exercise_date"));
                e.setTaskNum(finalObject.getString("task_num"));
                e.setExerciseDuration(finalObject.getString("exercise_duration"));
                e.setExerciseBody(finalObject.getString("exercise_body"));
                e.setmTask(newTasks);
                newExercises.add(e);
            }

            return newExercises;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public interface OnExercisePass{
        public void setExercise(Exercise e);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            onExercisePass = (OnExercisePass)activity;
        }catch (Exception e){}
    }
}
