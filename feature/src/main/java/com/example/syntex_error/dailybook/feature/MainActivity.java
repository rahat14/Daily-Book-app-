package com.example.syntex_error.dailybook.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName,etLastName,etFavFood;
    Button btnAdd,btnView , btndell , btncal;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFavFood = (EditText) findViewById(R.id.etFavFood);
        //etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btndell= (Button) findViewById(R.id.btn_del);
        btncal = (Button)findViewById(R.id.button_cal) ;
        myDB = new DatabaseHelper(this);


        btncal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       total_money();
                    }
                }
        );

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewListContents.class);
                startActivity(intent);

               btndell.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       del();

                   }
               });
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = getDateTime() ;
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();
               if(fName.length() != 0 && lName.length() != 0 && fFood.length() != 0){
                    AddData(fName,lName, fFood);
                    etFavFood.setText("");
                   etLastName.setText("");
                    //etFirstName.setText("");
                }else{
                    Toast.makeText(MainActivity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void AddData(String firstName,String lastName, String favFood ){
        boolean insertData = myDB.addData(firstName,lastName,favFood);

        if(insertData==true){
            Toast.makeText(MainActivity.this,"Successfully Entered Data!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }
public void del(){

        boolean deletcart = myDB.deleteCart();
        if (deletcart==true){
            Toast.makeText(MainActivity.this,"Successfully Deleted All the  Data!",Toast.LENGTH_LONG).show();
        }

}
public  void total_money(){
        String total= myDB.getTotalAmount();
        if(total == "0.0"){
            Toast.makeText(MainActivity.this, "You Have Spent  0 Taka  Till NOw " , Toast.LENGTH_LONG).show();
        }
else
    Toast.makeText(MainActivity.this, "You Have Spent " +total +" Taka  Till NOw " , Toast.LENGTH_LONG).show();
}
    public  String getDateTime() {

        String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
        return  date ;
    }


}