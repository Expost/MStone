package com.company.AST;

import com.company.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ghost on 2016/12/4.
 */
public class ASTLeaf extends ASTree {
    // 空集合
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    protected Token token;
    public ASTLeaf(Token t) { token = t; }
    // 返回第i个子节点，显然叶节点下不会再有子节点了
    public ASTree child(int i) { return null; }
    // 返回子节点的个数
    public int numChildren() { return 0; }
    // 返回空集合的迭代
    public Iterator<ASTree> children() { return empty.iterator(); }

    public String toString() { return token.getText(); }
    public String location() { return "at line " + token.getLineNumber(); }
    public Token token() { return token; }
}
