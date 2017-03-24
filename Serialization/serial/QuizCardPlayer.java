package com.fubin.serial;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuizCardPlayer {
	private JTextArea display;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	public static void main(String[] args){
		QuizCardPlayer reader = new QuizCardPlayer();
		reader.go();
	}
	//��������ʾgui
	public void go(){
		frame = new JFrame("Quiz Card Player");
		JPanel mainPanel = new JPanel();
		Font font = new Font("sanserif",Font.BOLD,24);
		display = new JTextArea(10,20);
		display.setLineWrap(true);
		display.setEditable(false);
		display.setFont(font);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		nextButton = new JButton("Show Question");
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load card set");
		loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640,500);
		frame.setVisible(true);
	}
	
	class NextCardListener implements ActionListener{
		@Override
		//����Ǹ����⣬��ʾ�𰸣�������ʾ��һ������
		//��һ����ʶ���������Ѿ������������
		public void actionPerformed(ActionEvent e) {
			if(isShowAnswer){
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			}else{
				if(currentCardIndex < cardList.size())
					{
						showNextCard();
					}
				else{
						display.setText("That was last card!");
						nextButton.setEnabled(false);
					}
				}
		}
	}
	
	class OpenMenuListener implements ActionListener{
		@Override
		//����һ���ļ��Ի������û���һ����Ƭ���ô�
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	
	//������Ƭ��ArrayList,�����ı��ļ��ж�ȡ����
	//����OpenMenuListener�¼���������ÿ�δ��ļ���ȡһ��
	//����makeCard()��������һ���¿�Ƭ
	private void loadFile(File file){
		cardList = new ArrayList<QuizCard>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine())!=null){
				makeCard(line);
			}
			reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//����loadFile�����ı��ļ��ж�ȡһ��
	//����һ���µ�QuizCard,ͨ������CardList��������ArrayList��
	private void makeCard(String lineToParse){
		String[] result = lineToParse.split("/");
		QuizCard card = new QuizCard(result[0], result[1]);
		cardList.add(card);
		System.out.println("made a card");
	}
	private void showNextCard(){
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShowAnswer = true;
	}
}
