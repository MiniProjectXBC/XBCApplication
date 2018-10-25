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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import xbc.miniproject.com.xbcapplication.EditTestimonyActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.MonitoringModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TestimonyModel;
import xbc.miniproject.com.xbcapplication.model.testimony.DataListTestimony;

public class TestimonyViewHolder extends RecyclerView.ViewHolder {
    private TextView listTesimonyTitle;
    private ImageView listTestimonyButtonAction;
    public TestimonyViewHolder(@NonNull View itemView) {
        super(itemView);
        listTesimonyTitle = (TextView)itemView.findViewById(R.id.listTesimonyTitle);
        listTestimonyButtonAction = (ImageView)itemView.findViewById(R.id.listTestimonyButtonAction);
    }
    public void setModel(final DataListTestimony testimonyModel, final int position, final Context context){
        listTesimonyTitle.setText(testimonyModel.getTitle());
        listTestimonyButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu =  new PopupMenu(context, listTestimonyButtonAction);
                popupMenu.inflate(R.menu.testimony_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case  R.id.testimonyMenuEdit:
                                Toast.makeText(context,"Anda menekan button edit"+position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, EditTestimonyActivity.class);
                                ((Activity)context).startActivity(intent);
                                return true;
                            case R.id.testimonyMenuDelete:
                                Toast.makeText(context,"Anda Menekan Delete pada Posisi"+position, Toast.LENGTH_SHORT).show();
                                DeleteQuestion(testimonyModel, position, context);
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
    private void DeleteQuestion(DataListTestimony testimonyModel, final int position, final Context context){
        final AlertDialog.Builder builder;
        builder =  new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Delete "+ testimonyModel.getTitle())
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DeleteSuccessNotification(context);
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
                .setMessage("Testimony Successfully Deleted!")
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
