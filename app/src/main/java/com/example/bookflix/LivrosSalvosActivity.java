package com.example.bookflix;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
                    carregarLista(); // recarrega a lista
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
