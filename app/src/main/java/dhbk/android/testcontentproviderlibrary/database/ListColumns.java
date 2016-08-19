package dhbk.android.testcontentproviderlibrary.database;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;

/**
 * Created by huynhducthanhphong on 8/19/16.
 */

public interface ListColumns extends TitleColumn {
    @DataType(INTEGER)
    @PrimaryKey
    @AutoIncrement
    String ID = "_id";

    String NOTES = "notes";
}
