package com.example.Notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MyActivity extends Activity {
    Button add;
    ListView listView;
    EditText editT;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = MyActivity.this;
        add = (Button)findViewById(R.id.add);
        listView = (ListView)findViewById(R.id.listView);
        editT = (EditText)findViewById(R.id.editText);
        Baza nb = new Baza(context);
        nb.openDB();
        listView.setAdapter(nb.readDB());
        nb.closeDB();
    }


    // переход в выбор типа заметки
    public void onClick(View v) {
        Intent intent = new Intent(MyActivity.this, Setvariable.class);
        startActivityForResult(intent, CHOOSE_THIEF);
    }

    static final private int CHOOSE_THIEF = 0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_THIEF) {
            if (resultCode == RESULT_OK) {
                String thiefname = data.getStringExtra(Setvariable.THIEF);
                if (thiefname.equals("Стандартная заметка")){
                    Intent intent = new Intent(MyActivity.this, AddStandartNote.class);
                    startActivity(intent);
                }
                if (thiefname.equals("Товар")){
                    Intent intent = new Intent(MyActivity.this, AddTovar.class);
                    startActivity(intent);
                }
                if (thiefname.equals("Местоположение")){
                    Intent intent = new Intent(MyActivity.this, AddLocation.class);
                    startActivity(intent);
                }
            }
        }
    }
}
