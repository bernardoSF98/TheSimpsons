//package
package cl.inacap.thesimpsons;
//imports
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cl.inacap.thesimpsons.adapters.PersonajesAdapter;
import cl.inacap.thesimpsons.dto.Personaje;
//class
public class MainActivity extends AppCompatActivity {
    //list of characters and references
    private List<Personaje> personajes = new ArrayList<>();
    private ListView personajes_listview;
    private PersonajesAdapter personajesAdapter;
    private RequestQueue queue;
    //others
    private Button solicitar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button
        solicitar = (Button) findViewById(R.id.solicitarConsejo);
        solicitar.setOnClickListener((View.OnClickListener) this);
        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.cantidad);
        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.cantidad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    //class of spinner
    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    //button method
    public void verView(View v) {

    }
    //method onResume
    public void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this.getActivity());
        this.personajes_listview = findViewById(R.id.personajes_listview);
        this.personajesAdapter = new PersonajesAdapter((Activity) this.getActivity()
                ,R.layout.list_personajes, this.personajes);
        this.personajes_listview.setAdapter(this.personajesAdapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET
                , "https://thesimpsonsquoteapi.glitch.me/quotes", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    personajes.clear();
                    Personaje[] arreglo = new Gson()
                            .fromJson(response.getString("quotes")
                            ,Personaje[].class);
                    personajes.addAll(Arrays.asList(arreglo));
                } catch (Exception ex) {
                    personajes.clear();
                    Log.e("PERSONAJES", "Error De Peticion");
                } finally {
                    personajesAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                personajes.clear();
                Log.e("PERSONAJES","Error De Respuesta");
                personajesAdapter.notifyDataSetChanged();
            }
        });
        queue.add(jsonObjectRequest);
    }
    //context activity
    private Context getActivity() {
        return getActivity();
    }
}
//end