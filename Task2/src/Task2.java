/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 215886
 */
// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.List;
import java.util.ArrayList;

class SyntaxException extends Exception {
    public String msg;
    public SyntaxException ( String _msg ) { msg = _msg; } }

class Task2Exception extends Exception {
    public String msg;
    public Task2Exception ( String _msg ) { msg = _msg; } }

interface Parser {
    public Block parse ( List<Token> input ) throws SyntaxException, Task2Exception; }


//Trying to recreate
//BLOCK →	T_LeftCurlyBracket  ENE  T_RightCurlyBracket
//ENE →	 E  |  E  T_Semicolon  ENE
//E →	INT | T_Skip | BLOCK
//INT →	T_Integer
class Task2 {
    public static Parser create () {
    Parser parser = new Parser(){
        //Creates list of expressions in an arraylist
        List<Exp> expr = new ArrayList<Exp>();
        
        //overriding the method given
        @Override
        public Block parse ( List<Token> input ) throws SyntaxException, Task2Exception{
            //Leftcurlybracket
            if(input.get(0) instanceof T_LeftCurlyBracket) 
            {
                try { input.remove(0);}
                catch(Exception e){throw new Task2Exception("");}
            //ENE
                expr.add((Exp) new BlockExp(parse(input)));    
            }
            //RightCurlyBracket
            else if(input.get(0) instanceof T_RightCurlyBracket){
                input.remove(0);
            }
            else {
                //num holds the t int input
                Token num = input.get(0);
                //semicolon first
                if (input.get(0) instanceof T_Semicolon){
                    input.remove(0);
                    parse(input);
                }
                //then int
                if(input.get(0) instanceof T_Integer){
                    input.remove(0);
                    expr.add((Exp) new IntLiteral(((T_Integer)num).n));
                    parse(input);
                }
                //then skip
                if(input.get(0) instanceof T_Skip){
                    input.remove(0);
                    parse(input);
                }
                else{
                    throw new SyntaxException("");
                }
            }
            //gives back block
            //This is broken // can't remember what I was trying to do here //int t = IntLiteral(((T_Integer)num).n));
            return new Block(expr);}
    };
    return parser;}
}
//tester skeleton does not seem to work so I don't know if parser works.
//Could never figure it out before moving on to next task