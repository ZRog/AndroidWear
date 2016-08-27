package com.proyecto.roger.mascotas.Pojo;

/**
 * Created by Roger on 28/06/2016.
 */
public class
Mascota {

    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes = 0;
    private String urlFotoPerfil;
    private String idMedia;

    public Mascota(String urlFoto, String nombreCompleto, String telefono, String email, int likes){
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public Mascota(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(String idMedia) {
        this.idMedia = idMedia;
    }
}
