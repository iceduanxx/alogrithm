package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ����5:19
 * To change this template use File | Settings | File Templates.
 */
public class Vertex {
    private Object info;        //������Ϣ
    private LinkedList adjacentEdges;    //������ڽӱ߱�
    private LinkedList reAdjacentEdges;    //��������ڽӱ߱�����ͼʱΪ��
    private boolean visited;    //����״̬
    private Node vexPosition;    //�����ڶ�����е�λ��
    private int graphType;        //��������ͼ������
    private Object application;    //Ӧ����Ϣ�����������·��ʱΪPath����ؼ�·��ʱΪVtime

    public Vertex(Object info, Graph graph) {
        this.info = info;
        this.adjacentEdges = new LinkedListDLNode();
        this.reAdjacentEdges = new LinkedListDLNode();
        this.visited = false;
        this.vexPosition = graph.insert(this);
        this.graphType = graph.getType();
        this.application = null;
    }

    //��������:�ж϶�������ͼ������
    private boolean isUnDiGraphNode() {
        return graphType == Graph.UndirectedGraph;
    }

    public int getDeg() {
        if (isUnDiGraphNode()) {
            return adjacentEdges.getSize();
        } else {
            return getInDeg() + getOutDeg();
        }
    }

    public LinkedList getAdjacentEdges() {
        return adjacentEdges;
    }

    public LinkedList getReAdjacentEdges() {
        if (isUnDiGraphNode()) {
            return adjacentEdges;
        } else {
            return reAdjacentEdges;
        }
    }

    public int getOutDeg() {
        return this.adjacentEdges.getSize();
    }

    public int getInDeg() {
        if (isUnDiGraphNode()) {
            return adjacentEdges.getSize();
        } else {
            return reAdjacentEdges.getSize();
        }
    }

    //��ȡ�����ö�����Ϣ
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object obj) {
        this.info = info;
    }


    //ȡ����������ͼ���㼯�е�λ��
    public Node getVexPosition() {
        return vexPosition;
    }

    //�붥�����״̬��ط���
    public boolean isVisited() {
        return visited;
    }

    public void setToVisited() {
        visited = true;
    }

    public void setToUnvisited() {
        visited = false;
    }

    //ȡ�����ö���Ӧ����Ϣ
    protected Object getAppObj() {
        return application;
    }

    protected void setAppObj(Object app) {
        application = app;
    }

    public void resetStatus() {
        visited = false;
        application = null;
    }


}
