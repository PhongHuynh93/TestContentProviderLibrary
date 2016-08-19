package dhbk.android.testcontentproviderlibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.ExecOnCreate;
import net.simonvt.schematic.annotation.IfNotExists;
import net.simonvt.schematic.annotation.OnConfigure;
import net.simonvt.schematic.annotation.OnCreate;
import net.simonvt.schematic.annotation.OnUpgrade;
import net.simonvt.schematic.annotation.Table;

import dhbk.android.testcontentproviderlibrary.database.listtable.ListColumns;
import dhbk.android.testcontentproviderlibrary.database.notetable.NoteColumns;

/**
 * Created by huynhducthanhphong on 8/18/16.
 */
// TODO: 8/19/16 5 define a database
@Database(version = NotesDatabase.VERSION,
        packageName = "net.simonvt.schematic.sample.provider")
public final class NotesDatabase {

    private NotesDatabase() {
    }

    // TODO: 8/19/16 6 define version of database
    public static final int VERSION = 1;

    /**
     * // TODO: 8/19/16 7 - define "list" table
     */
    public static class Tables {
        @Table(ListColumns.class)
        @IfNotExists
        // name of table
        public static final String LISTS = "lists";
    }

    /**
     * todo 8 - define "note" table
     */
    @Table(NoteColumns.class)
    public static final String NOTES = "notes";

    /**
     * todo 9 define must have method to create the sqlitedatabase - onCreate and onUpgrade
     * @param context
     * @param db
     */
    @OnCreate
    public static void onCreate(Context context, SQLiteDatabase db) {
    }

    @OnUpgrade
    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion,
                                 int newVersion) {
    }

    @OnConfigure
    public static void onConfigure(SQLiteDatabase db) {
    }

    @ExecOnCreate
    public static final String EXEC_ON_CREATE = "SELECT * FROM " + NOTES;
}
