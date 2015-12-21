package info.androidhive.googlemapsv2;

import info.androidhive.googlemapsv2.update;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class tampil extends Activity {
	//Double setLo = update.longi, setLa = update.lati;
	
	
	Bundle b = getIntent().getExtras();
	String result = b.getString("putkode");
	String result2 = b.getString("putkota");
	double result3 = b.getDouble("putlong");
	double result4 = b.getDouble("putlat");
	

	// Google Map
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tampil);
		
		
		
		


		try {
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			//double latitude = -7.298785;
			//double longitude = 112.765407;
			
			
			// tambah marker
			
			
			Marker tanda = googleMap.addMarker(new MarkerOptions()
		       .position(new LatLng(result4,result3))
		       .title(result)
		       .snippet(result2));
			
			CameraPosition cameraPosition = new CameraPosition.Builder()
			.target(new LatLng(result4,
					result3)).zoom(15).build();
			
			/*tanda = googleMap.addMarker(new MarkerOptions()
		       .position(new LatLng(-7.2895805556176425,112.7773351338546))
		       .title("Ini Kantor ku")
		       .snippet("Microsains Server Pulsa All Operator")); */


			//  10 random markers
			/* for (int i = 0; i < 10; i++) {
				// random latitude and logitude
				double[] randomLocation = createRandLocation(latitude,
						longitude);

				// Adding a marker
				MarkerOptions marker = new MarkerOptions().position(
						new LatLng(randomLocation[0], randomLocation[1]))
						.title("Ini Hotel JOSSS " + i);

				Log.e("Random", "> " + randomLocation[0] + ", "
						+ randomLocation[1]);

				// changing marker color
				if (i == 0)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
				if (i == 1)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				if (i == 2)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
				if (i == 3)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
				if (i == 4)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
				if (i == 5)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
				if (i == 6)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				if (i == 7)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
				if (i == 8)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
				if (i == 9)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

				googleMap.addMarker(marker);

				// Move the camera to last position with a zoom level
				if (i == 9) {
					CameraPosition cameraPosition = new CameraPosition.Builder()
							.target(new LatLng(randomLocation[0],
									randomLocation[1])).zoom(15).build();

					googleMap.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
				}
			} */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
	
	// camera position


	
	// tampil menu
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mInfo:
			//Toast.makeText(this, "Info...", Toast.LENGTH_SHORT).show();
			Intent myIntent =  new  
        			Intent(getBaseContext(), insert.class); 
        			startActivityForResult(myIntent, 0); 
			break;
		case R.id.mSetting:
			Intent myIntent2 =  new  
			Intent(getBaseContext(), update.class); 
			startActivityForResult(myIntent2, 0);
			finish();
			break;
		case R.id.mUpdate:
			Intent myIntent3 =  new  
			Intent(getBaseContext(), delete.class); 
			startActivityForResult(myIntent3, 0);
			break;
		}
		return true;
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/*
	 * creating random postion around a location for testing purpose only
	 */
	private double[] createRandLocation(double latitude, double longitude) {

		return new double[] { latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 10) };
	}
}
