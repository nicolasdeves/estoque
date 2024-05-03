/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.ProdutoDAO.getConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import model.Produto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Consumo;

/**
 *
 * @author nicolas.deves
 */
public class ConsumoDAO {
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
    
    public void salvar(Consumo consumo) {
    int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("insert into consumo(id_produto, quantidade, data) values(?,?,?)");
            sql.setInt(1, consumo.getIdProduto());
            sql.setInt(2, consumo.getQuantidade());
            sql.setString(3, consumo.getData());

            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Consumo inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir consumo.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir consumo: " + e.getMessage());
        }
    }
    
    public static List<Consumo> getTodosProdutos() {
        List<Consumo> lista = new ArrayList<Consumo>();
        
        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("select * from consumo");
            ResultSet resultado = sql.executeQuery();
            
            while(resultado.next()) {
                Consumo consumo = new Consumo();
                
                consumo.setId(resultado.getInt("id"));
                consumo.setIdProduto(resultado.getInt("id_produto"));
                consumo.setQuantidade(resultado.getInt("quantidade"));
                consumo.setData(resultado.getString("data"));
                
                lista.add(consumo);
            }
            
        } catch(Exception e) {
            System.out.print(e);
        }
        
        return lista;
    }
    
    public void excluir (Produto produto) {
        int status = 0;

        try {
            Connection conexao = getConnection();
            PreparedStatement sql = conexao.prepareStatement("delete from consumo where id_produto=?");
            sql.setDouble(1, produto.getId());

            status = sql.executeUpdate();

            if (status > 0) {
                System.out.println("Produto excluído com sucesso do relatório!");
            } else {
                System.out.println("Erro ao excluir o produto do relatório.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir o produto do relatório: " + e.getMessage());
        }
    }
}
