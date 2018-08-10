package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class X implements Node {

	ArrayList<String> xContent;
    
	@SuppressWarnings("static-access")
	@Override
	/**
	 * "(" Expression ")" Y
	 */
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens t = new Tokens();
		Expression exp = new Expression();
	    Main mObj = new Main();
	    xContent = new ArrayList<>();
		Y y = new Y();
		
		if(t.tokensList.get(mObj.index).type.equals("LEFT_ROUND_B")){
			xContent.add(t.tokensList.get(mObj.index).value);
			mObj.index++;
			printNode();
			xContent.clear();
			
			if(exp.parse(mObj.index) == -1) return -1;

		    if(t.tokensList.get(mObj.index).type.equals("RIGHT_ROUND_B")){
			xContent.add(t.tokensList.get(mObj.index).value);
			mObj.index ++;
			printNode();
			xContent.clear();
		    }
		    
			if(y.parse(mObj.index) == -1) return -1;

		}
		
		printNode();
		return 1;
	}
	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<xContent.size(); i++){
			System.out.print(xContent.get(i)+" ");
			if(i !=0 && xContent.get(i-1).equals(")")) System.out.print("\n");
		}
	}
}
