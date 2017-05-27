package rh.calorietracker.feature.fooddetails;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import rh.calorietracker.R;
import rh.calorietracker.entity.Portion;

public class PortionDialogFragment extends DialogFragment {

    @BindView(R.id.edit_portion_name)
    EditText editName;

    @BindView(R.id.edit_portion_amount)
    EditText editAmount;

    private OnDialogAcceptedListener onDialogAcceptedListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_portion, null);
        ButterKnife.bind(this, view);

        builder.setView(view)
                .setPositiveButton(R.string.save, null)
                .setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();

        Button positiveButton = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: validate input

                if (onDialogAcceptedListener != null) {
                    Portion portion = new Portion();
                    portion.setName(editName.getText().toString());
                    portion.setAmount(Integer.parseInt(editAmount.getText().toString()));

                    onDialogAcceptedListener.onDialogAccepted(portion);
                }

                dismiss();
            }
        });

        //noinspection ConstantConditions
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void setOnDialogAcceptedListener(OnDialogAcceptedListener onDialogAcceptedListener) {
        this.onDialogAcceptedListener = onDialogAcceptedListener;
    }

    public interface OnDialogAcceptedListener {
        void onDialogAccepted(Portion portion);
    }
}
