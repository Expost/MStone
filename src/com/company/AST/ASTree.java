package com.company.AST;

import java.util.Iterator;

/**
 * Created by ghost on 2016/12/4.
 */
public abstract class ASTree implements Iterable<ASTree> {
    // 返回第i个子节点
    public abstract ASTree child(int i);
    // 返回子节点的个数
    public abstract int numChildren();
    // 返回一个用于遍历子节点的iterator
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator() { return children(); }
}
