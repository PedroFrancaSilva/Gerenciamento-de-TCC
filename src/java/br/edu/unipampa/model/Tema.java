package br.edu.unipampa.model;
// Generated 12/06/2014 22:07:01 by Hibernate Tools 3.6.0



/**
 * Tema generated by hbm2java
 */
public class Tema  implements java.io.Serializable {


     private TemaId id;
     private Professor professor;
     private Aluno aluno;
     private String descricao;
     private Boolean aprovado;

    public Tema() {
    }

	
    public Tema(TemaId id, Professor professor, Aluno aluno) {
        this.id = id;
        this.professor = professor;
        this.aluno = aluno;
    }
    public Tema(TemaId id, Professor professor, Aluno aluno, String descricao, Boolean aprovado) {
       this.id = id;
       this.professor = professor;
       this.aluno = aluno;
       this.descricao = descricao;
       this.aprovado = aprovado;
    }
   
    public TemaId getId() {
        return this.id;
    }
    
    public void setId(TemaId id) {
        this.id = id;
    }
    public Professor getProfessor() {
        return this.professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public Aluno getAluno() {
        return this.aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Boolean getAprovado() {
        return this.aprovado;
    }
    
    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }




}

