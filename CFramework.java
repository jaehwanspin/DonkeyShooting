package Shooting20112858;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

/********************************
 * 
 * 		게임이 돌아가는 프레임웍 클래스
 *
 *******************************/

public class CFramework extends JFrame implements Runnable
{	
	/******************필드**********************/
	public Thread th; // 스레드 생성
	private ArrayList<CObject> objs;	//오브젝트를 담는 리스트
	public static int score = 0;	//점수 전역 필드
	public static int WoL = 0;	//이겼는지 졌는지 확인하는 전역필드 1이면 이김 2면 짐
	private int trgtScore = 300;
	
	/******************메소드*********************/
	CFramework()
	{
		setWnd();	//창의 기본적인 세팅
		init();		//필드들의 기본 세팅
		CSound.play("src\\Shooting20112858\\resource\\bgm.wav", true);	//BGM을 재생함
	}

	public void run() // 스레드가 무한 루프될 부분
	{
		try // 예외옵션 설정으로 무한루프 에러 방지
		{
			while(true) // while 문으로 무한 루프 시키기
			{
				update();
				repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
				Thread.sleep(20); // 20 milli sec 로 스레드 돌리기
			}
		}
		catch (Exception e){}
	}
	

	public void init()
	{
		objs = new ArrayList<CObject>();	//리스트의 객체를 초기화하고
		//플레이어를 추가함
		objs.add(new CPlayer(100, 100, true, "src\\Shooting20112858\\resource\\ass.png"));
		objs.add(new CEnemyTroop());
		
		th = new Thread(this);  // 스레드 생성
		th.start();  // 스레드 실행
	}
	
	//무한루프에서 실행될 업데이트메소드
	public void update()
	{
		for(int i = 0; i < objs.size(); i++)	//오브젝트들을 꺼내서 업데이트를 실행함
		{
			objs.get(i).update();
			if(!objs.get(i).getState()) remove(i);	//상태가 false면 제거
		}
		
		for(int i = 0; i < objs.get(1).child.size(); i++)
			CCollider.checkCollision(objs.get(0), objs.get(1).child.get(i));
		
		//충돌검사 루프
		/*for(int i = 0; i < objs.size(); i++)
		{
			for(int j = 0; j < objs.size(); j++)
			{
				int next = i < objs.size() ? i : i + 1;
				checkCollision(objs.get(i), objs.get(next).child.get(j));
			}
		}*/
		
		//점수가 300이면 WoL를 1로 설정
		if(score >= trgtScore)
		{
			WoL = 1;
			objs.clear();
		}
		//지면 오브젝트들을 모두 없앤다
		if(WoL == 2)
		{
			objs.clear();
		}
	}
		
	public void setWnd()
	{
		setTitle("당나귀슈팅");		//윈도우 타이틀 설정
		setSize(CGraphics.WIDTH, CGraphics.HEIGHT);	//윈도우 사이즈 설정
		
		//디멘션객체로 현재 스크린 사이즈를 구한 뒤
		Dimension screen = CGraphics.tk.getScreenSize();
		//윈도우를 창의 중앙에 놓도록함
		setLocation((int)(screen.getWidth() / 2 - CGraphics.WIDTH / 2),
				(int)(screen.getHeight() / 2 - CGraphics.HEIGHT / 2));
		
		setResizable(false);	//윈도우 사이즈 조절을 막는다
		setVisible(true);		//창을 화면에 띄움
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//X버튼을 마우스로 누르면 프로세스가 종료됨
		addKeyListener(new CInput()); //키보드 이벤트 실행	
	}
	
	public void paint(Graphics g)
	{
		//변경되는 좌표에 따라 이미지가 새로 그려짐
		CGraphics.buffImage = createImage(CGraphics.WIDTH, CGraphics.HEIGHT); 
		//더블버퍼링 버퍼 크기를 화면 크기와 같게 설정
		CGraphics.buffg = CGraphics.buffImage.getGraphics(); //버퍼의 그래픽 객체를 얻기
		//창에 있는 모든 그림들을 지움
		CGraphics.buffg.clearRect(0, 0, CGraphics.WIDTH, CGraphics.HEIGHT);
		
		//배경이미지를 그려줌
		Image img = CGraphics.tk.getImage("src\\Shooting20112858\\resource\\background.png");
		CGraphics.drawImage(img, 0, 0);
		
		//오브젝트들을 모두 꺼낸뒤에 화면에 그린다
		for(int i = 0; i < objs.size(); i++)
		{
			if(objs.get(i) != null)	//i번째 오브젝트가 null이 아니면
			{
				objs.get(i).drawImg();	//그린다
			}
		}
		if(WoL == 0)
		{
			CGraphics.drawText(trgtScore + "점을 달성해라", 70, 70, 20);	//화면에 게임목표를 표시함
			CGraphics.drawText("SCORE : "  + score, 1100, 70, 20);	//화면에 점수를 표시해줌
		}
		if(WoL == 1)
			CGraphics.drawText("-	이	김	-",
					(CGraphics.WIDTH / 2) - 65, (CGraphics.HEIGHT / 2) - 50, 50);
		else if(WoL == 2)
			CGraphics.drawText("-	짐	-",
					(CGraphics.WIDTH / 2) - 65, (CGraphics.HEIGHT / 2) - 50, 50);
				
		g.drawImage(CGraphics.buffImage, 0, 0, this);
	}
}