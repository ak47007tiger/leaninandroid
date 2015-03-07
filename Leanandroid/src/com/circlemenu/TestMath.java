package com.circlemenu;



public class TestMath {

	public static void main(String[] args) {
		int r = 200;
		int i, cCount = 6;
		int length = (int) (2 * Math.PI * r / 6);
		for (i = 0; i < cCount; i++) {
			CirPoint cirPoint = new CirPoint(r, length * (i + 1));
			int[] layout = cirPoint.getLayout(50);
			System.out.println(layout[0] + "," + layout[1] + "," + layout[2] + "," + layout[3]);
		}
	}
}
