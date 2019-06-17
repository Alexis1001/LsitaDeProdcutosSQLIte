package app.SQLito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.channels.InterruptedByTimeoutException;

public class MainActivity extends AppCompatActivity {

    EditText name,price;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dbHandler=new DataBaseHelper(MainActivity.this);
                String name_i=name.getText().toString();
                String price_i=price.getText().toString();

                dbHandler.insertProduct(name_i,price_i);
                //System.out.println("INSERTASTE PERRO ");
                Toast.makeText(getApplicationContext(),"producto agregado ",Toast.LENGTH_SHORT).show();
                name.setText("");
                price.setText("");
                //dbHandler.getProducts();

                Intent intent = new Intent (MainActivity.this,DetailsProductsActivity.class);
                startActivity(intent);


            }
        });
    }

}
