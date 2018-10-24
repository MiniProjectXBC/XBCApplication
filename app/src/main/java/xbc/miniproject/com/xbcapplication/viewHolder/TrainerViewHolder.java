package xbc.miniproject.com.xbcapplication.viewHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import xbc.miniproject.com.xbcapplication.EditTechnologyActivity;
import xbc.miniproject.com.xbcapplication.EditTrainerActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.TechnologyModel;
import xbc.miniproject.com.xbcapplication.dummyModel.TrainerModel;
import xbc.miniproject.com.xbcapplication.model.trainer.DataListTrainer;

public class TrainerViewHolder extends RecyclerView.ViewHolder {
    private TextView listTrainerName;
    private ImageView listTrainerButtonAction;

    public TrainerViewHolder(@NonNull View itemView) {
        super(itemView);
        listTrainerName = (TextView)itemView.findViewById(R.id.listTrainerName);
        listTrainerButtonAction = (ImageView)itemView.findViewById(R.id.listTrainerButtonAction);
    }
    public void setModel(DataListTrainer trainerModel, final int position, final Context context) {
        listTrainerName.setText(trainerModel.getName());
        listTrainerButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, listTrainerButtonAction);
                popupMenu.inflate(R.menu.trainer_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.trainerMenuEdit:
                                Toast.makeText(context, "Anda Menekan Action Edit pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, EditTrainerActivity.class);
                                ((Activity)context).startActivity(intent);
                                return true;
                            case R.id.trainerMenuDeactivate:
                                //Toast.makeText(context, "Anda Menekan Action Deactive pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                DeactiveSuccessNotification(context);
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
