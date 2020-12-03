package com.example.ex102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.nio.Buffer;
import java.util.Arrays;

/**
 *  * @author		Shahar Yani
 *  * @version  	1.0
 *  * @since		26/11/2020
 *
 *  * This MainActivity.class displays 5 different types of configured dialog view object
 *    and a menu get to Credits.class
 *  */
public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, DialogInterface.OnMultiChoiceClickListener {

    LinearLayout layoutScreen;
    AlertDialog.Builder adb;
    final String[] colors = {"Red", "Green", "Blue"};
    int[] color = {0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutScreen = findViewById(R.id.layoutScreen);
    }

    /**
     * This method changes the background color of the layout which is selected in a radio group.
     *
     * @param view the view
     */
    public void withRadioGroup(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("AlertDialog With RadioGroup");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which] = 255;
                layoutScreen.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
                color[which] = 0;
            }
        });
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * This method changes the background color of the layout which is selected in a multi choice.
     *
     * @param view the view
     */
    public void withMultiChoiceItems(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("AlertDialog With Multi Choice Items");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) color[which] = 255;
                else color[which] = 0;
            }
        });
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layoutScreen.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
                color[0] = 0;
                color[1] = 0;
                color[2] = 0;
            }
        });

        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * This method changes the background color of the layout to white
     *
     * @param view the view
     */
    public void resetBackgroundColor(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Reset background color to white");
        adb.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layoutScreen.setBackgroundColor(Color.rgb(255,255,255));
            }
        });
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * This method gets a String from an EditText and shows it on a Toast message
     *
     * @param view the view
     */
    public void withEditTextAndToast(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Display the input from the EditText on a Toast");
        final EditText eT = new EditText(this);
        eT.setHint("TYPE HERE");
        adb.setView(eT);
        adb.setPositiveButton("DISPLAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = eT.getText().toString();
                if (str.equals(""))
                    Toast.makeText(MainActivity.this, "EMPTY TOAST", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * This method gets two Strings from two different EditTexts and shows it on one Toast message
     *
     * @param view the view
     */
    public void twoEditTextWithToast(View view) {
        LinearLayout AdScreen = new LinearLayout(this);
        AdScreen.setOrientation(LinearLayout.VERTICAL);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Display the input from two EditTexts on a Toast");
        final EditText firstEt = new EditText(this);
        firstEt.setHint("TYPE HERE");
        final EditText secondEt = new EditText(this);
        secondEt.setHint("TYPE HERE");
        AdScreen.addView(firstEt);
        AdScreen.addView(secondEt);
        adb.setView(AdScreen);
        adb.setPositiveButton("DISPLAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = firstEt.getText().toString();
                String secondStr = secondEt.getText().toString();
                if (!str.equals("") && !secondStr.equals("")) {
                    Toast.makeText(MainActivity.this, str+'\n'+secondStr, Toast.LENGTH_SHORT).show();
                }
                else if (str.equals("") && secondStr.equals(""))
                    Toast.makeText(MainActivity.this, "EMPTY TOAST", Toast.LENGTH_SHORT).show();
                else if (!str.equals(""))
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, secondStr, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Credits")){
            Intent si = new Intent(this,CreditsActivity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}