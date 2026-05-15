package br.com.smartstock.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smartstock.api.entities.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository extends JpaRepository <MovimentacaoEstoque, Long>{

}
