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

import xbc.miniproject.com.xbcapplication.AddPlacementMonitoringActivity;
import xbc.miniproject.com.xbcapplication.EditBiodataActivity;
import xbc.miniproject.com.xbcapplication.EditIdleMonitoringActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.MonitoringModel;
import xbc.miniproject.com.xbcapplication.model.monitoring.ModelMonitoring;
import xbc.miniproject.com.xbcapplication.model.monitoring.MonitoringDataList;

public class MonitoringViewHolder extends RecyclerView.ViewHolder {
    TextView listMonitoringTextViewName,
            listMonitoringTextViewIdleDate,
            listMonitoringTextViewPlacementDate;

    ImageView listMonitoringButtonAction;

    public MonitoringViewHolder(@NonNull View itemView) {
        super(itemView);

        listMonitoringTextViewName = (TextView) itemView.findViewById(R.id.listMonitoringTextViewName);
        listMonitoringTextViewIdleDate = (TextView) itemView.findViewById(R.id.listMonitoringTextViewIdleDate);
        listMonitoringTextViewPlacementDate = (TextView) itemView.findViewById(R.id.listMonitoringTextViewPlacementDate);

        listMonitoringButtonAction = (ImageView) itemView.findViewById(R.id.listMonitoringButtonAction);
    }

    public void setModel(final MonitoringDataList monitoringModel, final int position, final Context context){
        listMonitoringTextViewName.setText(monitoringModel.getMonitoringBiodata().getName());
        listMonitoringTextViewIdleDate.setText(monitoringModel.getIdleDate());
        if (monitoringModel.getPlacementDate() == null){
            listMonitoringTextViewPlacementDate.setText("");
        } else{
            listMonitoringTextViewPlacementDate.setText(monitoringModel.getPlacementDate().toString());
        }

        listMonitoringButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Anda Menekan Action Posisi: "+position,Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(context, listMonitoringButtonAction);
                popupMenu.inflate(R.menu.monitoring_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.monitoringMenuEdit:
                                //Toast.makeText(context, "Anda Menekan Action Edit pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context,EditIdleMonitoringActivity.class);
                                context.startActivity(intent1);
                                return true;
                            case R.id.monitoringMenuPlacement:
                                //Toast.makeText(context, "Anda Menekan Action Placement pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(context,AddPlacementMonitoringActivity.class);
                                context.startActivity(intent2);
                                return true;
                            case R.id.monitoringMenuDelete:
                                Toast.makeText(context, "Anda Menekan Action Delete pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                DeleteQuestion(monitoringModel, position, context);
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

    private void DeleteQuestion(final MonitoringDataList monitoringModel, final int position, final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Delete "+ monitoringModel.getMonitoringBiodata().getName()+"?")
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
                .setMessage("Data Successfully Deleted!")
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
