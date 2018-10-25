package xbc.miniproject.com.xbcapplication.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.ClassModel;

public class ClassViewHolder extends RecyclerView.ViewHolder {
    TextView listClassTextViewBatch,
            listClassTextViewName;
    ImageView listClassButtonAction;

    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);

        listClassTextViewBatch = (TextView) itemView.findViewById(R.id.listClassTextViewBatch);
        listClassTextViewName = (TextView) itemView.findViewById(R.id.listClassTextViewName);
        listClassButtonAction = (ImageView) itemView.findViewById(R.id.listClassButtonAction);
    }

    public void setModel (final ClassModel classModel, final int position, final Context context){
        listClassTextViewBatch.setText(classModel.getBatch());
        listClassTextViewName.setText(classModel.getName());

        listClassButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, listClassButtonAction);
                popupMenu.inflate(R.menu.class_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.classMenuDelete:
                                //Toast.makeText(context, "Anda Menekan Action Deactive pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                DeleteQuestion(classModel,position,context);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void DeleteQuestion(final ClassModel classModel, final int position, final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Menghapus "+ classModel.getName()+"?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteSuccessNotification(context);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void DeleteSuccessNotification(final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Delete!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
