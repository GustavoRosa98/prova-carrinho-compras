package br.com.liax.prova;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {
    
    private HashMap<String, CarrinhoCompras> mapaCarrinhos;

	public CarrinhoComprasFactory() {
	}

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param identificacaoCliente
     * @return CarrinhoCompras
     */
    public CarrinhoCompras criar(String identificacaoCliente) {
        //Carrinho de compras para retornar o resultado.
        CarrinhoCompras carrinho = new CarrinhoCompras(identificacaoCliente);
        
        //Mapeia a identificação fornecida no mapa de carrinhos, verificando se ele já existe.
        if(mapaCarrinhos.containsKey(identificacaoCliente)){//Caso já exista:
            //Recupera o carrinho encontrado no mapeamento.
            carrinho = (CarrinhoCompras)mapaCarrinhos.get(identificacaoCliente);
            
        }else{//Caso não exista:
            //Adiciona o novo carrinho ao mapa.
            mapaCarrinhos.put(identificacaoCliente, carrinho);
        }
        //Retorna o carrinho.
        return carrinho;
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTicketMedio() {
        //Lista para receber os carrinhos de compras.
        List<CarrinhoCompras> listaCarrinhos = new ArrayList<>(mapaCarrinhos.values());
        
        //Variável BigDecimal auxiliar para somar os valores totais dos carrinhos.
        BigDecimal totalCarrinhos = new BigDecimal("0.0");
        
        //Percorre toda a lista de carrinhos, somando seus totais à variável auxiliar.
        listaCarrinhos.forEach((car) -> {
            totalCarrinhos.add(car.getValorTotal());
        });        
        //Agora a variável totalCarrinhos já possui a soma dos valores totais de todos os carrinhos.
        
        //Novo BigDecimal contendo o número de carrinhos da lista, que será usado para calcular a média.
        BigDecimal numeroCarrinhos = new BigDecimal(listaCarrinhos.size());
        
        //Nova variável de média dos totais, que recebe a soma deles dividida pelo número de carrinhos na lista.
        //O arredondamento de 2 casas depois da vírgula já é executado nesse mesmo cálculo.
        BigDecimal mediaTotalCarrinhos = totalCarrinhos.divide(numeroCarrinhos, 2, RoundingMode.HALF_UP);
        
        //Retorna a média calculada.
        return mediaTotalCarrinhos;
        
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param identificacaoCliente
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidar(String identificacaoCliente) {
        try{
            //Apaga o carrinho do mapa, baseado na identificação fornecida.
            mapaCarrinhos.remove(identificacaoCliente);
            
            //Tendo sucesso, retorna true.
            return true;
            
        } catch (RuntimeException e){//Caso o carrinho não exista no mapa, retorna false.
            return false;
        }
    }

}
