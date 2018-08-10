package parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lexical.Main;
import lexical.Tokens;

public class U implements Node{

	static ArrayList<String> uContent;
	
	@SuppressWarnings("static-access")
	@Override
	public int parse(int ind) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Tokens token = new Tokens();
		Main obj = new Main();
		Expression exp = new Expression();
		uContent = new ArrayList<>();
		
		if(token.tokensList.get(obj.index).type.equals("LEFT_SQUARE_B")){
			uContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
            
			printNode();
			uContent.clear();
			
			int expResult = exp.parse(obj.index);
			if(expResult == -1 || expResult == 0) return -1;
			
			if(!token.tokensList.get(obj.index).type.equals("RIGHT_SQUARE_B"))
				return -1;
			uContent.add(token.tokensList.get(obj.index).value);
			obj.index++;
		}
		
		printNode();
		return 1;
	}

	@Override
	public void printNode() throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i=0; i<uContent.size(); i++){
			System.out.print(uContent.get(i));
		}
	}

	
}
