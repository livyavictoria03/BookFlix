package com.example.bookflix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LivroSalvoAdapter extends RecyclerView.Adapter<LivroSalvoAdapter.ViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(LivroSalvo item);
    }

    private List<LivroSalvo> lista;
    private final OnDeleteClickListener listener;

    public LivroSalvoAdapter(List<LivroSalvo> lista, OnDeleteClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_livro_salvo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LivroSalvo item = lista.get(position);
        holder.titulo.setText(item.getTitulo());
        holder.btnExcluir.setOnClickListener(v -> listener.onDeleteClick(item));
    }

    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    public void atualizarLista(List<LivroSalvo> novaLista) {
        this.lista = novaLista;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        Button btnExcluir;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // tem que bater com o ID do TextView no item_livro_salvo.xml
            titulo = itemView.findViewById(R.id.txtTituloSalvo);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}
