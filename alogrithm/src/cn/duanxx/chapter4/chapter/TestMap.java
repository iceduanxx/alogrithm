/*
package cn.duanxx.chapter4.chapter;

import java.awt.Graphics;
import java.awt.Image;
import org.loon.framework.game.image.Bitmap;

*/
/** *//*
*/
/**
 * <p>
 * Title: LoonFramework
 * </p>
 * <p>
 * Description:一个简单的地图实现
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
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 *//*

public class TestMap {

    final static private int CS = 32;

    // 地图描述
    final static public int[][] MAP = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    // 无法移动区域
    final static public int[] HIT = { 1 };

    // 设定背景方格默认行数
    final static private int ROW = 15;

    // 设定背景方格默认列数
    final static private int COL = 15;

    private Image floorImage;

    private Image wallImage;

    public TestMap() {
        floorImage = new Bitmap("./astart/floor.gif").getImage();
        wallImage = new Bitmap("./astart/wall.gif").getImage();
    }

    public void draw(Graphics g) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                switch (MAP[i][j]) {
                case 0:
                    g.drawImage(floorImage, j * CS, i * CS, null);
                    break;
                case 1:
                    g.drawImage(wallImage, j * CS, i * CS, null);
                    break;
                }
            }
        }
    }

}*/
