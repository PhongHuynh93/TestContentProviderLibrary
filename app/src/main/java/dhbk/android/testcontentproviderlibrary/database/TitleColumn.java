package dhbk.android.testcontentproviderlibrary.database;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;

import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

/**
 * Created by huynhducthanhphong on 8/19/16.
 */

public interface TitleColumn {
    @DataType(TEXT)
    @NotNull
    String TITLE = "title";
}