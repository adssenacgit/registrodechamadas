package com.senac.johnny.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chamada_atendente")
public class ChamadaAtendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chamada_atendente_id")
    private Integer id;
    @Column(name = "chamada_atendente_descricao")
    private String descricao;
    @Column(name = "chamada_atendente_data_abertura")
    private LocalDateTime dataAbertura;
    @Column(name = "chamada_atendente_data_fechamento")
    private LocalDateTime dataFechamento;
    @Column(name = "chamada_atendente_status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "atendente_id", nullable = false)
    @JsonIgnore
    private Atendente atendente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}
