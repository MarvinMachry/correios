package br.edu.ifsul.servicos;

import br.edu.ifsul.calculo.CResultado;
import br.edu.ifsul.calculo.CalcPrecoPrazoWS;
import br.edu.ifsul.dao.CompraDAO;
import br.edu.ifsul.modelo.Compra;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author imarv
 */
@Stateless
@Path("compra")
public class ServicoCompra implements Serializable {

    @EJB
    private CompraDAO dao;

    public ServicoCompra() {
    }

    @GET
    @Produces("application/json; charset=ISO-8859-1")
    @Path("lista")
    public Response listaCompra() {
        try {
            List<Compra> lista = dao.getLista();
            return Response.ok().entity(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces("application/json; charset=ISO-8859-1")
    public Response findById(@PathParam("id") Integer id) {
        try {
            return Response.ok().entity(dao.findById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response adicionar(Compra objeto) {
        try {
            CalcPrecoPrazoWS servico = new CalcPrecoPrazoWS();

            CResultado resultado = servico.getCalcPrecoPrazoWSSoap().calcPrecoPrazo(
                    "", // código da empresa
                    "", // senha
                    objeto.getCodigo_servico(), // código serviço
                    objeto.getCep_origem(), // cep origem
                    objeto.getCep_dest(), // cep destino
                    "0.2", // peso 
                    1, // formato  
                    new BigDecimal(20.0), // comprimento
                    new BigDecimal(10.0), // altura
                    new BigDecimal(30.0), // largura
                    new BigDecimal(30.0), // diametro
                    "N", // Mão propria
                    objeto.getValor_compra(), // valor declarado
                    "N" // aviso recebimento
            );
            if(!resultado.getServicos().getCServico().get(0).getErro().equals("0")){
                Map<String, Object> rtn = new LinkedHashMap<>();
                rtn.put("msg", resultado.getServicos().getCServico().get(0).getMsgErro());
                return Response.status(Response.Status.BAD_REQUEST).entity(rtn).build();
            }

            String valor = resultado.getServicos().getCServico().get(0).getValor().replace(",", ".");
            objeto.setValor_frete(new BigDecimal(valor));
            objeto.setPrazo_entrega(resultado.getServicos().getCServico().get(0).getPrazoEntrega());
            
            return Response.ok().entity(dao.persist(objeto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Produces("application/json; charset=ISO-8859-1")
    @Consumes("application/json; charset=ISO-8859-1")
    public Response alterar(Compra objeto) {
        try {
            return Response.ok().entity(dao.merge(objeto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/remove/{id}")
    public Response remove(@PathParam("id") Integer id) {
        try {
            dao.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public CompraDAO getDao() {
        return dao;
    }

    public void setDao(CompraDAO dao) {
        this.dao = dao;
    }

}