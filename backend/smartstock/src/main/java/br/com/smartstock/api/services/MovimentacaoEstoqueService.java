package br.com.smartstock.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.smartstock.api.entities.MovimentacaoEstoque;
import br.com.smartstock.api.entities.Produto;
import br.com.smartstock.api.repositories.MovimentacaoEstoqueRepository;
import br.com.smartstock.api.repositories.ProdutoRepository;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<MovimentacaoEstoque> listarTodos() {
        return repository.findAll();
    }

    public MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacao) {
        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (movimentacao.getTipo().equalsIgnoreCase("ENTRADA")) {
            produto.setUnidade(produto.getUnidade() + movimentacao.getQuantidade());
        } else if (movimentacao.getTipo().equalsIgnoreCase("SAIDA")) {
            if (produto.getUnidade() < movimentacao.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente.");
            }
            produto.setUnidade(produto.getUnidade() - movimentacao.getQuantidade());
        }

        produtoRepository.save(produto);

        return repository.save(movimentacao);
    }
}