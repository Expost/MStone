package com.company.AST;

import java.util.List;

/**
 * Created by ghost on 2016/12/4.
 */

public class BinaryExpr extends ASTList {
    // 在该类的列表中存有3个对象，分别为左对象，运算表达式以及右对象
    public BinaryExpr(List<ASTree> c) { super(c); }
    // 获取左对象
    public ASTree left() { return child(0); }
    // 获取表达式
    public String operator(){
        return ((ASTLeaf)child(1)).token().getText();
    }
    // 获取右对象
    public ASTree right() { return child(2); }
}
