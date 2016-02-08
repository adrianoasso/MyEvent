package com.myevent.dao;

/**
 * Created by adriano.assennato on 08/02/16.
 */
public class Events {
    private int idEvents;
    private String nome;
    private String data;
    private String descrizione;
    private String flagEsist;
    private String flagPassato;
    private String dataUltMod;
    private String utenteUltMod;

    public Events() {
    }

    public int getIdEvents() {
        return idEvents;
    }

    public void setIdEvents(int idEvents) {
        this.idEvents = idEvents;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String isFlagEsist() {
        return flagEsist;
    }

    public void setFlagEsist(String flagEsist) {
        this.flagEsist = flagEsist;
    }

    public String isFlagPassato() {
        return flagPassato;
    }

    public void setFlagPassato(String flagPassato) {
        this.flagPassato = flagPassato;
    }

    public String getDataUltMod() {
        return dataUltMod;
    }

    public void setDataUltMod(String dataUltMod) {
        this.dataUltMod = dataUltMod;
    }

    public String getUtenteUltMod() {
        return utenteUltMod;
    }

    public void setUtenteUltMod(String utenteUltMod) {
        this.utenteUltMod = utenteUltMod;
    }
}
