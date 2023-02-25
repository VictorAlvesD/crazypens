package br.unitins.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


import javax.ws.rs.Produces;

import br.unitins.model.Caneta;

@Path("/caneta")
public class CanetaResource {
    
    @POST //envia informação
    @Produces(MediaType.APPLICATION_JSON) //get
    @Consumes(MediaType.APPLICATION_JSON) //set
    @Transactional
    public Caneta inserir(Caneta obj) {
        obj.persist();
        return obj;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Caneta remover(@PathParam("id") Long id, Caneta obj) {
        Caneta objDelete = Caneta.findById(id);
        objDelete.delete();
        return objDelete;
    }

    @PATCH
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Caneta editarPacialmente(@PathParam("id") Long id, Caneta obj) {
        Caneta objEditar = Caneta.findById(id);
        objEditar.setDescricao(obj.getDescricao());
        objEditar.setPonta(obj.getPonta());
        return objEditar;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Caneta consultarId(@PathParam("id") Long id) {
        return Caneta.findById(id);
    }

    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Caneta> retornarTudo() {
        return Caneta.findAll().list();
    }
}
