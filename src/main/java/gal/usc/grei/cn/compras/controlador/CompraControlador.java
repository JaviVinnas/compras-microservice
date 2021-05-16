package gal.usc.grei.cn.compras.controlador;


import gal.usc.grei.cn.compras.fachada.CompraFachada;
import gal.usc.grei.cn.compras.modelo.Compra;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Compras API",
        description = "Operaciones que puede hacer un usuario sobre la compra de acciones"
)
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
    @Operation(
            operationId = "Obtener una compra",
            summary = "Obtener una compra",
            description = "Obtiene un objeto compra por su identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se obtiene el objeto compra satisfactoriamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Compra.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se ha encontrado un objeto compra con el id indicado",
                    content = @Content
            )
    })
    ResponseEntity<Compra> get(@PathVariable("id") @Parameter(description = "Identificador único de la compra") String id) {
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
    @Operation(
            operationId = "Crear una Compra",
            summary = "Crear una compra",
            description = "Registra a una compra en base a una objeto compra que se le pasa en el body"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Objeto creado correctamente",
                    headers = {
                            @Header(
                                    name = "Location",
                                    description = "URI para acceder a la compra creada",
                                    schema = @Schema(type = "Location")
                            )
                    },
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Compra.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "El objeto compra a introducir es incorrecto",
                    content = @Content
            )
    })
    ResponseEntity<Object> create(@Valid @RequestBody
                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                          description = "La compra que se va a introducir en el sistema",
                                          content = @Content(
                                                  mediaType = "application/json",
                                                  schema = @Schema(implementation = Compra.class)
                                          )
                                  )
                                          Compra compra) {
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
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
