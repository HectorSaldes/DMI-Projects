package dev.thepandadevs.myapplication.mapas

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dev.thepandadevs.myapplication.R
import dev.thepandadevs.myapplication.databinding.ActivityMapsBinding
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled = true // Activar mi ubicación
        cameraListener()
    }

    private fun cameraListener() {
        mMap.setOnCameraIdleListener {
            val lat = mMap.cameraPosition.target.latitude
            val long = mMap.cameraPosition.target.longitude
            Log.i("MAPS_LOG", "$lat $long")
            getDirectionByLatLng(lat, long)
        }
    }

    private fun getDirectionByLatLng(lat: Double, long: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val directions = geocoder.getFromLocation(lat, long, 1)
        if (directions.size > 0) {
            val pais = directions[0].countryName
            if (pais != null) {
                Log.i("MAPS_LOG_PAIS", pais)
            }

            val estado = directions[0].adminArea
            if(estado != null){
                Log.i("MAPS_LOG_ESTADO", estado)
            }

            val municipio = directions[0].locality
            if(municipio != null){
                Log.i("MAPS_LOG_MUNICIPIO", municipio)
            }

            val colonia = directions[0].subLocality
            if(colonia != null){
                Log.i("MAPS_LOG_COLONIA", colonia)
            }

            val calle = directions[0].thoroughfare
            if(calle != null){
                Log.i("MAPS_LOG_CALLE", calle)
            }

            val numero = directions[0].subThoroughfare
            if(numero != null){
                Log.i("MAPS_LOG_NUMERO", numero)
            }

            val postal = directions[0].postalCode
            if(postal != null){
                Log.i("MAPS_LOG_POSTAL", postal)
            }

            val direccionCompleta = directions[0].getAddressLine(0)
            if (direccionCompleta != null){
                Log.i("MAPS_LOG_DIRECCIONCOMPLETA", direccionCompleta)
            }
        }
    }
}