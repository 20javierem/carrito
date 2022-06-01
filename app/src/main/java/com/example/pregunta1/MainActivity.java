package com.example.pregunta1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Product> products=new ArrayList<>();
    private Map<Integer,Product> carrito=new HashMap<>();
    private Button btnAgregarAlCarrito;
    private TextView itemSelected;
    private TextView articulosEnElCarrito;
    private Product productSelected;
    private TextView tvTotal;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        itemSelected=findViewById(R.id.itemSelected);
        btnAgregarAlCarrito=findViewById(R.id.btnAñadir);
        articulosEnElCarrito=findViewById(R.id.articulosEnElCarrito);
        tvTotal=findViewById(R.id.tvTotal);
        btnCancelar=findViewById(R.id.btnCancelar);

        cargarProducts();
        ArrayAdapter<Product> adapter=new ProductAdapter(this,0,products);
        listView.setAdapter(adapter);
        cargarActions();
    }
    private void cargarProducts(){
        Product product1=new Product("Pantalón rojo",50.0);
        Product product2=new Product("Pantalón verde",55.0);
        Product product3=new Product("Pantalón negro",60.0);
        Product product4=new Product("Pantalón blanco",65.0);
        Product product5=new Product("Polo rojo",70.0);
        Product product6=new Product("Polo verde",75.0);
        Product product7=new Product("Polo negro",85.0);
        Product product8=new Product("Polo blanco",73.0);
        Product product9=new Product("Camisa rojo",28.0);
        Product product10=new Product("Camisa verde",80.0);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
    }
    private void cargarActions(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productSelected=products.get(position);
                cargarProduct();
            }
        });
        btnAgregarAlCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirProducto();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }
    private void cargarProduct(){
        itemSelected.setText(productSelected.getNameProduct()+"\n S/."+productSelected.getPrice());
    }
    private void añadirProducto(){
        String items =articulosEnElCarrito.getText().toString();
        if(productSelected!=null){
            if(carrito.get(products.indexOf(productSelected))==null){
                carrito.put(products.indexOf(productSelected),productSelected);
                items+="\n* "+productSelected.getNameProduct();
            }
            articulosEnElCarrito.setText(items);
        }
    }
    private void calcularTotal(){
        double total=0;
        for(Integer key:carrito.keySet()){
            total+=carrito.get(key).getPrice();
        }
        tvTotal.setText("Total: "+total);
    }
}