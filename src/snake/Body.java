package snake;

public class Body {
	public static int grade;//得分
	//将画布作为一格一格的单元，对其进行动态上色处理形成移动效果
	protected Grid[][] grids;
	protected Grid head,end;//头尾单独处理
	protected int[] arry;//形如1010=>[10,10]

	//初始化
	public Body() {
		//		grade=0;//计分，此功能未实现
		int num=Panel.XY_num;
		grids=new Grid[num][num];
		arry=new int[num*num];
		//		为每个单元格初始化
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++){
				grids[i][j]=new Grid(i,j);
			}
		}	
		//		将头结点，尾节点定位
		head=grids[num/2][num/2];
		end=grids[num/2-1][num/2];
		//		初始化蛇的位置
		arry[1]=num/2*100+num/2;
		arry[2]=(num/2-1)*100+num/2;
		//		arry的第一个数据存放此时蛇的长度
		arry[0]=2;
	}

	//把尾部移动到头部之前，并重定向
	public void Move() {
		//		定义x，y移动后的位置
		int x=head.getX()+head.getDx();
		int y=head.getY()+head.getDy();
		int num=Panel.XY_num;
		//		如果上下左右出界则从另一端出现
		if(x<0)x=num-1;
		if(x>=num)x=0;
		if(y<0)y=num-1;
		if(y>=num)y=0;
		//		新的头部保持原来的运动趋势
		grids[x][y].setDx(head.getDx());
		grids[x][y].setDy(head.getDy());
		head=grids[x][y];
		//		使新头部可显示
		head.IsUser=true;
		//		数组代表snake的肢体顺序，移动后整体前移
		for(int i=arry[0];i>=2;i--){
			arry[i]=arry[i-1];
		}
		//		head指向头部
		arry[1]=head.getX()*100+head.getY();
		//		尾部改为不可见并指向move后的位置
		end.IsUser=false;
		end=grids[arry[arry[0]]/100][arry[arry[0]]%100];
	}

	//增长
	public void add(int xY) {
		//		将之前储存的尾部数据存到arry的最后一个单位，同时长度自加
		arry[arry[0]+1]=xY;
		arry[0]++;
		//		设置新的尾部指向
		end=grids[arry[arry[0]]/100][arry[arry[0]]%100];
		end.IsUser=true;
	}


}
class Grid{
	private int X;//基础的坐标，主要给head使用
	private int Y;
	private int dx,dy;//位移趋势
	public boolean IsUser;//确定这一格是否可见
	public Grid(int x,int y) {
		this.setX(x);
		this.setY(y);
		dx=1;
		dy=0;
		IsUser=false;
	}
	public Grid() {
		IsUser=false;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	} 

}

class Food{
	private int X;
	private int Y;
	public Food() {
		X=(int) (Math.random()*Panel.XY_num);
		Y=(int) (Math.random()*Panel.XY_num);
	}
	public void Rand(Body bodys){
		//		不能出现在蛇身体覆盖的区域内
		while(true){
			X=(int) (Math.random()*Panel.XY_num);
			Y=(int) (Math.random()*Panel.XY_num);
			if(!bodys.grids[X][Y].IsUser)
				return;
		}
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}



}
