/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unipampa.controller;

import br.edu.unipampa.model.Aluno;
import br.edu.unipampa.model.Pessoa;
import br.edu.unipampa.model.Tcc;
import br.edu.unipampa.model.Tema;
import br.edu.unipampa.model.web.AcessoSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
public class RelacaoNotasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AcessoSistema acessoSistema = new AcessoSistema();
        List<Aluno> alunosAvaliados = acessoSistema.procurarAlunos();
        request.setAttribute("listaTemas", fazerListaTemas(alunosAvaliados, acessoSistema));
        request.getRequestDispatcher("relacaoNotas.jsp").forward(request, response);
    }

    public List<List> fazerListaTemas(List<Aluno> listaAlunos, AcessoSistema acessoSistema) {
        Tema tema;
        Tcc tcc1 = null;
        Tcc tcc2;
        List<List> listaTemas = new ArrayList<>();
        List<Object> listaTemasNotas = new ArrayList<>();
        for (Aluno aluno : listaAlunos) {
            tcc1 = acessoSistema.procurarTipoVersaoTcc(aluno.getMatricula(), 0, 0);
            tcc2 = acessoSistema.procurarTipoVersaoTcc(aluno.getMatricula(), 0, 1);
            tema = aluno.getTema();
            if (verificarTcc(tcc1)) {
                listaTemasNotas.add(tema);
                listaTemasNotas.add(retornarMedia(tcc1));
                if (tcc2 != null) {
                    listaTemasNotas.add(retornarMedia(tcc2));
                }
            }
            listaTemas.add(listaTemasNotas);
        }
        return listaTemas;
    }

    public boolean verificarTcc(Tcc tcc) {
        if (tcc != null) {
            return tcc.getStatus() == Tcc.APROVADO || tcc.getStatus() == Tcc.REPROVADO;
        }
        return false;
    }

    public float retornarMedia(Tcc tcc) {
        float notaFinal = tcc.getNotaOrientador()
                + tcc.getNotaConvidado1() + tcc.getNotaConvidado2();
        if (tcc.getNotaCoorientador() != -1) {
            notaFinal += tcc.getNotaCoorientador();
            notaFinal = notaFinal / 4;
        } else {
            notaFinal = notaFinal / 3;
        }
        return notaFinal;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
