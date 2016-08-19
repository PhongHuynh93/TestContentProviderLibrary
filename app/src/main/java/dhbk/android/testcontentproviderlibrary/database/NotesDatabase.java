package dhbk.android.testcontentproviderlibrary.database;

import net.simonvt.schematic.annotation.IfNotExists;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by huynhducthanhphong on 8/18/16.
 */
public class NotesDatabase {

    public static class Tables {

        @Table(ListColumns.class)
        @IfNotExists
        public static final String LISTS = "lists";
    }
}
