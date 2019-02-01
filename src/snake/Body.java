package snake;

public class Body {
	public static int grade;//�÷�
	//��������Ϊһ��һ��ĵ�Ԫ��������ж�̬��ɫ�����γ��ƶ�Ч��
	protected Grid[][] grids;
	protected Grid head,end;//ͷβ��������
	protected int[] arry;//����1010=>[10,10]

	//��ʼ��
	public Body() {
		//		grade=0;//�Ʒ֣��˹���δʵ��
		int num=Panel.XY_num;
		grids=new Grid[num][num];
		arry=new int[num*num];
		//		Ϊÿ����Ԫ���ʼ��
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++){
				grids[i][j]=new Grid(i,j);
			}
		}	
		//		��ͷ��㣬β�ڵ㶨λ
		head=grids[num/2][num/2];
		end=grids[num/2-1][num/2];
		//		��ʼ���ߵ�λ��
		arry[1]=num/2*100+num/2;
		arry[2]=(num/2-1)*100+num/2;
		//		arry�ĵ�һ�����ݴ�Ŵ�ʱ�ߵĳ���
		arry[0]=2;
	}

	//��β���ƶ���ͷ��֮ǰ�����ض���
	public void Move() {
		//		����x��y�ƶ����λ��
		int x=head.getX()+head.getDx();
		int y=head.getY()+head.getDy();
		int num=Panel.XY_num;
		//		����������ҳ��������һ�˳���
		if(x<0)x=num-1;
		if(x>=num)x=0;
		if(y<0)y=num-1;
		if(y>=num)y=0;
		//		�µ�ͷ������ԭ�����˶�����
		grids[x][y].setDx(head.getDx());
		grids[x][y].setDy(head.getDy());
		head=grids[x][y];
		//		ʹ��ͷ������ʾ
		head.IsUser=true;
		//		�������snake��֫��˳���ƶ�������ǰ��
		for(int i=arry[0];i>=2;i--){
			arry[i]=arry[i-1];
		}
		//		headָ��ͷ��
		arry[1]=head.getX()*100+head.getY();
		//		β����Ϊ���ɼ���ָ��move���λ��
		end.IsUser=false;
		end=grids[arry[arry[0]]/100][arry[arry[0]]%100];
	}

	//����
	public void add(int xY) {
		//		��֮ǰ�����β�����ݴ浽arry�����һ����λ��ͬʱ�����Լ�
		arry[arry[0]+1]=xY;
		arry[0]++;
		//		�����µ�β��ָ��
		end=grids[arry[arry[0]]/100][arry[arry[0]]%100];
		end.IsUser=true;
	}


}
class Grid{
	private int X;//���������꣬��Ҫ��headʹ��
	private int Y;
	private int dx,dy;//λ������
	public boolean IsUser;//ȷ����һ���Ƿ�ɼ�
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
		//		���ܳ����������帲�ǵ�������
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
