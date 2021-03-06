/*
 * Copyright 2015 Udacity, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.udacity.immersiveimages;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.graphics.Matrix;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.ImageView.*;

/**
 * @author ernesto
 */
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.imageView)
    ImageView imageView;

    // Radio Buttons
    @InjectView(R.id.noneBtn)
    RadioButton noneBtn;
    @InjectView(R.id.centerBtn)
    RadioButton centerBtn;
    @InjectView(R.id.centerCropBtn)
    RadioButton centerCropBtn;
    @InjectView(R.id.centerInsideBtn)
    RadioButton centerInsideBtn;
    @InjectView(R.id.fitCenterBtn)
    RadioButton fitCenterBtn;
    @InjectView(R.id.fitEndBtn)
    RadioButton fitEndBtn;
    @InjectView(R.id.fitStartBtn)
    RadioButton fitStartBtn;
    @InjectView(R.id.fitXYBtn)
    RadioButton fitXYBtn;
    @InjectView(R.id.matrixBtn)
    RadioButton matrixBtn;

    Matrix matrix;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);


/*        imageView.buildDrawingCache();
        Bitmap bmap = imageView.getDrawingCache();

        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), bmap);

        drawable.setCircular(true);*/

        fitCenterBtn.setChecked(true);
        matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f);
        matrix.postTranslate(100, 100);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public RadioButton getSelectedRadio(View view) {
        RadioButton[] btns = {centerBtn, centerCropBtn, centerInsideBtn, fitCenterBtn,
                fitEndBtn, fitStartBtn, fitXYBtn, matrixBtn, noneBtn};
        for (RadioButton radioButton : btns) {
            if (radioButton.isChecked() && radioButton != view) {
                return radioButton;
            }
        }
        return null;
    }

    @OnClick({R.id.centerBtn, R.id.centerCropBtn, R.id.centerInsideBtn, R.id.fitCenterBtn,
            R.id.fitEndBtn, R.id.fitStartBtn, R.id.fitXYBtn, R.id.matrixBtn, R.id.noneBtn})
    public void clickRadioButton(RadioButton view) {
        // Check to see what is clicked
        RadioButton checkedRadio = getSelectedRadio(view);
        if (checkedRadio != null && checkedRadio != view) {
            checkedRadio.setChecked(false);
        }
        // If currently checked, do nothing.
        imageView.setPadding(0, 0, 0, 0);
        switch ((String) view.getText()) {

            case "NO!!!!":
                int px = dpToPx(32);
                imageView.setPadding(px, px, px, px);
                imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "CENTER":
                imageView.setScaleType(ScaleType.CENTER);
                break;
            case "CENTER_CROP":
                imageView.setScaleType(ScaleType.CENTER_CROP);
                break;
            case "CENTER_INSIDE":
                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case "FIT_CENTER":
                imageView.setScaleType(ScaleType.FIT_CENTER);
                break;
            case "FIT_END":
                imageView.setScaleType(ScaleType.FIT_END);
                break;
            case "FIT_START":
                imageView.setScaleType(ScaleType.FIT_START);
                break;
            case "FIT_XY":
                imageView.setScaleType(ScaleType.FIT_XY);
                break;
            case "MATRIX":
                imageView.setScaleType(ScaleType.MATRIX);
                imageView.setImageMatrix(matrix);
                break;

        }
        // If different, alter view

    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
