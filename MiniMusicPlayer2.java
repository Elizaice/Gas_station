package com.fubin.gui;

import java.awt.*;
import java.io.*;
import javax.sound.midi.*;
import javax.swing.*;

//���Ǳ���Ҫ����ControllerEvent,���ʵ������ӿ�
public class MiniMusicPlayer2  {

	static JFrame frame = new JFrame("my first music video");
	static MyDrawPanel ml;
	public static void main(String[] args){
		MiniMusicPlayer2 mini = new MiniMusicPlayer2();
		mini.go();
		
	}
	
	public void setUpGui(){
		ml = new MyDrawPanel();
		frame.setContentPane(ml);
		frame.setBounds(30, 30, 300, 300);
		frame.setVisible(true);
	}
	public void go(){
		setUpGui();
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			//��sequencerע���¼���ע��ķ���ȡ�ü������������Ҫ�������¼���int���飬����ֻ��Ҫ127�¼�
			
			sequencer.addControllerEventListener(ml, new int[] {127});
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			int r = 0;
			for(int i = 5; i < 61; i+=4){
				r = (int)(Math.random()*50+1);
				track.add(makeEvent(144,1,r,100,i));
				//�����¼����Ϊ127���Զ���ControllerEvent(176),���������κ����飬ֻ��������֪�������������ţ���Ϊ����tick��note on ��ͬʱ���е�
				track.add(makeEvent(176,1,127,0,i));
				track.add(makeEvent(128,1,r,100,i+2));
			}
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(120);
			sequencer.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a, tick);
		}catch(Exception e){
		}
		return event;
	}
	class MyDrawPanel extends JPanel implements ControllerEventListener{
		boolean msg = false;
		@Override
		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}
		public void paintComponent(Graphics g){
			if(msg){
				Graphics2D g2 = (Graphics2D) g;
				int r = (int)(Math.random()*255);
				int gr = (int)(Math.random()*255);
				int b = (int)(Math.random()*255);
				g.setColor(new Color(r,gr,b));
				
				int ht = (int)(Math.random()*120+10);
				int width = (int)(Math.random()*120+10);
				int x = (int)(Math.random()*40+10);
				int y = (int)(Math.random()*40+10);
				g.fillRect(x, y, width, ht);
				msg = false;
			}
		}
		
	}
}
