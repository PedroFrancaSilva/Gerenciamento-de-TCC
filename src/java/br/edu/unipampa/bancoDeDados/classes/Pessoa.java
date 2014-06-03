package br.edu.unipampa.bancoDeDados.classes;
// Generated 03/06/2014 11:00:57 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Pessoa generated by hbm2java
 */
public class Pessoa  implements java.io.Serializable {


     private Integer idPessoa;
     private String email;
     private String nome;
     private String senha;
     private Set professors = new HashSet(0);
     private Set bancas = new HashSet(0);
     private Set alunos = new HashSet(0);

    public Pessoa() {
    }

	
    public Pessoa(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }
    public Pessoa(String email, String nome, String senha, Set professors, Set bancas, Set alunos) {
       this.email = email;
       this.nome = nome;
       this.senha = senha;
       this.professors = professors;
       this.bancas = bancas;
       this.alunos = alunos;
    }
   
    public Integer getIdPessoa() {
        return this.idPessoa;
    }
    
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Set getProfessors() {
        return this.professors;
    }
    
    public void setProfessors(Set professors) {
        this.professors = professors;
    }
    public Set getBancas() {
        return this.bancas;
    }
    
    public void setBancas(Set bancas) {
        this.bancas = bancas;
    }
    public Set getAlunos() {
        return this.alunos;
    }
    
    public void setAlunos(Set alunos) {
        this.alunos = alunos;
    }




}


