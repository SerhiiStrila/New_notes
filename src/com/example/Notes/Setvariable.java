package com.example.Notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*Выбор нужного окна*/
public class Setvariable extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_variable);
    }
    public final static String THIEF = "barambam";

    public void onRadioClick(View v) {
        Intent answerInent = new Intent();
        switch (v.getId()) {
            case R.id.radioStandart:
                answerInent.putExtra(THIEF, "Стандартная заметка");
                break;
            case R.id.radioTovar:
                answerInent.putExtra(THIEF, "Товар");
                break;
            case R.id.radioLocation:
                answerInent.putExtra(THIEF, "Местоположение");
                break;

            default:
                break;
        }

        setResult(RESULT_OK, answerInent);
        finish();
    }
}