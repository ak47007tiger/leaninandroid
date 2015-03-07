package com.circlemenu;


public class CirPoint {
		public CirPoint(int r, double length) {
			this.r = r;
			this.length = length;
			changeToNewPoint(length);
		}

		public CirPoint(int r, double length, int xCenter, int yCenter) {
			this.r = r;
			this.length = length;
			x = (int) (Math.cos(length) * r);
			y = (int) (Math.sin(length) * r);
		}

		int x, y, r, xCenter, yCenter;
		double length;

		public void changeToNewPoint(double length) {
			this.length = length;
			double tx = Math.cos(length / r) * r;
			double ty = Math.sin(length / r) * r;
			double dx = tx + r;
			double dy = r - ty;
			x = (int) (dx);
			y = (int) (dy);
		}
		public int[] getLayout(int size) {
			return new int[] { x, y, x + size, y + size };
		}
	}