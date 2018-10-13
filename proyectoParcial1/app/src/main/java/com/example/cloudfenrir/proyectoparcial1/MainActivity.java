package com.example.cloudfenrir.proyectoparcial1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Alumno> alumnos;
    private ArrayList<Alumno> alumnos2;
    private ArrayList<String> matriculas1;
    private ArrayList<String> matriculas2;
    private ArrayList<Integer> asistencia1;
    private ArrayList<Integer> asistencia2;
    private int grupo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navegacion);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enviando correo...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                switch (grupo){
                    case 1 : enviarCorreo(matriculas1, asistencia1); break;
                    case 2 : enviarCorreo(matriculas2, asistencia2); break;
                }

            }
        });
        alumnos = new ArrayList<>();
        alumnos.add(new Alumno(R.drawable.arcineda, "Ramon Arcineda", "15130101", true));
        alumnos.add(new Alumno(R.drawable.aldrin, "Aldrin Arredondo", "15130229", true));
        alumnos.add(new Alumno(R.drawable.andrea, "Andrea Gaxiola", "15130128", true));
        alumnos.add(new Alumno(R.drawable.rosa, "Rosa Lazcano", "15130210", true));
        alumnos.add(new Alumno(R.drawable.to_o, "Antonio Lopez", "15130268", true));
        alumnos.add(new Alumno(R.drawable.abdel, "Abdel Quiñonez", "15130221", true));
        alumnos.add(new Alumno(R.drawable.lm, "Luis Mario Romero", "15130129", true));
        alumnos.add(new Alumno(R.drawable.diana, "Diana Ruiz", "15130223", true));
        alumnos.add(new Alumno(R.drawable.chapito, "Juan Ruiz", "15130229", true));
        alumnos.add(new Alumno(R.drawable.hector, "Hector Zaragoza", "15130259", true));

        alumnos2 = new ArrayList<>();
        alumnos2.add(new Alumno(R.drawable.arcineda, "Ramon Arcineda", "15130101", true));
        alumnos2.add(new Alumno(R.drawable.aldrin, "Aldrin Arredondo", "15130229", true));
        alumnos2.add(new Alumno(R.drawable.andrea, "Andrea Gaxiola", "15130128", true));
        alumnos2.add(new Alumno(R.drawable.rosa, "Rosa Lazcano", "15130210", true));
        alumnos2.add(new Alumno(R.drawable.to_o, "Antonio Lopez", "15130268", true));
        alumnos2.add(new Alumno(R.drawable.abdel, "Abdel Quiñonez", "15130221", true));
        alumnos2.add(new Alumno(R.drawable.lm, "Luis Mario Romero", "15130129", true));
        alumnos2.add(new Alumno(R.drawable.diana, "Diana Ruiz", "15130223", true));
        alumnos2.add(new Alumno(R.drawable.chapito, "Juan Ruiz", "15130229", true));
        alumnos2.add(new Alumno(R.drawable.hector, "Hector Zaragoza", "15130259", true));

        agregarMatriculas();
        agregarAsistencias();

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
        listaAlumnos.setLayoutManager(llm);
        AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(alumnos, asistencia1);
        listaAlumnos.setAdapter(alumnoAdaptador);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregar_correo) {
            obtenerEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_grupo1:
                    grupo = 1;
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos.setLayoutManager(llm);
                    AdaptadorAlumnos alumnoAdaptador = new AdaptadorAlumnos(alumnos, asistencia1);
                    listaAlumnos.setAdapter(alumnoAdaptador);
                    Log.d("gp 1", "1");
                    return true;
                case R.id.navegacion_grupo2:
                    grupo = 2;
                    LinearLayoutManager llm2 = new LinearLayoutManager(MainActivity.this);
                    llm2.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView listaAlumnos2 = findViewById(R.id.rv_listaGrupos);
                    listaAlumnos2.setLayoutManager(llm2);
                    AdaptadorAlumnos alumnoAdaptador2 = new AdaptadorAlumnos(alumnos2, asistencia2);
                    listaAlumnos2.setAdapter(alumnoAdaptador2);
                    Log.d("gp 2", "2");
                    return true;
            }
            return false;
        }
    };

    public void obtenerEmail(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setTitle("Ingrese email:");
        final View dialoglayout = inflater.inflate(R.layout.mandar_email, null);
        builder.setView(dialoglayout)
                // Add action buttons
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etEmail = dialoglayout.findViewById(R.id.tidt_email);
                        String email = etEmail.getText().toString();
                        Log.d("EMAIL: ", ""+email);
                        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", email);
                        editor.commit();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void agregarMatriculas(){
        matriculas1 = new ArrayList<>();
        matriculas2 = new ArrayList<>();
        matriculas1.add("15130101");
        matriculas1.add("15130229");
        matriculas1.add("15130128");
        matriculas1.add("15130210");
        matriculas1.add("15130268");
        matriculas1.add("15130221");
        matriculas1.add("15130129");
        matriculas1.add("15130223");
        matriculas1.add("15130229");
        matriculas1.add("15130259");
        matriculas2.add("15130101");
        matriculas2.add("15130229");
        matriculas2.add("15130128");
        matriculas2.add("15130210");
        matriculas2.add("15130268");
        matriculas2.add("15130221");
        matriculas2.add("15130129");
        matriculas2.add("15130223");
        matriculas2.add("15130229");
        matriculas2.add("15130259");
    }

    public void agregarAsistencias(){
        asistencia1 = new ArrayList<>();
        asistencia2 = new ArrayList<>();
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia1.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
        asistencia2.add(1);
    }

    public void enviarCorreo(ArrayList<String> matriculas, ArrayList<Integer> asistecias){
        Intent intent = new Intent(Intent.ACTION_SEND);

        SharedPreferences preferences  = getSharedPreferences("Email", Context.MODE_PRIVATE);
        String emailGuardado = preferences.getString("email", "nada");

        String[] to = {emailGuardado};
        String[] cc = {""};
        String contenido = "";

        for (int i = 0; i < 10; i++) {
            contenido = contenido.concat(matriculas.get(i) + " --- "+ asistecias.get(i)+"\n");
        }

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_CC, cc);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Programa Pase Lista 15130128");
        intent.putExtra(Intent.EXTRA_TEXT,"Lista asistencias: \n"+contenido);
        try {
            startActivity(Intent.createChooser(intent, "How to send mail?"));
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

}
