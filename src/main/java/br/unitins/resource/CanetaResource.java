package br.unitins.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unitins.model.Caneta;
import br.unitins.repository.CanetaRepository;

@Path("/caneta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CanetaResource {

    @Inject
    private CanetaRepository repository;

    @GET
    public List<Caneta> getAll() {
        return repository.findAll().list();
    }

    @POST
    @Transactional
    public Caneta insert(Caneta obj) {
        repository.persist(obj);
        return obj;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Caneta remover(@PathParam("id") Long id, Caneta obj) {
        Caneta canetaRemovida = repository.findById(id);
        repository.delete(canetaRemovida);
        return canetaRemovida;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Caneta update(@PathParam("id") Long id, Caneta obj) {

        Caneta entity = repository.findById(id);

        entity.setMarca(obj.getMarca());
        entity.setCor(obj.getCor());
        entity.setFornecedor(obj.getFornecedor());
        entity.setPonta(obj.getPonta());
        entity.setDescricao(obj.getDescricao());

        return entity;
    }

    @GET
    @Path("/count")
    public long count() {
        return repository.count();
    }

    @GET
    @Path("/search/{marca}")
    public Caneta search(@PathParam("marca") String marca) {
        return repository.findByMarca(marca);
    }

    @GET
    @Path("/searchlist/{marca}")
    public List<Caneta> searchList(@PathParam("marca") String marca) {
        return repository.findlistaMarcas(marca);
    }
}
