//package
package cl.inacap.thesimpsons.adapters;
//imports
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.util.List;
import cl.inacap.thesimpsons.R;
import cl.inacap.thesimpsons.dto.Personaje;
//class
public class PersonajesAdapter extends ArrayAdapter<Personaje> {
    //variables
    private List<Personaje> personajes;
    private Activity activity;
    //1
    public PersonajesAdapter(@NonNull Activity context, int resource, @NonNull List<Personaje> objects) {
        super(context, resource, objects);
        this.personajes = objects;
        this.activity = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_personajes, null, true);
        TextView nombreTxt = rowView.findViewById(R.id.nombre_txt);
        TextView fraseTxt = rowView.findViewById(R.id.frase_txt);
        ImageView imagenImg = rowView.findViewById(R.id.imagen_img);
        nombreTxt.setText(personajes.get(position).getCharacter());
        fraseTxt.setText(personajes.get(position).getQuote());
        Picasso.get().load(this.personajes.get(position).getImage())
                .resize(300,300)
                .centerCrop()
                .into(imagenImg);
        return rowView;
    }
}
//end