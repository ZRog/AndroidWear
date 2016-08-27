package com.proyecto.roger.mascotas.Deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.proyecto.roger.mascotas.Pojo.Mascota;
import com.proyecto.roger.mascotas.ResApi.JsonKeys;
import com.proyecto.roger.mascotas.ResApi.Model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Joan on 28/06/2016.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse>{
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializadorMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializadorMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            JsonObject mascotaJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = mascotaJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = mascotaJson.get(JsonKeys.USER_FULLNAME).getAsString();
            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();
            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();
            String idMedia = mascotaResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);
            mascotaActual.setIdMedia(idMedia);
            mascotas.add(mascotaActual);
        }

        return mascotas;
    }
}
