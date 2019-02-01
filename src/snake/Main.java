package snake;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		// 1.新建窗口
				JFrame frame = new JFrame("贪吃蛇");
				/**创建画布*/
				Panel t=new Panel();
				frame.add(t);
				// 2.窗口大小
				frame.setSize(Panel.XY_num*Panel.width,Panel.XY_num*Panel.width);
				// 3.居中显示
				frame.setLocationRelativeTo(null);
				//取消边框
				frame.setUndecorated(true);
				//9.设置窗口为最上层
				frame.setAlwaysOnTop(true);
				// 4.关闭模式
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// 5.始终最上方像是
				frame.setAlwaysOnTop(true);
				// 6.窗口显示
				frame.setVisible(true);
				//7.调用业务执行
				t.action();

	}

}
