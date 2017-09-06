package com.week5.coursera.coursera_lab5_modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.graphics.Color;

public class MainActivity extends Activity {

    // Link launched by the info dialog
    static private final String URL = "https://www.google.com/search?q=modern+art+ui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set main View
        setContentView(R.layout.activity_main);

        // Get references to Views
        // Rectangles are numbered top to bottom and left to right
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final LinearLayout rect1 = (LinearLayout) findViewById((R.id.rect1));
        final LinearLayout rect2 = (LinearLayout) findViewById((R.id.rect2));
        final LinearLayout rect3 = (LinearLayout) findViewById((R.id.rect3));
        final LinearLayout rect4 = (LinearLayout) findViewById((R.id.rect4));
        final LinearLayout rect5 = (LinearLayout) findViewById((R.id.rect5));
        final LinearLayout rect6 = (LinearLayout) findViewById((R.id.rect6));
        final LinearLayout rect7 = (LinearLayout) findViewById((R.id.rect7));
        final LinearLayout rect8 = (LinearLayout) findViewById((R.id.rect8));
        final LinearLayout rect9 = (LinearLayout) findViewById((R.id.rect9));
        final LinearLayout rect10 = (LinearLayout) findViewById((R.id.rect10));

        // Initial rectangle colors
        final int rect1Color = ((ColorDrawable) rect1.getBackground()).getColor();
        final int rect2Color = ((ColorDrawable) rect2.getBackground()).getColor();
        final int rect3Color = ((ColorDrawable) rect3.getBackground()).getColor();
        final int rect4Color = ((ColorDrawable) rect4.getBackground()).getColor();
        final int rect5Color = ((ColorDrawable) rect5.getBackground()).getColor();
        final int rect6Color = ((ColorDrawable) rect6.getBackground()).getColor();
        final int rect7Color = ((ColorDrawable) rect7.getBackground()).getColor();
        final int rect8Color = ((ColorDrawable) rect8.getBackground()).getColor();
        final int rect9Color = ((ColorDrawable) rect9.getBackground()).getColor();
        final int rect10Color = ((ColorDrawable) rect10.getBackground()).getColor();

        // Rectangle Array
        final LinearLayout[] rectangles = {rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8,
                rect9, rect10};

        // Initial rectangle colors Array
        final int[] initialRectangleColors = {rect1Color, rect2Color, rect3Color, rect4Color,
                rect5Color, rect6Color, rect7Color, rect8Color, rect9Color, rect10Color};

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                for (int i=0; i<10; i++) {
                    // Convert color to HSV
                    float[] rectHSV = {0, 0, 0};
                    Color.colorToHSV(initialRectangleColors[i], rectHSV);

                    // Change color differently for each rectangle
                    if (i == 0) {
                        // Black -> White
                        // Gradually convert black to white by changing the V in HSV
                        rectHSV[2] = (float) (progress * 0.01);

                    } else if (i == 1) {
                        // White -> Black
                        // Same as before but in reverse
                        rectHSV[2] = (float) (1.0 - progress * 0.01);

                    } else if (i % 2 == 0) {
                        // Rectangles in the left side of the screen
                        // Same as before but the initial colors change in hue (H) proportional to
                        // the change in the seekBar
                        rectHSV[0] = (float) (rectHSV[0] + progress * 0.5) % 360;

                    } else {
                        // Rectangles in the right side of the screen
                        rectHSV[0] = (float) (rectHSV[0] - progress * 0.5) % 360;
                    }

                    // Convert HSV to Color again and set the new color as background of the Layout
                    rectangles[i].setBackgroundColor(Color.HSVToColor(rectHSV));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not used
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used
            }
        });
    }

    // Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Get referente to the Menu Inflater
        MenuInflater inflater = getMenuInflater();

        // Draw menu
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    // Menu listener
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Show AlertDialogFragment
        DialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Alert");

        return true;
    }

    public static class AlertDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Setup dialog
            return new AlertDialog.Builder(getActivity())

                    // Message shown
                    .setMessage(getString(R.string.uis_online) + "\n\n" +
                            getString(R.string.click_to_check))

                    // Ok button
                    .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            // Base Intent for viewing URL
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));

                            // Start the chooser Activity
                            startActivity(webIntent);

                            // Dismiss dialog
                            dialog.dismiss();
                        }
                    })

                    // Cancel button
                    .setNegativeButton(getString(R.string.cancel_button),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                    })

                    // Create Dialog
                    .create();
        }
    }
}
