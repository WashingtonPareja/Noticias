package com.example.noticias;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Banderitas {
    private String titulo;
    private String subtitulo;
    private String url;

    public Banderitas() {
      //  titulo = a.getString("titulo").toString();
      //  subtitulo = a.getString("intro").toString();
      //  url = "http://www.uteq.edu.ec/"+a.getString("url").toString();
    }

  //  public static ArrayList<Noticia> JsonObjectsBuild(JSONArray datos) throws JSONException {
   //    ArrayList<Noticia> noticias = new ArrayList<>();
    //    for (int i = 0; i < datos.length(); i++) {
    //        noticias.add(new Noticia(datos.getJSONObject(i)));
    //    }
    //    return noticias;
   // }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {

        this.url = "http://www.geognos.com/api/en/countries/flag/"+url+".png";
    }
}
