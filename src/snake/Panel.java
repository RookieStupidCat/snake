package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Panel extends JPanel{
	//��ʼ������
	Body bodys;
	Food food;
	Timer timer;
	public static int width=10;//ÿ��Ԫ���
	public static int XY_num=50; //��Ԫ����

	//��ʼ��
	public Panel() {
		bodys=new Body();
		food=new Food();
	}
	//�滭
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.BLACK);//�ڵ�
		g.setColor(Color.WHITE);//��ɫͼ��
		//����body
		paintBody(g);
		//����food
		paintFood(g);
	}
	private void paintFood(Graphics g) {
		g.setColor(Color.YELLOW);//��ɫ��ʳ��
		g.fillRect(food.getX()*width, food.getY()*width, width, width);
		g.setColor(Color.WHITE);
	}
	private void paintBody(Graphics g) {
		//		���岿��
		for(int i=0;i<XY_num;i++){
			for(int j=0;j<XY_num;j++){
				if(bodys.grids[i][j].IsUser)
					g.fillRect(i*width, j*width, width, width);
				//				�����ͷ�������
				if(i==bodys.head.getX() && j==bodys.head.getY()){
					g.setColor(Color.RED);
					g.fillRect(i*width, j*width, width, width);
					g.setColor(Color.WHITE);
				}
			}
		}
	}
	public void action() {
		// ��ʱ����ʼ��
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				// ��ǰ�����ƶ�
				int xY=bodys.arry[bodys.arry[0]];//��¼move����ǰ��β������
				bodys.Move();
				Judge(xY);//�ж��Ƿ�Ե�����Ϸ�Ƿ����
				// �ػ�
				repaint();
			}
		}, 0, 100);//�����ٶȣ��˴��������ʱ��ӿ�Ĺ���
		// 4.5�������̼����¼�
		this.addKeyListener(new KeyAdapter() {
			// �ڲ����������̰���
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				// �жϰ����Ƿ���ϵͳ����ֵ��Ӧ
				int type = e.getKeyCode();
				// ѡ�����
				switch (type) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					//					���෴�����ƶ�ʱ�ܾ�
					if(bodys.head.getDx()!=1){
						bodys.head.setDx(-1);
						bodys.head.setDy(0);
					}
					break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					if(bodys.head.getDx()!=-1){
						bodys.head.setDx(1);
						bodys.head.setDy(0);
					}
					break;
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					if(bodys.head.getDy()!=1){
						bodys.head.setDx(0);
						bodys.head.setDy(-1);
					}
					break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					if(bodys.head.getDy()!=-1){
						bodys.head.setDx(0);
						bodys.head.setDy(1);
					}
					break;
				default:
					break;
				}
			}
		});
		//		��ý���
		this.setFocusable(true);
		this.requestFocus();
	}
	protected void Judge(int xY) {
		//�Ƿ�Ե�ʳ��
		if(bodys.head.getX()==food.getX() && bodys.head.getY()==food.getY()){
			food.Rand(bodys);
			bodys.add(xY);
		}
		//�Ƿ����
		for(int i=2;i<bodys.arry[0];i++){
			if(bodys.arry[1]==bodys.arry[i]){
				try {
					Thread.sleep(1800);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				System.exit(0);
			}
		}

	}

}
