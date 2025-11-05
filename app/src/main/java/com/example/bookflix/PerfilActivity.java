package com.example.bookflix;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    EditText etNome, etDescricao;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        etNome = findViewById(R.id.etNome);
        etDescricao = findViewById(R.id.etDescricao);
        btnSalvar = findViewById(R.id.btnSalvar);

        SharedPreferences prefs = getSharedPreferences("PerfilUsuario", MODE_PRIVATE);
        etNome.setText(prefs.getString("nome", ""));
        etDescricao.setText(prefs.getString("descricao", ""));

        btnSalvar.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nome", etNome.getText().toString());
            editor.putString("descricao", etDescricao.getText().toString());
            editor.apply();
        });
    }
}
