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
import java.util.ArrayList;
import model.Produto;

/**
 *
 * @author nicolas.deves
 */

@WebServlet(name = "ControllerProduto", urlPatterns = {"/adicionaProduto", "/editarProduto", "/editarProdutoBotao", "/excluirProduto", "/adicionarProduto"})
public class ControllerProduto extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        Produto produto = new Produto();
        ProdutoDAO dao = new ProdutoDAO();
        ConsumoDAO daoConsumo = new ConsumoDAO();
        
        if(action.equals("/adicionaProduto")) {
            
            produto.setDescricao(request.getParameter("descricao"));
            produto.setValor(Double.parseDouble(request.getParameter("valor")));
            produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
            produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));

            dao.salvar(produto);
            
            response.sendRedirect("cadastro-produtos.jsp");
        }
        
        if(action.equals("/editarProduto")) {
            
            produto = dao.getProdutoCodigo(Integer.parseInt(request.getParameter("codigo")));

            String valorAlterado = request.getParameter("campo");
            String novoConteudo = request.getParameter("novoConteudo");
            
            System.out.println("valorAlterado::::::::::");
            System.out.println(valorAlterado);
            
            System.out.println("novoCOnteudo::::::::::");
            System.out.println(novoConteudo);
            
            if (valorAlterado.equals("Valor")) {
                produto.setValor(Double.parseDouble(novoConteudo));
            } else if (valorAlterado.equals("Estoque")) {
                produto.setEstoque(Integer.parseInt(novoConteudo));
            } else {
                produto.setDescricao(novoConteudo);
            }
            
            dao.editar(produto);
            response.sendRedirect("lista-produtos.jsp");

        }
        
        if(action.equals("/excluirProduto")) {
            produto = dao.getProdutoId(Integer.parseInt(request.getParameter("id")));
            daoConsumo.excluir(produto);
            dao.excluir(produto);
            
            response.sendRedirect("lista-produtos.jsp");
        }
        
        if(action.equals("/editarProdutoBotao")) {
            request.setAttribute("codigo", request.getParameter("codigo"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edicao-produtos.jsp");
            dispatcher.forward(request, response);
        }
        
        
        
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
}
