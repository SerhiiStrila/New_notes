package com.example.Notes;


import android.provider.BaseColumns;

/*Корневой класс. Содержит переменные, которые обозначают входные данные*/

public final class Note {
    private Note(){
    }
    public final class Notes implements BaseColumns{
        public static final String TABLE_NAME = "Notes_table";
        public static final String colNote = "Note";
//        public static final String colNote_CREATED_DATE = "Date"; //Don`t use. It`s future.
    }
}
