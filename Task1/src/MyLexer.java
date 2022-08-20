/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Louis
 */
public class MyLexer implements Lexer {
   
    //Identifier regex
    String idenRegex = "\\b([A-Za-z][A-Za-z0-9_]*)\\b";
    
    //Symbols list
    public String[] symbols = new String[]{
            ";","(",")","=","==","<",">","<=",">=",",","{","}",":=","+","*","-","/"
        };
    
    //Keyword list
    public String[] words = new String[] {
            "def", "skip", "if","then","else","while","do","repeat","until","break","continue"
        };
    
    //This compares the string to the keywords to determine the correct tokens
    private tokens getKeyword(String input) throws LexicalException{
        if (input.equals("def")){
            return new T_Def();
        }
        else if (input.equals("skip")){
            return new T_Skip();
        }
        else if (input.equals("if")){
            return new T_If();
        }
        else if (input.equals("then")){
            return new T_Then();
        }
        else if (input.equals("else")){
            return new T_Else();
        }
        else if (input.equals("while")){
            return new T_While();
        }
        else if (input.equals("do")){
            return new T_Do();
        }
        else if (input.equals("repeat")){
            return new T_Repeat();
        }
        else if (input.equals("until")){
            return new T_Until();}
        else if (input.equals("break")){
            return new T_Break();
        }
        else {
            throw new LexicalException("");
        }
    }
    
    //This compares the string to different strings to decide on the correct token
    private tokens getSym(String input) throws LexicalException{
        if (input.equals(";")){
            return new T_Semicolon();}
        else if (input.equals("(")){
            return new T_LeftBracket();}
        else if (input.equals(")")){
            return new T_RightBracket();}
        else if (input.equals("=")){
            return new T_EqualDefines();}
        else if (input.equals("==")){
            return new T_Equal();}
        else if (input.equals("<")){
            return new T_LessThan();}
        else if (input.equals(">")){
            return new T_GreaterThan();}
        else if (input.equals("<=")){
            return new T_LessEq();}
        else if (input.equals(">=")){
            return new T_GreaterEq();}
        else if (input.equals(",")){
            return new T_Comma();}
        else if (input.equals("{")){
            return new T_LeftCurlyBracket();}
        else if (input.equals("}")){
            return new T_RightCurlyBracket();}
        else if (input.equals(":=")){
            return new T_Assign();}
        else if (input.equals("+")){
            return new T_Plus();}
        else if (input.equals("*")){
            return new T_Times();}
        else if (input.equals("-")){
            return new T_Minus();}
        else if (input.equals("/")){
            return new T_Div();}
        else {
            throw new LexicalException("");
        }
    }
   
    //This checks if it is an int
    private boolean checkIfInt(String input){
        if(input.matches("\\d+")){
            return true;
        }
        else{
            return false;
        }
    }
    
    //This gets the identifier and turns it into an identifer token or outputs error
    private tokens getIden(String input) throws LexicalException {
        if(input.matches(idenRegex)){
            return new T_Identifier(input);
        }
        else{
            System.out.println("Not in Language");
            throw new LexicalException("");
            
        }
    }
    
    @Override
    public List<Token> lex (String input) throws LexicalException, Task1Exception {
            List<String> wordsList = Arrays.asList(words); //keywords as list
            List<String> symbolsList = Arrays.asList(symbols); //symbols as list
            List<String> idenList = Arrays.asList(idenRegex); //iden as list
            ArrayList<Token> tokens = new ArrayList<>(); //where all tokens are held
            
            String[] split = input.split("\\s+"); //splits the string by whitespace
            for (int i = 0; i < split.length; i++) { //for all the splits it does the loop
                //checks the split if it is same as the keywords
                if (wordsList.contains(split[i])) {
                    tokens.add(getKeyword(split[i]));
                }
                //checks if the split is the same as the symbols
                else if(symbolsList.contains(split[i])){
                    tokens.add(getSym(split[i]));
                }
                //checks if the split is an int
                else if (checkIfInt(split[i])){
                    tokens.add(new T_Integer(Integer.parseInt(split[i])));
                }
                //checks if the split is an iden
                else if (idenList.contains(split[i])){
                    tokens.add(getIden(split[i]));
                }
               
            }
            return tokens;
            }
}