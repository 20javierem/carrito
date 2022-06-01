package com.example.pregunta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private List<Product> products;
    private Context context;

    public ProductAdapter( Context context, int resource, List<Product> products) {
        super(context, resource, products);
        this.products=products;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout= LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        Product product=products.get(position);
        TextView name=layout.findViewById(R.id.nameProduct);
        TextView price=layout.findViewById(R.id.precioProduct);
        name.setText(product.getNameProduct());
        price.setText("S/."+product.getPrice());
        return layout;
    }
}
