package com.company.AST;

import com.company.Token;

/**
 * Created by ghost on 2016/12/4.
 */
public class Name extends ASTLeaf {
    public Name(Token t){ super(t); }
    public String name(){ return token().getText(); }
}
