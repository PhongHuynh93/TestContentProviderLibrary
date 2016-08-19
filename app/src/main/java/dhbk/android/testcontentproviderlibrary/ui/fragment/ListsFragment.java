package dhbk.android.testcontentproviderlibrary.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dhbk.android.testcontentproviderlibrary.R;

public class ListsFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_lists, container, false);
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


    public interface OnListSelectedListener {
        void onListSelected(long listId);
    }
}
