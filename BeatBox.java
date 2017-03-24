package com.fubin.gui;

import javax.sound.midi.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BeatBox {
	JPanel jpanel;
	ArrayList<JCheckBox> checkboxList;
	Sequencer sequencer;
	Sequence seq ;
	Track track;
	JFrame frame;
	String[] instrumentNames = {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare",
								"Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas",
								"Whistle","Low Conga","Cowbell","Vibraslap","Low-mid Tom",
								"High Agogo","Open Hi Conga"};
	//ʵ�ʵ������ؼ��֣�����35��base,42��"Closed Hi-Hat
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	public static void main(String[] args){
		new BeatBox().buildGUI();
	}
	public void buildGUI(){
		frame = new JFrame("Cyber BeatBox");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel panel = new JPanel(layout);
		//�趨����ϰ������ʱ�Ŀհױ�Ե
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
		
		JButton start = new JButton("Start");
		start.addActionListener(new MystartListener());
		buttonBox.add(start);
		
		JButton stop = new JButton("Stop");
		stop.addActionListener(new MystopListener());
		buttonBox.add(stop);
		
		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i < 16; i++){
			nameBox.add(new Label(instrumentNames[i]));
		}
		panel.add(BorderLayout.EAST,buttonBox);
		panel.add(BorderLayout.WEST,nameBox);
		frame.getContentPane().add(panel);
		
		GridLayout grid = new GridLayout(16,16);
		//���ˮƽ����ֱ�Ų�
		grid.setVgap(1);
		grid.setHgap(2);
		jpanel = new JPanel(grid);
		panel.add(BorderLayout.CENTER,jpanel);
		for(int i = 0 ; i < 256; i++){
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			jpanel.add(c);
		}
		setUpMidi();
		frame.setBounds(50,50,300,300);
		frame.pack();
		frame.setVisible(true);
	}
	public void setUpMidi() {
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			seq = new Sequence(Sequence.PPQ, 4);
			track = seq.createTrack();
			sequencer.setTempoInBPM(120);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//�ص�������˴��Ὣ��ѡ��״̬ת��Ϊmidi�¼����ӵ�track��
	public void buildTrackAndStart(){
		//������16��Ԫ�ص��������洢һ��������ֵ������ý�Ӧ��Ҫ���࣬��ֵ�ǹؼ���ֵ����������
		int[] trackList = null;
		//������ɵ�track��һ���µ�
		seq.deleteTrack(track);
		track = seq.createTrack();
		//��ÿ������Ҫִ��һ��
		for(int i = 0 ; i < 16 ; i++){
			trackList = new int[16];
			//�趨���������Ĺؼ���
			int key = instruments[i];
			//ÿ��ִ��һ��
			for(int j = 0 ; j < 16 ; j ++){
				JCheckBox jc =(JCheckBox) (checkboxList.get(j+16*i));
				if(jc.isSelected()){
					trackList[j] = key;
				}else{
					trackList[j] = 0;
				}
			}
			//�������������¼����ӵ�track��
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
		}
		//ȷ����16�����¼�������beatbox�����ظ�����
		track.add(makeEvent(192,9,1,0,15));
		try{
			sequencer.setSequence(seq);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private MidiEvent makeEvent(int i, int j, int k, int l, int m) {
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(i,j,k,l);
			event = new MidiEvent(a,m);
		}catch(Exception e){
			e.printStackTrace();
		}
		return event;
	}
	//����ĳ�������������¼�
	
	public void makeTracks(int[] trackList) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 16; i ++){
			int key = trackList[i];
			if(key!= 0){
				//����note on ��note off �¼������뵽track��
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}
	//��һ���ڲ��࣬��ť�ļ�����
	public class MystartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			buildTrackAndStart();

		}

	}
	//��һ���ڲ��࣬Ҳ�ǰ�ť�ļ�����
	public class MystopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sequencer.stop();
		}

	}
	//�������ӣ�Ԥ��Ϊ1.0��ÿ�ε���3%
	public class MyUpTempoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor*1.03));
		}

	}
	public class MyDownTempoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			float tempoFactor = sequencer.getTempoFactor();
			sequencer.setTempoFactor((float) (tempoFactor*0.97));

		}

	}

}
