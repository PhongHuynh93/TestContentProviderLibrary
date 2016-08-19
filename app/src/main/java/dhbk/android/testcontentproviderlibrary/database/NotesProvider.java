package dhbk.android.testcontentproviderlibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.MapColumns;
import net.simonvt.schematic.annotation.NotifyBulkInsert;
import net.simonvt.schematic.annotation.NotifyDelete;
import net.simonvt.schematic.annotation.NotifyInsert;
import net.simonvt.schematic.annotation.NotifyUpdate;
import net.simonvt.schematic.annotation.TableEndpoint;

import java.util.HashMap;
import java.util.Map;

import dhbk.android.testcontentproviderlibrary.database.listtable.ListColumns;
import dhbk.android.testcontentproviderlibrary.database.notetable.NoteColumns;

/**
 * Created by huynhducthanhphong on 8/18/16.
 */

// TODO: 8/18/16 0 define authority + database
/**
 * authority: used to define the of position of this content provider
 * database: the lacal data which content provider abstract
 */
@ContentProvider(authority = NotesProvider.AUTHORITY,
        database = NotesDatabase.class,
        packageName = "net.simonvt.schematic.sample.provider")
public class NotesProvider {
    // TODO: 8/18/16 1 - giống như website, địa chỉ website để ta có thể access đến content mà website đó chứa, ta phải nêu 1 địa chỉ cho content provider
    // địa chỉ cho content provider: dạng tên package + tên class
    public static final String AUTHORITY = "net.simonvt.schematic.sample.NotesProvider";

    // TODO: 8/19/16 2 - tạo base url cho các resource
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    private NotesProvider() {
    }

    /**
     * todo 3 - app ta có 3 table tương ứng với 3 resource được chứa trong content provider, 3 tên table sẽ dc nối thêm vô base url để ta có thể access được tới 3 resource này.
     */
    interface Path {
        String LISTS = "lists";
        String NOTES = "notes";
        String FROM_LIST = "fromList";
    }

    /**
     * todo 10
     * @param paths
     * @return
     */
    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }


    /**
     * todo 11
     */
    @TableEndpoint(table = NotesDatabase.Tables.LISTS)
    public static class Lists {

        @MapColumns
        public static Map<String, String> mapColumns() {
            Map<String, String> map = new HashMap<>();

            map.put(ListColumns.NOTES, LIST_COUNT);

            return map;
        }

        // TODO: 8/18/16 12 return a "list"(multiple item) because "dir"
        @ContentUri(
                path = Path.LISTS,
                type = "vnd.android.cursor.dir/list",
                defaultSort = ListColumns.TITLE + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.LISTS);

        // todo 13 - return a single row based on an ID "where", (this depends on the kind of queries that you want for your database).
        @InexactContentUri(
                path = Path.LISTS + "/#",
                name = "LIST_ID",
                type = "vnd.android.cursor.item/list",
                whereColumn = ListColumns.ID,
                pathSegment = 1)

        // build uri mathcer for this resource
        public static Uri withId(long id) {
            return buildUri(Path.LISTS, String.valueOf(id));
        }

        static final String LIST_COUNT = "(SELECT COUNT(*) FROM "
                + NotesDatabase.NOTES
                + " WHERE "
                + NotesDatabase.NOTES
                + "."
                + NoteColumns.LIST_ID
                + "="
                + NotesDatabase.Tables.LISTS
                + "."
                + ListColumns.ID
                + ")";
    }

    @TableEndpoint(table = NotesDatabase.NOTES)
    public static class Notes {

        @ContentUri(
                path = Path.NOTES,
                type = "vnd.android.cursor.dir/note")
        public static final Uri CONTENT_URI = buildUri(Path.NOTES);

        @InexactContentUri(
                name = "NOTE_ID",
                path = Path.NOTES + "/#",
                type = "vnd.android.cursor.item/note",
                whereColumn = NoteColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(Path.NOTES, String.valueOf(id));
        }

        @InexactContentUri(
                name = "NOTES_FROM_LIST",
                path = Path.NOTES + "/" + Path.FROM_LIST + "/#",
                type = "vnd.android.cursor.dir/list",
                whereColumn = NoteColumns.LIST_ID,
                pathSegment = 2)

        // TODO: 8/18/16 21 uri for note
        public static Uri fromList(long listId) {
            return buildUri(Path.NOTES, Path.FROM_LIST, String.valueOf(listId));
        }

        @NotifyInsert(paths = Path.NOTES)
        public static Uri[] onInsert(ContentValues values) {
            final long listId = values.getAsLong(NoteColumns.LIST_ID);
            return new Uri[]{
                    Lists.withId(listId), fromList(listId),
            };
        }

        @NotifyBulkInsert(paths = Path.NOTES)
        public static Uri[] onBulkInsert(Context context, Uri uri, ContentValues[] values, long[] ids) {
            return new Uri[]{
                    uri,
            };
        }

        @NotifyUpdate(paths = Path.NOTES + "/#")
        public static Uri[] onUpdate(Context context,
                                     Uri uri, String where, String[] whereArgs) {
            final long noteId = Long.valueOf(uri.getPathSegments().get(1));
            Cursor c = context.getContentResolver().query(uri, new String[]{
                    NoteColumns.LIST_ID,
            }, null, null, null);
            c.moveToFirst();
            final long listId = c.getLong(c.getColumnIndex(NoteColumns.LIST_ID));
            c.close();

            return new Uri[]{
                    withId(noteId), fromList(listId), Lists.withId(listId),
            };
        }

        @NotifyDelete(paths = Path.NOTES + "/#")
        public static Uri[] onDelete(Context context,
                                     Uri uri) {
            final long noteId = Long.valueOf(uri.getPathSegments().get(1));
            Cursor c = context.getContentResolver().query(uri, null, null, null, null);
            c.moveToFirst();
            final long listId = c.getLong(c.getColumnIndex(NoteColumns.LIST_ID));
            c.close();

            return new Uri[]{
                    withId(noteId), fromList(listId), Lists.withId(listId),
            };
        }
    }
}
