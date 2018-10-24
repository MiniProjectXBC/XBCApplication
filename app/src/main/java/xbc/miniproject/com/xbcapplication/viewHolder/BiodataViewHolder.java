package xbc.miniproject.com.xbcapplication.viewHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import xbc.miniproject.com.xbcapplication.EditBiodataActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.BiodataModel;
import xbc.miniproject.com.xbcapplication.model.biodata.DataList;

public class BiodataViewHolder extends RecyclerView.ViewHolder {
    TextView listBiodataTextViewName,
            listBiodataTextViewMajors,
            listBiodataTextViewGpa;

    ImageView listBiodataButtonAction;

    public BiodataViewHolder(@NonNull View itemView) {
        super(itemView);

        listBiodataButtonAction = (ImageView) itemView.findViewById(R.id.listBiodataButtonAction);
        listBiodataTextViewName = (TextView) itemView.findViewById(R.id.listBiodataTextViewName);
        listBiodataTextViewMajors = (TextView) itemView.findViewById(R.id.listBiodataTextViewMajors);
        listBiodataTextViewGpa = (TextView) itemView.findViewById(R.id.listBiodataTextViewGpa);

    }

    public void setModel(final DataList biodataModel, final int position, final Context context) {
        listBiodataTextViewName.setText(biodataModel.getName());
        listBiodataTextViewMajors.setText(biodataModel.getMajors());
        listBiodataTextViewGpa.setText(biodataModel.getGpa());

        listBiodataButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Anda Menekan Action Posisi: "+position,Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(context,listBiodataButtonAction);
                popupMenu.inflate(R.menu.biodata_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.biodataMenuEdit:
                                //Toast.makeText(context, "Anda Menekan Action Edit pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, EditBiodataActivity.class);
                                ((Activity)context).startActivity(intent);
                                return true;
                            case R.id.biodataMenuDeactivate:
                                //Toast.makeText(context, "Anda Menekan Action Deactive pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                DeactiveQuestion(biodataModel,position,context);
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

    private void DeactiveQuestion(final DataList biodataModel, final int position, final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan MenonAktifkan "+ biodataModel.getName()+"?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeactiveSuccessNotification(context);
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

    private void DeactiveSuccessNotification(final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Deactivated!")
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
