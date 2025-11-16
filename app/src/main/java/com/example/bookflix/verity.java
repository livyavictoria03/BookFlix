package com.example.bookflix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class verity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verity);

        SalvosDao salvosDao = new SalvosDao(this);
        Button botaoSalvar6 = findViewById(R.id.botaoSalvar6);
        botaoSalvar6.setOnClickListener(v -> {
            boolean ok = salvosDao.inserirPorTitulo("Verity");
            Toast.makeText(this, ok ? "Livro salvo com sucesso!" : "Este livro já estava salvo.", Toast.LENGTH_SHORT).show();
        });

        // Botão Home
        FloatingActionButton fabHome = findViewById(R.id.fabHome);
        fabHome.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (vv, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            vv.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });
    }
}
