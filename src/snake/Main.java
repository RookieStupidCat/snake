package snake;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		// 1.�½�����
				JFrame frame = new JFrame("̰����");
				/**��������*/
				Panel t=new Panel();
				frame.add(t);
				// 2.���ڴ�С
				frame.setSize(Panel.XY_num*Panel.width,Panel.XY_num*Panel.width);
				// 3.������ʾ
				frame.setLocationRelativeTo(null);
				//ȡ���߿�
				frame.setUndecorated(true);
				//9.���ô���Ϊ���ϲ�
				frame.setAlwaysOnTop(true);
				// 4.�ر�ģʽ
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// 5.ʼ�����Ϸ�����
				frame.setAlwaysOnTop(true);
				// 6.������ʾ
				frame.setVisible(true);
				//7.����ҵ��ִ��
				t.action();

	}

}
