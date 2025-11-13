package com.example.bookflix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class eaqa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eaqa);

        SalvosDao salvosDao = new SalvosDao(this);
        Button botaoSalvar2 = findViewById(R.id.botaoSalvar2);
        botaoSalvar2.setOnClickListener(v -> {
            boolean ok = salvosDao.inserirPorTitulo("É Assim que Acaba");
            Toast.makeText(this, ok ? "Livro salvo com sucesso!" : "Este livro já estava salvo.", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (vv, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            vv.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });
    }
}
