package com.nathan2c.devcalc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button numeroZero, numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete,
			numeroOito, numeroNove, ponto, soma, subtracao, multiplicacao, divisao, igual, botao_limpar, backspace;

	private TextView txtExpressao, txtResultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().hide();
		IniciarComponentes();
	}

	private void IniciarComponentes() {
		// TextViews
		txtExpressao = findViewById(R.id.txt_expressao);
		txtResultado = findViewById(R.id.txt_resultado);

		// Números
		numeroZero = findViewById(R.id.numero_zero);
		numeroUm = findViewById(R.id.numero_um);
		numeroDois = findViewById(R.id.numero_dois);
		numeroTres = findViewById(R.id.numero_tres);
		numeroQuatro = findViewById(R.id.numero_quatro);
		numeroCinco = findViewById(R.id.numero_cinco);
		numeroSeis = findViewById(R.id.numero_seis);
		numeroSete = findViewById(R.id.numero_sete);
		numeroOito = findViewById(R.id.numero_oito);
		numeroNove = findViewById(R.id.numero_nove);

		// Operadores
		ponto = findViewById(R.id.ponto);
		soma = findViewById(R.id.adicao);
		subtracao = findViewById(R.id.subtracao);
		multiplicacao = findViewById(R.id.multiplicacao);
		divisao = findViewById(R.id.divisao);
		igual = findViewById(R.id.igual);
		backspace = findViewById(R.id.backspace);
		botao_limpar = findViewById(R.id.bt_limpar);

		// Configurar listeners para todos os botões
		numeroZero.setOnClickListener(this);
		numeroUm.setOnClickListener(this);
		numeroDois.setOnClickListener(this);
		numeroTres.setOnClickListener(this);
		numeroQuatro.setOnClickListener(this);
		numeroCinco.setOnClickListener(this);
		numeroSeis.setOnClickListener(this);
		numeroSete.setOnClickListener(this);
		numeroOito.setOnClickListener(this);
		numeroNove.setOnClickListener(this);
		ponto.setOnClickListener(this);
		soma.setOnClickListener(this);
		subtracao.setOnClickListener(this);
		multiplicacao.setOnClickListener(this);
		divisao.setOnClickListener(this);
		igual.setOnClickListener(this);
		backspace.setOnClickListener(this);
		botao_limpar.setOnClickListener(this);
	}

	public void AcrescentarExpressao(String string, boolean limpar_dados) {
		if (limpar_dados) {
			txtExpressao.append(string);
		} else {
			// Se não for para limpar dados, apenas adiciona o operador à expressão atual
			txtExpressao.append(string);
		}
		txtResultado.setText(""); // Sempre limpa o resultado quando adiciona algo novo
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		// Números
		case R.id.numero_zero:
			AcrescentarExpressao("0", true);
			break;
		case R.id.numero_um:
			AcrescentarExpressao("1", true);
			break;
		case R.id.numero_dois:
			AcrescentarExpressao("2", true);
			break;
		case R.id.numero_tres:
			AcrescentarExpressao("3", true);
			break;
		case R.id.numero_quatro:
			AcrescentarExpressao("4", true);
			break;
		case R.id.numero_cinco:
			AcrescentarExpressao("5", true);
			break;
		case R.id.numero_seis:
			AcrescentarExpressao("6", true);
			break;
		case R.id.numero_sete:
			AcrescentarExpressao("7", true);
			break;
		case R.id.numero_oito:
			AcrescentarExpressao("8", true);
			break;
		case R.id.numero_nove:
			AcrescentarExpressao("9", true);
			break;

		// Operadores matemáticos
		case R.id.ponto:
			// Verifica se já existe um ponto no número atual
			String currentExpr = txtExpressao.getText().toString();
			String[] parts = currentExpr.split("[+\\-*/]");
			String lastNumber = parts.length > 0 ? parts[parts.length - 1] : "";
			if (!lastNumber.contains(".")) {
				AcrescentarExpressao(".", true);
			}
			break;
		case R.id.adicao:
			AcrescentarExpressao("+", false);
			break;
		case R.id.subtracao:
			AcrescentarExpressao("-", false);
			break;
		case R.id.multiplicacao:
			AcrescentarExpressao("*", false);
			break;
		case R.id.divisao:
			AcrescentarExpressao("/", false);
			break;

		// Botões de ação
		case R.id.igual:
			CalcularResultado();
			break;
		case R.id.bt_limpar:
			txtExpressao.setText("");
			txtResultado.setText("");
			break;
		case R.id.backspace:
			String expressao = txtExpressao.getText().toString();
			if (!expressao.isEmpty()) {
				txtExpressao.setText(expressao.substring(0, expressao.length() - 1));
			}
			break;
		}
	}

	public void CalcularResultado() {
		try {
			String expressao = txtExpressao.getText().toString();

			// Verifica se a expressão está vazia
			if (expressao.isEmpty()) {
				return;
			}

			// Verifica se a expressão termina com um operador
			if (expressao.matches(".*[+\\-*/]$")) {
				txtResultado.setText("Erro: Expressão inválida");
				return;
			}

			// Cria o objeto Expression da biblioteca exp4j
			Expression expressaoMatematica = new ExpressionBuilder(expressao).build();

			// Calcula o resultado
			double resultado = expressaoMatematica.evaluate();

			// Verifica se o resultado é inteiro para remover ".0"
			long resultadoInteiro = (long) resultado;
			if (resultado == (double) resultadoInteiro) {
				txtResultado.setText(String.valueOf(resultadoInteiro));
			} else {
				txtResultado.setText(String.valueOf(resultado));
			}

			// Opcional: mover o resultado para a expressão para continuar calculando
			// txtExpressao.setText(txtResultado.getText().toString());

		} catch (Exception e) {
			txtResultado.setText("Erro");
		}
	}
}