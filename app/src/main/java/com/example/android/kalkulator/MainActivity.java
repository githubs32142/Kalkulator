package com.example.android.kalkulator;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText n1EdT,n2EdT,resEdT;
    Button sub,mul,div,add,pi;
    LogicService logicService;
    boolean mBound=false;
    private ServiceConnection logicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogicService.LocalBinder binder = (LogicService.LocalBinder)service;
            logicService= binder.getService();
            mBound= true;
            Toast.makeText(MainActivity.this,"Logic Service Connected!",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            logicService=null;
            mBound= false;
            Toast.makeText(MainActivity.this,"Logic Service Disconnected",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(!mBound){
            this.bindService(new Intent(MainActivity.this,LogicService.class),logicConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            mBound= false;
            this.unbindService(logicConnection);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1EdT = (EditText)findViewById(R.id.n1EditText);
        n2EdT=(EditText) findViewById(R.id.n2EditText);
        resEdT= (EditText) findViewById(R.id.resEditText);
        sub= (Button)findViewById(R.id.subbutton);
        mul=(Button)findViewById(R.id.mulbutton);
        div= (Button) findViewById(R.id.divbutton);
        add= (Button) findViewById(R.id.addbutton);
        pi =(Button) findViewById(R.id.piButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double n1=0.0;
                Double n2=0.0;
                try {
                    n1= Double.parseDouble(n1EdT.getText().toString());
                    n2= Double.parseDouble(n2EdT.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (mBound){
                    double ret=logicService.add(n1,n2) ;
                    resEdT.setText(String.valueOf(ret));
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double n1=0.0;
                Double n2=0.0;
                try {
                    n1= Double.parseDouble(n1EdT.getText().toString());
                    n2= Double.parseDouble(n2EdT.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (mBound){
                   double ret=logicService.sub(n1,n2) ;
                    resEdT.setText(String.valueOf(ret));
                }
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double n1=0.0;
                Double n2=0.0;
                try {
                    n1= Double.parseDouble(n1EdT.getText().toString());
                    n2= Double.parseDouble(n2EdT.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (mBound){
                    double ret= logicService.mul(n1,n2) ;
                    resEdT.setText(String.valueOf(ret));
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double n1=0.0;
                Double n2=0.0;
                try {
                    n1= Double.parseDouble(n1EdT.getText().toString());
                    n2= Double.parseDouble(n2EdT.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (mBound){
                    if(n1!=0.0){
                        double ret= logicService.div(n1,n2) ;
                        resEdT.setText(String.valueOf(ret));
                    }

                }
            }
        });
    }

}
