package com.example.gamal.echkofriends.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gamal.echkofriends.MainActivity;
import com.example.gamal.echkofriends.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Gama on 23/05/2018.
 */
public class Fragment_MapView extends Fragment implements OnMapReadyCallback {

    // Geocoding:
    // Transforma coordenadas geograficas en direcciones (calle, colonia, avenidas, pais, etc)
    // Reverse Geocoding
    // Transofma nombre de direcciones (calles, colonias, avenidas, pais, etc) en coordenadas geograficas
    Geocoder geocoder;

    // Objeto con el cual podremos hacer uso de nuestro mapa de Google
    GoogleMap map;

    // LocationManager: Nos permite obtener la ubicacion del usuario a traves de diferentes metodos.
    LocationManager locationManager;
    MainActivity activity;

    // Fragmento del mapa
    MapFragment mapFragment;

    // Extra: Lo utilizamos para agregar marcadores al mapa
    List<LatLng> markerPositions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_fragment,container,false);
        //mapFragment.getMapAsync(this);
        activity= (MainActivity)getActivity();
        // Fragmento que contiene al mapa de google en el Layout
        mapFragment = (MapFragment) activity.getFragmentManager().findFragmentById(R.id.main_map_fragment);
        return view;
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragmento que contiene al mapa de google en el Layout
        ///////mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.main_map_fragment);

        // Como estamos implementando OnMapReadyCallback (Ver arriba) esto quiere decir que se debe de encontrar
        // el metodo "onMapReady" y una ves que se termine de cargar el mapa se llamara a este metodo para poder
        // comenzar a utilizar el objeto mapa

    }
    */

    // Se llama cuando el mapa este listo para trabajar con el
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Instaciammos nuestor objeto mapa
        map = googleMap;

        // Inicializa nuestro objeto LocationManager
        locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);

        // Listener para detectar los eventos "Click" dentro del mapa
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            // Este evento nos devuelve la cooordenada geografica donde se dio click dentro del mapa
            @Override
            public void onMapClick(LatLng latLng) {

                // Funcion extra que desarrollamos para agregar marcadores al mapa
                addMarker("Mi prueba de marcador",latLng,true,false);
            }
        });

        // Si estamos en Android 6.0+ tenemos que pedir permisos en tiempo de ejecucion
        // Si estamos debajo de Android 6.0 solo hace falta pedir permisos desde el AndroidManifest
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPermissions();
        else
            moveMapCameraToUserLocation();
    }

    // Se llama cuando un permiso es aceptado o rechazado Android 6.0+
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       /*
        if (grantResults.length > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Se requiere aceptar el permiso", Toast.LENGTH_SHORT).show();
                checkPermissions();
            } else {
                Toast.makeText(this, "Permisio concedido", Toast.LENGTH_SHORT).show();
                moveMapCameraToUserLocation();
            }
        }
        */
    }

    private void checkPermissions() {
        // Apartir de Android 6.0+ necesitamos pedir el permiso de ubicacion
        // directamente en tiempo de ejecucion de la app
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no tenemos permiso para la ubicacion
            // Solicitamos permiso
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        } else {
            // Ya se han concedido los permisos anteriormente
            moveMapCameraToUserLocation();
        }
    }

    private void moveMapCameraToUserLocation() {
        // Continuamos obteniendo la ubicacion del usuario para despues mostrar esa ubicacion en el mapa por default
        // pero cuando no se encuentre la ubicacion entonces pondremos una ubicacion fija.
        LatLng currentLocation = null;
        try {
            // Muestra el boton de "Mi Ubicacion" en el mapa (El tipico circulo azul de google)
            map.setMyLocationEnabled(true);

            // Utilizamos el metodo que desarrollamos para obtener la ubicacion del usuario
            currentLocation = getCurrentLocation();

            showToast("Coords: " + currentLocation.latitude + " " + currentLocation.longitude);

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        // Si se pudo obtener la ubicacion del usuario
        if (currentLocation != null) { // .. cambiar if

            // Movemos la camara para que apunte a otra coordenada diferente e la default
            map.moveCamera((CameraUpdateFactory.newLatLngZoom(currentLocation, 16.f)));
        } else { // Si no se pudo obtener la ubicacion

            // Ponemos una ubicacion fija
            LatLng mtyLocation = new LatLng(25.65,-100.29);
            map.moveCamera((CameraUpdateFactory.newLatLngZoom(currentLocation, 16.f)));
        }
    }

    private LatLng getCurrentLocation() throws SecurityException {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers)
        {
            Location l = locationManager.getLastKnownLocation(provider);
            if(l == null) {
                continue;
            }
            if(bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy())
            {
                bestLocation = l;
            }
        }
        if (bestLocation == null){
            showToast("No se puede obtener tu ubicacion");
            return null;
        }
        return new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());
    }

    private void addMarker(String title, LatLng position, boolean clean, boolean polys) {
        if (clean) {
            map.clear();
        }

        // De esta manera se pueden agregar marcadores al mapa
        MarkerOptions opts = new MarkerOptions();
        opts.position(position);
        opts.title(title);

        // La clase GoogleMap tiene el metodo addMarker
        map.addMarker(opts);

        if (!polys)
            return;

        if (markerPositions == null)
            markerPositions = new ArrayList<>();

        // EXTRA: Tambien se pueden poner lineas dentro del mapa
        PolylineOptions line = new PolylineOptions();
        line.width(8);
        line.color(Color.BLUE);

        if (markerPositions.size() > 0) {
            LatLng latLng = markerPositions.get(markerPositions.size() - 1);
            line.add(latLng);
        }
        line.add(position);
        markerPositions.add(position);

        // Muestra una linea en el mapa
        map.addPolyline(line);
    }

    private void showToast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}