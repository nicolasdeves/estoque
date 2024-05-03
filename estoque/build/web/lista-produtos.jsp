<%-- 
    Document   : lista-produtos
    Created on : 7 de abr. de 2024, 16:21:02
    Author     : nicolas.deves
--%>

<%@page import="dao.ProdutoDAO,model.Produto,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        
        <%
            List<Produto> lista = ProdutoDAO.getTodosProdutos();
            request.setAttribute("lista", lista);
         %>  
         
         
         <nav class="navbar navbar-dark bg-dark fixed-top">
          <div class="container-fluid">
            <a class="navbar-brand" href="#">Estoque</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
              <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Menu</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
              </div>
              <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.html">Página inicial</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="consumir-estoque.jsp">Consumir Produtos</a>
                  </li>
                  

                  
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Produtos
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark">
                      <li><a class="dropdown-item" href="cadastro-produtos.jsp">Cadastrar</a></li>
                      <li><a class="dropdown-item" href="lista-produtos.jsp">Lista de Produtos</a></li>
                    </ul>
                  </li>  
                  
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Relatórios
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark">
                      <li><a class="dropdown-item" href="relatorio-controle-estoque.jsp">Consumo de Estoque</a></li>
                    </ul>
                  </li>
                  
                </ul>
              </div>
            </div>
          </div>
        </nav>
         
         <div class="lista-produtos mt-5 pt-5">
             <table border="0" width="90%" class="table table-striped" >
                <tr>
                <th>Produto</th>
                <th>Código</th>
                <th>Preço</th>
                <th>Estoque</th>
                <th>Editar</th>                
                <th>Deletar</th>
                </tr>

                <% for (Produto produto : lista) { %>
                    <tr>
                        <td><%= produto.getDescricao() %></td>
                        <td><%= produto.getCodigo() %></td>
                        <td><%= produto.getValor() %></td>
                        <td><%= produto.getEstoque() %></td>

                        <td>
                            <form action="editarProdutoBotao" method="post">
                                <input name="codigo" type="hidden" value="<%= produto.getCodigo() %>">
                                <input type="submit" value="Editar Produto" class="btn btn-secondary btn-sm"/>
                            </form>
                        </td>

                        <td>
                            <form action="excluirProduto" method="post"onsubmit="return confirm('Tem certeza que deseja excluir este produto? Excluirá todos registro no relatório!');">
                                 <input name="id" type="hidden" value="<%= produto.getId() %>">
                                 <input type="submit" value="Apagar Produto" class="btn btn-secondary btn-sm"/>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </table> 
         </div>
        
        
        
        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
