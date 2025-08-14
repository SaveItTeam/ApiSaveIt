package com.api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(unique = true)
    private long id;
    @Column(length = 20)
    @NotNull(message = "Cnpj vazio")
    private String cnpj;
    @Column(length = 100)
    @NotNull(message = "Nome vazio")
    private String nome;
    @Column(length = 50)
    @NotNull(message = "Codigo vazio")
    private String codigo;
    @Column(length = 100)
    @NotNull(message = "Email vazio")
    private String email;
    //ajustar campo tipoUsuario para que fique tudo minusculo
    @Column(length = 50)
    @NotNull(message = "Tipo de usuario vazio")
    private String tipo_usuario;
    @Column(length = 20)
    @NotNull(message = "Telefone vazio")
    private String telefone;
    @NotNull(message = "Id do endereco vazio")
    private long endereco_id;
    @NotNull(message = "Senha vazia")
    @Column(length = 255)
    private String senha;
    @NotNull(message = "Categoria vazia")
    @Column(length = 100)
    private String categoria;
    @NotNull(message = "is buyer vazio")
    private boolean is_buyer;
    public Empresa() {
    }

    public Empresa(long id, String cnpj, String nome, String codigo, String email, String tipo_usuario, String telefone, long endereco_id, String senha, String categoria, boolean is_buyer) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.codigo = codigo;
        this.email = email;
        this.tipo_usuario = tipo_usuario;
        this.telefone = telefone;
        this.endereco_id = endereco_id;
        this.senha = senha;
        this.categoria = categoria;
        this.is_buyer = is_buyer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipo_usuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipo_usuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(long endereco_id) {
        this.endereco_id = endereco_id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isIs_buyer() {
        return is_buyer;
    }

    public void setIs_buyer(boolean is_buyer) {
        this.is_buyer = is_buyer;
    }
    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", email='" + email + '\'' +
                ", tipo_usuario='" + tipo_usuario + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco_id=" + endereco_id +
                ", senha='" + senha + '\'' +
                ", categoria='" + categoria + '\'' +
                ", is_buyer=" + is_buyer +
                '}';
    }
}
