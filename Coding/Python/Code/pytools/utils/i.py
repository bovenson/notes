# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc

i_s = """
package com.neu.nsr.cloudsecurity.demoapp.client;

import com.neu.nsr.cloudsecurity.demoapp.dboperation.DbOperation;
import com.neu.nsr.cloudsecurity.demoapp.pojo.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ClientWindowBack implements ActionListener {

	/** 演示用变量 */ 
	
	/** 操作数据库用 */
	private DbOperation dbOperation = null;
	
	/** 虚拟机ID */
	String vmId1 = null;
	String vmId2 = null;
	String vmId3 = null;
	
	/** 演示主体程序 */
	int subjectorAppId;
	Subjector subjectorApp = null;
	
	/** 程序级演示 */
	Objector programLevelObjector1 = null;
	Objector programLevelObjector2 = null;
	
	/** 系统级演示 */
	int ftpServerPort = 21;
	String ftpServerIp = null;
	String ftpUserName = null;
	String ftpUserPasswd = null;
	Objector systemLevelObjector1 = null;
	Objector systemLevelObjector2 = null;

	/** 网络级演示 */
	String netlevelLocalIpAddress = null;
	String netLevelDestIpAddress = null;
	int netLevelDestPort= 8085;
	Objector netLevelObjector1 = null;
	Objector netLevelObjector2 = null;

	// boolean sendFileFlag1 = false, sendFileFlag2 = false;
	ClientThread sendFileThread1, sendFileThread2;
	
	
	private JFrame frmapp;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textAreaSystem;
	
	JButton openFileBtn, clearTextAreaBtnProgram, clearAreaTextNetworkBtn, sendFileBtn1, sendFileBtn2;
	JButton btnNewButton_3, button_1, clearTextAreaBtnSystemLevel, programLevelOpenFileBtn1;
	JButton programLevelOpenFileBtn2;
	private JTextArea programShowTextArea, textAreaNetwork;
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindowBack window = new ClientWindowBack();
					window.frmapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 从配置文件读取配置
	 */
	private void loadConfig() {
		int tId;
		Document doc = null;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(new File("config.xml"));
		} catch (Exception e) {
			try {
				doc = reader.read(new File("resource/config.xml")); 
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "没找到配置文件,请将配置文件config.xml和程序放在同一文件夹.");
			}
		}
		Element rootEl = doc.getRootElement();
		
		this.vmId1 = rootEl.element("vmid").element("vmid1").getText().trim();
		this.vmId2 = rootEl.element("vmid").element("vmid2").getText().trim();
		this.vmId3 = rootEl.element("vmid").element("vmid3").getText().trim();
		
		tId = Integer.valueOf(rootEl.element("subjectorappid").getText().trim());
		this.subjectorApp = this.dbOperation.subjectorMapper.selectByPrimaryKey(tId);
		
		tId = Integer.valueOf(rootEl.element("programlevel").element("objector1").getText().trim());
		this.programLevelObjector1 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);
		tId = Integer.valueOf(rootEl.element("programlevel").element("objector2").getText().trim());
		this.programLevelObjector2 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);
		
		this.ftpServerIp = rootEl.element("systemlevel").element("ftp").element("serverip").getText().trim();
		this.ftpUserName = rootEl.element("systemlevel").element("ftp").element("username").getText().trim();
		this.ftpUserPasswd = rootEl.element("systemlevel").element("ftp").element("password").getText().trim();
		this.ftpServerPort = Integer.valueOf(rootEl.element("systemlevel").element("ftp").element("serverport").getText().trim());
		tId = Integer.valueOf(rootEl.element("systemlevel").element("objector1").getText().trim());
		this.systemLevelObjector1 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);
		tId = Integer.valueOf(rootEl.element("systemlevel").element("objector2").getText().trim());
		this.systemLevelObjector2 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);

		this.netlevelLocalIpAddress = rootEl.element("netlevel").element("localip").getText().trim();
		this.netLevelDestIpAddress = rootEl.element("netlevel").element("remoteip").getText().trim();
		this.netLevelDestPort = Integer.valueOf(rootEl.element("netlevel").element("serverport").getText().trim());
		tId = Integer.valueOf(rootEl.element("netlevel").element("objector1").getText().trim());
		this.netLevelObjector1 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);
		tId = Integer.valueOf(rootEl.element("netlevel").element("objector2").getText().trim());
		this.netLevelObjector2 = this.dbOperation.objectorMapper.selectByPrimaryKey(tId);
	}

	/**
	 * Create the application.
	 */
	public ClientWindowBack() {
		// 初始化数据库操作对象
		dbOperation = new DbOperation();
		// 从配置文件加载配置
		loadConfig();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmapp = new JFrame();
		frmapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmapp.setTitle("\u4E91\u8FFD\u8D23\u7CFB\u7EDF\u6A21\u62DF\u4E3B\u4F53app");
		frmapp.setSize(new Dimension(800, 600));
		frmapp.setResizable(false);
		frmapp.setBounds(100, 100, 800, 600);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frmapp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("\u7A0B\u5E8F\u7EA7\u6F14\u793A", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 789, 165);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		openFileBtn = new JButton("\u6253\u5F00\u6587\u4EF6");
		openFileBtn.setVisible(false);
		openFileBtn.addActionListener(this);
		openFileBtn.setBounds(607, 6, 93, 23);
		panel_4.add(openFileBtn);
		
		JLabel programLevelVmInfoLabel = new JLabel("\u865A\u62DF\u673A1(\u672C\u5730\u865A\u62DF\u673A) ID: ");
		programLevelVmInfoLabel.setBounds(10, 10, 690, 15);
		panel_4.add(programLevelVmInfoLabel);
		
		JLabel programLevelObejctorInfo1 = new JLabel("\u5BA2\u4F53\u6587\u4EF61: ");
		programLevelObejctorInfo1.setBounds(10, 60, 463, 15);
		panel_4.add(programLevelObejctorInfo1);
		
		JLabel programLevelObejctorInfo2 = new JLabel("客体文件2: ");
		programLevelObejctorInfo2.setBounds(10, 85, 442, 15);
		panel_4.add(programLevelObejctorInfo2);
		
		programLevelOpenFileBtn1 = new JButton("\u8BFB\u53D6");
		programLevelOpenFileBtn1.addActionListener(this);
		programLevelOpenFileBtn1.setBounds(489, 57, 69, 20);
		panel_4.add(programLevelOpenFileBtn1);
		
		programLevelOpenFileBtn2 = new JButton("\u8BFB\u53D6");
		programLevelOpenFileBtn2.addActionListener(this);
		programLevelOpenFileBtn2.setBounds(489, 82, 69, 20);
		panel_4.add(programLevelOpenFileBtn2);
		
		JLabel programLevelSubjectorInfoLabel = new JLabel("\u4E3B\u4F53\u6587\u4EF6: ");
		programLevelSubjectorInfoLabel.setBounds(10, 35, 260, 15);
		panel_4.add(programLevelSubjectorInfoLabel);
		
		JLabel lblNewLabel = new JLabel(" \u64CD\u4F5C\u4FE1\u606F\u5C55\u793A");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 4f));
		lblNewLabel.setBounds(0, 179, 262, 15);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 204, 789, 338);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 789, 338);
		panel_3.add(scrollPane_1);
		
		programShowTextArea = new JTextArea();
		scrollPane_1.setViewportView(programShowTextArea);
		programShowTextArea.setEditable(false);
		programShowTextArea.setAutoscrolls(false);
		programShowTextArea.setLineWrap(true);
		programShowTextArea.setWrapStyleWord(true);
		
		clearTextAreaBtnProgram = new JButton("\u6E05\u7A7A\u4FE1\u606F\u5C55\u793A\u533A");
		clearTextAreaBtnProgram.addActionListener(this);
		clearTextAreaBtnProgram.setBounds(658, 175, 131, 23);
		panel.add(clearTextAreaBtnProgram);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("\u7CFB\u7EDF\u7EA7\u6F14\u793A", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 789, 165);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel vmIdLabel1 = new JLabel("\u865A\u62DF\u673A1(\u672C\u5730\u865A\u62DF\u673A) ID: ");
		vmIdLabel1.setBounds(10, 2, 690, 15);
		panel_5.add(vmIdLabel1);
		
		JLabel systemLevelSubjectorInfoLabel = new JLabel("\u4E3B\u4F53\u6587\u4EF6: ");
		systemLevelSubjectorInfoLabel.setBounds(10, 22, 218, 15);
		panel_5.add(systemLevelSubjectorInfoLabel);
		
		JLabel label_1 = new JLabel("\u4E3B\u4F53\u6807\u8BB0:");
		label_1.setBounds(10, 42, 59, 15);
		panel_5.add(label_1);
		
		JLabel vmIdLabel2 = new JLabel("\u865A\u62DF\u673A2(\u672C\u5730\u865A\u62DF\u673A) ID:");
		vmIdLabel2.setBounds(10, 65, 527, 15);
		panel_5.add(vmIdLabel2);
		
		JLabel systemLevelObjectorInfoLabel1 = new JLabel("\u5BA2\u4F53\u6587\u4EF61:");
		systemLevelObjectorInfoLabel1.setBounds(10, 85, 218, 15);
		panel_5.add(systemLevelObjectorInfoLabel1);
		
		JLabel systemLevelObjectorInfoLabel2 = new JLabel("\u5BA2\u4F53\u6587\u4EF62:");
		systemLevelObjectorInfoLabel2.setBounds(10, 125, 218, 15);
		panel_5.add(systemLevelObjectorInfoLabel2);
		
		JLabel subjectorSouerVmIdLabel = new JLabel("AbCDAbCDAbCDAbCD");
		subjectorSouerVmIdLabel.setBounds(77, 42, 308, 15);
		panel_5.add(subjectorSouerVmIdLabel);
		
		JLabel label_8 = new JLabel("---");
		label_8.setBounds(380, 42, 18, 15);
		panel_5.add(label_8);
		
		JLabel subjectorDestVmIdLabel = new JLabel("");
		subjectorDestVmIdLabel.setBounds(407, 42, 382, 15);
		panel_5.add(subjectorDestVmIdLabel);
		
		JLabel label_4 = new JLabel("\u5BA2\u4F53\u6807\u8BB01:");
		label_4.setBounds(10, 105, 69, 15);
		panel_5.add(label_4);
		
		JLabel objectorSourceVmId1 = new JLabel("");
		objectorSourceVmId1.setBounds(77, 105, 308, 15);
		panel_5.add(objectorSourceVmId1);
		
		JLabel label_10 = new JLabel("---");
		label_10.setBounds(380, 105, 18, 15);
		panel_5.add(label_10);
		
		JLabel objectorDestVmId1 = new JLabel("");
		objectorDestVmId1.setBounds(407, 105, 382, 15);
		panel_5.add(objectorDestVmId1);
		
		JLabel label_12 = new JLabel("\u5BA2\u4F53\u6807\u8BB02:");
		label_12.setBounds(10, 145, 69, 15);
		panel_5.add(label_12);
		
		JLabel objectorSourceVmId2 = new JLabel("");
		objectorSourceVmId2.setBounds(77, 145, 308, 15);
		panel_5.add(objectorSourceVmId2);
		
		JLabel label_14 = new JLabel("---");
		label_14.setBounds(380, 145, 18, 15);
		panel_5.add(label_14);
		
		JLabel objectorDestVmId2 = new JLabel("");
		objectorDestVmId2.setBounds(407, 145, 382, 15);
		panel_5.add(objectorDestVmId2);
		
		btnNewButton_3 = new JButton("\u8BFB\u53D6");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(720, 82, 69, 20);
		panel_5.add(btnNewButton_3);
		
		button_1 = new JButton("\u8BFB\u53D6");
		button_1.addActionListener(this);
		button_1.setBounds(720, 122, 69, 20);
		panel_5.add(button_1);
		
		JLabel lblNewLabel_1 = new JLabel(" \u64CD\u4F5C\u4FE1\u606F\u5C55\u793A");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getStyle() | Font.BOLD, lblNewLabel_1.getFont().getSize() + 4f));
		lblNewLabel_1.setBounds(0, 179, 262, 15);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 204, 789, 338);
		panel_1.add(panel_6);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 789, 338);
		panel_6.add(scrollPane);
		
		textAreaSystem = new JTextArea();
		scrollPane.setViewportView(textAreaSystem);
		textAreaSystem.setEditable(false);
		
		clearTextAreaBtnSystemLevel = new JButton("\u6E05\u7A7A\u4FE1\u606F\u5C55\u793A\u533A");
		clearTextAreaBtnSystemLevel.addActionListener(this);
		clearTextAreaBtnSystemLevel.setBounds(658, 175, 131, 23);
		panel_1.add(clearTextAreaBtnSystemLevel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("\u7F51\u7EDC\u7EA7\u6F14\u793A", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(0, 0, 789, 165);
		panel_2.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel netLevelLocalIpLabel = new JLabel("\u865A\u62DF\u673A1(\u672C\u5730) IP:");
		netLevelLocalIpLabel.setBounds(10, 10, 344, 15);
		panel_7.add(netLevelLocalIpLabel);
		
		JLabel netLevelRemoteIpLabel = new JLabel("\u865A\u62DF\u673A2(\u8FDC\u7A0B) IP:");
		netLevelRemoteIpLabel.setBounds(356, 10, 344, 15);
		panel_7.add(netLevelRemoteIpLabel);
		
		JLabel netLevelObjectorInfoLabel1 = new JLabel("\u5BA2\u4F53\u6587\u4EF61(\u672C\u5730):");
		netLevelObjectorInfoLabel1.setBounds(10, 49, 344, 15);
		panel_7.add(netLevelObjectorInfoLabel1);
		
		JLabel netLevelObjSourceIp1 = new JLabel("\u5BA2\u4F53\u6807\u8BB01: \u8D77\u59CBIP\u8303\u56F4: ");
		netLevelObjSourceIp1.setBounds(10, 74, 403, 15);
		panel_7.add(netLevelObjSourceIp1);
		
		JLabel lblNewLabel_5 = new JLabel("\u53D1\u9001\u9891\u7387(\u79D2):");
		lblNewLabel_5.setBounds(484, 49, 88, 15);
		panel_7.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setBounds(566, 49, 56, 18);
		textField.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9){ 
                    e.consume(); //关键，屏蔽掉非法输入  
                }
            }  
        });
		panel_7.add(textField);
		textField.setColumns(10);
		
		sendFileBtn1 = new JButton("\u53D1\u9001");
		sendFileBtn1.addActionListener(this);
		sendFileBtn1.setBounds(721, 45, 68, 23);
		panel_7.add(sendFileBtn1);
		
		JLabel netLevelObjectorInfoLabel2 = new JLabel("\u5BA2\u4F53\u6587\u4EF62(\u672C\u5730):");
		netLevelObjectorInfoLabel2.setBounds(10, 112, 344, 15);
		panel_7.add(netLevelObjectorInfoLabel2);
		
		JLabel label_20 = new JLabel("\u53D1\u9001\u9891\u7387(\u79D2):");
		label_20.setBounds(484, 112, 88, 15);
		panel_7.add(label_20);
		
		textField_1 = new JTextField();
		textField_1.setText("5");
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9){ 
                    e.consume(); //关键，屏蔽掉非法输入  
                }
            }  
        });
		textField_1.setBounds(566, 112, 56, 18);
		panel_7.add(textField_1);
		
		sendFileBtn2 = new JButton("\u53D1\u9001");
		sendFileBtn2.addActionListener(this);
		sendFileBtn2.setBounds(721, 108, 68, 23);
		panel_7.add(sendFileBtn2);
		
		JLabel netLevelObjDestIp1 = new JLabel("\u76EE\u7684IP\u8303\u56F4:");
		netLevelObjDestIp1.setBounds(412, 74, 377, 15);
		panel_7.add(netLevelObjDestIp1);
		
		JLabel netLevelObjSourceIp2 = new JLabel("\u5BA2\u4F53\u6807\u8BB02: \u8D77\u59CBIP\u8303\u56F4: ");
		netLevelObjSourceIp2.setBounds(10, 140, 403, 15);
		panel_7.add(netLevelObjSourceIp2);
		
		JLabel netLevelObjDestIp2 = new JLabel("\u76EE\u7684IP\u8303\u56F4:");
		netLevelObjDestIp2.setBounds(412, 139, 377, 15);
		panel_7.add(netLevelObjDestIp2);
		
		JLabel lblNewLabel_2 = new JLabel(" \u64CD\u4F5C\u4FE1\u606F\u5C55\u793A");
		lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(lblNewLabel_2.getFont().getStyle() | Font.BOLD, lblNewLabel_2.getFont().getSize() + 4f));
		lblNewLabel_2.setBounds(0, 179, 262, 15);
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 204, 789, 338);
		panel_2.add(panel_8);
		panel_8.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 789, 339);
		panel_8.add(scrollPane_2);
		
		textAreaNetwork = new JTextArea();
		scrollPane_2.setViewportView(textAreaNetwork);
		textAreaNetwork.setLineWrap(true);
		textAreaNetwork.setEditable(false);
		
		clearAreaTextNetworkBtn = new JButton("\u6E05\u7A7A\u4FE1\u606F\u5C55\u793A\u533A");
		clearAreaTextNetworkBtn.addActionListener(this);
		clearAreaTextNetworkBtn.setBounds(658, 175, 131, 23);
		panel_2.add(clearAreaTextNetworkBtn);
		frmapp.getContentPane().setLayout(groupLayout);
		
		// TODO
		// 设置界面显示数据
		// 程序级演示界面
		programLevelVmInfoLabel.setText(programLevelVmInfoLabel.getText() + this.vmId1);
		
		if (this.subjectorApp == null) {
			this.appendShowInfo("主体文件不存在!", this.programShowTextArea);
			this.appendShowInfo("主体文件不存在!", this.textAreaSystem);
			programLevelSubjectorInfoLabel.setText(programLevelSubjectorInfoLabel.getText() + "不存在!");
			systemLevelSubjectorInfoLabel.setText(systemLevelSubjectorInfoLabel.getText() + "不存在");
		} else {
			programLevelSubjectorInfoLabel.setText(programLevelSubjectorInfoLabel.getText() + this.getFile(this.subjectorApp).getPath());
			systemLevelSubjectorInfoLabel.setText(systemLevelSubjectorInfoLabel.getText() + this.getFile(this.subjectorApp).getPath());
		}
		if (this.programLevelObjector1 == null) {
			this.appendShowInfo("客体文件1不存在!", this.programShowTextArea);
			programLevelObejctorInfo1.setText(programLevelObejctorInfo1.getText() + "不存在!");
		} else {
			programLevelObejctorInfo1.setText(programLevelObejctorInfo1.getText() + this.getFile(this.programLevelObjector1).getPath());
		}
		if (this.programLevelObjector2 == null) {
			this.appendShowInfo("客体文件2不存在!", this.programShowTextArea);
			programLevelObejctorInfo2.setText(programLevelObejctorInfo2.getText() + "不存在!");
		} else {
			programLevelObejctorInfo2.setText(programLevelObejctorInfo2.getText() + this.getFile(this.programLevelObjector2).getPath());
		}
		
		/** 系统级 */
		if (this.systemLevelObjector1 == null) {
			this.appendShowInfo("客体文件1不存在!", this.programShowTextArea);
			systemLevelObjectorInfoLabel1.setText(systemLevelObjectorInfoLabel1.getText() + "不存在!");
		} else {
			systemLevelObjectorInfoLabel1.setText(systemLevelObjectorInfoLabel1.getText() + this.getFile(this.systemLevelObjector1).getPath());
		}
		if (this.systemLevelObjector2 == null) {
			this.appendShowInfo("客体文件2不存在!", this.programShowTextArea);
			systemLevelObjectorInfoLabel2.setText(systemLevelObjectorInfoLabel2.getText() + "不存在!");
		} else {
			systemLevelObjectorInfoLabel2.setText(systemLevelObjectorInfoLabel2.getText() + this.getFile(this.systemLevelObjector2).getPath());
		}
		
		/** 网络级演示 */
		netLevelLocalIpLabel.setText(netLevelLocalIpLabel.getText() + this.netlevelLocalIpAddress);
		netLevelRemoteIpLabel.setText(netLevelRemoteIpLabel.getText() + this.netLevelDestIpAddress);
		subjectorSouerVmIdLabel.setText(vmId1);
		subjectorDestVmIdLabel.setText(vmId2);
		objectorSourceVmId1.setText(vmId1);
		objectorDestVmId1.setText(vmId2);
		objectorSourceVmId2.setText(vmId3);
		objectorDestVmId2.setText(vmId2);
		netLevelObjectorInfoLabel1.setText(netLevelObjectorInfoLabel1.getText() + this.getFile(this.netLevelObjector1).getPath());
		netLevelObjectorInfoLabel2.setText(netLevelObjectorInfoLabel2.getText() + this.getFile(this.netLevelObjector2).getPath());		
		// 标签
		ObjectorStampExample osExample1 = new ObjectorStampExample();
		osExample1.or().andObjectorIdEqualTo(this.netLevelObjector1.getId());
		List<ObjectorStamp> objectorStamps = this.dbOperation.objectorStampMapper.selectByExample(osExample1);
		if (objectorStamps.size() > 0) {
			ObjectorStamp objectorStamp = objectorStamps.get(0);
			StampTagExample stExample = new StampTagExample();
			stExample.or().andStampIdEqualTo(objectorStamp.getStampId());
			List<StampTag> stampTags = this.dbOperation.stampTagMapper.selectByExample(stExample);
			if (stampTags.size() > 0) {
				StampTag st = stampTags.get(0);
				Tag tag = this.dbOperation.tagMapper.selectByPrimaryKey(st.getTagId());
				netLevelObjSourceIp1.setText(netLevelObjSourceIp1.getText() + tag.getSourceIpStart() + " - " + tag.getSourceIpEnd());
				netLevelObjDestIp1.setText(netLevelObjDestIp1.getText() + tag.getDestIpStart() + " - " + tag.getDestIpEnd());
			} else {
				netLevelObjSourceIp1.setText(netLevelObjSourceIp1.getText() + "没有标签!");
				netLevelObjDestIp1.setText(netLevelObjDestIp1.getText() + "没有标签!");
			}
		} else {
			netLevelObjSourceIp1.setText(netLevelObjSourceIp1.getText() + "没有标签!");
			netLevelObjDestIp1.setText(netLevelObjDestIp1.getText() + "没有标签!");
		}
		
		ObjectorStampExample osExample2 = new ObjectorStampExample();
		osExample2.or().andObjectorIdEqualTo(this.netLevelObjector2.getId());
		objectorStamps = this.dbOperation.objectorStampMapper.selectByExample(osExample2);
		if (objectorStamps.size() > 0) {
			ObjectorStamp objectorStamp = objectorStamps.get(0);
			StampTagExample stExample = new StampTagExample();
			stExample.or().andStampIdEqualTo(objectorStamp.getStampId());
			List<StampTag> stampTags = this.dbOperation.stampTagMapper.selectByExample(stExample);
			if (stampTags.size() > 0) {
				StampTag st = stampTags.get(0);
				Tag tag = this.dbOperation.tagMapper.selectByPrimaryKey(st.getTagId());
				// System.out.println(tag.getId());
				try {
					netLevelObjSourceIp2.setText(netLevelObjSourceIp2.getText() + tag.getSourceIpStart() + " - " + tag.getSourceIpEnd());
					netLevelObjDestIp2.setText(netLevelObjDestIp2.getText() + tag.getDestIpStart() + " - " + tag.getDestIpEnd());
				} catch (Exception e) {
					
				}
			} else {
				netLevelObjSourceIp2.setText(netLevelObjSourceIp2.getText() + "没有标签!");
				netLevelObjDestIp2.setText(netLevelObjDestIp2.getText() + "没有标签!");
			}
		} else {
			netLevelObjSourceIp2.setText(netLevelObjSourceIp2.getText() + "没有标签!");
			netLevelObjDestIp2.setText(netLevelObjDestIp2.getText() + "没有标签!");
		}
		
		vmIdLabel1.setText(vmIdLabel1.getText() + this.vmId1);
		vmIdLabel2.setText(vmIdLabel2.getText() + this.vmId2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.programLevelOpenFileBtn1) {
			// TODO
			/** 程序级演示 打开客体文件一 */
			this.openProgramLevelFile(this.programLevelObjector1);
		} else if (e.getSource() == this.programLevelOpenFileBtn2) {
			/** 程序级演示 打开客体文件二 */
			this.openProgramLevelFile(this.programLevelObjector2);
		} else if (e.getSource() == this.btnNewButton_3) {
			/** 系统级演示 打开第一个客体文件 */
			this.openSystemLevelFile(this.systemLevelObjector1);
		} else if (e.getSource() == this.button_1) {
			/** 系统级演示 打开第二个客体文件 */
			this.openSystemLevelFile(this.systemLevelObjector2);
		} else if (e.getSource() == this.clearTextAreaBtnSystemLevel) {
			/** 系统级演示 清空信息显示区*/
			this.textAreaSystem.setText("");
		} else if (e.getSource() == clearTextAreaBtnProgram) {
			/** 程序级演示 清空信息显示区*/
			this.programShowTextArea.setText("");
		} else if (e.getSource() == this.clearAreaTextNetworkBtn) {
			/** 网络级演示 清空信息显示区*/
			this.textAreaNetwork.setText("");
		} else if (e.getSource() == this.sendFileBtn1) {
			// TODO
			/** 网络级演示 发送文件1*/
			if (this.sendFileThread1 == null) {
				this.sendFileThread1 = new ClientThread(this.netLevelObjector1, this.netLevelDestIpAddress, this.netLevelDestPort, this.textAreaNetwork);
				this.sendFileThread1.start();
			}
			sendNetLevelFile(this.sendFileBtn1, this.sendFileThread1, this.netLevelObjector1, this.textField);
		} else if (e.getSource() == this.sendFileBtn2) {
			/** 网络级演示 发送文件2*/
			if (this.sendFileThread2 == null) {
				this.sendFileThread2 = new ClientThread(this.netLevelObjector2, this.netLevelDestIpAddress, this.netLevelDestPort, this.textAreaNetwork);
				this.sendFileThread2.start();
			}
			sendNetLevelFile(this.sendFileBtn2, this.sendFileThread2, this.netLevelObjector2, this.textField_1);
		}
	}
	
	private void sendNetLevelFile(JButton sendBtn, ClientThread sendThread, Objector objector, JTextField intervalTextFiled) {
		boolean sendFlag = sendBtn.getText().equals("停止"); 
		if (sendFlag) {
			sendBtn.setText("发送");
			if (sendThread != null)
				sendThread.setRunFlag(false);
		} else {

//			if (!this.checkAuthority(this.subjectorApp, objector, "网络级")) {
//				this.appendShowInfo("权限认证失败!\n", this.textAreaNetwork);
//				return;
//			} else {
//				System.out.println("权限认证成功");
//			}

			sendBtn.setText("停止");
			

			int intervalTime = 5;
			try {
				intervalTime = Integer.valueOf(intervalTextFiled.getText());
			} catch (Exception e2) {
				e2.printStackTrace();
				intervalTime = 5;
			}
			
			sendThread.setIntervalTime(intervalTime);
			sendThread.setRunFlag(true);
		}
	}
	
	private void openSystemLevelFile(Objector objector) {
		File file = this.getFile(objector);
		this.appendShowInfo("正在打开文件...", this.textAreaSystem);
		if (checkAuthority(this.subjectorApp, objector, "系统级")) {
			// 如果验证权限成功, 从远程获取数据
			System.out.println("Check Authority Pass.");				

			FtpClient ftp = null;
			try {
				ftp = FTPUtil.connectFTP(ftpServerIp, ftpServerPort, ftpUserName, ftpUserPasswd);
				
				String filePath = file.getPath().replaceAll("\\\\", "/");
				String fileContent = FTPUtil.download(filePath, ftp);
				this.appendShowInfo(fileContent, this.textAreaSystem);
				FTPUtil.disconnectFTP(ftp);
			} catch (FileNotFoundException e) {
				this.appendShowInfo("文件不存在!", this.textAreaSystem);
				// e.printStackTrace();
			} catch (FtpProtocolException e) {
				this.appendShowInfo("读取失败!", this.textAreaSystem);
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.appendShowInfo("权限认证失败!", this.textAreaSystem);
			System.out.println("Check Authority Error.");
		}
		this.appendShowInfo("", this.textAreaSystem);
	}

	/**
	 * 传入Objector/Subjector对象, 获取对象相应File对象
	 * @param obj
	 * @return
	 */
	private File getFile(Object obj) {
		File file = null;
		Path filePath = null;
		// System.out.println(obj);
		if (obj instanceof Objector) {
			Objector objector = (Objector)obj;
			String fileName = objector.getObjectorName();
			if (objector.getObjectorType()!=null && objector.getObjectorType().trim().length()!=0) {
				fileName += "." + objector.getObjectorType();
			}
			if (objector.getObjectorPath() != null)
				filePath = Paths.get(objector.getObjectorPath(), fileName);
			else 
				filePath = Paths.get(fileName);
			System.out.println(filePath);
			file = filePath.toFile();
		} else if (obj instanceof Subjector) {
			Subjector subjector= (Subjector)obj;
			String fileName = subjector.getSubjectorName();
			if (subjector.getSubjectorType()!=null && subjector.getSubjectorType().trim().length()!=0) {
				fileName += "." + subjector.getSubjectorType();
			}
			if (subjector.getSubjectorPath() != null)
				filePath = Paths.get(subjector.getSubjectorPath(), fileName);
			else 
				filePath = Paths.get(fileName);
			file = filePath.toFile();
		}
		return file;
	}
	
	private void openProgramLevelFile(Objector objector) {
		
		File file = this.getFile(objector);
		if (file==null || file.exists()==false) {
			this.appendShowInfo("文件不存在!", this.programShowTextArea);
			return;
		}
		
		// 权限验证
		this.appendShowInfo("正在打开文件 " + file.getName() + " ...", this.programShowTextArea);
		if (checkAuthority(this.subjectorApp, objector, "程序级")) {
			this.appendShowInfo(file, this.programShowTextArea);
			 System.out.println("Check Authority Pass.");				
		} else {
			this.appendShowInfo("权限认证失败!", this.programShowTextArea);
			 System.out.println("Check Authority Error.");
		}
		this.appendShowInfo("", this.programShowTextArea);
	}
	
	/**
	 * 验证权限并记录日志
	 * @param subjector
	 * @param objector
	 * @param level
	 * @return
	 */
	private boolean checkAuthority(Subjector subjector, Objector objector, String level) {
		dbOperation.clearCache();
		System.out.println("subjector Id:" + subjector.getId());
		System.out.println("oubjector Id:" + objector.getId());
				
		ObjectorStampExample osExample = new ObjectorStampExample();
		osExample.or().andObjectorIdEqualTo(objector.getId());
		List<ObjectorStamp> objectorStamps = dbOperation.objectorStampMapper.selectByExample(osExample);

		SubjectorStampExample ssExample = new SubjectorStampExample();
		ssExample.or().andSubjectorIdEqualTo(subjector.getId());
		List<SubjectorStamp> subjectorStamps = dbOperation.subjectorStampMapper.selectByExample(ssExample);

		
		for (ObjectorStamp objectorStamp: objectorStamps) {
			for (SubjectorStamp subjectorStamp: subjectorStamps) {
				if (objectorStamp.getStampId().equals(subjectorStamp.getStampId())) {
					Stamp stamp = dbOperation.stampMapper.selectByPrimaryKey(objectorStamp.getStampId());
					System.out.println("Stamp id:" + stamp.getId());
					if (stamp.getStamplevel().equals(level))	// 标签相同
						switch (level) {
						case "程序级":
							// 验证成功
							// 记录日志
							saveLog(subjector, objector, "是", level, "正常");
							return true;
						case "系统级":
							/** 验证Tag内源和目的虚拟机ID是否匹配 */
							if (checkSystemLevelStamp(stamp, vmId1, vmId2)) {
								// 验证成功
								// 记录日志
								saveLog(subjector, objector, "是", level, "正常");
								return true;
							}
							break;
						case "网络级":
							System.out.println("验证网络级权限.");
							/** 验证Tag内源和目的起止IP地址是否匹配 */
							if (checkNetLevelStamp(stamp, this.netlevelLocalIpAddress, this.netLevelDestIpAddress)) {
								// 验证成功
								// 记录日志
								saveLog(subjector, objector, "是", level, "正常");
								return true;
							}
							break;
						}
						
				}
			}
		}
		// 验证失败
		
		// 记录日志
		saveLog(subjector, objector, "否", level, "高");
		return false;
	}
	
	private boolean checkNetLevelStamp(Stamp stamp, String sourceIp, String destIp) {
 		StampTagExample example = new StampTagExample();
 		example.or().andStampIdEqualTo(stamp.getId());
 		List<StampTag> stampTagList = this.dbOperation.stampTagMapper.selectByExample(example);
 		
 		for (StampTag stampTag: stampTagList) {
 			Tag tag = this.dbOperation.tagMapper.selectByPrimaryKey(stampTag.getTagId());
 			String dbTagSourceIpStart = tag.getSourceIpStart();
 			String dbTagSourceIpEnd = tag.getSourceIpEnd();
 			String dbTagDestIpStart = tag.getDestIpStart();
 			String dbTagDestIpEnd = tag.getDestIpEnd();
 			
 			System.out.println(sourceIp + " " + dbTagSourceIpStart + " " + dbTagSourceIpEnd);
 			System.out.println(destIp + " " + dbTagDestIpStart + " " + dbTagDestIpEnd);
 			if (dbTagSourceIpStart!=null && dbTagSourceIpEnd!=null && dbTagDestIpStart!=null && dbTagDestIpEnd!=null) {
 				if (ClientWindowBack.ipInRange(sourceIp, dbTagSourceIpStart, dbTagSourceIpEnd) 
 						&& ClientWindowBack.ipInRange(destIp, dbTagDestIpStart, dbTagDestIpEnd))
 					return true;
 			}
 		}
 		return false;

	}
	
	/**
	 * 验证标记对应的标签组, 是否存在满足给定源虚拟机ID和目的虚拟机ID的标签
	 * @param stamp			标记对象
	 * @param sourceVmId	源虚拟机ID
	 * @param destVmId		目的虚拟机ID
	 * @return 存在返回true; 否则返回false
	 */
 	private boolean checkSystemLevelStamp(Stamp stamp, String sourceVmId, String destVmId) {
 		StampTagExample example = new StampTagExample();
 		example.or().andStampIdEqualTo(stamp.getId());
 		List<StampTag> stampTagList = this.dbOperation.stampTagMapper.selectByExample(example);
 		for (StampTag stampTag: stampTagList) {
 			Tag tag = this.dbOperation.tagMapper.selectByPrimaryKey(stampTag.getTagId());
 			String dbTagSourceVmId = tag.getSourceVmId();
 			String dbTagDestVmId = tag.getDestVmId();
 			if (dbTagSourceVmId!=null && dbTagDestVmId!=null && dbTagSourceVmId.equals(sourceVmId) 
 					&& dbTagDestVmId.equals(destVmId) && tag.getLevel().equals("系统级")) {
 				return true;
 			}
 		}
 		return false;
	}

 	/**
 	 * 保存日志
 	 * @param subjector
 	 * @param objector
 	 * @param isMatch
 	 * @param level
 	 * @param grade
 	 */
	private void saveLog(Subjector subjector, Objector objector, String isMatch, String level, String grade) {
		TbLog log = new TbLog();
		log.setGrade(grade);
		log.setGranularity(level);
		log.setIsMatch(isMatch);
		if (objector != null) {
			log.setObjectName(objector.getObjectorName());
			log.setObjectPath(objector.getObjectorPath());
			log.setObjectType(objector.getObjectorType());
			log.setObjectVmId(objector.getObjectorVmId());
		}
		if (subjector != null) {
			log.setSubjectName(subjector.getSubjectorName());
			log.setSubjectPath(subjector.getSubjectorPath());
			log.setSubjectType(subjector.getSubjectorType());
			log.setSubjectVmId(subjector.getSubjectorVmId());
		}
		this.dbOperation.logMapper.insertSelective(log);
		return;
 	}
	
	private void appendShowInfo(String info, JTextArea tTextArea) {
		tTextArea.setText(tTextArea.getText() + info + "\r\n");

		tTextArea.selectAll();  
		tTextArea.setCaretPosition(tTextArea.getSelectedText().length());  
		tTextArea.requestFocus();  
	}
	
	/**
	 * 向特定JTextArea控件追加信息
	 * @param file
	 * @param tTextArea
	 */
	private void appendShowInfo(File file, JTextArea tTextArea) {
		String line;
		BufferedReader bf;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while((line = bf.readLine()) != null){
    			appendShowInfo(line, tTextArea);
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			appendShowInfo("文件不存在", tTextArea);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断给定ip是否在指定范围内
	 * @param checkIp
	 * @param ipRangeStart
	 * @param ipRangeEnd
	 * @return
	 */
    public static boolean ipInRange(String checkIp, String ipRangeStart, String ipRangeEnd) {
        String[] checkIpSegment, ipRangeStartSegment, ipRangeEndSegment;

        if (checkIp==null || ipRangeStart==null || ipRangeEnd==null)
            return false;

        checkIpSegment = checkIp.split("\\.");
        ipRangeStartSegment = ipRangeStart.split("\\.");
        ipRangeEndSegment = ipRangeEnd.split("\\.");

        if (checkIpSegment.length!=4 || ipRangeEndSegment.length!=4 || ipRangeStartSegment.length!=4 )
            return false;

        try {
            for (int i=0; i < 4; ++i) {
                int _i = Integer.valueOf(checkIpSegment[i]);
                int _j = Integer.valueOf(ipRangeStartSegment[i]);
                int _k = Integer.valueOf(ipRangeEndSegment[i]);

                if (_i < _j || _i > _k)
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
"""