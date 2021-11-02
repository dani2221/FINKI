package MatematickiIzraz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

	public static int evaluateExpression(String expression){
		Stack<Integer> nums = new Stack<>();
		Stack<Character> ops = new Stack<>();
		String[] numStrings = expression.split("\\+|\\*");
		String[] opsStrings = expression.split("\\d+");

		for(int i=1;i<opsStrings.length;i++){
			nums.push(Integer.parseInt(numStrings[i-1]));
			ops.push(opsStrings[i].charAt(0));
		}
		nums.push(Integer.parseInt(numStrings[numStrings.length-1]));

		while(!ops.empty()){
			int num1 = nums.pop();
			int num2 = nums.pop();
			char op = ops.pop();
			if(op=='*'){
				nums.push(num1*num2);
			}
			else {
				if(ops.empty()){
					nums.push(num1+num2);
					break;
				}
				if(ops.peek()=='+'){
					nums.push(num1+num2);
				}
				else {
					int num3 = nums.pop();
					ops.pop();
					nums.push(num2*num3);
					nums.push(num1);
					ops.push('+');
				}
			}
		}
		return nums.pop();

	}
	public static void main(String[] args) throws IOException {
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(evaluateExpression(input.readLine()));
	}

}