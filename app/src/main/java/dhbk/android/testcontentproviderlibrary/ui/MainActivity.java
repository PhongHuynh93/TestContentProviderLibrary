package dhbk.android.testcontentproviderlibrary.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import dhbk.android.testcontentproviderlibrary.ui.fragment.ListFragment;
import dhbk.android.testcontentproviderlibrary.ui.fragment.ListsFragment;
import dhbk.android.testcontentproviderlibrary.ui.fragment.NoteFragment;

public class MainActivity extends AppCompatActivity implements ListsFragment.OnListSelectedListener, ListFragment.OnNoteSelectedListener,
        NoteFragment.NoteListener{

    private static final String FRAGMENT_LISTS =
            "net.simonvt.schematic.samples.ui.SampleActivity.LISTS";
    private static final String FRAGMENT_LIST =
            "net.simonvt.schematic.samples.ui.SampleActivity.LIST";
    private static final String FRAGMENT_NOTE =
            "net.simonvt.schematic.samples.ui.SampleActivity.NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 8/18/16 15 - add list
        if (savedInstanceState == null) {
            Fragment f = new ListsFragment();
            getSupportFragmentManager() //
                    .beginTransaction() //
                    .add(android.R.id.content, f, FRAGMENT_LISTS) //
                    .commit();
        }
    }

    /**
     * {@link NoteFragment}
     */
    @Override
    public void onNoteChange() {

    }

    @Override
    public void onNoteRemoved() {

    }

    /**
     * {@link ListsFragment}
     * @param listId
     */
    @Override
    public void onListSelected(long listId) {
        Fragment f = ListFragment.newInstance(listId);
        getSupportFragmentManager() //
                .beginTransaction() //
                .replace(android.R.id.content, f, FRAGMENT_LIST) //
                .addToBackStack(null) //
                .commit();
    }

    /**
     * {@link ListFragment}
     * @param listId
     */
    @Override
    public void onAddNote(long listId) {

    }

    @Override
    public void onNoteSelected(long listId, long noteId, String note, String status) {

    }

    // when we remove data from db, we update the layout again
    @Override
    public void onListRemoved() {
        getSupportFragmentManager().popBackStack();
    }
}
