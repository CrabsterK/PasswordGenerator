package com.example.arkadiusz.passwordgenerator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    /**
     * The sensor manager object.
     */
    private SensorManager sensorManager;
    /**
     * The accelerometer.
     */
    /**
     * Accelerometer x value
     */
    private float aX = 0;
    /**
     * Accelerometer y value
     */
    private float aY = 0;
    /**
     * Accelerometer z value
     */
    private float aZ = 0;
    private Sensor accelerometer;
    private TextView TwPass;
    private TextView TwLength;
    private CheckBox Cb1;
    private CheckBox Cb2;
    private CheckBox Cb3;
    private CheckBox Cb4;
    private SeekBar Sb1;
    private final int MINIMUM_PASS_LENGTH = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  PASSWORD GENERATOR");
        toolbar.setLogo(android.R.drawable.ic_lock_idle_lock);
        TwLength = (TextView) findViewById(R.id.textView44);


        // Set the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // if the default accelerometer exists
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // set accelerometer
            accelerometer = sensorManager
                    .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // register 'this' as a listener that updates values. Each time a sensor value changes,
            // the method 'onSensorChanged()' is called.
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // No accelerometer!
        }


        Sb1 = (SeekBar) findViewById(R.id.seekBar1);
        Sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                int passwordLength = MINIMUM_PASS_LENGTH + Sb1.getProgress();
                TwLength.setText(" (" + passwordLength + ")");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_aboutApp) {
            final Intent intent1 = new Intent(this, AboutAppActivity.class);
            startActivity(intent1);
            return true;
        }

        if (id == R.id.action_author) {
            final Intent intent1 = new Intent(this, AuthorActivity.class);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // onResume() registers the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    // onPause() unregisters the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        aX = event.values[0];
        aY = event.values[1];
        aZ = event.values[2];

        Cb1 = (CheckBox) findViewById(R.id.checkedTextView1);
        Cb2 = (CheckBox) findViewById(R.id.checkedTextView2);
        Cb3 = (CheckBox) findViewById(R.id.checkedTextView3);
        Cb4 = (CheckBox) findViewById(R.id.checkedTextView4);
        Sb1 = (SeekBar) findViewById(R.id.seekBar1);
        TwPass = (TextView) findViewById(R.id.textView12);
        PasswordGenerator pg = new PasswordGenerator(Cb1.isChecked(), Cb2.isChecked(), Cb3.isChecked(), Cb4.isChecked());
        int passwordLength = MINIMUM_PASS_LENGTH + Sb1.getProgress();
        String newPassword = pg.generate(passwordLength);

        if ((Math.abs(aX) > Math.abs(aY)) && (Math.abs(aX) > Math.abs(aZ))) {
            TwPass.setText(newPassword);
        }
//        if ((Math.abs(aY) > Math.abs(aX)) && (Math.abs(aY) > Math.abs(aZ))) {
//            titleAcc.setTextColor(Color.BLUE);
//        }
//        if ((Math.abs(aZ) > Math.abs(aY)) && (Math.abs(aZ) > Math.abs(aX))) {
//            titleAcc.setTextColor(Color.GREEN); //to jest ciągle i odświerza się bez przerwy bez jakichkolwiek ruchów
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing.
    }
}
