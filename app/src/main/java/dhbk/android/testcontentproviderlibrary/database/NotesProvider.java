package dhbk.android.testcontentproviderlibrary.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;

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
     * todo
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





}
