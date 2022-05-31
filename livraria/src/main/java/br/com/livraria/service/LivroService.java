package br.com.livraria.service;

import br.com.livraria.exceptions.LivroNaoEncontradoException;
import br.com.livraria.model.Livro;
import br.com.livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private static final String LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro buscarPorId(Long id) {
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(
                () -> new LivroNaoEncontradoException(LIVRO_NAO_ENCONTRADO));
    }

    public List<Livro> buscarTodos(Long id_cat) {
        categoriaService.buscarPorId(id_cat);
        return livroRepository.buscarPorCategoria(id_cat);
    }
}
