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

public class ovqmmv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ovqmmv);

        SalvosDao salvosDao = new SalvosDao(this);
        Button botaoSalvar4 = findViewById(R.id.botaoSalvar4);
        botaoSalvar4.setOnClickListener(v -> {
            boolean ok = salvosDao.inserirPorTitulo("O Verão Que Mudou a Minha Vida");
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
