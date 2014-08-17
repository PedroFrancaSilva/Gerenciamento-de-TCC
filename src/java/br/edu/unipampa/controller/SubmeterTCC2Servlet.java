/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unipampa.controller;

import static br.edu.unipampa.controller.DatasPrazosServlet.ANO;
import static br.edu.unipampa.controller.DatasPrazosServlet.DIA;
import static br.edu.unipampa.controller.DatasPrazosServlet.MES;
import br.edu.unipampa.model.Aluno;
import br.edu.unipampa.model.Datas;
import br.edu.unipampa.model.Orientador;
import br.edu.unipampa.model.Pessoa;
import br.edu.unipampa.model.Professor;
import br.edu.unipampa.model.Tcc;
import br.edu.unipampa.model.Tema;
import br.edu.unipampa.model.web.AcessoSistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author pontofrio
 */
public class SubmeterTCC2Servlet extends HttpServlet {

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

        String usuarioAluno = (String) request.getSession().getAttribute("usuario");

        AcessoSistema acessoSistema = new AcessoSistema();
        Pessoa pessoaEncontrada;

        if (usuarioAluno == null) {
            request.getSession().setAttribute("caminho", "SubmeterTCCServlet");
            request.setAttribute("retorno", "A sua sessão acabou faça o login novamente.");
            request.getRequestDispatcher("telaLogin.jsp").forward(request, response);
        } else {
            pessoaEncontrada = acessoSistema.procurarPessoaEspecifica(usuarioAluno);
            if (!(pessoaEncontrada instanceof Aluno)) {
                try {
                    request.getSession().invalidate();
                } catch (Exception e) {

                }
                request.setAttribute("retorno", "Você não pode acessar esta página, faça o login novamente!");
                request.getRequestDispatcher("telaLogin.jsp").forward(request, response);

            } else {
                submeterTcc(request, response, usuarioAluno);
            }
        }
    }

    public void submeterTcc(HttpServletRequest request, HttpServletResponse response, String usuarioAluno)
            throws ServletException, IOException {

        AcessoSistema acessoSistema = new AcessoSistema();
        String botaoRefazer = request.getParameter("rafazerUpload");
        Datas prazo = acessoSistema.procurarDatas();
        List<Tcc> listaTcc;
        String[] prazoInicial = separarDatas(prazo.getDataInicioTcc());
        String[] prazoFinal = separarDatas(prazo.getDataFimTcc());
        String tipoTcc = "tccInicial";

        listaTcc = acessoSistema.procurarTCC(Integer.parseInt(usuarioAluno));

        if (botaoRefazer != null) {
            if (botaoRefazer.equals("0")) {
                acessoSistema.deletarTcc(listaTcc.get(0));
            }
        }

        if (salvarArquivo(request, response, Integer.parseInt(usuarioAluno), tipoTcc)) {
            request.setAttribute("retorno", "Envio de arquivo bem sucedido");
        }

        listaTcc = acessoSistema.procurarTCC(Integer.parseInt(usuarioAluno));

        request.setAttribute("PrazoTccInicial",true);// verificarPrazo("tccInicial")
        request.setAttribute("PrazoTccFinal", true); //verificarPrazo("tccFinal")

        request.setAttribute("tccInicial", acessoSistema.procurarVersaoTcc(Integer.parseInt(usuarioAluno), 0));
        request.setAttribute("tccFinal", acessoSistema.procurarVersaoTcc(Integer.parseInt(usuarioAluno), 1));
        
        request.setAttribute("dataInicial", prazoInicial[DIA]
                + "/" + prazoInicial[MES] + "/" + prazoInicial[ANO]);

        request.setAttribute("dataFinal", prazoFinal[DIA]
                + "/" + prazoFinal[MES] + "/" + prazoFinal[ANO]);

        request.getSession().setAttribute("tcc", acessoSistema.procurarVersaoTcc(Integer.parseInt(usuarioAluno), 0));
        
        acessoSistema.completarTransacoes();
        
        request.getRequestDispatcher("Tema/submeterTCC.jsp").forward(request, response);
    }

    /**
     * Salva o arquivo enviado pelo usuário no banco de dados
     *
     * @param request Requisição do usuário
     * @param response Resposta do usuário
     * @param matriculaAluno Matrícula do aluno
     * @return true se o envio foi bem sucedido
     * @throws ServletException Caso haja problemas no servlet
     * @throws IOException Caso haja problemas na conexão
     */
    public boolean salvarArquivo(HttpServletRequest request, HttpServletResponse response, int matriculaAluno, String tipoTCC)
            throws ServletException, IOException {

        AcessoSistema as = new AcessoSistema();//Clase que da acesso ao banco de dados

        if (ServletFileUpload.isMultipartContent(request)) {

            int cont = 0;

            ServletFileUpload servletFileUpload = new ServletFileUpload(
                    new DiskFileItemFactory());

            List fileItemsList = null;

            try {
                fileItemsList = servletFileUpload.parseRequest(request);
            } catch (FileUploadException e1) {
                e1.printStackTrace();
            }

            FileItem fileItem = null;

            Iterator it = fileItemsList.iterator();

            do {
                cont++;

                FileItem fileItemTemp = (FileItem) it.next();

                if (cont != (fileItemsList.size()) - 1) {

                    fileItem = fileItemTemp;

                    if (fileItem != null && fileItem.getName() != null) {
                        byte[] arquivo = fileItem.get();
                        String fileName = fileItem.getName();

                        if (fileItem.getSize() > 0) {
                            //Salva no banco de dados
                            Aluno aluno = as.procurarAluno(matriculaAluno);
                            Tema tema = aluno.getTema();
                            Tcc tcc = new Tcc();

                            tcc.setArquivoTcc(arquivo);
                            tcc.setTema(tema);
                            tcc.setDescricao("Uma ai por enquanto");
                            tcc.setTipoArquivo(fileItem.getContentType());
                            tcc.setTitulo(fileName);
                            tcc.setStatus(Tcc.NAO_APROVADO);
                            tcc.setNotaOrientador(-1);
                            tcc.setNotaCoorientador(-1);
                            tcc.setNotaConvidado1(-1);
                            tcc.setNotaConvidado2(-1);
                            tcc.setTipoTCC(1);
                            if (tipoTCC.equals("tccInicial")) {
                                tcc.setVersaoTCC(0);
                            } else if (tipoTCC.equals("tccFinal")) {
                                tcc.setVersaoTCC(1);
                            }

                            as.salvarTcc(tcc);
                        }
                    }
                }
            } while (it.hasNext());
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarPrazo(String tipoTCC) {
        AcessoSistema acessoSistema = new AcessoSistema();
        Datas datas = acessoSistema.procurarDatas();
        String[] prazoInicial = {};
        String[] prazoFinal = {};

        if (tipoTCC.equals("tccInicial")) {
            prazoInicial = separarDatas(datas.getDataInicioTcc());
            prazoFinal = separarDatas(datas.getDataFimTcc());
        } else if (tipoTCC.equals("tccFinal")) {
            prazoInicial = separarDatas(datas.getDataFinalTccFinal());
            prazoFinal = separarDatas(datas.getDataFinalTccFinal());
        }

        String[] atual;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

        atual = separarDatas(formatador.format(date));

        int diaAtual = Integer.parseInt(atual[2]);
        int mesAtual = Integer.parseInt(atual[1]);
        int anoAtual = Integer.parseInt(atual[0]);

        if (anoAtual > Integer.parseInt(prazoFinal[ANO])) {
            return false;
        } else if (mesAtual > Integer.parseInt(prazoFinal[MES])) {
            return false;
        } else if (diaAtual > Integer.parseInt(prazoFinal[DIA])) {
            return false;
        }

        if (anoAtual < Integer.parseInt(prazoInicial[ANO])) {
            return false;
        } else if (mesAtual < Integer.parseInt(prazoInicial[MES])) {
            return false;
        } else if (diaAtual < Integer.parseInt(prazoInicial[DIA])) {
            return false;
        }

        return true;
    }

    public String[] separarDatas(String data) {
        String ano = "";
        String mes = "";
        String dia = "";
        String[] datas = new String[3];
        int cont = 0;

        for (int i = 0; i < data.length(); i++) {
            if (cont < 4) {
                ano = ano + data.charAt(i);
            } else if (cont < 7 && cont != 4) {
                mes = mes + data.charAt(i);
            } else if (cont > 6 && cont != 7) {
                dia = dia + data.charAt(i);
            }
            cont++;
        }

        datas[ANO] = ano;
        datas[MES] = mes;
        datas[DIA] = dia;

        return datas;
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
