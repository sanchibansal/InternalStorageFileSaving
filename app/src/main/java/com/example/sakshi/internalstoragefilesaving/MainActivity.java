package com.example.sakshi.internalstoragefilesaving;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    //Declaring EditText and buttons
    EditText editText;
    Button check,  save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //associatin edit ttext and buttons with their respective IDs
        editText = (EditText)findViewById(R.id.text);
        check = (Button)findViewById(R.id.check);
        save = (Button)findViewById(R.id.save);

        //on click listener for file saving in mytext.txt file
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //writing into mytext.txt file
                    FileOutputStream fileout = openFileOutput("mytext.txt",MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileout);
                    //writing data from editText
                    outputStreamWriter.write(editText.getText().toString());
                    //closing outputstreamwriter
                    outputStreamWriter.close();

                    //Toast on successfully saving file
                    Toast.makeText(MainActivity.this, "File Saved Successfully!", Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        //on click listener for check button
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = isFilePresent("mytext.txt");
                if(result){
                    //displays this toast if file is found
                    Toast.makeText(MainActivity.this, "File Found!", Toast.LENGTH_SHORT).show();
                }else{
                    //displays this toast if file is not found
                    Toast.makeText(MainActivity.this, "File Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean isFilePresent(String fileName) {
        File file = getBaseContext().getFileStreamPath(fileName);
        //returns true if file is found else false if not found
        return file.exists();
    }
}
