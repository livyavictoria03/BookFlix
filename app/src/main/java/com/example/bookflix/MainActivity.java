package com.example.bookflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Botão Verity
        Button verityButton = findViewById(R.id.verity);
        verityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, verity.class);
                startActivity(intent);
            }
        });

        // Botão Os Sete Maridos...
        Button os7mButton = findViewById(R.id.os7m);
        os7mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, seteMaridos.class);
                startActivity(intent);
            }
        });

        // Botão É Assim Que Começa
        Button eaqcButton = findViewById(R.id.eaqc);
        eaqcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, eaqc.class);
                startActivity(intent);
            }
        });

        // Botão É Assim Que Acaba
        Button eaqaButton = findViewById(R.id.eaqa);
        eaqaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, eaqa.class);
                startActivity(intent);
            }
        });

        // Botão O Vilão Que Me Morde
        Button ovqmmvButton = findViewById(R.id.ovqmmv);
        ovqmmvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ovqmmv.class);
                startActivity(intent);
            }
        });

        // Botão Minha Vida Fora de Série
        Button mvfsButton = findViewById(R.id.mvfs);
        mvfsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, mvfs.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
