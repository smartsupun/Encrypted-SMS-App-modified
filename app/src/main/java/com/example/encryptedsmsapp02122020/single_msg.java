package com.example.encryptedsmsapp02122020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class single_msg extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    EditText textMsg, textPhoneNo;
    String msg, phoneNo;
    Button send;
    //database connect
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_single_msg );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //check if the permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            //if permission is not granted then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                //a popup will apear asking for required permission i.e. allow or deny
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        textMsg = findViewById(R.id.textMsg);
        textPhoneNo = findViewById(R.id.textPhoneNo);
        send = findViewById(R.id.send);
        //database connect
        openHelper = new MyDatabaseHelper(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendTextMessage();
                textMsg.setText("");
                textPhoneNo.setText("");
                //database connect
                db=openHelper.getWritableDatabase();
                insertData(phoneNo, msg );
            }
        });


    }//oncreate

    // after getting the permission request, the result will be passed through this method
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //will check the requestCode
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                //CHECK whether the length of grantResults is grater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thanks for permitting!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "why Don't you permit ,idiot!", Toast.LENGTH_LONG).show();
                }

            }
            //switch
            //method
        }
    }

    protected void sendTextMessage()
    {
        msg = textMsg.getText().toString();
        phoneNo = textPhoneNo.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, msg, null, null);
        Toast.makeText(this, "sent!", Toast.LENGTH_LONG).show();


    }


    //database connect
    public void insertData(String phoneNo, String msg ){
        ContentValues contentValues= new ContentValues();
        contentValues.put(MyDatabaseHelper.PHONE_NO,phoneNo);
        contentValues.put(MyDatabaseHelper.MESSAGE,msg);
        long id=db.insert(MyDatabaseHelper.TABLE_NAME,null , contentValues);
    }

}
