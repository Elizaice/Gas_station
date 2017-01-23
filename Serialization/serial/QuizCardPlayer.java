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
	//创建并显示gui
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
		//如果是个问题，显示答案，否则显示下一个问题
		//改一个标识表明我们已经浏览了问题或答案
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
		//生成一个文件对话框，让用户把一个卡片设置打开
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	
	//创建卡片的ArrayList,并从文本文件中读取他们
	//调用OpenMenuListener事件处理器，每次从文件读取一行
	//告诉makeCard()方法创建一个新卡片
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
	//调用loadFile，从文本文件中读取一行
	//创建一个新的QuizCard,通过调用CardList把他加入ArrayList中
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
