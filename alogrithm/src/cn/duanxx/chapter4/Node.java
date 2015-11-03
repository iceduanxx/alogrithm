package cn.duanxx.chapter4;

public class Node {
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x, y;
        public Node parent;

        public int G;
        public int H;
        public int F;

        public boolean equals(int x, int y) {
            return this.x == x && this.y == y;
        }
    }