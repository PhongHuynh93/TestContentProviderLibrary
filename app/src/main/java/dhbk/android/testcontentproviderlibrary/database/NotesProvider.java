package dhbk.android.testcontentproviderlibrary.database;

import net.simonvt.schematic.annotation.ContentProvider;

/**
 * Created by huynhducthanhphong on 8/18/16.
 */

// TODO: 8/18/16 define authority + database

/**
 * authority: used to define the of position of this content provider
 * database: the lacal data which content provider abstract
 *
 */
@ContentProvider(authority = NotesProvider.AUTHORITY,
        database = NotesDatabase.class,
        packageName = "net.simonvt.schematic.sample.provider")
public class NotesProvider {
    // TODO: 8/18/16 1 -
    public static final String AUTHORITY = "net.simonvt.schematic.sample.NotesProvider";
}
