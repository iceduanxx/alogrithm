/*
package cn.duanxx.chapter4.chapter;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.loon.framework.game.image.Bitmap;

*/
/** *//*


*/
/**
 * <p>
 * Title: LoonFramework
 * </p>
 * <p>
 * Description:Java��A*Ѱ��ʵ��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: LoonFramework
 * </p>
 * <p>
 * License: http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 *
 * @author chenpeng
 * @version 0.1
 * @email��ceponline@yahoo.com.cn
 *//*

public class Main extends Panel {

    */
/** *//*

    */
/**
     *
     *//*

    final static private long serialVersionUID = 1L;

    final static private int WIDTH = 480;

    final static private int HEIGHT = 480;

    final static private int CS = 32;

    private TestMap map;

    private PathFinder astar;

    // ��ʼ����1,1
    private static Point START_POS = new Point(1, 1);

    // Ŀ������10,13
    private static Point OBJECT_POS = new Point(10, 13);

    private Image screen;

    private Graphics graphics;

    private List path;

    public Main() {

        setSize(WIDTH, HEIGHT);
        setFocusable(true);
        screen = new Bitmap(WIDTH, HEIGHT).getImage();
        graphics = screen.getGraphics();
        map = new TestMap();
        // ע���ͼ�������ϰ�������
        astar = new PathFinder(TestMap.MAP, TestMap.HIT);
        // searchPath�����������ƶ�·�������List����
        // ��ʵ��Ӧ���У�����Thread�ֲ�����List�����꼴��ʵ���Զ�����
        path = astar.searchPath(START_POS, OBJECT_POS);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        // ���Ƶ�ͼ
        map.draw(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(START_POS.x * CS, START_POS.y * CS, CS, CS);

        graphics.setColor(Color.BLUE);
        graphics.fillRect(OBJECT_POS.x * CS, OBJECT_POS.y * CS, CS, CS);

        // ����·��
        if (path != null) {
            graphics.setColor(Color.YELLOW);
            //�������꣬��һһ���
            for (int i = 0; i < path.size(); i++) {
                Node node = (Node) path.get(i);
                Point pos = node._pos;
                // ���߿�
                graphics.fillRect(pos.x * CS + 7, pos.y * CS + 7, CS - 14,
                        CS - 14);
            }
        }
        g.drawImage(screen, 0, 0, this);
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setTitle("Java��A*Ѱ��ʵ��");
        frame.setSize(WIDTH, HEIGHT + 20);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new Main());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}*/
