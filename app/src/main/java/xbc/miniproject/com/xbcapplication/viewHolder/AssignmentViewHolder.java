package xbc.miniproject.com.xbcapplication.viewHolder;

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

import xbc.miniproject.com.xbcapplication.DoneAssignmentActivity;
import xbc.miniproject.com.xbcapplication.EditAssignmnetActivity;
import xbc.miniproject.com.xbcapplication.R;
import xbc.miniproject.com.xbcapplication.dummyModel.AssignmentModel;
import xbc.miniproject.com.xbcapplication.model.assignment.AssignmentList;

public class AssignmentViewHolder extends RecyclerView.ViewHolder {
    TextView listAssignmentTextViewName,
            listAssignmentTextViewStartDate,
            listAssignmentTextViewEndDate;

    ImageView listAssignmentButtonAction;

    public AssignmentViewHolder(@NonNull View itemView) {
        super(itemView);

        listAssignmentTextViewName = (TextView) itemView.findViewById(R.id.listAssignmentTextViewName);
        listAssignmentTextViewStartDate = (TextView) itemView.findViewById(R.id.listAssignmentTextViewStartDate);
        listAssignmentTextViewEndDate = (TextView) itemView.findViewById(R.id.listAssignmentTextViewEndDate);

        listAssignmentButtonAction = (ImageView) itemView.findViewById(R.id.listAssignmentButtonAction);
    }

    public void setModel(final AssignmentList assignmentModel, final int position, final Context context) {
        listAssignmentTextViewName.setText(assignmentModel.getBiodata().getName());
        listAssignmentTextViewStartDate.setText(assignmentModel.getStartDate());
        listAssignmentTextViewEndDate.setText(assignmentModel.getEndDate());

        listAssignmentButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Anda Menekan Action Posisi: "+position,Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(context, listAssignmentButtonAction);
                popupMenu.inflate(R.menu.assignmnet_action_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.assignmentMenuEdit:
                                //Toast.makeText(context, "Anda Menekan Action Edit pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context, EditAssignmnetActivity.class);
                                context.startActivity(intent1);
                                return true;
                            case R.id.assignmentMenuDelete:
                                //Toast.makeText(context, "Anda Menekan Action Deactive pada Posisi: "+position,Toast.LENGTH_SHORT).show();
                                DeleteQuestion(assignmentModel,position,context);
                                return true;
                            case R.id.assignmentMenuHold:
//                                Toast.makeText(context, "Anda Menekan Action Hold pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                HoldQuestion(assignmentModel, position, context);
                                return true;
                            case R.id.assignmentMenuDone:
//                                Toast.makeText(context, "Anda Menekan Action Done pada Posisi: " + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, DoneAssignmentActivity.class);
                                context.startActivity(intent);
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

    private void DeleteQuestion(final AssignmentList assignmentModel, final int position, final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Menghapus " + assignmentModel.getBiodata().getName() + "?")
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

    private void HoldQuestion(final AssignmentList assignmentModel, final int position, final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning!")
                .setMessage("Apakah Anda Yakin Akan Hold " + assignmentModel.getBiodata().getName() + "?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HoldSuccessNotification(context);
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

    private void HoldSuccessNotification(final Context context) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Update!")
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