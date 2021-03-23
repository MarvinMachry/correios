
 
package br.edu.ifsul.testes;

import java.math.BigDecimal;
import br.edu.ifsul.calculo.CResultado;
import br.edu.ifsul.calculo.CalcPrecoPrazoWS;


public class TestaClientePrecoPrazo {

    
    public static void main(String[] args) {
        CalcPrecoPrazoWS servico = new CalcPrecoPrazoWS();

        CResultado resultado = servico.getCalcPrecoPrazoWSSoap().calcPrecoPrazo(
                "", // código da empresa
                "", // senha
                "04014", // código serviço 
                "95300000", // cep origem 
                "99054328", // cep destino
                "0.2", // peso 
                1, // formato  
                new BigDecimal(20.0), // comprimento
                new BigDecimal(10.0), // altura
                new BigDecimal(30.0), // largura
                new BigDecimal(30.0), // diametro
                "N", // Mão propria
                new BigDecimal(0.0), // valor declarado
                "N" // aviso recebimento
        );

        System.out.println("Erro: " + resultado.getServicos().getCServico().get(0).getErro());
        System.out.println("MSG Erro: " + resultado.getServicos().getCServico().get(0).getMsgErro());
        System.out.println("Valor: " + resultado.getServicos().getCServico().get(0).getValor());
        System.out.println("Prazo: " + resultado.getServicos().getCServico().get(0).getPrazoEntrega());
    }

}
