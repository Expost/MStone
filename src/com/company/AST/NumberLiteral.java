package com.company.AST;

import com.company.Token;

/**
 * Created by ghost on 2016/12/4.
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) { super(t); }
    public int value() { return token().getNumber(); }
}
