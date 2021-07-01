package com.Re;

import java.util.*;

public class BreadthFirst {
    public static void main(String[] args) {
        getShortLength(1);
    }

    public static List<Point> list=new ArrayList<>();  //记录所有的点

    public static List<List<Point>> pathList=new ArrayList<>(); //记录所有的路径

    public static List<Point> oneOflist=new ArrayList<>();  //记录其中一天记录

    public static Map<Integer,List<Point>> map=new HashMap<>(); //记录某个路径长度对应的路径

    static{
        list.add(new Point(1,4,10));
        list.add(new Point(1,2,1));
        list.add(new Point(1,3,2));
        list.add(new Point(2,4,4));
        list.add(new Point(3,4,2));
    }

    public static int start =1;

    public static int end=4;

    /**
     * 获得最短路径
     * @return 最短路径
     */
    public static void getShortLength(int from){
        getAllPath(from);
        for(List<Point> item:pathList){
            int length=0;
            for(Point point:item){
                length+=point.getLength();
            }
            map.put(length,item);
        }
        Set<Integer> keySet = map.keySet();
        int min_len=Integer.MAX_VALUE;
        for(Integer item:keySet){
            if(item<=min_len) min_len=item;
        }
        System.out.println("min_len="+min_len);
    }

    /**
     * 获得所有路径
     */
    public static void getAllPath(int from){
        if(from==end){
            pathList.add(new ArrayList<>(oneOflist));  //如果开始点就是终点，那么直接将这条记录加入到总的路径里
            return;
        }
        //否则寻找到起点为from的point
        for(Point item:list){
            if(item.getFrom()==from){
                oneOflist.add(item);
                getAllPath(item.getTo());//设置新的起点
                if(oneOflist.size()>=1)oneOflist.remove(oneOflist.size()-1);//如果没够找到对应的下一个点，说明此条路线不通
            }
        }
    }

}

/**
 * 自定义数据结构，表示from表示出发点，to表示目的地
 */
class Point{
    int from;

    int to;

    int length;

    public Point(int from, int to,int length) {
        this.from = from;
        this.to = to;
        this.length=length;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }
}