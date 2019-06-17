package app.SQLito;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsProductsActivity extends AppCompatActivity {
    Cursor cursor;
    ListView ListaVista;
    ArrayList<Productos> productitos=new ArrayList<Productos>();
    TextView textView;
    int pocicion[]=new int[10];
    Button agregar,borrar,actualizar,guardar;
    EditText name,price;
    ImageView b;
    String nom="",pre="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_products);
        borrar=findViewById(R.id.borrar);
        actualizar=findViewById(R.id.actualiza);
        agregar=findViewById(R.id.agrega);
        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        guardar=findViewById(R.id.save);
        ListaVista=findViewById(R.id.items);
        textView=findViewById(R.id.eliminar);
        final DataBaseHelper dbhandler=new DataBaseHelper(DetailsProductsActivity.this);
        final ArrayList<String> datos=new ArrayList<String>();
        cursor=dbhandler.getProducts();

        while(cursor.moveToNext()) {
            Productos productos=new Productos();
            productos.setId(cursor.getString(0));
            productos.setName(cursor.getString(1));
            productos.setPrice(cursor.getString(2));
            productitos.add(productos);
        }

        for (int i=0;i<productitos.size();i++){
            System.out.println(productitos.get(i).getId()+" "+productitos.get(i).name+" $"+productitos.get(i).price);
            datos.add("id :"+productitos.get(i).getId()+" "+productitos.get(i).name+" $"+productitos.get(i).price);
        }
        System.out.println("TAMAÑO DE  DATOS PERRO "+datos.size());
        final  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DetailsProductsActivity.this, R.layout.activity_details_main,R.id.eliminar,
                datos);
        ListaVista.setAdapter(arrayAdapter);


        ListaVista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("tamanio de la lista productitos (objeto)"+productitos.size());
                System.out.println("tamanio de la lista datos (datos) "+datos.size());
                pocicion[0]=position;
                System.out.println("pocicion listView "+pocicion[0]);
                ListaVista.setVisibility(View.INVISIBLE);
                actualizar.setVisibility(View.VISIBLE);
                agregar.setVisibility(View.INVISIBLE);
                borrar.setVisibility(View.VISIBLE);
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indice=productitos.get(pocicion[0]).getId();
                int indiceborrar=Integer.parseInt(indice);
                System.out.println("indice base de datos "+indiceborrar + " lo que se borrara "+datos.get(pocicion[0]) );
                dbhandler.DelateProducts(indiceborrar);
                datos.remove(pocicion[0]);
                productitos.remove(pocicion[0]);
                arrayAdapter.notifyDataSetChanged();
                ListaVista.setVisibility(View.VISIBLE);
                System.out.println("tamanio de la lista productitos "+productitos.size());
                System.out.println("tamanio de la lista datos "+datos.size());
                ListaVista.setVisibility(View.VISIBLE);
                agregar.setVisibility(View.VISIBLE);
                actualizar.setVisibility(View.INVISIBLE);
                borrar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Producto Eliminado id :"+indice,Toast.LENGTH_SHORT).show();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar.setVisibility(View.INVISIBLE);
                borrar.setVisibility(View.INVISIBLE);
                actualizar.setVisibility(View.INVISIBLE);
                name.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                name.setText(productitos.get(pocicion[0]).getName());
                price.setText(productitos.get(pocicion[0]).getPrice());
                guardar.setVisibility(View.VISIBLE);
                agregar.setVisibility(View.INVISIBLE);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              nom=name.getText().toString();
              pre=price.getText().toString();
              System.out.println("nombre "+nom +" precio "+pre);
              String id=productitos.get(pocicion[0]).getId();
              System.out.println("actualizo base de datos "+id +" lo que actulizare "+datos.get(pocicion[0]));
              int idx=Integer.parseInt(id);
              productitos.remove(pocicion[0]);
              datos.remove(pocicion[0]);
              Productos pro = new Productos(id,nom,pre);
              productitos.add(pocicion[0],pro);
              datos.add(pocicion[0],"id : "+pro.getId()+" "+pro.getName()+" $ "+pro.getPrice());
              dbhandler.UpdateProducts(nom,pre,idx);
              arrayAdapter.notifyDataSetChanged();
              ListaVista.setVisibility(View.VISIBLE);
              name.setText("");
              price.setText("");
              name.setVisibility(View.INVISIBLE);
              price.setVisibility(View.INVISIBLE);
              guardar.setVisibility(View.INVISIBLE);
              ListaVista.setVisibility(View.VISIBLE);
              agregar.setVisibility(View.VISIBLE);
              Toast.makeText(getApplicationContext(),"Producto Actualizado id :"+id,Toast.LENGTH_SHORT).show();
            }
        });

         agregar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent (DetailsProductsActivity.this,MainActivity.class);
                 startActivity(intent);
             }
         });













    }

}
