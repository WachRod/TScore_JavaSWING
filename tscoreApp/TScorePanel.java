/* File :TScorePanel.java
* Project: Math Tools in Java 
* Purpose: Test class for finding TScore in applet mode
* Author : Wachara R.
* First Released: Sat 17 May 2008
* Last Updated : Sat 21 Nov 2009
*/
package tscoreApp;
import static java.lang.Math.*;
import tscore.ScoreTextArea;
import static tscore.TScoreConstants.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.*;  // for date and time
import java.io.*; // for reading data from a file.
public class TScorePanel extends JPanel {
   
	public TScorePanel() {
        initComponents();
    }
 
    private void initComponents() {
		/* set header label at the top of the frame
		*  and put them on header Panel
		*/

		headerLabel = new JLabel("Normalized T - Score Calculation",JLabel.CENTER);
		headerLabel2=new JLabel(" School of Physics, RMUTT ", JLabel.CENTER);
		 headerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		 headerLabel2.setFont(new Font("Dialog", Font.BOLD, 16));
        headerLabel.setForeground(Color.BLUE);
        headerLabel2.setForeground(Color.BLUE);

		headerPanel = new JPanel(new BorderLayout());
		headerPanel.add(headerLabel, BorderLayout.CENTER);
		headerPanel.add(headerLabel2, BorderLayout.SOUTH);
		headerPanel.setBackground(Color.WHITE);

		/* Create programPanel which consists of  inputLabel , scoreTextArea, buttonPanel
		* statPanel and  tablePanel 
		*/
		JLabel inputLabel = new JLabel ("Input raw scores in following text area....");
		
				/* ScoreTextArea */
		scoreText = new  ScoreTextArea();
		scoreText.setColumns(30);
        scoreText.setRows(10);
		jScrollPane1 = new  JScrollPane(scoreText);
		
		/* grade option Panel with ComboBox */
		selectedGradePanel = new JPanel();
		gradeBox = new JComboBox(selectedGrade);
		gradeTitle = new JLabel("selected grade :");

		gradeBox.addItemListener(
				new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange() == ItemEvent.SELECTED) {
						gradeOption = gradeBox.getSelectedIndex();
						computeTscore();
					}
						
					}
			}
			);
		selectedGradePanel.add(gradeTitle);
		selectedGradePanel.add(gradeBox);

			/* buttons  and buttonPanel*/
		cmdReadFile = new JButton("Read File");
//		cmdReadFile.setForeground(Color.black);

		cmdReadFile.addActionListener(new  ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                cmdReadFileActionPerformed(evt);
            }
        });
        cmdCal = new  JButton("Calculate");

        cmdCal.addActionListener(new  ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                cmdCalActionPerformed(evt);
            }
        });
		cmdClear = new  JButton("Clear Data");
	
        cmdClear.addActionListener(new   ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });
		cmdPrint = new  JButton("Print Result");

		cmdPrint.setEnabled(false);
        cmdPrint.addActionListener(new   ActionListener() {
            public void actionPerformed( ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });
		buttonPanel = new JPanel();
		buttonPanel.add(cmdReadFile);
		buttonPanel.add(cmdCal);
		buttonPanel.add(cmdClear);
		buttonPanel.add(cmdPrint);

			/* statistic quantities display */
		meanLabel = new JLabel("                                  ");
		sdLabel = new JLabel("                                       ");
		statPanel = new JPanel();
		statPanel.add(meanLabel);
		statPanel.add(sdLabel);
		statPanel.setVisible(false);
	
			/* table and tablePanel */
	tablePanel = new JPanel(new BorderLayout());
	table = new JTable(new TScoreTableModel());
	table.setPreferredScrollableViewportSize(new Dimension(400,200));
	 jScrollPane2 = new JScrollPane(table);
	 tablePanel.add(jScrollPane2);
	tablePanel.setVisible(false);

		/* display Counting Panel */
		countPanel =new JPanel( new GridLayout(2,6));
		summaryLabel= new JLabel  ("Summary :       ");
		countPanel.add(summaryLabel);

		for (int i =0; i < gLabel.length; i++)  {
			gLabel[i] = new JLabel();
			countPanel.add(gLabel[i]);
		}
		TotalLabel = new  JLabel ();
		countPanel.add(TotalLabel);
		countPanel.setVisible(false);
		

		/* put them all together in programPanel */
		programPanel = new JPanel();
		programPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		programPanel.add(inputLabel);
		programPanel.add(jScrollPane1);
		programPanel.add(selectedGradePanel);
		programPanel.add(buttonPanel);
		programPanel.add(statPanel);
		programPanel.add(tablePanel);
		programPanel.add(countPanel);

		/* create gradeOptionPanel, helpPanel and aboutPanel
		* and keep them in TabbedPane
		*/
		helpPanel = new HelpPanel();
		aboutPanel = new AboutPanel();
		tabPane = new JTabbedPane();
		tabPane.addTab("Program", programPanel);
		tabPane.addTab("User Guide",helpPanel);
		 tabPane.addTab("About", aboutPanel);
	
			/* Now add all panels into this TScorePanel */

      	setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(tabPane, BorderLayout.CENTER);
    }

    private void cmdCalActionPerformed( ActionEvent evt) {
    	computeTscore();
		
	}
    
    
    private void cmdClearActionPerformed( ActionEvent evt) {
   	int choice = displayWarning ("Are you sure to clear all data?", 1);  //YES_NO_OPTION = 1
    	if ( choice == JOptionPane.OK_OPTION) {
	cmdPrint.setEnabled(false);
	scoreText.setText("");
	tablePanel.setVisible(false);
	 statPanel.setVisible(false);
	 countPanel.setVisible(false);
	}
    }
    private void cmdReadFileActionPerformed( ActionEvent evt) {
	JFileChooser chooser = new JFileChooser();
     chooser.setMultiSelectionEnabled(false);
     int chooserSelection = chooser.showOpenDialog(this);
     if (chooserSelection == JFileChooser.APPROVE_OPTION) {
		// Read in data file to ScoreTextArea
       	try{
       	String strLine;
		fileName = "";
		fileName = chooser.getSelectedFile().toString();  // keep file name for printing
       	FileInputStream inStream = new FileInputStream(chooser.getSelectedFile());
		BufferedReader  bufferedData = new BufferedReader(new InputStreamReader(inStream));
		while ((strLine = bufferedData.readLine()) != null) {
			scoreText.append(strLine + "\n");
		}
       	}catch(Exception e){
       		displayError("Cannot read file "+chooser.getSelectedFile() , "Error!!!");
	
       	}
     }
    }
    private void cmdPrintActionPerformed( ActionEvent evt) {

	PrinterJob prntJob = PrinterJob.getPrinterJob();
	prntJob.setPrintable(new Printable() {
	int maxPage=1;
			
		public int print (Graphics g, PageFormat pageFormat, int pageIndex) {
			int r,c ; // row and column in printable paper
			Date today = new Date();

			if(pageIndex >= maxPage ) {
				return NO_SUCH_PAGE;
			}	else{
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			pageFormat.setOrientation(pageFormat.PORTRAIT);
	//		int widthPage= (int)pageFormat.getImageableWidth();
			int heightPage =(int)pageFormat.getImageableHeight();
		
			int y =20;
			if ( pageIndex > 0) g2.drawString("page : "+( pageIndex+1)+" / "+maxPage,300, y) ;
			 y+=20;
			g2.setFont( new Font("Dialog", Font.BOLD,10));
			FontMetrics fontMetrics = g2.getFontMetrics();
			y += fontMetrics.getAscent();
			g2.drawString( "Normalized T Score Calculation Report ", 200,y);
			y +=20;
			g2.drawString("Data from  :  " + fileName, 120,y);
			y+=20;
			g2.drawString("Printed Date  : "+ today ,120,y);
			y+=20;
			g2.drawString("*** " +meanLabel.getText(), 120, y );  // message, column, row
			g2.drawString(sdLabel.getText(), 270,y);
			y+=20;
			switch(gradeOption){
				case 0 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			g2.drawString(" B+ = "+gc[1], 220,y); g2.drawString("B  =  "+gc[2], 270,y);
			g2.drawString(" C+ = "+gc[3], 320,y); g2.drawString("C  =  "+gc[4], 370,y);
			g2.drawString(" D+ = "+gc[5], 420,y); g2.drawString("D  =  "+gc[6], 470,y);
			g2.drawString("F   =  "+gc[7],520,y);break;
				}
				case 1 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			 g2.drawString("B  =  "+gc[2], 220,y);
			 g2.drawString("C  =  "+gc[4], 270,y);
			 g2.drawString("D  =  "+gc[6], 320,y);
			g2.drawString("F   =  "+gc[7],370,y);break;				}
				case 2 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			g2.drawString(" B+ = "+gc[1], 220,y); g2.drawString("B  =  "+gc[2], 270,y);
			g2.drawString(" C+ = "+gc[3], 320,y); g2.drawString("C  =  "+gc[4], 370,y);
			g2.drawString(" D+ = "+gc[5], 420,y); g2.drawString("D  =  "+gc[6], 470,y);
			break;				}
				case 3 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			 g2.drawString("B  =  "+gc[2], 220,y);
			 g2.drawString("C  =  "+gc[4], 270,y);
			 g2.drawString("D  =  "+gc[6], 320,y);
			break;				}
				case 4 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			g2.drawString(" B+ = "+gc[1], 220,y); g2.drawString("B  =  "+gc[2], 270,y);
			g2.drawString(" C+  = "+gc[3], 320,y); g2.drawString("C  =  "+gc[4], 370,y);
			break;				}
				case 5 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			g2.drawString("B  =  "+gc[2], 220,y);
			 g2.drawString("C  =  "+gc[4], 270,y);
			break;				}
				case 6 : {
			g2.drawString("Summary :    A = " + gc[0],120,y);
			g2.drawString("B  =  "+gc[2], 220,y);
			break;				}
			} //switch
			y+=10;
			g2.drawLine(120,y, 540,y);
			y+=10;
			
			TableColumnModel colModel = table.getColumnModel();
			int numCols = colModel.getColumnCount();
			int [ ] x = new int [ numCols];
			x[0] =120;
			 y += g2.getFontMetrics().getHeight();
 			for (c=0; c < numCols ; c ++) {
				TableColumn tableCol = colModel.getColumn(c);
				int width = tableCol.getWidth();
				if (c+1 < numCols) 	x[c+1] = x[c] + width;
				String headTitle = (String)tableCol.getIdentifier();
				g2.drawString(headTitle, x[c], y);
			}
			g2.setFont( new Font("Dialog", Font.PLAIN,8));
			int head = y;
			int h = g2.getFontMetrics().getHeight();
	//	int  tableRowHeight = max((int)(h*1.5),10);
		int  tableRowHeight = max((int)(h*1.1),10);
			int rowPerPage = (heightPage -head)/ tableRowHeight;
			maxPage = max((int)ceil(table.getRowCount()/(double)rowPerPage),1);

			TableModel tModel = table.getModel();
			int startRow = pageIndex*rowPerPage;
			int endRow = min(table.getRowCount(), startRow+rowPerPage);

			for  (r = startRow; r < endRow; r++) {
				y += h;
				for(c=0;  c < numCols; c++) {
					int col = table.getColumnModel().getColumn(c).getModelIndex();
					Object cell = tModel.getValueAt(r,col);
					String str = cell.toString();
					g2.drawString(str, x[c]+20, y);
				} // for c
			} // for r
			if(pageIndex == maxPage-1) {
				g2.setFont( new Font("Dialog", Font.BOLD,10));
				g2.drawString("Invite you to visit :  www.rmutphysics.com", 200, y+3*h);
			}
			return PAGE_EXISTS;
		}
		} // method print
	} // new printable
		);
	if (prntJob.printDialog())	{
				try{
					prntJob.print();
					} catch(Exception e) {
						displayError("Printer Error", "Error");
				}
	}
    }

    void  computeTscore() {
    		tablePanel.setVisible(false);
    		 statPanel.setVisible(false);
    		 countPanel.setVisible(false);


    		  numberOfData= scoreText.readScore();
    		  Score = scoreText.getScore();
    		  if (numberOfData > 0) {
 
    			  TScoreTableModel ttm = new TScoreTableModel( Score, numberOfData, gradeOption);
    			  table.setModel(ttm);

    			  	// Table cell alignment
    			  TableColumnModel tcm = table.getColumnModel();

    			  DefaultTableCellRenderer cellAlignmentCenter = new DefaultTableCellRenderer();
    			  cellAlignmentCenter.setHorizontalAlignment(SwingConstants.CENTER);
    			tcm.getColumn(1).setCellRenderer(cellAlignmentCenter);
    			  tcm.getColumn(2).setCellRenderer(cellAlignmentCenter);
    			  tcm.getColumn(5).setCellRenderer(cellAlignmentCenter);

    			  DefaultTableCellRenderer cellAlignmentRight = new DefaultTableCellRenderer();
    			  cellAlignmentRight.setHorizontalAlignment(SwingConstants.RIGHT);
    			  tcm.getColumn(3).setCellRenderer(cellAlignmentRight);
    			  tcm.getColumn(4).setCellRenderer(cellAlignmentRight);

    			  avg = ttm.getMean();
    			  sd = ttm.getSD();
    			  gc = ttm.getGradeCount();

    			meanLabel.setText("Average raw  score  : "+ String.format("%.2f",avg));
    			sdLabel.setText("  Standard Deviation. : "+String.format("%.2f", sd));
    			String [ ] gStrPlus = {"A", "B+", "B", "C+", "C", "D+", "D", "F"};

    			switch(gradeOption) {
    				case 0 : {
    					for (int i =0 ; i < gLabel.length; i++) {
    						gLabel[i].setText( gStrPlus[i] + "  =  " + gc[i]);
    					}
    				 break; }
    				case 1:{
    					for (int i =0 ; i < gLabel.length; i++) {
    						if(i==4) 
    							gLabel[i].setText(  "F   =  " + gc[7]);
    						else
    						    if( i<4)
    								gLabel[i].setText( gStrPlus[i*2] + "  =  " + gc[i*2]);
    							else
    								gLabel[i].setText("");
    						}
    			
    					 break; }
    			case 2 : {

    					for (int i =0 ; i < gLabel.length; i++) {
    							gLabel[i].setText( gStrPlus[i] + "  =  " + gc[i]);
    							if ( i >6) gLabel[i].setText("");
    					}

    					 break; }
    			case 3:{
    					for (int i =0 ; i < gLabel.length; i++) {
    						    if( i<4)
    								gLabel[i].setText( gStrPlus[i*2] + "  =  " + gc[i*2]);
    							else
    								gLabel[i].setText("");
    						}
    		
    					break; }
    				case 4 : {
    					for (int i =0 ; i < gLabel.length; i++) {
    								gLabel[i].setText( gStrPlus[i] + "  =  " + gc[i]);
    						if (  i > 4) gLabel[i].setText("");
    					}

    					break; }
    				case 5:{
    						for (int i =0 ; i < gLabel.length; i++) {
    						    if( i<3)
    								gLabel[i].setText( gStrPlus[i*2] + "  =  " + gc[i*2]);
    							else
    								gLabel[i].setText("");
    						}
    				 break; }
    				case 6:{
    						for (int i =0 ; i < gLabel.length; i++) {
    						    if( i<2)
    								gLabel[i].setText( gStrPlus[i*2] + "  =  " + gc[i*2]);
    							else
    								gLabel[i].setText("");
    						}

    				break; }

    			} //switch (gradeOption)
    			int tempTotal =0;
    					for(int i =0 ; i<8; i++)  tempTotal += gc[i];
    					TotalLabel.setText(" Total :    " + tempTotal);
    			   tablePanel.setVisible(true);
    			  statPanel.setVisible(true);
    			  countPanel.setVisible(true);
    			  cmdPrint.setEnabled(true);

    	  } else {
    		  if (numberOfData == 0) {
    			  displayError(" Data not found!! \n Please input your scores!", "Error");
      		  }  // if
    	    } // else
      	
    }
    void displayError( String msgErr, String msgTitle) {
		  JOptionPane.showMessageDialog(null, msgErr, msgTitle,JOptionPane.WARNING_MESSAGE);
    }
    int displayWarning(String msgWarning, int buttonStyle) {
    	  return JOptionPane.showConfirmDialog(null, msgWarning, "Warning", JOptionPane.WARNING_MESSAGE , buttonStyle);
    	
    }
private  JLabel headerLabel;
private JLabel headerLabel2;
private  JPanel headerPanel;

private  ScoreTextArea scoreText;
private  JScrollPane jScrollPane1;

private  JPanel  selectedGradePanel ;
private JComboBox  gradeBox;
private String[]  selectedGrade = { "A, B+, B , C+, C , D+, D , F",
		 "A, B, C, D, F",  "A, B+, B , C+, C , D+, D", "A, B, C, D", "A, B+, B , C +, C", "A, B, C ", 
		 "A, B" };
  private JLabel  gradeTitle;

private  JButton cmdReadFile;
private  JButton cmdCal;
private  JButton cmdClear;
private JButton cmdPrint;
private JPanel buttonPanel;

private JLabel meanLabel ;
private	JLabel sdLabel; 
private JPanel statPanel;

private  JTable table;
private JPanel  tablePanel;
private JScrollPane jScrollPane2;

private JPanel countPanel;
private JLabel  summaryLabel;
private JLabel gLabel[ ]  = new JLabel[8];
private JLabel TotalLabel;

private  JPanel programPanel;
private HelpPanel helpPanel;
private AboutPanel aboutPanel;
private JTabbedPane tabPane; 

private int numberOfData;
private double [ ] Score = new double[MAXIMUM_NUMBER_OF_DATA+1];
private String fileName=" text area by user";
private int gradeOption=0;
//  statistical value for printing 
private double avg, sd;  // mean and standard deviation
private int[ ] gc = new int [8]; // for counting grade
}

