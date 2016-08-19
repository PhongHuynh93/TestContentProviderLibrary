package dhbk.android.testcontentproviderlibrary.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import dhbk.android.testcontentproviderlibrary.R;
import dhbk.android.testcontentproviderlibrary.database.NotesProvider;
import dhbk.android.testcontentproviderlibrary.database.listtable.ListColumns;

/**
 * Created by huynhducthanhphong on 8/19/16.
 * todo - make a custome dialog simpler by useing alertdialog and setView()
 */
public class NewListDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = LayoutInflater.from(builder.getContext()).inflate(R.layout.dialog_new_list, null);
        final EditText listName = (EditText) v.findViewById(R.id.listName);

        builder.setView(v);

        builder.setTitle(R.string.new_list)
                .setPositiveButton(R.string.create_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name = listName.getText().toString();
                        final Context context = getActivity().getApplicationContext();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO: 8/19/16 insert into list db
                                ContentValues cv = new ContentValues();
                                cv.put(ListColumns.TITLE, name);
                                context.getContentResolver().insert(NotesProvider.Lists.CONTENT_URI, cv);
                            }
                        }).start();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        return builder.create();
    }
}
