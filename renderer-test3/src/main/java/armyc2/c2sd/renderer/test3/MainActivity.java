package armyc2.c2sd.renderer.test3;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import armyc2.c2sd.renderer.MilStdIconRenderer;
import armyc2.c2sd.renderer.utilities.RendererSettings;
import armyc2.c2sd.renderer.test3.R;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private EditText editText = null;
    private MyView myView = null;
    private String fillcolor = "";
    private String linecolor = "";
    private String textcolor = "";
    private String T = "";
    private String T1 = "";
    private String H = "";
    private String H1 = "";
    private String W = "";
    private String W1 = "";
    private String linetype = "";
    private String AM = "";
    private String AN = "";
    private String X = "";
    private String extents = "";
    private String rev = "";
    private String lineWidth = "";
    private String symbolFillIds="";
    MilStdIconRenderer mir = null;
    private String TAG = "armyc2.c2sd.MainActivity";
    private boolean populateModifiers = false;
    private boolean svg = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText = (EditText) findViewById(R.id.edit_message);
        loadRenderer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.layout.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    private static String lastContext = "modifiers";

    //this method automatically called when user select menu items

    public boolean onOptionsItemSelected(MenuItem item) {

        if (myView == null) {
            myView = new MyView(this);
        }
        if (lastContext == "attributes") {
            editText = (EditText) findViewById(R.id.edit_LineColor);
            linecolor = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_TextColor);
            textcolor = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_FillColor);
            fillcolor = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_LineWidth);
            lineWidth = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_AM);
            AM = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_AN);
            AN = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_X);
            X = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_Extents);
            extents = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_Revision);
            rev = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_SymbolFillIds);
            symbolFillIds = editText.getText().toString();
            
        } else if (lastContext == "modifiers") {
            editText = (EditText) findViewById(R.id.edit_message);
            linetype = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_T);
            T = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_T1);
            T1 = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_H);
            H = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_H1);
            H1 = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_W);
            W = editText.getText().toString();
            editText = (EditText) findViewById(R.id.edit_W1);
            W1 = editText.getText().toString();
        }
        switch (item.getItemId()) {
            case R.id.DRAW:
                utility.lineWidth = lineWidth;
                utility.linetype = linetype;
                MyView.linetype = linetype;
                MyView.extents = extents;
                MyView.Rev = rev;
                utility.T = T;
                utility.T1 = T1;
                utility.H = H;
                utility.H1 = H1;
                utility.W = W;
                utility.W1 = W1;
                utility.linecolor = linecolor;
                utility.textcolor = textcolor;
                utility.fillcolor = fillcolor;
                utility.AM = AM;
                utility.AN = AN;
                utility.X = X;
                utility.Rev = rev;
                utility.symbolFillIds=symbolFillIds;
                lastContext = "draw";
                setContentView(myView);
                break;
            case R.id.MODIFIERS:
                lastContext = "modifiers";
                setContentView(R.layout.main);
                editText = (EditText) findViewById(R.id.edit_message);
                editText.setText(linetype);
                editText = (EditText) findViewById(R.id.edit_T);
                editText.setText(T);
                editText = (EditText) findViewById(R.id.edit_T1);
                editText.setText(T1);
                editText = (EditText) findViewById(R.id.edit_H);
                editText.setText(H);
                editText = (EditText) findViewById(R.id.edit_H1);
                editText.setText(H1);
                editText = (EditText) findViewById(R.id.edit_W);
                editText.setText(W);
                editText = (EditText) findViewById(R.id.edit_W1);
                editText.setText(W1);
                //editText = (EditText) findViewById(R.id.edit_message);
                break;
            case R.id.ATTRIBUTES:
                lastContext = "attributes";
                setContentView(R.layout.attributes);
                editText = (EditText) findViewById(R.id.edit_LineColor);
                editText.setText(linecolor);
                editText = (EditText) findViewById(R.id.edit_TextColor);
                editText.setText(textcolor);
                editText = (EditText) findViewById(R.id.edit_FillColor);
                editText.setText(fillcolor);
                editText = (EditText) findViewById(R.id.edit_LineWidth);
                editText.setText(lineWidth);
                editText = (EditText) findViewById(R.id.edit_AM);
                editText.setText(AM);
                editText = (EditText) findViewById(R.id.edit_AN);
                editText.setText(AN);
                editText = (EditText) findViewById(R.id.edit_X);
                editText.setText(X);
                editText = (EditText) findViewById(R.id.edit_Revision);
                editText.setText(rev);
                editText = (EditText) findViewById(R.id.edit_Extents);
                editText.setText(extents);
                editText = (EditText) findViewById(R.id.edit_SymbolFillIds);
                editText.setText(symbolFillIds);
                break;
            default:
                break;
        }
        return true;
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
