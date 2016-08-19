package dhbk.android.testcontentproviderlibrary.ui.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import dhbk.android.testcontentproviderlibrary.R;
import dhbk.android.testcontentproviderlibrary.database.NotesProvider;
import dhbk.android.testcontentproviderlibrary.ui.adapter.ListsAdapter;
import dhbk.android.testcontentproviderlibrary.ui.dialog.NewListDialog;

public class ListsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_LISTS = 10;
    private static final String DIALOG_NEW_LIST =  "net.simonvt.schematic.samples.ui.fragment.ListsFragment.newList";

    @BindView(R.id.addList)
    LinearLayout mAddList;
    @BindView(android.R.id.list)
    ListView listView;
    @BindView(android.R.id.empty)
    TextView emptyView;

    private ListsAdapter adapter;

    private OnListSelectedListener mListener;

    public ListsFragment() {
        // Required empty public constructor
    }

    public static ListsFragment newInstance() {
        return new ListsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    // TODO: 8/18/16 5 - set up view, call loader to start
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setEmptyView(emptyView);
        if (adapter != null) {
            listView.setAdapter(adapter);
        }

        // todo call loader by naming it with id
        getLoaderManager().initLoader(LOADER_LISTS, null, this);
    }

    // TODO: 8/18/16 6 -  onCreateLoader return cursor loader that getting data from content provider
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), NotesProvider.Lists.CONTENT_URI, ListsAdapter.PROJECTION, null, null, null);
    }

    // TODO: 8/18/16 9 - when getting data done, set it to listview
    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        if (adapter == null) {
            adapter = new ListsAdapter(getActivity(), data);
            listView.setAdapter(adapter);
        } else {
            adapter.changeCursor(data);
        }
    }

    // TODO: 8/18/16 10 - remove data when reset loader
    @Override
    public void onLoaderReset(Loader loader) {
        if (adapter != null) {
            adapter.changeCursor(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListSelectedListener) {
            mListener = (OnListSelectedListener) context;
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

    // TODO: 8/18/16 11 - when click button add, show a dialog to add a item into list.
    @OnClick(R.id.addList)
    void addList() {
        new NewListDialog().show(getFragmentManager(), DIALOG_NEW_LIST);
    }

    // TODO: 8/18/16 12 onListSelected
    @OnItemClick(android.R.id.list)
    void onListClicked(long id) {
        mListener.onListSelected(id);
    }


    public interface OnListSelectedListener {
        void onListSelected(long listId);
    }
}
