package com.example.pedro.tarefa2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author pedrohcavalcante
 */
public class MainActivity extends Activity {

    /**
     *  RequestCodes
     *  22 for peso
     *  33 for altura
     */
    public static final int REQUEST_CODE_PESO = 22;
    public static final int REQUEST_CODE_ALTURA = 33;
    public static final String SEU_IMC_E = "Seu IMC é: ";

    Button peso;
    Button altura;
    double valorPesoRetornado = 0.0;
    double valorAlturaRetornado = 0.0;
    EditText pesoView;
    EditText alturaView;
    double imc;
    TextView resultado;
    TextView magrezaGrave;
    TextView magrezaModerada;
    TextView magrezaLeve;
    TextView saudavel;
    TextView sobrepeso;
    TextView obesidade1;
    TextView obesidade2;
    TextView obesidade3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        pesoView = findViewById(R.id.pesoView);
        alturaView = findViewById(R.id.alturaView);
        peso = findViewById(R.id.peso);
        altura = findViewById(R.id.altura);
        resultado = findViewById(R.id.resultado);
        magrezaGrave = findViewById(R.id.magrezagrave);
        magrezaLeve = findViewById(R.id.magrezaleve);
        magrezaModerada = findViewById(R.id.magrezamoderada);
        saudavel = findViewById(R.id.saudavel);
        sobrepeso = findViewById(R.id.sobrepeso);
        obesidade1 = findViewById(R.id.obesidade1);
        obesidade2 = findViewById(R.id.obesidade2);
        obesidade3 = findViewById(R.id.obesidade3);
        //pesoView.setText(Double.toString(valorPesoRetornado));
        pesoView.setHint("0.0");
        //alturaView.setText(Double.toString(valorAlturaRetornado));
        alturaView.setHint("0.0");
    }


    public void alterarPeso(View view) {
        Intent alterarPeso = new Intent(getApplicationContext(), EditDados.class);
        alterarPeso.putExtra("peso", this.valorPesoRetornado);
        alterarPeso.putExtra("requestCode", REQUEST_CODE_PESO);
        startActivityForResult(alterarPeso, REQUEST_CODE_PESO);
    }

    public void alterarAltura(View view) {
        // altura = (Button)findViewById(R.id.altura);

        Intent alterarAltura = new Intent(getApplicationContext(), EditDados.class);
        alterarAltura.putExtra("altura", this.valorAlturaRetornado);
        alterarAltura.putExtra("requestCode", REQUEST_CODE_ALTURA);
        startActivityForResult(alterarAltura, REQUEST_CODE_ALTURA);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Peso
        if (requestCode == REQUEST_CODE_PESO) {
            try {
                Bundle valorPeso = data.getExtras();
                valorPesoRetornado = valorPeso.getDouble("peso");

            } catch (NullPointerException npe) {
                valorPesoRetornado = this.valorPesoRetornado;
                //Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
            }
            pesoView.setText(Double.toString(valorPesoRetornado));
            //altura
        } else if (requestCode == REQUEST_CODE_ALTURA) {
            try {
                Bundle valorAltura = data.getExtras();
                valorAlturaRetornado = valorAltura.getDouble("altura");
            } catch (NullPointerException npe) {
                valorAlturaRetornado = this.valorAlturaRetornado;
                //Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
            } catch (Resources.NotFoundException rnfe){
                valorAlturaRetornado = this.valorAlturaRetornado;
                //Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show();
            }
            alturaView.setText(Double.toString(valorAlturaRetornado));
        }
    }

    public void calcular(View view) {
        if (pesoView == null || pesoView.length() == 0){
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }else{
            try{
                valorPesoRetornado = Double.parseDouble(pesoView.getText().toString());
                pesoView.setEnabled(false);
            } catch(IllegalStateException ise){
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            }
        }
        if (alturaView == null || alturaView.length() == 0){
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }else{
            try{
                valorAlturaRetornado = Double.parseDouble(alturaView.getText().toString());
                alturaView.setEnabled(false);
            } catch (IllegalStateException ise){
                Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
            }
        }
        magrezaGrave.setTextColor(Color.BLACK);
        magrezaModerada.setTextColor(Color.BLACK);
        magrezaLeve.setTextColor(Color.BLACK);
        saudavel.setTextColor(Color.BLACK);
        sobrepeso.setTextColor(Color.BLACK);
        obesidade1.setTextColor(Color.BLACK);
        obesidade2.setTextColor(Color.BLACK);
        obesidade3.setTextColor(Color.BLACK);
        Double alturaQuadrado = valorAlturaRetornado * valorAlturaRetornado;
        imc = (valorPesoRetornado / alturaQuadrado);
        Double valorRedondo = imc*10000;
        //valorRedondo = Math.round(valorRedondo * 100)/100d;
        //Toast.makeText(this, Double.toString(valorRedondo), Toast.LENGTH_SHORT).show();
        if (valorRedondo < 16.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.RED);
            magrezaGrave.setTextColor(Color.RED);
        }else if (valorRedondo >= 16.0 && valorRedondo < 17.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.YELLOW);
            magrezaModerada.setTextColor(Color.YELLOW);
        }else if(valorRedondo >= 17.0 && valorRedondo < 18.5){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.YELLOW);
            magrezaLeve.setTextColor(Color.YELLOW);
        }else if(valorRedondo >= 18.5 && valorRedondo < 25){
            //Saudavel
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.GREEN);
            saudavel.setTextColor(Color.GREEN);
        }else if(valorRedondo >= 25.0 && valorRedondo < 30.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.RED);
            sobrepeso.setTextColor(Color.RED);
        }else if(valorRedondo >= 30.0 && valorRedondo < 35.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.RED);
            obesidade1.setTextColor(Color.RED);
        }else if(valorRedondo >= 35.0 && valorRedondo < 40.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.RED);
            obesidade2.setTextColor(Color.RED);
        }else if(valorRedondo >= 40.0){
            resultado.setText(SEU_IMC_E + Double.toString(valorRedondo) + ".");
            resultado.setTextColor(Color.RED);
            obesidade3.setTextColor(Color.RED);
        }else{
            resultado.setText("Erro");
        }
    }
}
