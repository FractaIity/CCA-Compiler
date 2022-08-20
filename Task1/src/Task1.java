/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 215886
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LexicalException extends Exception {
    public String msg;
    public LexicalException(String _msg) {
        msg = _msg; } }

class Task1Exception extends Exception {
    public String msg;
    public Task1Exception(String _msg) {
        msg = _msg;}}

interface Lexer {
    public List<Token> lex(String input) throws LexicalException, Task1Exception;}

class Task1 {
    public static MyLexer create() {
        return new MyLexer();

    } 
}
