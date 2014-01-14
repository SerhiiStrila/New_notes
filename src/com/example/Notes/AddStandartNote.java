package com.example.Notes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.io.FileOutputStream;

public class AddStandartNote extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private ImageButton bt_addimage;
    private EditText editT;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstandartnote);
        imageView = (ImageView)this.findViewById(R.id.imageView);
        bt_addimage = (ImageButton)this.findViewById(R.id.imageButton);
        editT = (EditText)this.findViewById(R.id.editText);
    }

    // запускает системную камеру
    public void click_addimage(View v){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
        bt_addimage.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);

    }


    //выводит картинку Bitmap в imageView
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            try {
                FileOutputStream out = new FileOutputStream(Environment.getExternalStorageDirectory()+"/MyCameraApp/");
                photo.compress(Bitmap.CompressFormat.PNG, 90, out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void click_addtobd(View arg0){
        Baza myDB = new Baza(this);
        SQLiteDatabase sqldb = myDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Note.Notes.colNote, editT.getText().toString());
        sqldb.insert(Note.Notes.TABLE_NAME, null, cv);

        backtomain();
    }

    //возврат в главное меню
    public void backtomain(){
        Intent intent = new Intent(AddStandartNote.this, MyActivity.class);
        startActivity(intent);
    }
}