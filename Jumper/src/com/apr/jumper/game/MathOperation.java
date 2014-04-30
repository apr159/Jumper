package com.apr.jumper.game;

public class MathOperation {
	int operand1;
	int operand2;
	int result;
	Operation operation;
	
	
	
	public MathOperation(World world){
		operand1 = world.rand.nextInt(world.level*2);
		operand2 = world.rand.nextInt(world.level*2);
		operation = Operation.PLUS;
		result = operation.result(operand1, operand2);
	}
	
	@Override
	public String toString(){
		return String.valueOf(operand1) + operation + String.valueOf(operand2);
	}
	
	
	public enum Operation{
		PLUS("+"),
		MINUS("-");
		
		String op;
		private Operation(String op){
			this.op = op;
		}
		
		public String toString(){
			return op;
		}
		
		public int result(int op1, int op2){
			if (op.equals("+")){
				return op1+op2;
			}
			if (op.equals("-")){
				return op1-op2;
			}
			return 0;
		}
	}
}
