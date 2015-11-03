package cn.autumnstar.offer.dynamicProgramming.knapsack;

public class Knapsack {

    /**
     * ��������
     */
    private int weight;

    /**
     * ������Ʒ��ֵ
     */
    private int value;

    /**
     * ������
     */
    public Knapsack(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "[weight: " + weight + " " + "value: " + value + "]";
    }

}