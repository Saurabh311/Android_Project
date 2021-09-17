package com.example.android_project.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.android_project.Model.Character

class CharScoreDatabaseHelper (
    context: Context?) : SQLiteOpenHelper(context,"charScore",null,1) {

    val SCORE_TABLE = "SCORE_TABLE"
    val COLUMN_CHAR_ID = "CHAR_ID"
    val COLUMN_WINS = "WINS"
    val COLUMN_LOSS = "LOSS"


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + SCORE_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHAR_ID + " INT, " +
                COLUMN_WINS + "INT, " +
                COLUMN_LOSS + "INT)"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //lets you send in a list of already made characters//properly from an prior api call and get their wins/loses updated against database columns
    fun getAllCharScores(charList: List<Character>) :List<Character>{

        val query = "SELECT * FROM $SCORE_TABLE"
        val db = this.writableDatabase

        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val customerId = cursor.getInt(0)
                val charId = cursor.getInt(1)
                val wins = cursor.getInt(2)
                val loss = cursor.getInt(3)


                for (char in charList){
                    if (char.charId == charId){
                        char.wins = wins
                        char.loss = loss
                    }
                }

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return charList
    }
    //lets you send in a single character class and get its win score/loses if they exists in database
    fun getChar (character: Character) : Character{
        val query = "SELECT * FROM $SCORE_TABLE WHERE $COLUMN_CHAR_ID = "  + character.charId
        val db = this.writableDatabase

        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val wins = cursor.getInt(2)
                val loss = cursor.getInt(3)

                character.wins = wins
                character.loss = loss
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return character
    }
    //lets you send in a single character to add its new win/loss scores to database this method will cover if a database entry docent exists and if it dose exist
    fun addCharScore (character: Character) :Boolean{
        if (charHasScore(character)){
            val db = this.writableDatabase
            val cv = ContentValues()
            val charId = character.charId.toString()
            cv.put(COLUMN_LOSS, character.loss)
            cv.put(COLUMN_WINS, character.wins)
            val long = db.update(
                SCORE_TABLE,cv,COLUMN_CHAR_ID + " =?", arrayOf(charId)
            )
            println("a character has been updated")
            return true


        }else{
            val db = this.writableDatabase
            val cv = ContentValues()

            cv.put(COLUMN_CHAR_ID, character.charId)
            cv.put(COLUMN_LOSS, character.loss)
            cv.put(COLUMN_WINS, character.wins)
            val long = db.insert(SCORE_TABLE,null,cv)
            println("a character has been added")
            return long != -1L

        }

    }

//a function to check if a specific character allredy has a score row in database
    private fun charHasScore(character: Character) : Boolean{
        val db = this.writableDatabase
        val queryString = "SELECT * FROM $SCORE_TABLE"

        val cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()){
            do {
                val charId = cursor.getInt(1)

                if (charId == character.charId){
                    cursor.close()
                    db.close()
                    return true
                }

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return false
    }


}