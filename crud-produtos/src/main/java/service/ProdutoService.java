package service;

import lombok.RequiredArgsConstructor;
import model.Produto;
import org.springframework.stereotype.Service;
import repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        return produtoRepository.save(produtoAtualizado);
    }
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public String compararPrecos(Long id1, Long id2){
        Produto produto1 = buscarPorId(id1);
        Produto produto2 = buscarPorId(id2);
        if(produto1.getPreco() < produto2.getPreco()){
            return " O " + produto1.getNome() + " é mais barato que " + produto2.getNome();
        } else if (produto1.getPreco() > produto2.getPreco()) {
            return " O " + produto2.getNome() + " é mais barato que " + produto1.getNome();
        } else {
            return "Os produtos tem o mesmo valor";
        }
    }
}
