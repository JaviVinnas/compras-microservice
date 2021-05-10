package gal.usc.grei.cn.compras.fachada;

import gal.usc.grei.cn.compras.modelo.Compra;
import gal.usc.grei.cn.compras.repositorio.CompraRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class CompraFachada {

    private final CompraRepositorio compras;

    /**
     * Constructor de la clase
     * @param compras Referencia al CompraRepositorio
     */
    public CompraFachada(CompraRepositorio compras) {
        this.compras = compras;
    }


    public Optional<Compra> get(String id) {
        // Se recupera la compra por el id
        return compras.findById(id);
    }
    /**
     * Método que permite insertar una nueva compra en la base de datos.
     * @param compra Los datos de la compra a insertar.
     * @return Los datos de la compra una vez insertados, incluyendo el id.
     * @throws ResponseStatusException Excepción lanzada en caso de que se facilite alguna
    información incorrecta.
     */
    public Optional<Compra> create(Compra compra) {
        //Comprobamos que la película haya llegado sin un id:
        if(compra.getId() == null || compra.getId().isEmpty()) {
            //Si es así, se devuelve un optional con los datos de la película insertada.
            return Optional.of(compras.insert(compra));
        }
        return Optional.empty();
    }

}
