package com.example.dlwjd.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;
    Button btnAddData;

    /// just make from layout
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //// just extract to activate DatabaseHelper and this works as contructor for database;;
        myDb= new DatabaseHelper(this);

        editName = findViewById(R.id.editText_Name);
        editSurname = findViewById(R.id.editText_Surname);
        editMarks = findViewById(R.id.editText_Marks);
        editTextId = findViewById(R.id.editText_id);
        btnAddData = findViewById(R.id.button_add);
        btnviewAll = findViewById(R.id.button_viewAll);
        btnviewUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);
        //this extract from layout button for making clicklistening.




        /// this is for below method working
        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Integer deletedRows = myDb.deleteDate(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
    //this is for update click button
    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString()
                                ,editName.getText().toString()
                                ,editSurname.getText().toString()
                                ,editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();


                        /// this method is made before in mainactivity for alt+f7
                    }
                }
        );        ;
    }

/// make click event about viewall button
    public void viewAll(){

        btnviewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ///this method from DatabaseHelper what i made
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){

                            // show message

                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            //this is getting sequence from id,surname ..... 0 is first
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname:"+ res.getString(2)+"\n");
                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }

                        //show all data ,this is what i made below
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); // we can cancel
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        // just show upthings

    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());


                        if(isInserted==true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();



                    }
                }
        );
    }
}


