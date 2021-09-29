package com.example.android_project.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.android_project.data.Character

class CharScoreDatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "charScore", null, 1) {

    private val SCORE_TABLE = "SCORE_TABLE"
    private val COLUMN_CHAR_ID = "CHAR_ID"
    private val COLUMN_WINS = "WINS"
    private val COLUMN_LOSS = "LOSS"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + SCORE_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHAR_ID + " INT, " +
                COLUMN_WINS + " INT, " +
                COLUMN_LOSS + " INT)"

        db.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //lets you send in a single character class and get its win score/loses if they exists in database
    fun getCharScore(character: Character): Character {
        val query = "SELECT * FROM $SCORE_TABLE WHERE $COLUMN_CHAR_ID = " + character.id
        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val wins = cursor.getInt(2)
                val loss = cursor.getInt(3)

                character.wins = wins
                character.loss = loss
            } while (cursor.moveToNext())
        } else {
            character.wins = 0
            character.loss = 0
        }
        cursor.close()
        db.close()

        return character
    }

    //send in single Character to add new score
    fun addCharScore(character: Character): Boolean {
        if (charHasScore(character)) {
            val db = this.writableDatabase
            val cv = ContentValues()
            val charId = character.id.toString()
            cv.put(COLUMN_LOSS, character.loss)
            cv.put(COLUMN_WINS, character.wins)
            val long = db.update(
                SCORE_TABLE, cv, COLUMN_CHAR_ID + " =?", arrayOf(charId)
            )
            return true

        } else {
            val db = this.writableDatabase
            val cv = ContentValues()

            cv.put(COLUMN_CHAR_ID, character.id)
            cv.put(COLUMN_LOSS, character.loss)
            cv.put(COLUMN_WINS, character.wins)
            val long = db.insert(SCORE_TABLE, null, cv)
            return long != -1L
        }
    }

    //a function to check if a specific character already has a score row in database
    private fun charHasScore(character: Character): Boolean {
        val db = this.writableDatabase
        val queryString = "SELECT * FROM $SCORE_TABLE"

        val cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                val charId = cursor.getInt(1)

                if (charId == character.id) {
                    cursor.close()
                    db.close()
                    return true
                }

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return false
    }
}