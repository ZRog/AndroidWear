package com.proyecto.roger.mascotas.ResApi.Model;

/**
 * Created by Roger on 20/07/2016.
 */
public class UsuarioResponse {

    private String id;
    private String token;
    private String idusuario;

    public UsuarioResponse(String id,String token, String idusuario){
        this.id = id;
        this.token = token;
        this.idusuario = idusuario;
    }

    public UsuarioResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAnimal() {
        return idusuario;
    }

    public void setAnimal(String animal) {
        this.idusuario = animal;
    }
}
