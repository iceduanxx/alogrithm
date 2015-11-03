package cn.autumnstar.offer.bit;

public class BitCounts implements BitCountMethods {

	@Override
	public int normal(int x) {
		int c = 0;
		for (; x > 0; x >>>= 1) {
			c += x & 1; // 如果当前位是 1，计数器加 1
		}
		return c;
	}

	@Override
	public int quick(int x) {
		int c = 0;
		for (; x > 0; c++) {
			x &= (x - 1); // 清除最右边的 1
		}
		return c;
	}

	@Override
	public int static4bit(int x) {
		int[] table = { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4 };
		int c = 0;
		for (; x > 0; x >>>= 4) {
			c += table[x & 0xF];
		}

		return c;
	}

	@Override
	public int static8bit(int x) {
		int[] table = { 0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2,
				2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3,
				4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5,
				4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2,
				3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4,
				4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5,
				6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
				2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3,
				4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5,
				5, 6, 5, 6, 6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5,
				6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5,
				4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5,
				6, 6, 7, 6, 7, 7, 8, };
		int c = 0;
		for(; x > 0; x >>>= 8){
			c += table[x & 0xFF];
		}
		
		return c;
	}

	@Override
	public int parallel(int x) {
		// 0x55 = 0101 0101
		x = (x & 0x55555555) + ((x >>> 1) & 0x55555555);
		//0x33 = 0011 0011
		x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
		//0x0f = 0000 1111
		x = (x & 0x0f0f0f0f) + ((x >>> 4) & 0x0f0f0f0f);
		//0x00ff = 0000 0000 1111 1111
		x = (x & 0x00ff00ff) + ((x >>> 8) & 0x00ff00ff);
		//0x0000ffff = 0000 0000 0000 0000 1111 1111 1111 1111
		x = (x & 0x0000ffff) + ((x >>> 16) & 0x0000ffff);
		
		return x;
	}

	@Override
	public int perfectness(int x) {
		int temp = x - (x >>> 1) & 033333333333 - (x >>> 2) & 011111111111;
		return (temp +(temp >>>3)) & 030707070707 % 63;
	}


}
