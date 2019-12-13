package com.example.noticias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask, AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://www.geognos.com/api/en/countries/info/all.json", datos,
                MainActivity.this, MainActivity.this);
        ws.execute("");
        getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        getPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        ListView LS=(ListView)findViewById(R.id.lvbandera);
        LS.setOnItemClickListener(this);

    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<Banderitas> lpais= new ArrayList<Banderitas>();
        JSONObject jsonObject = new JSONObject(result);
        JSONObject jresults = jsonObject.getJSONObject("Results");
        Iterator<?> iterator = jresults.keys();
        while (iterator.hasNext()){
            String key =(String)iterator.next();
            JSONObject jpais = jresults.getJSONObject(key);
            Banderitas pais = new Banderitas();

            pais.setTitulo(jpais.getString("Name"));
            JSONObject jCountryCodes = jpais.getJSONObject("CountryCodes");
            pais.setUrl(jCountryCodes.getString("iso2"));
            lpais.add(pais);
        }
        AdaptadorPais adaptadornoticias = new AdaptadorPais(this, lpais);
        ListView lstOpciones = (ListView)findViewById(R.id.lvbandera);
        lstOpciones.setAdapter(adaptadornoticias);
    }

    public void getPermission(String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED))
                ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            Toast.makeText(this.getApplicationContext(),"OK", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this.getApplicationContext(),((Paises)parent.getItemAtPosition(position)).getUrlPdf(),Toast.LENGTH_LONG).show();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(((Banderitas)parent.getItemAtPosition(position)).getUrl()));
        request.setDescription("PDF	Paper");
        request.setTitle("Pdf Imagen");
        if (Build.VERSION.SDK_INT >=	Build.VERSION_CODES.HONEYCOMB)	{
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,	"pais.png");
        DownloadManager manager	=	(DownloadManager)this.getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        try	{
            manager.enqueue(request);
        }	catch	(Exception e)	{
            Toast.makeText(this.getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
