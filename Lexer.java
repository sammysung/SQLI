package com.sqli.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class Lexer {
	private int line = 1;
	private char peek = ' ';
	private InputStream stream;
	private Hashtable<String, Word> words = new Hashtable<>();
	private driver drive=null;
	
	public Lexer(InputStream stream, driver d){
		this.stream = stream;
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
		drive=d;
	}
	
	private void reserve(Word t){
		words.put(t.lexeme, t);
	}

	public driver scan () throws IOException, SyntaxException{

            for (; ; peek = (char) stream.read()) {
                if (peek == ' ' || peek == '\t') {
                    continue;
                } else if (peek == '\n') {
                    line++;
                } else if (peek == ';') {
                    System.out.println("Gotcha!");
                    drive.setSemi(true);
                } else {
                    break;
                }
            }
            // extending the lexer to remove comment
            if (peek == '/') {
                peek = (char) stream.read();
                if (peek == '/') {
                    // single line comment
                    for (; ; peek = (char) stream.read()) {
                        if (peek == '\n') {
                            //end of single line comment, notify of skipp
                            System.out.println("Skipped Single line comment");
                            break;
                        }
                    }
                } else if (peek == '*') {
				/* block comment*/
                    char prevPeek = ' ';
                    for (; ; prevPeek = peek, peek = (char) stream.read()) {
                        if (prevPeek == '*' && peek == '/') {
                            //end of multiline comment, notify of skip
                            System.out.println("Skipped multi line comment");
                            break;
                        }
                        if ((prevPeek == '*' && peek == '\n') || (prevPeek != '*' && peek == '/')
                                || (prevPeek != ' ' && peek == '\n')) {
                            System.out.println("Error: Multiline comments not properly closed");
                            throw new SyntaxException();
                        }
                    }
                } else {
                    System.out.println("Error: '/' without a '*' or '/'");
                    throw new SyntaxException();
                }
            }

            // extending the lexer to handle relational operator
            String relD = "*=!<>;";
            char c = peek;
            if (relD.indexOf(c) > -1) {
                StringBuffer b = new StringBuffer();
                b.append(c);
                peek = (char) stream.read();
                if (peek == '=') {
                    b.append(peek);
                }
		        if (peek == '\'') {
                    b.append(peek);
                }
                if(peek== ';' ){
                    drive.setSemi(true);
                    System.out.println("Semi is true!");
                }
                new Rel(b.toString()).PrintRelOp();
            }

            // handle integer values
            if (Character.isDigit(peek)) {
                int v = 0;
                do {
                    v = 10 * v + Character.digit(peek, 10);
                    peek = (char) stream.read();
                } while (Character.isDigit(peek));
                new Num(v).PrintDigit();
            }

            // handle word
            if (Character.isLetter(peek)) {
                StringBuffer b = new StringBuffer();
                do {
                    b.append(peek);
                    peek = (char) stream.read();
                } while (Character.isLetterOrDigit(peek));
                String s = b.toString();
                Word w = words.get(s);
                if (s.equals("true") || s.equals("false"))
                    w.PrintWord();
                if (w == null) {
                    w = new Word(Tag.ID, s);
                    words.put(s, w);
                    w.PrintWord();
                }
            }

            Token t = new Token(peek);
            peek = ' ';
            return drive;
	}
}
