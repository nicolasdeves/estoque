/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ConsumoDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import dao.ProdutoDAO;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import model.Consumo;
import model.Produto;
/**
 *
 * @author nicolas.deves
 */
@WebServlet(name = "ControllerConsumo", urlPatterns = {"/consomeProduto"})
public class ControllerConsumo extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        Produto produto = new Produto();
        Consumo consumo = new Consumo();
        ConsumoDAO dao = new ConsumoDAO();
        ProdutoDAO daoProduto = new ProdutoDAO();
        Date data = new Date();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataAtual = LocalDateTime.now();
        String dataAtualString = dataAtual.format(formatter);
        
        if(action.equals("/consomeProduto")) {
            consumo.setIdProduto(Integer.parseInt(request.getParameter("produtoSelecionado")));
            consumo.setQuantidade(Integer.parseInt(request.getParameter("quantidadeSelecionada")));
            consumo.setData(dataAtualString);
            
            dao.salvar(consumo);
            daoProduto.diminuirEstoque(daoProduto.getProdutoId(Integer.parseInt(request.getParameter("produtoSelecionado"))), Integer.parseInt(request.getParameter("quantidadeSelecionada")));
            
            response.sendRedirect("consumir-estoque.jsp");
        }
        
        
    }
}
