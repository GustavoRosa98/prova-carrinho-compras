package br.com.liax.prova;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {
    
        private List<Item> listaDeItens;
        private String id_carrinho;

	/**
	 * Cria um carrinho com o identificar do parâmetro
	 *
	 * Irá retornar IllegalArgumentException caso o identificador esteja nula ou vazio.
	 *
	 * @param identificador
	 */
	public CarrinhoCompras(String identificador) {
            this.id_carrinho = identificador;
	}

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 *
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
	 * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
	 * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
	 * o passado como parâmetro.
	 *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
	 * @param quantidade
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
            //Índice para mostrar a posição do item na lista, caso encontrado (-1 para não coincidir com os índices da listaDeItens).
            int indiceItem = -1;
            
            //Percorre a Lista de Itens e compara seus componentes com o item sendo adicionado.
            for (int i = 0; i < listaDeItens.size() && indiceItem < 0; i++){
                Item dummy_item = listaDeItens.get(i);
                
                if(dummy_item.getProduto().equals(produto)){//Mesmo produto?
                    //Indica sua posição na lista.
                    indiceItem = i;
                }
            }
            
            try{
                
                if(indiceItem < 0){//Caso o item adicionado não exista na lista:
                    //Cria um novo item com os dados fornecidos e o adiciona na lista.
                    Item dummy_item = new Item(produto, valorUnitario, quantidade);
                    listaDeItens.add(dummy_item);
                    
                } else{ //Caso o item adicionado já exista na lista:
                   //Atualiza o item da lista com quantidade e valor unitário fornecidos.
                   Item dummy_item = listaDeItens.get(indiceItem);
                   if(valorUnitario != dummy_item.getValorUnitario()){//Valor unitário diferente?
                       //Atualiza o valor unitário com o novo fornecido.
                       valorUnitario = dummy_item.getValorUnitario();
                   }
                   quantidade = quantidade + dummy_item.getQuantidade();
                   //Adiciona o item fornecido e atualizado para a lista, na mesma posição de antes.
                   Item novoItem = new Item(produto, valorUnitario, quantidade);
                   listaDeItens.set(indiceItem, novoItem);
                }
                
            } catch (RuntimeException e){//Caso não seja possível adicionar o item no carrinho:
                e.getStackTrace(); //Recupera o registro do erro
            }
            
            
            
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {
        
        int indiceItem = -1; //Variável para o índice do item, caso encontrado na lista.
        
        //Percorre a lista de itens, procurando pelo produto a ser removido.
        for(int i = 0; i < listaDeItens.size() && indiceItem < 0; i++){
            Item dummy_item = listaDeItens.get(i);
            
            if(dummy_item.getProduto().equals(produto)){//Achou o item?
                //Armazena seu índice.
                indiceItem = i;
            }
        }
        
        if(indiceItem >= 0){//Caso o item tenha sido encontrado:
            //Apaga ele da lista, utilizando o índice encontrado anteriormente, e retorna TRUE.
            listaDeItens.remove(indiceItem);
            return true;
        } else{//Caso o item não tenha sido encontrado na lista:
            //Como não encontrou nem apagou o item fornecido, retorna FALSE.
            return false;
        }
        
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
        //Tenta encontrar e remover o item da lista, procurando pela posição:
        try{
            listaDeItens.remove(posicaoItem);
            return true;
        } catch(RuntimeException e){//Caso não consiga apagar a posição e dê erro, retorna FALSE.
            return false;
        }
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
        //Variável para recuperar o valor total dos itens do carrinho.
        BigDecimal valorTotalCarrinho = new BigDecimal("0.0");
        
        //Percorre a lista de itens do carrinho, somando seus valores totais na variável.
        for(Item item: listaDeItens){
            valorTotalCarrinho = valorTotalCarrinho.add(item.getValorTotal());
        }
        
        return valorTotalCarrinho;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
        //Caso a lista de itens esteja nula, retorna uma nova.
        if(listaDeItens == null){
            listaDeItens = new ArrayList<>();
        }
        //Retorna a lista.
        return listaDeItens;
    }

	/**
	 * Retorna o identificador
	 *
	 * @return identificador
	 */
	public String getIdentificador() {
            return id_carrinho;
	}

}