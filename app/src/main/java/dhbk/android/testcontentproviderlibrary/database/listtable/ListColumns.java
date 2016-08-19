package dhbk.android.testcontentproviderlibrary.database.listtable;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;

/**
 * Created by huynhducthanhphong on 8/19/16.
 */

public interface ListColumns extends TitleColumn {
    // todo 4a - list column contain  one column name "id" that autoincrease and "title" to name the title of note
    @DataType(INTEGER)
    @PrimaryKey
    @AutoIncrement
    String ID = "_id";

    String NOTES = "notes";
}
