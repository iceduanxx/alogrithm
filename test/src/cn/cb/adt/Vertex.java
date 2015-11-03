package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class Vertex {
    private Object info;        //顶点信息
    private LinkedList adjacentEdges;    //顶点的邻接边表
    private LinkedList reAdjacentEdges;    //顶点的逆邻接边表，无向图时为空
    private boolean visited;    //访问状态
    private Node vexPosition;    //顶点在顶点表中的位置
    private int graphType;        //顶点所在图的类型
    private Object application;    //应用信息。例如求最短路径时为Path，求关键路径时为Vtime

    public Vertex(Object info, Graph graph) {
        this.info = info;
        this.adjacentEdges = new LinkedListDLNode();
        this.reAdjacentEdges = new LinkedListDLNode();
        this.visited = false;
        this.vexPosition = graph.insert(this);
        this.graphType = graph.getType();
        this.application = null;
    }

    //辅助方法:判断顶点所在图的类型
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

    //获取或设置顶点信息
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object obj) {
        this.info = info;
    }


    //取顶点在所属图顶点集中的位置
    public Node getVexPosition() {
        return vexPosition;
    }

    //与顶点访问状态相关方法
    public boolean isVisited() {
        return visited;
    }

    public void setToVisited() {
        visited = true;
    }

    public void setToUnvisited() {
        visited = false;
    }

    //取或设置顶点应用信息
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
