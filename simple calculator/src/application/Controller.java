package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Controller implements Initializable {//314 155
	
	@FXML
	private Button one;
	@FXML
	private Button two;
	@FXML
	private Button three;
	@FXML
	private Button four;
	@FXML
	private Button five;
	@FXML
	private Button six;
	@FXML
	private Button seven;
	@FXML
	private Button eight;
	@FXML
	private Button nine;
	@FXML
	private Button zero;
	@FXML
	private Button clear;
	@FXML
	private Button div;
	@FXML
	private Button mul;
	@FXML
	private Button add;
	@FXML
	private Button sub;
	@FXML
	private Button dec;
	@FXML
	private Button equals;
	@FXML
	private Label output;
	
	private String expression;
	
	@FXML
	private void one(ActionEvent e) {
		expression+="1";
		output.setText(expression);
	}
	@FXML
	private void two(ActionEvent e) {
		expression+="2";
		output.setText(expression);
	}
	@FXML
	private void three(ActionEvent e) {
		expression+="3";
		output.setText(expression);
	}
	@FXML
	private void four(ActionEvent e) {
		expression+="4";
		output.setText(expression);
	}
	@FXML
	private void five(ActionEvent e) {
		expression+="5";
		output.setText(expression);
	}
	@FXML
	private void six(ActionEvent e) {
		expression+="6";
		output.setText(expression);
	}
	@FXML
	private void seven(ActionEvent e) {
		expression+="7";
		output.setText(expression);
	}
	@FXML
	private void eight(ActionEvent e) {
		expression+="8";
		output.setText(expression);
	}
	@FXML
	private void nine(ActionEvent e) {
		expression+="9";
		output.setText(expression);
	}
	@FXML
	private void zero(ActionEvent e) {
		expression+="0";
		output.setText(expression);
	}
	@FXML
	private void div(ActionEvent e) {
		expression+="÷";
		output.setText(expression);
	}
	@FXML
	private void mul(ActionEvent e) {
		expression+="×";
		output.setText(expression);
	}
	@FXML
	private void sub(ActionEvent e) {
		expression+="-";
		output.setText(expression);
	}
	@FXML
	private void add(ActionEvent e) {
		expression+="+";
		output.setText(expression);
	}
	@FXML
	private void dec(ActionEvent e) {
		expression+=".";
		output.setText(expression);
	}
	@FXML
	private void clear(ActionEvent e) {
		if(expression.isEmpty()) {
			return;
		}
		expression=expression.substring(0, expression.length()-1);
		output.setText(expression);
	}
	@FXML
	private void equals(ActionEvent e) {
		Stack<Double>values=new Stack<>();
		Stack<Character>operators=new Stack<>();
		char tokens[]=expression.toCharArray();
		boolean minus=false;
		for(int i=0;i<tokens.length;i++) {
			if(i==0&&tokens[i]=='-') {
				minus=true;
				i++;
			}
			if((tokens[i]>='0'&&tokens[i]<='9')||tokens[i]=='.') {
				String num="";
				if(minus) {
					num+="-";
					minus=false;
				}
				while(i<tokens.length&&((tokens[i]>='0'&&tokens[i]<='9')||tokens[i]=='.')) {
					num+=tokens[i++];
				}
				values.push(Double.parseDouble(num));
				i--;
			}else if (tokens[i]=='+'||tokens[i]=='-'||tokens[i]=='÷'||tokens[i]=='×') {
				while(!operators.isEmpty()&&prec(tokens[i], operators.peek())) {
					values.push(operation(operators.pop(), values.pop(), values.pop()));
				}
				operators.push(tokens[i]);
			}
		}
		while(!operators.isEmpty()) {
			values.push(operation(operators.pop(), values.pop(), values.pop()));
		}
		expression=String.valueOf(values.pop());
		output.setText(expression);
	}
	
	public double operation(char op,double a,double b) {
		if(op=='+') {
			return a+b;
		}else if (op=='-') {
			return b-a;
		}else if (op=='÷') {
			if(a==0) {
				throw new UnsupportedOperationException("Cannot divide by zero");
			}
			return b/a;
		}else if (op=='×') {
			return a*b;
		}
		return 0;
	}
	
	public boolean prec(char op1,char op2) {
		if ((op1 == '×' || op1 == '÷')&&(op2 == '+' || op2 == '-')) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		expression="";
		output.setStyle("-fx-border-color: black;");
		output.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
	}
}
