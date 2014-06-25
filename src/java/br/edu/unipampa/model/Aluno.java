package br.edu.unipampa.model;
// Generated 21/06/2014 18:48:59 by Hibernate Tools 3.6.0

import br.edu.unipampa.bancoDeDados.hibernate.HibernateUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;

/**
 * Aluno generated by hbm2java
 */
public class Aluno implements java.io.Serializable {

    private static final int VALOR_MINIMO_HORAS = 360;
    
    private int matricula;
    private Pessoa pessoa;
    private Integer cargaHoraria;
    private Set bancas = new HashSet(0);
    private Set temas = new HashSet(0);

    public Aluno() {
    }

    public Aluno(int matricula, Pessoa pessoa) {
        this.matricula = matricula;
        this.pessoa = pessoa;
    }

    public Aluno(int matricula, Pessoa pessoa, Integer cargaHoraria, Set bancas, Set temas) {
        this.matricula = matricula;
        this.pessoa = pessoa;
        this.cargaHoraria = cargaHoraria;
        this.bancas = bancas;
        this.temas = temas;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getCargaHoraria() {
        return this.cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Set getBancas() {
        return this.bancas;
    }

    public void setBancas(Set bancas) {
        this.bancas = bancas;
    }

    public Set getTemas() {
        return this.temas;
    }

    public void setTemas(Set temas) {
        this.temas = temas;
    }

    public String getSenha() {
        return pessoa.getSenha();
    }

    public String getNome() {
        return pessoa.getNome();
    }

    public String getUsuario() {
        return pessoa.getUsuario();
    }

    /**
     * Método responsável por cadastrar o tema no sistema
     *
     * @param matriculaAluno Matricula do aluno que requisitou o tema
     * @param usuarioProfessor Usuário do professor que o aluno indicou como
     * orientador
     * @param decricao descrição do tema
     * @return true se o cadastro for efetuado com sucesso
     */
    public boolean cadastrarTema(int matriculaAluno, String usuarioProfessor, String decricao) {
        List<Professor> professoresEncontrados;
        List<Aluno> alunosEncontrados;
        Aluno aluno = null;
        Professor professor = null;
        Tema tema = new Tema();
        Session sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();

        professoresEncontrados = (List<Professor>) sessao.createQuery("From Professor").list();
        alunosEncontrados = (List<Aluno>) sessao.createQuery("From Aluno").list();

        for (Aluno alunoEncontrado : alunosEncontrados) {
            if (alunoEncontrado.getMatricula() == matriculaAluno) {
                aluno = alunoEncontrado;
                break;
            }
        }

        for (Professor professorEncontrado : professoresEncontrados) {
            if (professorEncontrado.getUsuario().equals(usuarioProfessor)) {
                professor = professorEncontrado;
            }
        }

        if (aluno != null && professor != null && verificarCargaHoraria(aluno)) {
            tema.setAluno(aluno);
            tema.setProfessor(professor);
            tema.setAprovado(false);
            tema.setDescricao(decricao);
            sessao.save(tema);
            sessao.getTransaction().commit();
            return true;
        }
        return false;
    }

    /**
     * Verifica se o aluno tem a carga horário necessária para realizar o TCC
     *
     * @param aluno Aluno para se verificar
     * @return True se o aluno pode fazer o tcc
     */
    public boolean verificarCargaHoraria(Aluno aluno) {
        return aluno.getCargaHoraria() >= VALOR_MINIMO_HORAS;
    }
}
