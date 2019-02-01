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
	//初始化声明
	Body bodys;
	Food food;
	Timer timer;
	public static int width=10;//每单元格宽
	public static int XY_num=50; //单元格数

	//初始化
	public Panel() {
		bodys=new Body();
		food=new Food();
	}
	//绘画
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.BLACK);//黑底
		g.setColor(Color.WHITE);//白色图形
		//绘制body
		paintBody(g);
		//绘制food
		paintFood(g);
	}
	private void paintFood(Graphics g) {
		g.setColor(Color.YELLOW);//黄色的食物
		g.fillRect(food.getX()*width, food.getY()*width, width, width);
		g.setColor(Color.WHITE);
	}
	private void paintBody(Graphics g) {
		//		主体部分
		for(int i=0;i<XY_num;i++){
			for(int j=0;j<XY_num;j++){
				if(bodys.grids[i][j].IsUser)
					g.fillRect(i*width, j*width, width, width);
				//				如果是头部，变红
				if(i==bodys.head.getX() && j==bodys.head.getY()){
					g.setColor(Color.RED);
					g.fillRect(i*width, j*width, width, width);
					g.setColor(Color.WHITE);
				}
			}
		}
	}
	public void action() {
		// 计时器初始化
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				// 向当前方向移动
				int xY=bodys.arry[bodys.arry[0]];//记录move操作前的尾部坐标
				bodys.Move();
				Judge(xY);//判断是否吃到和游戏是否结束
				// 重绘
				repaint();
			}
		}, 0, 100);//调整速度，此处可添加随时间加快的功能
		// 4.5监听键盘监听事件
		this.addKeyListener(new KeyAdapter() {
			// 内部方法：键盘按下
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				// 判断按键是否与系统按键值对应
				int type = e.getKeyCode();
				// 选择语句
				switch (type) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					//					向相反方向移动时拒绝
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
		//		获得焦点
		this.setFocusable(true);
		this.requestFocus();
	}
	protected void Judge(int xY) {
		//是否吃到食物
		if(bodys.head.getX()==food.getX() && bodys.head.getY()==food.getY()){
			food.Rand(bodys);
			bodys.add(xY);
		}
		//是否结束
		for(int i=2;i<bodys.arry[0];i++){
			if(bodys.arry[1]==bodys.arry[i]){
				try {
					Thread.sleep(1800);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				System.exit(0);
			}
		}

	}

}
