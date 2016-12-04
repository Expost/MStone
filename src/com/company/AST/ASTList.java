package com.company.AST;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ghost on 2016/12/4.
 */
public class ASTList extends ASTree {
    protected List<ASTree> children;

    public ASTList(List<ASTree> list) { children = list; }
    // 获取第i个节点
    public ASTree child(int i) { return children.get(i); }
    // 获取子节点的个数
    public int numChildren() { return children.size(); }
    // 返回列表的迭代
    public Iterator<ASTree> children() { return children.iterator(); }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        String sep = "";
        for(ASTree t : children){
            builder.append(sep);
            sep = " ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }

    public String location(){
        for(ASTree t : children){
            String s = t.location();
            if(s != null)
                return s;
        }

        return null;
    }
}
