package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText navninn;
    EditText telefoninn;
    EditText idinn;
    TextView utskrift;
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navninn = (EditText) findViewById(R.id.navn);
        telefoninn = (EditText) findViewById(R.id.telefon);
        idinn = (EditText) findViewById(R.id.min_id);
        utskrift = (TextView) findViewById(R.id.utskrift);
        dbHelper = new DBHandler(this);
        db = dbHelper.getWritableDatabase();

    }

    public void leggtil(View v) {
        Kontakt kontakt = new Kontakt(navninn.getText().toString(),
                telefoninn.getText().toString());
        dbHelper.leggTilKontakt(db, kontakt);
    }

    public void visalle(View v) {
        String tekst = "";
        List<Kontakt> kontakter = dbHelper.finnAlleKontakter(db);
        for (Kontakt kontakt : kontakter) {
            tekst = tekst + "Id: " + kontakt.get_ID() + ",Navn: " +
                    kontakt.getNavn() + " ,Telefon: " +
                    kontakt.getTlf() + "\n";
        }
        utskrift.setText(tekst);
    }

    public void slett(View v) {
        Long kontaktid = (Long.parseLong(idinn.getText().toString()));
        dbHelper.slettKontakt(db,kontaktid);
    }

    public void oppdater(View v) {
        Kontakt kontakt = new Kontakt();
        kontakt.setNavn(navninn.getText().toString());
        kontakt.setTlf(telefoninn.getText().toString());
        kontakt.set_ID(Long.parseLong(idinn.getText().toString()));
        dbHelper.oppdaterKontakt(db, kontakt);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
