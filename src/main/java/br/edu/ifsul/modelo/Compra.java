package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/*
{
"nome_cli": "cliente",
"endereco": "endereco",
"cep_origem": "03047000",
"codigo_servico": "04790",
"cep_dest": "90020180",
"valor_compra": 50.5
}
*/



/**
 *
 * @author imarv
 */
@Entity
@Table(name = "compra")
public class Compra implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_compra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name = "nome_cli", nullable = false, length = 50)
    private String nome_cli;
    @Column(name = "endereco", nullable = false, length = 50)
    private String endereco;
    @Column(name = "codigo_servico", nullable = false, length = 50)
    private String codigo_servico;
    @Column(name = "cep_origem", nullable = false, length = 8)
    private String cep_origem;
    @Column(name = "cep_destino", nullable = false, length = 8)
    private String cep_dest;
    @Column(name="valor_frete", nullable = false, length = 10)
    private BigDecimal valor_frete;
    @Column(name="valor_compra")
    private BigDecimal valor_compra;
    @Column(name = "prazo_entrega", nullable = false, length = 2)
    private String prazo_entrega;
    
    public Compra() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCodigo_servico() {
        return codigo_servico;
    }

    public void setCodigo_servico(String codigo_servico) {
        this.codigo_servico = codigo_servico;
    }

    public String getCep_origem() {
        return cep_origem;
    }

    public void setCep_origem(String cep_origem) {
        this.cep_origem = cep_origem;
    }

    public String getCep_dest() {
        return cep_dest;
    }

    public void setCep_dest(String cep_dest) {
        this.cep_dest = cep_dest;
    }

    public BigDecimal getValor_frete() {
        return valor_frete;
    }

    public void setValor_frete(BigDecimal valor_frete) {
        this.valor_frete = valor_frete;
    }

    public BigDecimal getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(BigDecimal valor_compra) {
        this.valor_compra = valor_compra;
    }

    public String getPrazo_entrega() {
        return prazo_entrega;
    }

    public void setPrazo_entrega(String prazo_entrega) {
        this.prazo_entrega = prazo_entrega;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}