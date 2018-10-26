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

import xbc.miniproject.com.xbcapplication.EditTechnologyActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.model.technology.DataList;

public class TechnologyViewHolder extends RecyclerView.ViewHolder{
    private TextView listTecnologyName;
    private ImageView listTechnologyButtonAction;

    public TechnologyViewHolder(@NonNull View itemView) {
        super(itemView);
        listTecnologyName = (TextView)itemView.findViewById(R.id.listTecnologyName);
        listTechnologyButtonAction = (ImageView)itemView.findViewById(R.id.listTechnologyButtonAction);
    }
    public void setModel(final DataList dataList, final int position, final Context context) {
        listTecnologyName.setText(dataList.getName());

        listTechnologyButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, listTechnologyButtonAction);
                popupMenu.inflate(R.menu.technology_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.technologyMenuEdit:
                                Toast.makeText(context, "Anda Menekan Action Edit pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, EditTechnologyActivity.class);
                                ((Activity)context).startActivity(intent);
                                return true;
                            case R.id.tchnologyMenuDeactivate:
                                //Toast.makeText(context, "Anda Menekan Action Deactive pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                DeactiveQuestion(dataList, position, context);
//                                DeactiveSuccessNotification(context);
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
    private  void  DeactiveQuestion(DataList dataList, final int position, final Context context){
        final AlertDialog.Builder builder;
        builder =  new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Deactive "+ dataList.getName())
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DeactiveSuccessNotification(context);
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
                .setMessage("Testimony Successfully Deactivated!")
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
