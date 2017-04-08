package com.example.zhanghaochong.bottomnavigationbar;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhanghaochong.bottomnavigationbar.Data.Task;
import com.example.zhanghaochong.bottomnavigationbar.Recycler.MyAdapterBTListView;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

//implements AdapterView.OnItemClickListener


public class BluetoothActivity extends AppCompatActivity {
    private static final String TAG = "BluetoothActivity";
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    BluetoothAdapter mBluetoothAdapter;
    Button btnEnableDisable_Discoverable;

    BluetoothConnectionService mBluetoothConnection;
    TextView incomingMessages;
    StringBuilder messages;

    Button btnStartConnection;
    Button btnSend;
    EditText etSend;
//    int flag = 1;


    private Firebase mRef;
    private ArrayList<Task> mTasks = new ArrayList<>();
    //private MyAdapterBT adapter;
    public MyAdapterBTListView adapter;
    private RecyclerView mRecyclerView;
    private ListView mTaskListView;

    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    public DeviceListAdapter mDeviceListAdapter;
    ListView lvNewDevices;
    Context mContext;

    BluetoothDevice mBTDevice;
    boolean regestered1,regestered2,regestered3,regestered4;

    //Broadcase recever to recever status of bluetooth on and off.
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "mBroadcastReceiver1: STATE OFF");
                        //Toast.makeText(mContext,"please turn on the blue tooth first",Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING ON");
                        break;
                }
            }
            regestered1 = true;
        }
    };

    // broadcast receiver 2 to receive status of discoverability of the phone.
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, mBluetoothAdapter.ERROR);

                switch (mode) {
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Enabled.");
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Disabled. Able to receive connections.");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Disabled. Not able to receive connections.");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "mBroadcastReceiver2: Connecting...");
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "mBroadcastReceiver2: Connected.");
                        break;
                }
            }
            regestered2 = true;
        }
    };

    /**
     * Broadcast Receiver for listing devices that are not yet paired
     * -Executed by btnDiscover.setOnClickListener method.
     */
    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, mBTDevices);
                lvNewDevices.setAdapter(mDeviceListAdapter);
            }
            regestered3 = true;
        }
    };

    private final BroadcastReceiver mBroadcastReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                    //inside BroadcastReceiver4
                    mBTDevice = mDevice;
//                    flag = 0;
                }
                //case2: creating a bone
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                }
                //case3: breaking a bond
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                }
            }
            regestered4 = true;
        }
    };


    @Override
    protected void onDestroy() {


            Log.d(TAG, "onDestroy: called.");
//            flag = 1;
            super.onDestroy();
            if (regestered1 ) {
                unregisterReceiver(mBroadcastReceiver1);
            }
            if (regestered2) {
                unregisterReceiver(mBroadcastReceiver2);
            }
            if (regestered3) {
                unregisterReceiver(mBroadcastReceiver3);
            }

                unregisterReceiver(mBroadcastReceiver4);
        if(mBluetoothConnection!= null) {
            mBluetoothConnection.stop();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        Button btnONOFF = (Button) findViewById(R.id.btnONOFF);
        // Button btnDiscover = (Button) findViewById(R.id.btnFindUnpairedDevices);
        btnEnableDisable_Discoverable = (Button) findViewById(R.id.btnDiscoverable_on_off);
        lvNewDevices = (ListView) findViewById(R.id.lvNewDevices);
        mBTDevices = new ArrayList<>();

        mTaskListView = (ListView) findViewById(R.id.taskList);

        btnStartConnection = (Button) findViewById(R.id.btnStartConnection);
        btnSend = (Button) findViewById(R.id.btnSend);
        etSend = (EditText) findViewById(R.id.editText);

        //Broadcasts when bond state changes (ie:pairing)
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver4, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //lvNewDevices.setOnItemClickListener(BluetoothActivity.this);

        lvNewDevices.setOnItemClickListener(mDeviceClickListener);

        mTaskListView.setOnItemClickListener(mTaskClickListener);
        btnONOFF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: enabling/disabling bluetooth.");
                enableDisableBT();
            }
        });

        mContext = getApplicationContext();
        btnStartConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   // Toast.makeText(mContext,"You should only click this button after a successful pair.",Toast.LENGTH_SHORT).show();
                    //btnStartConnection.setEnabled(false);
                try {
                    startConnection();
                }catch (NullPointerException e) {

                    Toast.makeText(mContext,"You should only click this button after a successful pair.",Toast.LENGTH_SHORT).show();
                }

            }
        });

//        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerID);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mTaskListView = (ListView) findViewById(R.id.)

        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://tibaapplication.firebaseio.com/");

       retrieveData();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] bytes = etSend.getText().toString().getBytes(Charset.defaultCharset());
                try {
                    mBluetoothConnection.write(bytes);
                }catch(NullPointerException e) {
                    Toast.makeText(mContext,"You have not connect with other devices yet, please check then continue",Toast.LENGTH_SHORT).show();
                }
                etSend.setText("");
            }
        });

        incomingMessages = (TextView) findViewById(R.id.incomingMessage);
        messages = new StringBuilder();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRecevier,new IntentFilter("incomingMessage"));
    }


    BroadcastReceiver mRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("theMessage");

            messages.append(text + "\n");
            incomingMessages.setText(messages);
        }
    };

    //create method for starting connection
//***remember the conncction will fail and app will crash if you haven't paired first
    public void startConnection(){
        startBTConnection(mBTDevice,MY_UUID_INSECURE);
    }

    /**
     * starting chat service method
     */
    public void startBTConnection(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startBTConnection: Initializing RFCOM Bluetooth Connection.");

        mBluetoothConnection.startClient(device,uuid);
    }


    /**
     * The on-click listener for all devices in the ListViews
     */
    private AdapterView.OnItemClickListener mDeviceClickListener
            = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "view_id = " + view.getTag());
            Log.d(TAG, "adapterview_id = " + adapterView.getTag());
            Log.d(TAG, "onItemClick: You Clicked on a device.");
            String deviceName = mBTDevices.get(i).getName();
            String deviceAddress = mBTDevices.get(i).getAddress();

            Log.d(TAG, "onItemClick: deviceName = " + deviceName);
            Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

            //create the bond.
            //NOTE: Requires API 17+? I think this is JellyBean
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Log.d(TAG, "Trying to pair with " + deviceName);
                mBTDevices.get(i).createBond();

                mBTDevice = mBTDevices.get(i);
                mBluetoothConnection = new BluetoothConnectionService(BluetoothActivity.this);
            }
        }
    };


    private AdapterView.OnItemClickListener mTaskClickListener
            = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Log.d(TAG, "view_id = " + view.getTag());
            Log.d(TAG, "adapterview_id = " + adapterView.getTag());
            Log.d(TAG, "onItemClick: You Clicked on a task.");

 //           int position = holder.getAdapterPosition();
            Task task = mTasks.get(i);
                //Toast.makeText(view.getContext(),"you clicked view"+task.getName(),Toast.LENGTH_SHORT).show();
            byte[] bytes = mTasks.get(i).getName().toString().getBytes(Charset.defaultCharset());
            try {
                mBluetoothConnection.write(bytes);
            }catch (NullPointerException e) {
                Toast.makeText(mContext,"You have not connect with other devices yet, please check then continue",Toast.LENGTH_SHORT).show();
            }
        }
    };


    //turn on/off the blue tooth
    public void enableDisableBT() {
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "enableDisableBT: Dose not have BT capabilities.");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
    }

    //turn on/off the discoverability
    public void btnEnableDisable_Discoverable(View view) {
        Log.d(TAG, "btnEnableDisable_Discoverable: Making device discoverable for 600 seconds.");
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 600);
        startActivity(discoverableIntent);

        IntentFilter intentFilter = new IntentFilter(mBluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(mBroadcastReceiver2, intentFilter);
    }

    public void btnDiscover(View view) {
        Log.d(TAG, "btnDiscover: Looking for unpaired devices.");

        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "btnDiscover: Canceling discovery.");

            //check BT permissions in manifest
            //checkBTPermissions();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
        if (!mBluetoothAdapter.isDiscovering()) {

            //check BT permissions in manifest
            //checkBTPermissions();

            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
        if( mBluetoothAdapter.getState()== BluetoothAdapter.STATE_OFF) {
            Toast.makeText(mContext,"please turn on the blue tooth first",Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        mBluetoothAdapter.cancelDiscovery();
//        Log.d(TAG, "view_id = " + view.getTag());
//        Log.d(TAG, "adapterview_id = " + adapterView.getTag());
//        Log.d(TAG, "onItemClick: You Clicked on a device.");
//        String deviceName = mBTDevices.get(i).getName();
//        String deviceAddress = mBTDevices.get(i).getAddress();
//
//        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
//        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);
//
//        //create the bond.
//        //NOTE: Requires API 17+? I think this is JellyBean
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            Log.d(TAG, "Trying to pair with " + deviceName);
//            mBTDevices.get(i).createBond();
//
//            mBTDevice = mBTDevices.get(i);
//            mBluetoothConnection = new BluetoothConnectionService(BluetoothActivity.this);
//
//        }


        /**
         * This method is required for all devices running API23+
         * Android must programmatically check the permissions for bluetooth. Putting the proper permissions
         * in the manifest is not enough.
         *
         * NOTE: This will only execute on versions > LOLLIPOP because it is not needed otherwise.
         */
//    private void checkBTPermissions() {
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
//            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
//            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
//            if (permissionCheck != 0) {
//
//                this.requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
//            }
//        }else{
//            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
//        }
//    }
 //   }


    private void retrieveData(){
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getUpdates(DataSnapshot ds) {
        mTasks.clear();

        for(DataSnapshot data : ds.getChildren()) {
            Task t = new Task();
            t.setName(data.getValue(Task.class).getName());
            t.setDescription(data.getValue(Task.class).getDescription());
            t.setTime(data.getValue(Task.class).getTime());
            t.setId(data.getValue(Task.class).getId());

            mTasks.add(t);
        }

        if(mTasks.size() > 0){
//            //ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mTasks);
//            adapter = new MyAdapterBT(BluetoothActivity.this,mTasks);
//            mRecyclerView.setAdapter(adapter);
            adapter = new MyAdapterBTListView(BluetoothActivity.this, R.layout.tast_adapter_view,mTasks);
            mTaskListView.setAdapter(adapter);
        }else{
            Toast.makeText(BluetoothActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }
    }


}
