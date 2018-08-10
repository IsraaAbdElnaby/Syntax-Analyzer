package parser;

import java.io.FileNotFoundException;

import lexical.Main;

public class ExpressionDouPrime implements Node{

	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ExpCommon comm = new ExpCommon(); 
		Main obj = new Main();
		int result = comm.parse(obj.index);
		
		if(result == -1) return -1;
		else if(result == 1){
			if(this.parse(obj.index) == -1) return -1;
		}
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
