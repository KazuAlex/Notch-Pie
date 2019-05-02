package com.oddlyspaced.np.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.oddlyspaced.np.Adapter.BatteryColorAdapter;
import com.oddlyspaced.np.R;
import com.oddlyspaced.np.Utils.ColorLevel;
import com.oddlyspaced.np.Utils.ColorPicker;
import com.oddlyspaced.np.Utils.DataManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfigurationScreen extends AppCompatActivity implements ColorPicker.ColorPickerListener, BatteryColorAdapter.onTouchColorLevel {

    private SeekBar height, widht, notchSize, notchRadiusB, notchRadiusT;
    private Button importBtn, presetBtn;
    private DataManager dataManager;

    private int[] heightLimit = {50, 500};
    private int[] widhtLimit = {1, 500};
    private int[] notchSizeLimit = {1, 500};
    private int[] notchRadiusBLimit = {0, 100};
    private int[] notchRadiusTLimit = {0, 100};

    private RecyclerView batteryColorView;
    private BatteryColorAdapter batteryColorAdapter;
    private ArrayList<ColorLevel> list;

    private int positionTouch = -1;

    private final HashMap<String, Map<String, Integer>> presetData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_screen);
        checkFile();
        initViews();
        loadRecycler();
    }

    private void checkFile() {
        File dataFile = new File(Environment.getExternalStorageDirectory() + "/notchpie.data");
        if (!dataFile.exists()) {
            DataManager dataManager = new DataManager();
            dataManager.setHeight(50);
            dataManager.setWidht(10);
            dataManager.setBottomRadius(0);
            dataManager.setTopRadius(0);
            dataManager.setNotchSize(0);
            ArrayList<ColorLevel> levels = new ArrayList<>();

            ColorLevel level = new ColorLevel();

            level.setColor("#FF5555");
            level.setStartLevel(0);
            level.setEndLevel(5);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(6);
            level.setEndLevel(10);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(11);
            level.setEndLevel(20);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(21);
            level.setEndLevel(30);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(31);
            level.setEndLevel(40);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(41);
            level.setEndLevel(50);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(51);
            level.setEndLevel(60);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(61);
            level.setEndLevel(70);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(71);
            level.setEndLevel(80);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#ffb86c");
            level.setStartLevel(81);
            level.setEndLevel(90);
            levels.add(level);

            level = new ColorLevel();
            level.setColor("#50fa7b");
            level.setStartLevel(91);
            level.setEndLevel(100);
            levels.add(level);

            dataManager.setColorLevels(levels);
            dataManager.save();

        }
    }

    private void setDataFromManager() {
        height.setProgress(dataManager.getHeight());
        widht.setProgress(dataManager.getWidht());
        notchSize.setProgress(dataManager.getNotchSize());
        notchRadiusB.setProgress(dataManager.getBottomRadius());
        notchRadiusT.setProgress(dataManager.getTopRadius());
    }

    private void initViews() {
        height = findViewById(R.id.sbHeight);
        widht = findViewById(R.id.sbWidht);
        notchSize = findViewById(R.id.sbNotchSize);
        notchRadiusB = findViewById(R.id.sbBottomRadius);
        notchRadiusT = findViewById(R.id.sbTopRadius);
        // setting progress
        dataManager = new DataManager();
        dataManager.read();
        this.setDataFromManager();
        // setting listener
        height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataManager.setHeight(progress);
                dataManager.save();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        widht.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataManager.setWidht(progress);
                dataManager.save();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        notchSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataManager.setNotchSize(progress);
                dataManager.save();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        notchRadiusB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataManager.setBottomRadius(progress);
                dataManager.save();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        notchRadiusT.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataManager.setTopRadius(progress);
                dataManager.save();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // setting limits
        height.setMin(heightLimit[0]);
        height.setMax(heightLimit[1]);
        widht.setMin(widhtLimit[0]);
        widht.setMax(widhtLimit[1]);
        notchSize.setMin(notchSizeLimit[0]);
        notchSize.setMax(notchSizeLimit[1]);
        notchRadiusT.setMin(notchRadiusTLimit[0]);
        notchRadiusT.setMax(notchRadiusTLimit[1]);
        notchRadiusB.setMin(notchRadiusBLimit[0]);
        notchRadiusB.setMax(notchRadiusBLimit[1]);
        FloatingActionButton open = findViewById(R.id.fabOpen);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), getString(R.string.popup_accessibility_toast), Toast.LENGTH_LONG).show();
            }
        });
        batteryColorView = findViewById(R.id.rvBatteryColor);


        // create HashMap with notch data by devices
        this.setPresetDataMap();

        // set button listeners
        importBtn = findViewById(R.id.button_import);
        presetBtn = findViewById(R.id.button_preset);
        importBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                Toast.makeText(getApplicationContext(), "Soon...", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        presetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] presetDevices = getResources().getStringArray(R.array.preset_devices);
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        new ContextThemeWrapper(ConfigurationScreen.this, R.style.DialogTheme)
                );
                builder.setTitle(R.string.device_preset_choose);
                builder.setItems(presetDevices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfigurationScreen.this.setDataFromPreset(presetDevices[which]);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setPresetDataMap() {
        HashMap<String, Integer> OP6TData = new HashMap<>();
        OP6TData.put("height", 94);
        OP6TData.put("width", 21);
        OP6TData.put("notchSize", 92);
        OP6TData.put("topRadius", 0);
        OP6TData.put("bottomRadius", 43);
        presetData.put(getString(R.string.device_oneplus_6t), OP6TData);
        HashMap<String, Integer> RN7Data = new HashMap<>();
        OP6TData.put("height", 97);
        OP6TData.put("width", 1);
        OP6TData.put("notchSize", 76);
        OP6TData.put("topRadius", 0);
        OP6TData.put("bottomRadius", 100);
        presetData.put(getString(R.string.device_redmi_note_7), RN7Data);
        HashMap<String, Integer> RN7ProData = new HashMap<>();
        OP6TData.put("height", 97);
        OP6TData.put("width", 1);
        OP6TData.put("notchSize", 76);
        OP6TData.put("topRadius", 0);
        OP6TData.put("bottomRadius", 100);
        presetData.put(getString(R.string.device_redmi_note_7_pro), RN7ProData);
        HashMap<String, Integer> HuaweiP20Data = new HashMap<>();
        OP6TData.put("height", 100);
        OP6TData.put("width", 56);
        OP6TData.put("notchSize", 167);
        OP6TData.put("topRadius", 81);
        OP6TData.put("bottomRadius", 80);
        presetData.put(getString(R.string.device_huawei_p20), HuaweiP20Data);
        HashMap<String, Integer> HuaweiP20ProData = new HashMap<>();
        OP6TData.put("height", 100);
        OP6TData.put("width", 56);
        OP6TData.put("notchSize", 167);
        OP6TData.put("topRadius", 81);
        OP6TData.put("bottomRadius", 80);
        presetData.put(getString(R.string.device_huawei_p20_pro), HuaweiP20ProData);
        HashMap<String, Integer> HuaweiP30ProData = new HashMap<>();
        OP6TData.put("height", 89);
        OP6TData.put("width", 15);
        OP6TData.put("notchSize", 91);
        OP6TData.put("topRadius", 66);
        OP6TData.put("bottomRadius", 63);
        presetData.put(getString(R.string.device_huawei_p30_pro), HuaweiP30ProData);
    }

    @SuppressWarnings("ConstantConditions")
    private void setDataFromPreset(String device) {
        Log.d("NotchPie", "device choosed : "+ device);
        if (presetData.containsKey(device)) {
            Map<String, Integer> preset = presetData.get(device);
            if (null != preset) {
                try {
                    dataManager.setHeight(preset.get("height"));
                    dataManager.setWidht(preset.get("width"));
                    dataManager.setNotchSize(preset.get("notchSize"));
                    dataManager.setTopRadius(preset.get("topRadius"));
                    dataManager.setBottomRadius(preset.get("bottomRadius"));
                    dataManager.save();
                    this.setDataFromManager();
                } catch (NullPointerException e) {
                    // TODO
                }
            }
        }
        //dataManager.setHeight();

    }

    private void loadRecycler() {
        batteryColorView.setHasFixedSize(true);
        batteryColorView.setLayoutManager(new LinearLayoutManager(this));
        list = dataManager.getColorLevels();
        batteryColorAdapter = new BatteryColorAdapter(getApplicationContext(), list);
        batteryColorView.setAdapter(batteryColorAdapter);
    }



    @Override
    public void onColorSet(String color) {
        ColorLevel prev = list.get(positionTouch);
        prev.setColor(color);
        list.set(positionTouch, prev);
        batteryColorAdapter.notifyItemChanged(positionTouch);
        dataManager.setColorLevels(list);
        dataManager.save();
    }

    @Override
    public void onTouchItem(int position) {
        positionTouch = position;
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.color = list.get(position).getColor();
        colorPicker.show(getSupportFragmentManager(), "color picker");
    }
}
