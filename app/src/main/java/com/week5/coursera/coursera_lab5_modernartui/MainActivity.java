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

        // Initial rectangle colors (only needed for colored rectangles)
        final int rect3Color = ((ColorDrawable) rect3.getBackground()).getColor();
        final int rect4Color = ((ColorDrawable) rect4.getBackground()).getColor();
        final int rect5Color = ((ColorDrawable) rect5.getBackground()).getColor();
        final int rect6Color = ((ColorDrawable) rect6.getBackground()).getColor();
        final int rect7Color = ((ColorDrawable) rect7.getBackground()).getColor();
        final int rect8Color = ((ColorDrawable) rect8.getBackground()).getColor();
        final int rect9Color = ((ColorDrawable) rect9.getBackground()).getColor();
        final int rect10Color = ((ColorDrawable) rect10.getBackground()).getColor();

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                /* Black -> White
                Convert Layout color to HSV
                Gradually convert black to white by changing the V in HSV
                Convert HSV to Color again and set the new color as background of the Layout
                */
                float[] rect1HSV = {0, 0, 0};
                Color.colorToHSV(((ColorDrawable) rect1.getBackground()).getColor(), rect1HSV);
                rect1HSV[2] = (float) (progress * 0.01);
                int rect1ColorNew = Color.HSVToColor(rect1HSV);
                rect1.setBackgroundColor(rect1ColorNew);

                /* White -> Black
                Same as before but in reverse
                */
                float[] rect2HSV = {0, 0, 0};
                Color.colorToHSV(((ColorDrawable) rect2.getBackground()).getColor(), rect2HSV);
                rect2HSV[2] = (float) (1.0 - progress * 0.01);
                int rect2ColorNew = Color.HSVToColor(rect2HSV);
                rect2.setBackgroundColor(rect2ColorNew);

                /* Magenta
                Same as before but the initial colors change in hue (H) proportional to the change
                in the seekBar
                */
                float[] rect3HSV = {0, 0, 0};
                Color.colorToHSV(rect3Color, rect3HSV);
                rect3HSV[0] = (float) (rect3HSV[0] + progress * 0.5) % 360;
                int rect3ColorNew = Color.HSVToColor(rect3HSV);
                rect3.setBackgroundColor(rect3ColorNew);

                // PurpleBlue
                float[] rect4HSV = {0, 0, 0};
                Color.colorToHSV(rect4Color, rect4HSV);
                rect4HSV[0] = (float) (rect4HSV[0] - progress * 0.5) % 360;
                int rect4ColorNew = Color.HSVToColor(rect4HSV);
                rect4.setBackgroundColor(rect4ColorNew);

                // Blue
                float[] rect5HSV = {0, 0, 0};
                Color.colorToHSV(rect5Color, rect5HSV);
                rect5HSV[0] = (float) (rect5HSV[0] + progress * 0.5) % 360;
                int rect5ColorNew = Color.HSVToColor(rect5HSV);
                rect5.setBackgroundColor(rect5ColorNew);

                // BlueGreen
                float[] rect6HSV = {0, 0, 0};
                Color.colorToHSV(rect6Color, rect6HSV);
                rect6HSV[0] = (float) (rect6HSV[0] - progress * 0.5) % 360;
                int rect6ColorNew = Color.HSVToColor(rect6HSV);
                rect6.setBackgroundColor(rect6ColorNew);

                // Green
                float[] rect7HSV = {0, 0, 0};
                Color.colorToHSV(rect7Color, rect7HSV);
                rect7HSV[0] = (float) (rect7HSV[0] + progress * 0.5) % 360;
                int rect7ColorNew = Color.HSVToColor(rect7HSV);
                rect7.setBackgroundColor(rect7ColorNew);

                // GreenYellow
                float[] rect8HSV = {0, 0, 0};
                Color.colorToHSV(rect8Color, rect8HSV);
                rect8HSV[0] = (float) (rect8HSV[0] - progress * 0.5) % 360;
                int rect8ColorNew = Color.HSVToColor(rect8HSV);
                rect8.setBackgroundColor(rect8ColorNew);

                // Yellow
                float[] rect9HSV = {0, 0, 0};
                Color.colorToHSV(rect9Color, rect9HSV);
                rect9HSV[0] = (float) (rect9HSV[0] + progress * 0.5) % 360;
                int rect9ColorNew = Color.HSVToColor(rect9HSV);
                rect9.setBackgroundColor(rect9ColorNew);

                // Orange
                float[] rect10HSV = {0, 0, 0};
                Color.colorToHSV(rect10Color, rect10HSV);
                rect10HSV[0] = (float) (rect10HSV[0] - progress * 0.5) % 360;
                int rect10ColorNew = Color.HSVToColor(rect10HSV);
                rect10.setBackgroundColor(rect10ColorNew);
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
