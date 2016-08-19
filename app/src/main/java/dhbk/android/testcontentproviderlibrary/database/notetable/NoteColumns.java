package dhbk.android.testcontentproviderlibrary.database.notetable;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.Check;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.References;

import dhbk.android.testcontentproviderlibrary.database.NotesDatabase;
import dhbk.android.testcontentproviderlibrary.database.listtable.ListColumns;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by huynhducthanhphong on 8/19/16.
 */

/**
 * todo 4 - create a class the contains the columns of database table
 * table note contains notes from user, related to the position of a row that users has clicked.
 */
public interface NoteColumns {
    /**
     * the status of a note, must be one of two strings.
     */
    String STATUS_NEW = "new";
    String STATUS_COMPLETED = "completed";

    // column 1
    @DataType(INTEGER)
    @PrimaryKey
    @AutoIncrement
    String ID = "_id";

    // column 2 - related to id of list table
    @DataType(INTEGER)
    @References(table = NotesDatabase.Tables.LISTS, column = ListColumns.ID)
    String LIST_ID = "listId";

    // column 3
    @DataType(TEXT)
    String NOTE = "note";

    // column 4, this is a constraint - the user must write one of two string (new or complete )
    @DataType(TEXT)
    @Check(NoteColumns.STATUS + " in ('" + NoteColumns.STATUS_NEW + "', '" + NoteColumns.STATUS_COMPLETED + "')")
    String STATUS = "status";

}