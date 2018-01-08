package com.example.tansu.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int priceOfCoffee = 5;
    private int priceOfWippedCream =2;
    private int priceOfChocolate = 3;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrders(View view) {
         String priceMessage;
        CheckBox wippedCreamCheckBox =(CheckBox) findViewById(R.id.wippedCream_checkBox_view);
        boolean haswippedCream =wippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox =(CheckBox) findViewById(R.id.chocolate_checkBox_view);
        boolean haschocolate =chocolateCheckBox.isChecked();

        EditText customerName =(EditText) findViewById(R.id.customerName_Edit_Text);
        name = customerName.getText().toString();

        priceMessage= orderSummaryMethod(haswippedCream,haschocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Justjava order for"+ name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        TextView priceTextView = (TextView) findViewById(R.id.order_Summary_text_view);
//        priceTextView.setText(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

     private String orderSummaryMethod(boolean addWippedCream,boolean addChololate) {
         int price = calculatePrice(quantity,priceOfCoffee);
         String priceMessage = "Name :" + name;
         priceMessage += "\nQuantity :" + quantity;
         if(addWippedCream){
             price += (quantity * priceOfWippedCream);
             priceMessage += "\nAdd Wipped Cream : "+ addWippedCream;
         }
         if(addChololate){
             price += (quantity * priceOfChocolate);
             priceMessage += "\nAdd Chocolate : "+ addChololate;
         }
         priceMessage += "\nTotal :" + price;
         priceMessage += "\nThank You";
         return (priceMessage);
    }
    /**
     * This Method  is called when increment button is clicked
     */
    public void increment(View view) {
       if(quantity<100){
           quantity++;
           displayQuantity(quantity);
       }
    }

    /**
     * This Method  is called when decrement button is clicked
     */
    public void decrement(View view) {
        if (quantity>=1) {
            quantity--;
            displayQuantity(quantity);
        }
    }
    private int calculatePrice(int price,int noOfCoffee){
        return  (price *noOfCoffee);
    }
}