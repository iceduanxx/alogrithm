package cn.autumnstar.offer.bit;

/**
 * 任意给定一个32位无符号整数n，求n的二进制表示中1的个数，比如n = 5（0101）时，返回2，n = 15（1111）时，返回4
 * 
 * @author vivizhyy
 * 
 */
public interface BitCountMethods {

	/** 移位+计数 */
	public int normal(int x);

	/** 不断清除x的二进制表示中最右边的1，同时累加计数器，直至x为0 */
	public int quick(int x);

	/**
	 * @see #static8bit(int)
	 */
	public int static4bit(int x);

	/**
	 * 首先构造一个包含256个元素的表table，table[i]即i中1的个数，这里的i是[0-255]之间任意一个值。
	 * 然后对于任意一个32bit无符号整数n
	 * ，我们将其拆分成四个8bit，然后分别求出每个8bit中1的个数，再累加求和即可，这里用移位的方法，每次右移8位
	 * ，并与0xff相与，取得最低位的8bit
	 * ，累加后继续移位，如此往复，直到n为0。所以对于任意一个32位整数，需要查表4次。以十进制数2882400018为例
	 * ，其对应的二进制数为10101011110011011110111100010010
	 * ，对应的四次查表过程如下：红色表示当前8bit，绿色表示右移后高位补零。
	 * 
	 * 第一次（n & 0xff） 10101011110011011110111100010010
	 * 
	 * 第二次（(n >> 8) & 0xff） 00000000101010111100110111101111
	 * 
	 * 第三次（(n >> 16) & 0xff）00000000000000001010101111001101
	 * 
	 * 第四次（(n >> 24) & 0xff）00000000000000000000000010101011
	 */
	public int static8bit(int x);

	/** 先将n写成二进制形式，然后相邻位相加，重复这个过程，直到只剩下一位。 
	 * 1 1  0 1  1 0  0 1
	 * \ /  \ /  \ /  \ /
	 *  2    1    1    1
	 *  \    /     \   /
	 *     3         2
	 *     \        /
	 *          5
	 */
	public int parallel(int x);
	
	/** http://www.cnblogs.com/graphics/archive/2010/06/21/1752421.html */
	public int perfectness(int x);

}
