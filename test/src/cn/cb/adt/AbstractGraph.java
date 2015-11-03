package cn.cb.adt;


import cn.cb.adt.exception.UnsupportedOperation;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGraph implements Graph {
    protected LinkedList vertexs;//顶点表
    protected LinkedList edges;    //边表
    protected int type;            //图的类型

    public AbstractGraph(int type) {
        this.type = type;
        vertexs = new LinkedListDLNode();
        edges = new LinkedListDLNode();
    }

    //返回图的类型
    public int getType() {
        return type;
    }

    //返回图的顶点数
    public int getVexNum() {
        return vertexs.getSize();
    }

    //返回图的边数
    public int getEdgeNum() {
        return edges.getSize();
    }

    //返回图的所有顶点
    public Iterator getVertex() {
        return vertexs.elements();
    }

    //返回图的所有边
    public Iterator getEdge() {
        return edges.elements();
    }

    //添加一个顶点v
    public Node insert(Vertex v) {
        return vertexs.insertLast(v);
    }

    //添加一条边e
    public Node insert(Edge e) {
        return edges.insertLast(e);
    }

    //判断顶点u、v是否邻接，即是否有边从u到v
    public boolean areAdjacent(Vertex u, Vertex v) {
        return edgeFromTo(u, v) != null;
    }

    //删除一个顶点v
    public abstract void remove(Vertex v);

    //删除一条边e
    public abstract void remove(Edge e);

    //返回从u指向v的边，不存在则返回null
    public abstract Edge edgeFromTo(Vertex u, Vertex v);

    //返回从u出发可以直接到达的邻接顶点
    public abstract Iterator adjVertexs(Vertex u);

    //求无向图的最小生成树,如果是有向图不支持此操作
    public abstract void generateMST() throws UnsupportedOperation;

    //求有向图的拓扑序列,无向图不支持此操作
    public abstract Iterator toplogicalSort() throws UnsupportedOperation;

    //求有向无环图的关键路径,无向图不支持此操作
    public abstract void criticalPath() throws UnsupportedOperation;

    public void resetVexStatus() {
        Iterator iterator = getVertex();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            Vertex vertex = (Vertex) iterator.currentItem();
            vertex.resetStatus();
        }

    }

    public void resetEdgeType() {
        Iterator iterator = getEdge();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            Edge edge = (Edge) iterator.currentItem();
            edge.resetType();
        }
    }

    /**
     * 对图进行深度优先遍历
     *
     * @param v
     * @return
     */
    @Override
    public Iterator DFSTraverse(Vertex v) {
        LinkedList traverseSeq = new LinkedListDLNode();
        resetVexStatus();
        DFS(v, traverseSeq);
        Iterator iterator = getVertex();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            Vertex vertex = (Vertex) iterator.currentItem();
            if (!vertex.isVisited()) DFS(vertex, traverseSeq);
        }
        return traverseSeq.elements();
    }

    /**
     * 深度优先的非递归算法
     *
     * @param v
     * @param list
     */
    private void DFS(Vertex v, LinkedList list) {
        Stack s = new StackSLinked();
        s.push(s);
        while (!s.isEmpty()) {
            Vertex vertex = (Vertex) s.pop();
            if (!vertex.isVisited()) {
                vertex.setToVisited();
                list.insertLast(vertex);
                Iterator iterator = adjVertexs(v);
                for (iterator.first(); !iterator.isDone(); iterator.next()) {
                    Vertex currentVertex = (Vertex) iterator.currentItem();
                    if (!currentVertex.isVisited()) {
                        s.push(currentVertex);
                    }
                }
            }

        }
    }

    @Override
    public Iterator BFSTraverse(Vertex v) {
        LinkedListDLNode traverseSeq = new LinkedListDLNode();
        resetVexStatus();
        BFS(v, traverseSeq);
        Iterator iterator = getVertex();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            Vertex vertex = (Vertex) iterator.currentItem();
            if (!vertex.isVisited()) BFS(v, traverseSeq);
        }
        return traverseSeq.elements();
    }

    private void BFS(Vertex v, LinkedList list) {
        Queue q = new QueueSLinked();
        v.setToVisited();
        list.insertLast(v);
        q.enqueue(v);
        while (!q.isEmpty()) {
            Vertex u = (Vertex) q.dequeue();
            Iterator iterator = adjVertexs(u);
            for (iterator.first(); !iterator.isDone(); iterator.next()) {
                Vertex vertex = (Vertex) iterator.currentItem();
                if (!vertex.isVisited()) q.enqueue(vertex);
            }
        }
    }

    @Override
    public Iterator shortestPath(Vertex v) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * 求最短路径时，对v.application的操作
     *
     * @param v
     * @return
     */
    protected int getDistance(Vertex v) {
        return ((Path) v.getAppObj()).getDistance();
    }

    protected void setDistance(Vertex v, int dis) {
        ((Path) v.getAppObj()).setDistance(dis);
    }

    protected Path getPath(Vertex v) {
        return (Path) v.getAppObj();
    }

    protected void setPath(Vertex v, Path p) {
        v.setAppObj(p);
    }


}
