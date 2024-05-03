/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nicolas.deves
 */
public class Produto {
    private int id;
    private String descricao;
    private Double valor;
    private int estoque;
    private int codigo;
    
//    public Produto(int id, String descricao, Double valor, int estoque, int codigo) {
//        this.id = id;
//        this.descricao = descricao;
//        this.valor = valor;
//        this.estoque = estoque;
//        this.codigo = codigo;
//    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para o campo descricao
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Getter e Setter para o campo valor
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    // Getter e Setter para o campo estoque
    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    // Getter e Setter para o campo codigo
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
