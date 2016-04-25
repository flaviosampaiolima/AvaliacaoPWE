/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.beans;

import java.sql.Timestamp;


/**
 *
 * @author Gabriel e Flávio
 */

public class Atividade {
    private int id;
    private String titulo;
    private String corpo;
    private Timestamp dataDeAdicao;
    private Usuario usuario = null;
    private Correcao correcao = null;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the corpo
     */
    public String getCorpo() {
        return corpo;
    }

    /**
     * @param corpo the corpo to set
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     * @return the dataDeAdicao
     */
    public java.sql.Timestamp getDataDeAdicao() {
        return dataDeAdicao;
    }

    /**
     * @param dataDeAdicao the dataDeAdicao to set
     */
    public void setDataDeAdicao(java.sql.Timestamp dataDeAdicao) {
        this.dataDeAdicao = dataDeAdicao;
    }
    
    /**
     * @return the Usuario.Id
     */
    public int getIdUsuario() {
        return usuario.getId();
    }

    /**
     * @param idUsuario the to set Usuario.Id
     */
    public void setIdUsuario(int idUsuario) {
        this.usuario.setId(idUsuario);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Correcao getCorrecao() {
        return correcao;
    }

    public void setCorrecao(Correcao correcao) {
        this.correcao = correcao;
    }    
    
}
