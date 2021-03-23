package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.edu.ifsul.modelo.Compra;
import javax.ejb.Stateless;

/**
 *
 * @author imarv
 */
@Stateless
public class CompraDAO implements Serializable{
    @PersistenceContext(unitName = "correios-PU")
    private EntityManager em;
    
    private List<Compra> lista;

    public CompraDAO() {
    }
    
    public Compra persist(Compra objeto) throws Exception{
        objeto.setId(null);
        getEm().persist(objeto);
        return objeto;
    }
    
    public Compra merge(Compra objeto) throws Exception{
        getEm().merge(objeto);
        return objeto;
    }
    
    public void remove(Object id) throws Exception{
        Compra obj = getEm().find(Compra.class, id);
        getEm().remove(obj);
    }
    
    public Compra findById(Object id) throws Exception{
        return (Compra) getEm().find(Compra.class, id);
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Compra> getLista() {
        return getEm().createQuery("from Compra order by id").getResultList();
    }

    public void setLista(List<Compra> lista) {
        this.lista = lista;
    }  

}