package br.com.smartstock.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smartstock.api.entities.Produto;
import br.com.smartstock.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
	
	public List<Produto> listarTodos() {
		return repository.findAll();
	}
	
	public Optional<Produto> buscarPorId(Long id) {
		return repository.findById(id);
	}
	
	
	public Produto atualizar(Long id, Produto produtoAlterado) {
		Optional<Produto> produtoExistente = buscarPorId(id);
		
		if(produtoExistente.isPresent()) {
			
			Produto atualizado = produtoExistente.get();
			
			atualizado.setNome(produtoAlterado.getNome());
			atualizado.setDataVencimento(produtoAlterado.getDataVencimento());
			atualizado.setPrecoVenda(produtoAlterado.getPrecoVenda());
			atualizado.setUnidade(produtoAlterado.getUnidade());

			
			return repository.save(atualizado);

		}
		
		return null;
		
	}	

	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
}