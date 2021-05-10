package gal.usc.grei.cn.compras.repositorio;

import gal.usc.grei.cn.compras.modelo.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompraRepositorio extends MongoRepository<Compra, String> {
}
