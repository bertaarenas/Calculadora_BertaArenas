package com.example.calculadora;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText getPeso, getAltura;
    RadioGroup rgUnidad;
    RadioButton btnMetros, btnCentimetros;
    Button buttonCalcular;
    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPeso = findViewById(R.id.ETPeso);
        getAltura = findViewById(R.id.ETAltura);
        rgUnidad = findViewById(R.id.rgUnidad);
        btnMetros = findViewById(R.id.btnMetros);
        btnCentimetros = findViewById(R.id.btnCentimetros);
        buttonCalcular = findViewById(R.id.button);
        textViewResultado = findViewById(R.id.textViewResultado);


        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }

    public double calcularImc(double peso, double altura) {
        return peso / (altura * altura);
    }

    public void calcular() {

        String pesoTexto = getPeso.getText().toString();
        String alturaTexto = getAltura.getText().toString();

        if (pesoTexto.isEmpty()) {
            getPeso.setError("Introduce el peso: ");
            return;
        }

        if (alturaTexto.isEmpty()) {
            getAltura.setError("Introduce la altura: ");
            return;
        }

        try {
            double peso = Double.parseDouble(pesoTexto.replace(',', '.'));
            double altura = Double.parseDouble(alturaTexto.replace(',', '.'));

            if (peso <= 0) {
                getPeso.setError("El peso tiene que ser mayor que cero.");
                return;
            }

            if (altura <= 0) {
                getAltura.setError("La altura tiene que ser mayor que cero.");
                return;
            }

            if (btnCentimetros.isChecked()) {
                altura = altura / 100;
            }

            double imc = calcularImc(peso, altura);

            String categoria;

            if (imc < 18.5) {
                categoria = "Bajo peso";
            }
            else if (imc < 25) {
                categoria = "Peso normal";
            }
            else if (imc < 30) {
                categoria = "Sobrepeso";
            }
            else if (imc < 35) {
                categoria = "Obesidad grado 1";
            }
            else {
                categoria = "Obesidad mórbida";
            }

            String resultado = String.format(java.util.Locale.getDefault(), "Tu IMC es: %.2f\nCategoría: " + categoria, imc, categoria);

            textViewResultado.setText(resultado);

        }
        catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Introduce números válidos",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
