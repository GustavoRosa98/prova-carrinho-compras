package br.com.liax.prova;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;

    /**
     * Construtor da classe Item.
	 *
	 * O produto e o valor unitário não pode ser nulo a quantidade não pode ser menor ou igual a zero.
     * 
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
        this.produto = produto;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    //Métodos SET para atribuição de valor aos atributos:
    
    public void setProduto(Produto prod){
        this.produto = prod;
    }
    
    public void setValorUnitario(BigDecimal vlrUnit){
        this.valorUnitario = vlrUnit;
    }
    
    public void setQuantidade(int qtde){
        this.quantidade = qtde;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }

}
