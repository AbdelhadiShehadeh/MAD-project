package com.example.quizapp.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import com.example.quizapp.data.QuizContract.ScoreEntry
import com.example.quizapp.data.QuizDbHelper

class QuizContentProvider : ContentProvider() {

    private lateinit var appContext: Context
    private lateinit var dbHelper: QuizDbHelper

    override fun onCreate(): Boolean {
        appContext = context!!.applicationContext
        dbHelper = QuizDbHelper(appContext)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val match = URI_MATCHER.match(uri)
        val writableDatabase = dbHelper.writableDatabase
        var insertedUri: Uri? = null

        when (match) {
            CODE_SCORES -> {
                val id = writableDatabase.insert(ScoreEntry.TABLE_NAME, null, values)
                if (id != -1L) {
                    insertedUri = ContentUris.withAppendedId(uri, id)
                    appContext.contentResolver.notifyChange(uri, null)
                }
            }
        }

        return insertedUri
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val match = URI_MATCHER.match(uri)
        val readableDatabase = dbHelper.readableDatabase
        var cursor: Cursor? = null

        when (match) {
            CODE_SCORES -> {
                cursor = readableDatabase.query(
                    ScoreEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            CODE_SCORES_ITEM -> {
                val id = ContentUris.parseId(uri)
                val singleSelection = "${BaseColumns._ID} = ?"
                val singleSelectionArgs = arrayOf(id.toString())

                cursor = readableDatabase.query(
                    ScoreEntry.TABLE_NAME,
                    projection,
                    singleSelection,
                    singleSelectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
        }

        cursor?.setNotificationUri(appContext.contentResolver, uri)
        return cursor
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val match = URI_MATCHER.match(uri)
        val writableDatabase = dbHelper.writableDatabase
        var rowsUpdated = 0

        when (match) {
            CODE_SCORES -> {
                rowsUpdated = writableDatabase.update(
                    ScoreEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs
                )
            }
            CODE_SCORES_ITEM -> {
                val id = ContentUris.parseId(uri)
                val singleSelection = "${BaseColumns._ID} = ?"
                val singleSelectionArgs = arrayOf(id.toString())

                rowsUpdated = writableDatabase.update(
                    ScoreEntry.TABLE_NAME,
                    values,
                    singleSelection,
                    singleSelectionArgs
                )
            }
        }

        if (rowsUpdated > 0) {
            appContext.contentResolver.notifyChange(uri, null)
        }

        return rowsUpdated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val match = URI_MATCHER.match(uri)
        val writableDatabase = dbHelper.writableDatabase
        var rowsDeleted = 0

        when (match) {
            CODE_SCORES -> {
                rowsDeleted = writableDatabase.delete(
                    ScoreEntry.TABLE_NAME,
                    selection,
                    selectionArgs
                )
            }
            CODE_SCORES_ITEM -> {
                val id = ContentUris.parseId(uri)
                val singleSelection = "${BaseColumns._ID} = ?"
                val singleSelectionArgs = arrayOf(id.toString())

                rowsDeleted = writableDatabase.delete(
                    ScoreEntry.TABLE_NAME,
                    singleSelection,
                    singleSelectionArgs
                )
            }
        }

        if (rowsDeleted > 0) {
            appContext.contentResolver.notifyChange(uri, null)
        }

        return rowsDeleted
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    companion object {
        private const val AUTHORITY = "com.example.quizapp.provider"
        private const val PATH_SCORES = "scores"
        private const val CODE_SCORES = 100
        private const val CODE_SCORES_ITEM = 101

        private val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            URI_MATCHER.addURI(AUTHORITY, PATH_SCORES, CODE_SCORES)
            URI_MATCHER.addURI(AUTHORITY, "$PATH_SCORES/#", CODE_SCORES_ITEM)
        }
    }

}
