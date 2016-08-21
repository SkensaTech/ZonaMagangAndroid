package com.zonamagang.zonamagang;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Denandra on 20/08/2016.
 */
public class dialogInterviewFragment
        extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
//        return new AlertDialog.Builder(getActivity())
//                .setTitle("Tentukan Waktu Interview")
//                .setMessage("MESSAGE")
//                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getContext(), "Telah di batalkan",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getContext(), "Telah disimpan",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }
}
