package com.example.bookflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Botão Verity
        Button verityButton = findViewById(R.id.verity);
        verityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, verity.class);
            startActivity(intent);
        });

        // Botão Os Sete Maridos
        Button os7mButton = findViewById(R.id.os7m);
        os7mButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, seteMaridos.class);
            startActivity(intent);
        });

        // Botão É Assim Que Começa
        Button eaqcButton = findViewById(R.id.eaqc);
        eaqcButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, eaqc.class);
            startActivity(intent);
        });

        // Botão É Assim Que Acaba
        Button eaqaButton = findViewById(R.id.eaqa);
        eaqaButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, eaqa.class);
            startActivity(intent);
        });

        // Botão O Vilão Que Me Morde
        Button ovqmmvButton = findViewById(R.id.ovqmmv);
        ovqmmvButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ovqmmv.class);
            startActivity(intent);
        });

        // Botão Minha Vida Fora de Série
        Button mvfsButton = findViewById(R.id.mvfs);
        mvfsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, mvfs.class);
            startActivity(intent);
        });

        // Botão "+" (Livros Salvos)
        FloatingActionButton botaoMais = findViewById(R.id.floatingActionButton9);
        botaoMais.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LivrosSalvosActivity.class);
            startActivity(intent);
        });

        // Botão de Notificação (sininho)
        FloatingActionButton sino = findViewById(R.id.floatingActionButton8);
        sino.setOnClickListener(v -> {
            String[] dicas = {
                    "Dica: Leia pelo menos 10 páginas por dia 📖",
                    "Curiosidade: 'Dom Quixote' é considerado o livro mais vendido da história!",
                    "Dica: Leia em um lugar silencioso para aumentar a concentração 🤫",
                    "Dica: Faça anotações enquanto lê para fixar melhor as ideias ✍️",
                    "Curiosidade: O primeiro livro impresso no mundo foi a Bíblia de Gutenberg 📜",
                    "Dica: Experimente ler diferentes gêneros literários!",
                    "Curiosidade: Stephen King escreve todos os dias, até aos fins de semana 👑",
                    "Dica: Releia seu livro favorito — sempre há algo novo a descobrir 💫",
                    "Curiosidade: O livro mais traduzido do mundo é a Bíblia 🌍",
                    "Dica: Use marcadores para guardar suas partes favoritas 📑",
                    "Curiosidade: O gênero romance surgiu no século XVIII 💕",
                    "Dica: Ler antes de dormir ajuda a relaxar e dormir melhor 😴",
                    "Curiosidade: O primeiro e-book foi criado em 1971 💻",
                    "Dica: Crie metas de leitura mensais 🎯",
                    "Curiosidade: O maior livro do mundo pesa mais de 1.500 kg!",
                    "Dica: Participe de clubes de leitura para trocar ideias 📚",
                    "Curiosidade: 'Harry Potter' foi rejeitado por 12 editoras antes de ser publicado 🧙‍♂️",
                    "Dica: Leia com fones e uma música leve para relaxar 🎶",
                    "Curiosidade: O livro mais caro já vendido custou US$ 30,8 milhões 💰",
                    "Dica: Leia pela manhã — seu cérebro absorve mais cedo 🌅",
                    "Curiosidade: Agatha Christie é a autora mais traduzida do mundo 🔍",
                    "Dica: Deixe o celular longe durante a leitura 📵",
                    "Curiosidade: Machado de Assis aprendeu francês sozinho 🇫🇷",
                    "Dica: Use um aplicativo para organizar sua lista de leituras 📝",
                    "Curiosidade: A palavra ‘livro’ vem do latim *liber*, que significa ‘casca de árvore’ 🌳",
                    "Dica: Troque livros com amigos para descobrir novas histórias 🤝",
                    "Curiosidade: 'O Pequeno Príncipe' já foi traduzido para mais de 400 idiomas 🌟",
                    "Dica: Faça um resumo após terminar um livro — ajuda na memória 🧠",
                    "Curiosidade: O menor livro do mundo tem 22 páginas e mede 0,75 mm 📏",
                    "Dica: Prefira ler versões físicas de clássicos, é uma experiência única 📗",
                    "Curiosidade: O primeiro romance moderno foi 'Dom Quixote' ⚔️",
                    "Dica: Tenha sempre um livro com você — nunca se sabe quando terá tempo ⏳",
                    "Curiosidade: O Japão é o país que mais publica livros por ano 📈",
                    "Dica: Experimente ler em voz alta — ajuda na compreensão 🗣️",
                    "Curiosidade: 'O Senhor dos Anéis' levou 12 anos para ser escrito ⛰️",
                    "Dica: Releia livros que marcaram sua infância 👶",
                    "Curiosidade: O autor de 'Sherlock Holmes' também era médico 👨‍⚕️",
                    "Dica: Escolha livros com temas que você ama 💖",
                    "Curiosidade: 'O Alquimista' é o livro brasileiro mais vendido no mundo 🌍",
                    "Dica: Não tenha pressa — o importante é aproveitar a leitura 🕰️",
                    "Curiosidade: 'Guerra e Paz' tem mais de 500 mil palavras 😮",
                    "Dica: Tire fotos das suas leituras e compartilhe com amigos 📸",
                    "Curiosidade: O primeiro romance escrito por uma mulher é de 1000 d.C. 👩‍💻",
                    "Dica: Tenha um cantinho especial só para ler 🛋️",
                    "Curiosidade: Ler reduz o estresse em até 68% 😌",
                    "Dica: Intercale leituras leves com livros mais densos ⚖️",
                    "Curiosidade: O livro mais longo do mundo tem 9.609.000 caracteres!",
                    "Dica: Evite ler cansado — o foco é essencial 💤",
                    "Curiosidade: 'Cem Anos de Solidão' levou 18 meses para ser escrito 🌻",
                    "Dica: Leitura constante melhora sua escrita ✍️"
            };

            String dica = dicas[new Random().nextInt(dicas.length)];

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("📚 BookFlix · Dica Literária")
                    .setMessage(dica)
                    .setPositiveButton("OK", null)
                    .show();
        });

        // Botão de Perfil (abre PerfilActivity)
        FloatingActionButton botaoPerfil = findViewById(R.id.floatingActionButton13);
        botaoPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        // Ajuste de layout Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
