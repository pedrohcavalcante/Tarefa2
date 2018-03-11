package com.example.pedro.tarefa2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditDados extends Activity {

    private static final int REQUEST_PESO = 22;
    private static final int REQUEST_ALTURA = 33;
    private static final String STRING_PESO = "Peso: ";
    private static final String STRING_ALTURA = "Altura: ";

    Button alterar;
    Button cancelar;
    int peso;
    int altura;
    int request;
    TextView valorX;
    EditText valorDado;
    Bundle valores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dados);

        alterar = findViewById(R.id.alterar);
        cancelar = findViewById(R.id.cancelar);
        valorX = findViewById(R.id.textoTipoEdicao);
        valorDado = findViewById(R.id.editarValor);

        valores = getIntent().getExtras();
        request = valores.getInt("requestCode");
        if (request == REQUEST_PESO){
            valorX.setText(STRING_PESO);
        }else if(request == REQUEST_ALTURA){
            valorX.setText(STRING_ALTURA);
        }
    }

    public void alterar(View view){

        Intent retorno = new Intent();
        if (request == REQUEST_PESO){
            retorno.putExtra("peso", Double.parseDouble(valorDado.getText().toString()));
        }else if(request == REQUEST_ALTURA){
            retorno.putExtra("altura", Double.parseDouble(valorDado.getText().toString()));
        }
        setResult(RESULT_OK, retorno);
        finish();
    }

    public void cancelar(View view){
        finish();
    }
}
