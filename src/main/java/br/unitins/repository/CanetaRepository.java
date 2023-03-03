package br.unitins.repository;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.Caneta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CanetaRepository implements PanacheRepository<Caneta>{
    public Caneta findByMarca(String marca){
        return find("marca", marca).firstResult();
    }
    public List<Caneta> findlistaMarcas(String marca){
        return find("marca like ?1" , "%" + marca + "%").list();
    }
}
