package com.hdone.lightsensor;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ImageView imageView ;
    SensorManager sm;
    SensorEventListener listener;
    Sensor light;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imgView);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        listener = new SensorEventListener() {

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Toast.makeText(MainActivity.this, "accuracy changed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] > 20){
                    imageView.setImageResource(R.drawable.on);
                }
                else{
                    imageView.setImageResource(R.drawable.off);
                }

            }
        };

        sm.registerListener(listener, light, SensorManager.SENSOR_DELAY_FASTEST);
    }
  @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(listener, light, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(listener, light);
    }
}
