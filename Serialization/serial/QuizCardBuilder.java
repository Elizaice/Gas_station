package com.fubin.serial;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuizCardBuilder {
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	private ArrayList<QuizCard> cardList;
	
	public static void main(String[] args){
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
	}
	public void go(){
		frame = new JFrame("Quiz Card Builder");
		JPanel mainPanel = new JPanel();
		Font font = new Font("sanserif",Font.BOLD,24);
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(font);
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(font);
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("Next Card");
		cardList = new ArrayList<QuizCard>();
		
		JLabel qLabel = new JLabel("Question");
		JLabel aLabel = new JLabel("Answer");
		
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		
		//创建菜单，把new和save加到File下，然后指定frame使用这个菜单，菜单项目会触发ActionEvent
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener() );
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
//		JMenu editMenu = new JMenu("Edit");
//		JMenuItem deleteMenuItem = new JMenuItem("Delete");
//		JMenuItem pasteMenuItem = new JMenuItem("Paste");
//		editMenu.add(deleteMenuItem);
//		editMenu.add(pasteMenuItem);
//		menuBar.add(editMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
		
	}
	public class NextCardListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}

	}
	
	public class NewMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cardList.clear();
			clearCard();
		}
		
	}
	
	public class SaveMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			
			//调出存盘对话框，等待用户决定，这都是靠JFileChooser完成的
			JFileChooser fileSave = new JFileChooser();
			fileSave.showOpenDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
		
	}
	

	private void clearCard() {
		// TODO Auto-generated method stub
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	//实际编写文件的方法由SaveMenuListener的事件处理程序所调用
	private void saveFile(File file){
		try{
			//将BufferedWriter链接到Filewriter
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			//将ArrayList中的卡片逐个写到文件中，一行一张卡片，问题和答案由“/”分布
			for(QuizCard card:cardList){
				writer.write(card.getQuestion()+"/");
				writer.write(card.getAnswer()+"\n");
			}
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
