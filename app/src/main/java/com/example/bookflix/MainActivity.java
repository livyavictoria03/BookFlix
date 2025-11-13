package com.example.bookflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listSugestoes;
    private ArrayAdapter<String> sugestoesAdapter;

    private final List<String> todosTitulos = new ArrayList<>();
    private final List<String> filtrados = new ArrayList<>();
    private final Map<String, Class<?>> mapaTituloActivity = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // mapear título -> activity
        mapaTituloActivity.put("Verity", verity.class);
        mapaTituloActivity.put("Os Sete Maridos de Evelyn Hugo", seteMaridos.class);
        mapaTituloActivity.put("É Assim que Começa", eaqc.class);
        mapaTituloActivity.put("É Assim que Acaba", eaqa.class);
        mapaTituloActivity.put("O Verão Que Mudou a Minha Vida", ovqmmv.class);
        mapaTituloActivity.put("Minha Vida Fora de Série", mvfs.class);

        // carregar títulos do banco
        LivrosDao livrosDao = new LivrosDao(this);
        todosTitulos.clear();
        todosTitulos.addAll(livrosDao.listarTitulos());

        // busca/sugestões
        searchView = findViewById(R.id.searchView);
        listSugestoes = findViewById(R.id.listSugestoes);

        // usa layout personalizado item_sugestao.xml
        sugestoesAdapter = new ArrayAdapter<>(
                this,
                R.layout.item_sugestao,   // layout do item
                R.id.txtItem,             // id do TextView dentro do layout
                filtrados                 // lista de dados
        );
        listSugestoes.setAdapter(sugestoesAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                abrirSeExistir(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrar(newText);
                return true;
            }
        });

        listSugestoes.setOnItemClickListener((parent, view, position, id) -> {
            String titulo = filtrados.get(position);
            abrirSeExistir(titulo);
        });

        // botões para cada livro
        Button verityButton = findViewById(R.id.verity);
        verityButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, verity.class)));

        Button os7mButton = findViewById(R.id.os7m);
        os7mButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, seteMaridos.class)));

        Button eaqcButton = findViewById(R.id.eaqc);
        eaqcButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, eaqc.class)));

        Button eaqaButton = findViewById(R.id.eaqa);
        eaqaButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, eaqa.class)));

        Button ovqmmvButton = findViewById(R.id.ovqmmv);
        ovqmmvButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ovqmmv.class)));

        Button mvfsButton = findViewById(R.id.mvfs);
        mvfsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, mvfs.class)));

        // FAB "+"
        FloatingActionButton botaoMais = findViewById(R.id.floatingActionButton9);
        botaoMais.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LivrosSalvosActivity.class)));

        // FAB sino (dicas)
        FloatingActionButton sino = findViewById(R.id.floatingActionButton8);
        sino.setOnClickListener(v -> {
            String[] dicas = {
                    "Dica: Leia pelo menos 10 páginas por dia 📖",
                    "Curiosidade: 'Dom Quixote' é considerado o livro mais vendido da história!",
                    "Dica: Leia em um lugar silencioso para aumentar a concentração 🤫",
                    "Dica: Faça anotações enquanto lê para fixar melhor as ideias ✍️",
                    "Curiosidade: O primeiro livro impresso no mundo foi a Bíblia de Gutenberg 📜",
                    "Dica: Experimente ler diferentes gêneros literários!",
                    "Curiosidade: 'O Senhor dos Anéis' levou 12 anos para ser escrito ⛰️",
                    "Dica: Releia livros que marcaram sua infância 👶",
            };
            String dica = dicas[new Random().nextInt(dicas.length)];
            new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("📚 BookFlix · Dica Literária")
                    .setMessage(dica)
                    .setPositiveButton("OK", null)
                    .show();
        });

        // FAB perfil
        FloatingActionButton botaoPerfil = findViewById(R.id.floatingActionButton13);
        botaoPerfil.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PerfilActivity.class)));

        // Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // ------- BUSCA -------
    private void filtrar(String texto) {
        filtrados.clear();
        if (texto == null || texto.trim().isEmpty()) {
            listSugestoes.setVisibility(View.GONE);
            sugestoesAdapter.notifyDataSetChanged();
            return;
        }
        String q = normalizar(texto);
        for (String t : todosTitulos) {
            if (normalizar(t).contains(q)) {
                filtrados.add(t);
            }
        }
        listSugestoes.setVisibility(filtrados.isEmpty() ? View.GONE : View.VISIBLE);
        sugestoesAdapter.notifyDataSetChanged();
    }

    private void abrirSeExistir(String tituloDigitado) {
        if (tituloDigitado == null) return;
        for (String key : mapaTituloActivity.keySet()) {
            if (normalizar(key).equals(normalizar(tituloDigitado))) {
                abrir(key);
                return;
            }
        }
        if (!filtrados.isEmpty()) {
            abrir(filtrados.get(0));
        }
    }

    private void abrir(String titulo) {
        Class<?> destino = mapaTituloActivity.get(titulo);
        if (destino != null) {
            startActivity(new Intent(MainActivity.this, destino));
            searchView.clearFocus();
            listSugestoes.setVisibility(View.GONE);
        }
    }

    private static String normalizar(String s) {
        String out = s.toLowerCase();
        out = out.replace("á", "a").replace("à", "a").replace("â", "a").replace("ã", "a")
                .replace("é", "e").replace("ê", "e")
                .replace("í", "i")
                .replace("ó", "o").replace("ô", "o").replace("õ", "o")
                .replace("ú", "u")
                .replace("ç", "c");
        return out;
    }
}
