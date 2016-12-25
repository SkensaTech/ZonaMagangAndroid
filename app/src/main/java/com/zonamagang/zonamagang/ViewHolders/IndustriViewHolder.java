package com.zonamagang.zonamagang.ViewHolders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import com.zonamagang.zonamagang.FirebaseUtil;
import com.zonamagang.zonamagang.MainActivity;
import com.zonamagang.zonamagang.Model.industri;
import com.zonamagang.zonamagang.R;
import com.zonamagang.zonamagang.detail_industri;

/**
 * Created by mmmm on 19-Dec-16.
 */

public class IndustriViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    String nama,alamat,email,no_telp,profil,jobdesc,kualifikasi,kuota,logo;

    public IndustriViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindIndustri(industri ind){
        TextView namaIndustri = (TextView) mView.findViewById(R.id.namaIndustri);
        TextView alamatIndustri = (TextView) mView.findViewById(R.id.alamatIndustri);
        TextView telpIndustri = (TextView) mView.findViewById(R.id.telpIndustri);
        TextView emailIndustri = (TextView) mView.findViewById(R.id.emailIndustri);
        TextView kuotaIndustri = (TextView) mView.findViewById(R.id.kuotaIndustri);
        ImageView baruItemFoto = (ImageView) mView.findViewById(R.id.baruItemFoto);
        Button btnDetail = (Button) mView.findViewById(R.id.siswa_layout_belum_btnDetail);
        Button btnDaftar = (Button) mView.findViewById(R.id.siswa_layout_belum_btnDaftar);

        namaIndustri.setText(ind.getNama());
        alamatIndustri.setText(ind.getAlamat());
        telpIndustri.setText(ind.getNo_telp());
        emailIndustri.setText(ind.getEmail());
        Picasso.with(mContext).load(ind.getLogo()).into(baruItemFoto);
        kuotaIndustri.setText("Kuota "+ind.getKuota()+" Orang");

        nama = ind.getNama();
        alamat = ind.getAlamat();
        email = ind.getEmail();
        no_telp = ind.getNo_telp();
        profil = ind.getProfil();
        jobdesc = ind.getJobdesc();
        kualifikasi = ind.getKualifikasi();
        kuota = ind.getKuota();
        logo = ind.getLogo();

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, detail_industri.class );
                intent.putExtra("nama",nama);
                intent.putExtra("alamat",alamat);
                intent.putExtra("email",email);
                intent.putExtra("no_telp",no_telp);
                intent.putExtra("profil",profil);
                intent.putExtra("jobdesc",jobdesc);
                intent.putExtra("kualifikasi",kualifikasi);
                intent.putExtra("kuota",kuota);
                intent.putExtra("logo",logo);
                mContext.startActivity(intent);

            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseUtil.getCurrentUserRef() == null){
                    String pesan = "Untuk mendaftar ke industri, anda harus login/daftar terlebih dahulu. Ingin login/daftar sekarang ?";
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage(pesan)
                            .setTitle("Perhatian");

                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intentMainActivity = new Intent(mContext, MainActivity.class);
                            mContext.startActivity(intentMainActivity);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

}
