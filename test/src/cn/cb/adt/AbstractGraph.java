package cn.cb.adt;


import cn.cb.adt.exception.UnsupportedOperation;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: ����11:13
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGraph implements Graph {
    protected LinkedList vertexs;//�����
    protected LinkedList edges;    //�߱�
    protected int type;            //ͼ������

    public AbstractGraph(int type) {
        this.type = type;
        vertexs = new LinkedListDLNode();
        edges = new LinkedListDLNode();
    }

    //����ͼ������
    public int getType() {
        return type;
    }

    //����ͼ�Ķ�����
    public int getVexNum() {
        return vertexs.getSize();
    }

    //����ͼ�ı���
    public int getEdgeNum() {
        return edges.getSize();
    }

    //����ͼ�����ж���
    public Iterator getVertex() {
        return vertexs.elements();
    }

    //����ͼ�����б�
    public Iterator getEdge() {
        return edges.elements();
    }

    //���һ������v
    public Node insert(Vertex v) {
        return vertexs.insertLast(v);
    }

    //���һ����e
    public Node insert(Edge e) {
        return edges.insertLast(e);
    }

    //�ж϶���u��v�Ƿ��ڽӣ����Ƿ��бߴ�u��v
    public boolean areAdjacent(Vertex u, Vertex v) {
        return edgeFromTo(u, v) != null;
    }

    //ɾ��һ������v
    public abstract void remove(Vertex v);

    //ɾ��һ����e
    public abstract void remove(Edge e);

    //���ش�uָ��v�ıߣ��������򷵻�null
    public abstract Edge edgeFromTo(Vertex u, Vertex v);

    //���ش�u��������ֱ�ӵ�����ڽӶ���
    public abstract Iterator adjVertexs(Vertex u);

    //������ͼ����С������,���������ͼ��֧�ִ˲���
    public abstract void generateMST() throws UnsupportedOperation;

    //������ͼ����������,����ͼ��֧�ִ˲���
    public abstract Iterator toplogicalSort() throws UnsupportedOperation;

    //�������޻�ͼ�Ĺؼ�·��,����ͼ��֧�ִ˲���
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
     * ��ͼ����������ȱ���
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
     * ������ȵķǵݹ��㷨
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
     * �����·��ʱ����v.application�Ĳ���
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
