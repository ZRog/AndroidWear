package com.proyecto.roger.mascotas.ResApi;

/**
 * Created by Joan on 28/06/2016.
 */
public final class ConstantesRestApi {
    public static final String VERSION ="/v1/";
    public static final String ROOT_URL ="https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN ="3446513169.e5b8785.9233e34ac33647beb951bf206d670977";
    public static final String KEY_ACCESS_TOKEN ="?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER ="users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER =KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String GET_USERS = "users/search?";
    public static final String USUARIO = "jonatanpere";
    public static final String OBTENER_USUARIO = GET_USERS + USUARIO + "&access_token=" + ACCESS_TOKEN;

    public static final String ID_GET_RECENT_MEDIA = "users/";
    public static final String ID_GET_RECENT_MEDIA2 = "/media/recent/?access_token=";

    public static final String[] USUARIOS = {"jonatanpere","jbertri","barneyrog"};


    public static final String KEY_TOQUE = "toque/{id}/{idusuario}/{idenvio}/";

    public static final String KEY_POST_LIKE = "registrar-like/";

    public static final String ROOT_URL_HEROKU = "https://mighty-ocean-94874.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";

}

