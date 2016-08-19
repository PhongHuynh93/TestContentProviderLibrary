package dhbk.android.testcontentproviderlibrary.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import dhbk.android.testcontentproviderlibrary.R;
import dhbk.android.testcontentproviderlibrary.database.NotesProvider;

public class ListFragment extends Fragment {
    private static final String ARG_ID = "net.simonvt.schematic.samples.ui.fragment.ListFragment.id";
    private long listId;

    private OnNoteSelectedListener mListener;

    public ListFragment() {
    }

    public static ListFragment newInstance(long id) {
        ListFragment f = new ListFragment();

        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        listId = args.getLong(ARG_ID);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_remove, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                final Context appContext = getActivity().getApplicationContext();
                final long id = listId;
//                run this code on ui thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // delele all content of table
                        appContext.getContentResolver().delete(NotesProvider.Notes.fromList(id), null, null);
                        appContext.getContentResolver().delete(NotesProvider.Lists.withId(id), null, null);
                    }
                }).start();
                mListener.onListRemoved();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteSelectedListener) {
            mListener = (OnNoteSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnNoteSelectedListener {
        void onAddNote(long listId);
        void onNoteSelected(long listId, long noteId, String note, String status);
        void onListRemoved();
    }
}
