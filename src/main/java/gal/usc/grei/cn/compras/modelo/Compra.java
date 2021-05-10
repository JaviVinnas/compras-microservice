package gal.usc.grei.cn.compras.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Document(collection = "compras")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Compra {

    @Id
    private String id;
    private String fecha;
    @NotEmpty(message = "El símbolo no puede ser vacío")
    private String simbolo;
    @NotNull(message = "El volumen no puede ser vacío")
    private Long volumen;
    @NotNull(message = "El precio por unidad no puede ser vacío")
    private Float unidad;
    @NotNull(message = "El precio total no puede ser vacío")
    private Float total;

    public Compra() {
    }

    public Compra(String id, String fecha, @NotEmpty(message = "El símbolo no puede ser vacío") String simbolo, @NotEmpty(message = "El volumen no puede ser vacío") Long volumen, @NotEmpty(message = "El precio por unidad no puede ser vacío") Float unidad, @NotEmpty(message = "El precio total no puede ser vacío") Float total) {
        this.id = id;
        this.fecha = fecha;
        this.simbolo = simbolo;
        this.volumen = volumen;
        this.unidad = unidad;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public Compra setId(String id) {
        this.id = id;
        return this;
    }

    public String getFecha() {
        return fecha;
    }

    public Compra setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public Compra setSimbolo(String simbolo) {
        this.simbolo = simbolo;
        return this;
    }

    public Long getVolumen() {
        return volumen;
    }

    public Compra setVolumen(Long volumen) {
        this.volumen = volumen;
        return this;
    }

    public Float getUnidad() {
        return unidad;
    }

    public Compra setUnidad(Float unidad) {
        this.unidad = unidad;
        return this;
    }

    public Float getTotal() {
        return total;
    }

    public Compra setTotal(Float total) {
        this.total = total;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) && Objects.equals(fecha, compra.fecha) && Objects.equals(simbolo, compra.simbolo) && Objects.equals(volumen, compra.volumen) && Objects.equals(unidad, compra.unidad) && Objects.equals(total, compra.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, simbolo, volumen, unidad, total);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id='" + id + '\'' +
                ", fecha='" + fecha + '\'' +
                ", simbolo='" + simbolo + '\'' +
                ", volumen=" + volumen +
                ", unidad=" + unidad +
                ", total=" + total +
                '}';
    }
}
