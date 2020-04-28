package com.example.monitoringandfeedback8;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
        ).get(MainViewModel.class);

        final TextView vendor = findViewById(R.id.vendor);
        final TextView name = findViewById(R.id.name);
        final TextView version = findViewById(R.id.version);
        final TextView resolution = findViewById(R.id.resolution);
        final TextView maxrange = findViewById(R.id.maxrange);
        final TextView power = findViewById(R.id.power);
        final TextView xyz = findViewById(R.id.xyz);

        mainViewModel.accelerationLiveData.observe(this, (acclerationInformation) -> {
            vendor.setText("Vendor " + acclerationInformation.getSensor().getVendor());
            name.setText("Name " + acclerationInformation.getSensor().getName());
            version.setText("Version " + acclerationInformation.getSensor().getVersion());
            resolution.setText("Resolution " + acclerationInformation.getSensor().getResolution());
            maxrange.setText("maxRange " + acclerationInformation.getSensor().getMaximumRange());
            power.setText("Power mA " + acclerationInformation.getSensor().getPower());
            xyz.setText(
                    "x: " + acclerationInformation.getX() + " y: " + acclerationInformation.getY() + " z: " + acclerationInformation.getZ()
            );
        });
    }
}
