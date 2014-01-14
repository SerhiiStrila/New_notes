package com.example.Notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

/**
 * Created by serega on 11.12.13.
 */
public class AddTovar extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private ImageButton bt_addimage;
    private EditText editT;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtovar);
        bt_addimage = (ImageButton)findViewById(R.id.imageButton);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void click_addimagetovar(View v){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
        bt_addimage.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

}