/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import model.Produto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas.deves
 */
public class ProdutoDAO {
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/loja", "root", "");
             
        } catch (Exception e) {
            System.out.println(con);
            System.out.println(e);

        }
        System.out.println(con);
        System.out.println("Conexão com MYSQL realizada!");
        return con;
    }
    
    public void salvar(Produto produto) {
    int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("insert into produto(preco, descricao, estoque, codigo) values(?,?,?,?)");
            sql.setDouble(1, produto.getValor());
            sql.setString(2, produto.getDescricao().toUpperCase());
            sql.setInt(3, produto.getEstoque());
            sql.setInt(4, produto.getCodigo());

            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Produto inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir o produto.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir o produto: " + e.getMessage());
        }
    }
    
    public void editar(Produto produto) {
    int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("update produto set preco=?, descricao=?, estoque=? where codigo=?");
            sql.setDouble(1, produto.getValor()); // Define o preço como double
            sql.setString(2, produto.getDescricao()); // Define a descrição como string
            sql.setInt(3, produto.getEstoque()); // Define o estoque como inteiro

            sql.setInt(4, produto.getCodigo());
            System.out.println("ENTROU NO EDITAR:::::::::");
            System.out.println(produto.getValor());
            System.out.println(produto.getDescricao());
            System.out.println(produto.getEstoque());
            System.out.println(produto.getCodigo());
            
            
            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Produto editado com sucesso!");
            } else {
                System.out.println("Erro ao editar o produto.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao editar o produto: " + e.getMessage());
        }
    }
    
    public void excluir (Produto produto) {
        int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("delete from produto where id=?");
            sql.setDouble(1, produto.getId());

            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Erro ao excluir o produto.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir o produto: " + e.getMessage());
        }
    }
    
    public Produto getProdutoCodigo(int codigo) {
        Produto produto = null;
        
        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("select * from produto where codigo=?");
            
            sql.setInt(1, codigo);
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next()) {
                produto = new Produto();
                produto.setId(resultado.getInt("id"));
                produto.setValor(resultado.getDouble("preco"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEstoque(resultado.getInt("estoque"));
                produto.setCodigo(resultado.getInt("codigo"));
            }
            
        } catch (Exception e) {
            System.out.print(e);
        }
        return produto;
    }
    
    public Produto getProdutoId(int id) {
        Produto produto = null;
        
        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("select * from produto where id=?");
            
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next()) {
                produto = new Produto();
                produto.setId(resultado.getInt("id"));
                produto.setValor(resultado.getDouble("preco"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEstoque(resultado.getInt("estoque"));
                produto.setCodigo(resultado.getInt("codigo"));
            }
            
        } catch (Exception e) {
            System.out.print(e);
        }
        return produto;
    }
    
    
    public static List<Produto> getTodosProdutos() {
        List<Produto> lista = new ArrayList<Produto>();
        
        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("select * from produto");
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next()) {
                Produto produto = new Produto();
                
                produto.setId(resultado.getInt("id"));
                produto.setValor(resultado.getDouble("preco"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setEstoque(resultado.getInt("estoque"));
                produto.setCodigo(resultado.getInt("codigo"));
                
                lista.add(produto);
            }
            
        } catch(Exception e) {
            System.out.print(e);
        }
        
        return lista;
    }
    
    public void diminuirEstoque(Produto produto, int quantidade) {
    int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("update produto set estoque = estoque - ? where id = ?");
            sql.setInt(1, quantidade);
            sql.setInt(2, produto.getId());
            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Estoque alterado com sucesso");
            } else {
                System.out.println("Erro ao alterar estoque.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar estoque.: " + e.getMessage());
        }
    }

}
