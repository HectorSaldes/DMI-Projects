package dev.thepandadevs.myapplication.mapas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
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
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnDirections.setOnClickListener { buscarDireccion(binding.etDirections.editText?.text.toString()) }
        binding.btnCalculate.setOnClickListener { calcularDistancia() }
    }

    private fun calcularDistancia() {
        val loc1 = Location("")
        loc1.latitude = 18.848139
        loc1.longitude = -99.231522

        val loc2 = Location("")
        loc2.latitude = 18.849834
        loc2.longitude = -99.235963

        var distancia = loc1.distanceTo(loc2)
        Log.i("DISTANCIA", "${distancia} metros")
        abrirNavegacion(
            LatLng(loc1.latitude, loc1.longitude),
            LatLng(loc2.latitude, loc2.longitude)
        )
    }

    private fun buscarDireccion(direccion: String) {
        var latLng = LatLng(0.0, 0.0)
        val geocoder = Geocoder(this)
        val direcciones = geocoder.getFromLocationName(direccion, 1)
        if (!direcciones.isNullOrEmpty()) {
            val direccionEncontrada = direcciones[0]
            latLng = LatLng(direccionEncontrada.latitude, direccionEncontrada.longitude)
            mMap.addMarker(MarkerOptions().position(latLng))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        }

    }

    // En aplicación de maps
    fun abrirNavegacion(origen: LatLng, destino: LatLng) {
        //http://maps.google.com/maps?saddr=location1&daddr=location2
        var intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://maps.google.com/maps?saddr=" + "${origen.latitude},${origen.longitude}&daddr=" + "${destino.latitude},${destino.longitude}")
        )
        startActivity(intent)
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
        iniciaServicio()
        mostrarHistorial()
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
            if (estado != null) {
                Log.i("MAPS_LOG_ESTADO", estado)
            }

            val municipio = directions[0].locality
            if (municipio != null) {
                Log.i("MAPS_LOG_MUNICIPIO", municipio)
            }

            val colonia = directions[0].subLocality
            if (colonia != null) {
                Log.i("MAPS_LOG_COLONIA", colonia)
            }

            val calle = directions[0].thoroughfare
            if (calle != null) {
                Log.i("MAPS_LOG_CALLE", calle)
            }

            val numero = directions[0].subThoroughfare
            if (numero != null) {
                Log.i("MAPS_LOG_NUMERO", numero)
            }

            val postal = directions[0].postalCode
            if (postal != null) {
                Log.i("MAPS_LOG_POSTAL", postal)
            }

            val direccionCompleta = directions[0].getAddressLine(0)
            if (direccionCompleta != null) {
                Log.i("MAPS_LOG_DIRECCIONCOMPLETA", direccionCompleta)
            }
        }
    }

    private fun iniciaServicio() {
        LocationLiveData(this).observe(this) {
            Log.i("MAPS_LOG_SERVICIO", "${it.latitude}, ${it.longitude}")
        }
    }

    private fun mostrarHistorial() {
        val polylineOptions: PolylineOptions = PolylineOptions()
            .add(
                LatLng(18.863051, -99.209969),
                LatLng(18.861092, -99.205226),
                LatLng(18.858208, -99.203510),
                LatLng(18.852176, -99.196464),
                LatLng(18.849078, -99.200137),
                LatLng(18.851595, -99.199880),
            )
            .width(10f)
            .color(ContextCompat.getColor(this, R.color.md_theme_dark_onPrimary))

        val polyline = mMap.addPolyline(polylineOptions)
        val patron = listOf(
            Dot(), Gap(10f), Dash(40f), Gap(10f)
        )
        polyline.pattern = patron
    }

}