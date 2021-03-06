/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.servlet;

import ifsp.pwe.prova.beans.Atividade;
import ifsp.pwe.prova.beans.Usuario;
import ifsp.pwe.prova.dao.AtividadeDAO;
import ifsp.pwe.prova.dao.CorrecaoDAO;
import ifsp.pwe.prova.dao.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Flávio e Gabriel
 */
public class BuscaAtividade implements Tarefa {

    //Declarações
    private ArrayList<Atividade> listaAtividade = null;
    private Usuario usuario = null;
    private String filtroDeTitulo = "";

    //Instância as DAO de Usuario e Correcao
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private CorrecaoDAO correcaoDAO = new CorrecaoDAO();

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse resp) {

        //Localiza o usuario que está logado
        HttpSession session = req.getSession();
        usuario = (Usuario) session.getAttribute("usuarioLogado");

        //Recupera o texto que será utilizado como filtro no Titulo das Atividades
        filtroDeTitulo = req.getParameter("filtroDeTitulo");

        try {
            //Localiza todas a atividades através do Titulo
            listaAtividade = new AtividadeDAO().buscaAtividadesPorTitulo(usuario, filtroDeTitulo);
        } catch (SQLException ex) {
            System.err.println("Erro ao Buscar Atividade. Detalhes: " + ex.getMessage());
            return "Erro.html";
        }

        for (Atividade obj : listaAtividade) {

            try {
                //Através de UsuarioDAO, localiza os dados do Usuário
                obj.setUsuario(usuarioDAO.buscaPorID(obj.getIdUsuario()));
            } catch (SQLException ex) {
                System.err.println("Erro ao Buscar Usuario. Detalhes: " + ex.getMessage());
                return "Erro.html";
            }

            /*Se o Usuario estiver vazio, entende que ele está deslogado. Desta forma, 
                não será atribuido os dados das Correções*/
            if (usuario != null) {
                //Verifica se o usuario possui o Perfil de Corretor de Atividades.
                //Se o usuario for corretor, atribui todas as correções.
                if (usuario.isCorretor()) {
                    try {
                        //Através de CorrecaoDAO, localiza os dados da Correcao
                        obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                    } catch (SQLException ex) {
                        System.err.println("Erro ao Buscar Correcao 1. Detalhes: " + ex.getMessage());
                        return "Erro.html";
                    }
                } else //Se o usuario não for corretor, atribui a Correcao, para as atividades 
                //que foram submetidas por ele
                {
                    if (usuario.getId() == obj.getIdUsuario()) {
                        try {
                            //Através de CorrecaoDAO, localiza os dados da Correcao
                            obj.setCorrecao(correcaoDAO.buscaPorIDAtividade(obj.getId()));
                        } catch (SQLException ex) {
                            System.err.println("Erro ao Buscar Correcao 2. Detalhes: " + ex.getMessage());
                            return "Erro.html";
                        }
                    }
                }
            }
        }

        //Atribui a lista de atividades preenchida como Atributo da Requisição 
        req.setAttribute("listaDeAtividades", listaAtividade);

        //Retorna o endereço da Página Web (VIEW) responsável em mostrar todas as atividades
        return "/WEB-INF/Paginas/Atividades.jsp";
    }

    @Override
    public boolean verifica() {
        return false;
    }

}
