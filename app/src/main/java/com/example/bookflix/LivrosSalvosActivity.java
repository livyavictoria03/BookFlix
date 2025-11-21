package com.example.bookflix;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LivrosSalvosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LivroSalvoAdapter adapter;
    private SalvosDao salvosDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros_salvos);

        recyclerView = findViewById(R.id.recyclerViewSalvos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        salvosDao = new SalvosDao(this);

        carregarLista();

        // Botão Home
        FloatingActionButton fabHome = findViewById(R.id.fabHomeSalvos);
        fabHome.setOnClickListener(v ->
                startActivity(new Intent(LivrosSalvosActivity.this, MainActivity.class))
        );
    }

    private void carregarLista() {
        List<LivroSalvo> lista = salvosDao.listar();

        adapter = new LivroSalvoAdapter(lista, item -> confirmarExclusao(item));
        recyclerView.setAdapter(adapter);
    }

    private void confirmarExclusao(LivroSalvo item) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Livro")
                .setMessage("Deseja remover \"" + item.getTitulo() + "\" dos salvos?")
                .setPositiveButton("Excluir", (d, w) -> {
                    salvosDao.excluir(item.getId());
                    carregarLista();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
