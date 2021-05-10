package gal.usc.grei.cn.compras.controlador;


import gal.usc.grei.cn.compras.fachada.CompraFachada;
import gal.usc.grei.cn.compras.modelo.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraControlador {

    private final CompraFachada compras;

    @Autowired
    public CompraControlador(CompraFachada compras) {
        this.compras = compras;
    }

    @GetMapping(
            path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Compra> get(@PathVariable("id") String id){
        return ResponseEntity.of(compras.get(id));
    }

    /**
     * Método: POST
     * Url para llegar: /compras
     * Objetivo: insertar la compra que se facilita como parámetro.
     *
     * @param compra los datos de la compra a insertar
     * @return Si la inserción se ha podido hacer, la nueva compra y la url para acceder a ella.
     */
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> create(@Valid @RequestBody Compra compra) {
        try {
            //Tratamos de crear la compra:
            Optional<Compra> inserted = compras.create(compra);
            //Si se crea correctamente, devolvemos la información de la compra creada.
            return ResponseEntity.created(
                    URI.create(
                            ServletUriComponentsBuilder
                                    .fromCurrentContextPath()
                                    .build()
                                    .toUriString() +
                                    "/compras/" +
                                    inserted.get().getId()
                    )
            )
                    .body(inserted.get());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
