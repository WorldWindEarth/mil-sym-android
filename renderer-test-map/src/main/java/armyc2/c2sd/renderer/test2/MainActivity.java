package armyc2.c2sd.renderer.test2;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import armyc2.c2sd.renderer.MilStdIconRenderer;
import armyc2.c2sd.renderer.utilities.RendererSettings;

public class MainActivity extends Activity {

    private MapFragment mapFragment;
    private GoogleMap map;
    private EditText editText;
    private MyView myView = null;
    MilStdIconRenderer mir = null;
    private String TAG = "armyc2.c2sd.MainActivity";
    private boolean populateModifiers = false;
    private boolean svg = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FragmentManager f = getFragmentManager();
        mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));       
        map = mapFragment.getMap();
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.setBuildingsEnabled(true);        
        if (myView == null) {
            myView = new MyView(this);
            myView.map = map;
        }
        utility.map = map;
        loadRenderer();

        editText = (EditText) findViewById(R.id.edit_message);
        editText.setTextColor(Color.RED);
        editText.setText("flot");
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                //this should instead be set by an event handler for editText
                MyView.linetype = editText.getText().toString().toUpperCase(Locale.US);
                myView.onTouchEvent(point);
                return;
            }
        });
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            private float currentZoom = -1;

            @Override
            public void onCameraChange(CameraPosition pos) {
                if (pos.zoom != currentZoom) {
                    currentZoom = pos.zoom;
                }
                myView.setExtents();
                myView.DrawFromZoom(null);
            }
        }
        );
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        map.clear();
        MyView._points.clear();
        MyView._pointsGeo.clear();
        return false;
    }

    public void loadRenderer() {
            //disable svg engine
        //((CheckBox)findViewById(R.id.cbSVG)).setActivated(false);

        //TextView t = (TextView)findViewById(R.id.tvStatus);
        //t.setText("Initializing Renderer");
        //depending on screen size and DPI you may want to change the font size.
        RendererSettings rs = RendererSettings.getInstance();
        rs.setModifierFont("Arial", Typeface.BOLD, 18);
        rs.setMPModifierFont("Arial", Typeface.BOLD, 18);
        rs.setSymbologyStandard(RendererSettings.Symbology_2525C);

        rs.setTextBackgroundMethod(RendererSettings.TextBackgroundMethod_COLORFILL);

        mir = MilStdIconRenderer.getInstance();
        String cacheDir = getApplicationContext().getCacheDir().getAbsoluteFile().getAbsolutePath();
        mir.init(this, cacheDir);
        DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int dpi = metrics.densityDpi;

        //t.setText("Renderer Initialized");
    }

}
