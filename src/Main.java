import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	//FRAME
	static JFrame jf;
	//GENERAL VARIABLES
	static int len = 5;

	private static int off = 0;
	private static int curAlg = 0;
	private static int spd = 200;
	private static int compare = 0;
	private static int swapsCount = 0;
	private static int passes = 0;
	private static int acc = 0;
	 static int counter = 0;
	//GRAPH VARIABLES
	private final static int SIZE = 600;
	private static int current = -1;
	private static int check = -1;
	private static int width = SIZE/len;
	
	//ARRAYS
	 static int[] list;
	private static String[] types = {"Bar Graph"};
	private static String[] algorithms = {"Selection Sort", 
								   "Bubble Sort"};
	
	//BOOLEANS
	static boolean sorting = false;
	static boolean shuffled = true;
	//UTIL OBJECTS
	SortingAlgorithms algorithm = new SortingAlgorithms();
	Random r = new Random();
	//PANELS
	JPanel tools = new JPanel();
	static GraphCanvas canvas;
	//LABELS
	static JLabel delayL = new JLabel("Delay :");
	static JLabel msL = new JLabel(spd+" ms");
	static JLabel sizeL = new JLabel("Size :");
	static JLabel lenL = new JLabel(len+"");
	static JLabel compareL = new JLabel("Comparisons : " + compare);
	static JLabel accessL = new JLabel("Array Accesses : " + acc);
	static JLabel swapL = new JLabel("Swaps : 0");
	static JLabel passesL = new JLabel("Passes : 0");
	static JLabel algorithmL = new JLabel("Algorithms");
	//DROP DOWN BOX
	static JComboBox alg = new JComboBox(algorithms);
	//BUTTONS
	static JButton sort = new JButton("SORT");
	static JButton shuffle = new JButton("RANDOM INPUT");
	static JButton btnUserInput = new JButton("USER INPUT");

	//SLIDERS
	static JSlider size = new JSlider(JSlider.HORIZONTAL,1,5,1);
	static JSlider speed = new JSlider(JSlider.HORIZONTAL,0,1000,spd);
	//BORDER STYLE
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	private static final JTextField textField = new JTextField();
	private static final JTextField inputTextField = new JTextField();
	//CONSTRUCTOR
	public Main() {
		
		shuffleList();	//CREATE THE LIST
		initialize();	//INITIALIZE THE GUI
	}
	
	public void createList() {
		list = new int[len];	//CREATES A LIST EQUAL TO THE LENGTH
		for(int i = 0; i < len; i++) {	//FILLS THE LIST FROM 1-LEN
			list[i] = i+1;
		} 
	}
	
	
	
	
	
	public void shuffleList() {
		createList();
		for(int a = 0; a < 500; a++) {	//SHUFFLE RUNS 500 TIMES
			for(int i = 0; i < len; i++) {	//ACCESS EACH ELEMENT OF THE LIST
				int rand = r.nextInt(len);	//PICK A RANDOM NUM FROM 0-LEN
				int temp = list[i];			//SETS TEMP INT TO CURRENT ELEMENT
				list[i] = list[rand];		//SWAPS THE CURRENT INDEX WITH RANDOM INDEX
				list[rand] = temp;			//SETS THE RANDOM INDEX TO THE TEMP
			}
		}
		sorting = false;
		shuffled = true;
	}
	
	public void initialize() {
		inputTextField.setBounds(161, 377, 81, 47);
		inputTextField.setColumns(10);
		textField.setColumns(10);
		//SET UP FRAME
		jf = new JFrame();
		jf.getContentPane().setBackground(new Color(135, 206, 250));
		jf.setSize(949,739);
		jf.setTitle("Sorting Simulator");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(true);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setLayout(null);
		tools.setBackground(new Color(135, 206, 250));
		
		//SET UP TOOLBAR
		tools.setLayout(null);
		tools.setBounds(5,10,290,862);
		tools.setBorder(BorderFactory.createTitledBorder(loweredetched,"Controls"));
		algorithmL.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		//SET UP ALGORITHM LABEL
		algorithmL.setHorizontalAlignment(JLabel.CENTER);
		algorithmL.setBounds(71,35,124,25);
		tools.add(algorithmL);
		alg.setForeground(Color.DARK_GRAY);
		alg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SET UP DROP DOWN
		alg.setBounds(39,73,203,47);
		tools.add(alg);
		sort.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//SET UP SORT BUTTON
		sort.setBounds(39,159,203,39);
		tools.add(sort);
		shuffle.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//SET UP SHUFFLE BUTTON
		shuffle.setBounds(39,205,203,39);
		tools.add(shuffle);
		delayL.setForeground(new Color(0, 0, 0));
		delayL.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		//SET UP DELAY LABEL
		delayL.setHorizontalAlignment(JLabel.LEFT);
		delayL.setBounds(39,333,91,25);
		tools.add(delayL);
		msL.setForeground(Color.BLACK);
		msL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SET UP MS LABEL
		msL.setHorizontalAlignment(JLabel.LEFT);
		msL.setBounds(107,326,121,39);
		tools.add(msL);
		speed.setBackground(new Color(135, 206, 250));
		speed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SET UP SPEED SLIDER
		speed.setMajorTickSpacing(5);
		speed.setBounds(39,352,200,39);
		speed.setPaintTicks(false);
		tools.add(speed);
		sizeL.setForeground(new Color(0, 0, 0));
		sizeL.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		//SET UP SIZE LABEL
		sizeL.setHorizontalAlignment(JLabel.LEFT);
		sizeL.setBounds(39,404,50,28);
		tools.add(sizeL);
		lenL.setForeground(Color.BLACK);
		lenL.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SET UP LEN LABEL
		lenL.setHorizontalAlignment(JLabel.LEFT);
		lenL.setBounds(91,399,50,39);
		tools.add(lenL);
		size.setBackground(new Color(135, 206, 250));
		size.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		//SET UP SIZE SLIDER
		size.setMajorTickSpacing(1);
		size.setBounds(39,425,200,39);
		size.setPaintTicks(false);
		tools.add(size);
		compareL.setForeground(new Color(0, 0, 0));
		compareL.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		//SET UP COMPARISONS LABEL
		compareL.setHorizontalAlignment(JLabel.LEFT);
		compareL.setBounds(39,555,200,25);
		tools.add(compareL);
		accessL.setForeground(new Color(0, 0, 0));
		accessL.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		//SET UP ARRAY ACCESS LABEL
		accessL.setHorizontalAlignment(JLabel.LEFT);
		accessL.setBounds(39,582,200,25);
		tools.add(accessL);
		

		
		//SET UP CANVAS FOR GRAPH
		canvas = new GraphCanvas();
		canvas.setBounds(300,25,SIZE,SIZE+40);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));
		jf.getContentPane().add(tools);
		btnUserInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUserInput.setBounds(39, 251, 203, 39);
		
		tools.add(btnUserInput);
		swapL.setHorizontalAlignment(SwingConstants.LEFT);
		swapL.setForeground(Color.BLACK);
		swapL.setFont(new Font("Tahoma", Font.BOLD, 18));
		swapL.setBounds(39, 492, 200, 25);
		
		tools.add(swapL);
		passesL.setHorizontalAlignment(SwingConstants.LEFT);
		passesL.setForeground(Color.BLACK);
		passesL.setFont(new Font("Tahoma", Font.BOLD, 18));
		passesL.setBounds(39, 521, 200, 25);
		
		tools.add(passesL);
			
		jf.getContentPane().add(canvas);

		//ADD ACTION LISTENERS
		alg.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				curAlg = alg.getSelectedIndex();
			}
			
		});
		
		
		sort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(shuffled) {
					sorting = true;
					compare = 0;
					swapsCount = 0;
					acc = 0;
					passes = 0;
				}
				
			}
		});
		
		btnUserInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				counter = 0;
				UserInput aw = new UserInput();

				aw.setUndecorated(true);
				aw.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				aw.setVisible(true);
				shuffle.setEnabled(false);
				speed.setEnabled(false);
				size.setEnabled(false);
				sort.setEnabled(false);
				btnUserInput.setEnabled(false);
				alg.setEnabled(false);
			}
			
			
		});
		shuffle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shuffleList();
				reset();
			}
		});
		speed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				spd = (int)speed.getValue();
				msL.setText(spd+" ms");
			}
		});
		size.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int val = size.getValue();
				len = val * 5;
				lenL.setText(len+"");
				if(list.length != len)
					shuffleList();
				reset();
			}
			
		});
		sorting();
	}
	
	//SORTING STATE
	public void sorting() {
		if(sorting) {
			try {
				switch(curAlg) {	//CURRENT ALGORITHM IS EXECUTED
					case 0:
						algorithm.selectionSort();
						break;
					case 1:
						algorithm.bubbleSort();
						break;
				}
			} catch(IndexOutOfBoundsException e) {}	//EXCEPTION HANDLER INCASE LIST ACCESS IS OUT OF BOUNDS
		}
		reset();	//RESET
		pause();	//GO INTO PAUSE STATE
	}
	
	//PAUSE STATE
	public void pause() {
		int i = 0;
		while(!sorting) {	//LOOP RUNS UNTIL SORTING STARTS
			i++;
			if(i > 100)
				i = 0;
			try {
				Thread.sleep(1);
			} catch(Exception e) {}
		}
		sorting();	//EXIT THE LOOP AND START SORTING
	}
	
	//RESET SOME VARIABLES
	public static void reset() {
		sorting = false;
		current = -1;
		check = -1;
		off = 0;
		Update();
	}
	
	//DELAY METHOD
	public void delay() {
		try {
			Thread.sleep(spd);
		} catch(Exception e) {}
	}
	
	//UPDATES THE GRAPH AND LABELS
	public static void Update() {	
		width = SIZE/len;
		canvas.repaint();
		compareL.setText("Comparisons : " + compare);
		accessL.setText("Array Accesses : " + acc);
		passesL.setText("Passes : " + passes);

		swapL.setText("Swaps : " + swapsCount);
	}
	
	//MAIN METHOD
	public static void main(String[] args) {
		new Main();
	}

	//SUB CLASS TO CONTROL THE GRAPH
	class GraphCanvas extends JPanel {
		
		public GraphCanvas() {
			setBackground(Color.white);
		}
		
		//PAINTS THE GRAPH
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i = 0; i < len; i++) {	//RUNS TROUGH EACH ELEMENT OF THE LIST
				int HEIGHT = list[i]*width;	//SETS THE HEIGHT OF THE ELEMENT ON THE GRAPH
				//BAR GRAPH TYPE
					g.setColor(Color.blue);	//DEFAULT COLOR
					if(current > -1 && i == current) {
						g.setColor(Color.green);	//COLOR OF CURRENT
						
					}
					if(check > -1 && i == check) {
						g.setColor(Color.yellow);	//COLOR OF CHECKING
					}
					//DRAWS THE BAR AND THE BLACK OUTLINE
					g.fillRect(i*width, SIZE-HEIGHT+40, width, HEIGHT+40);
					g.setColor(Color.white);
					g.drawRect(i*width, SIZE-HEIGHT+40, width, HEIGHT+40);

					
					if (len == 5) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
						g.drawString(String.valueOf(list[i]), (i*width)+(width/2), SIZE-1);
					}else if (len == 10) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 22)); 
						g.drawString(String.valueOf(list[i]), (i*width)+(width/2)-8, SIZE+20);
					}else if (len == 15) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 17)); 
						g.drawString(String.valueOf(list[i]), (i*width)+(width/2)-7, SIZE+25);
					}else if (len == 20) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
						g.drawString(String.valueOf(list[i]), (i*width)+(width/2)-5, SIZE+31);
					}else if (len == 25) {
						g.setFont(new Font("TimesRoman", Font.BOLD, 13)); 
						g.drawString(String.valueOf(list[i]), (i*width)+(width/2)-5, SIZE+33);
					}
			}
		}
	}
	
	//SUB CLASS FOR ALGORITHMS
	class SortingAlgorithms {
		
		public void selectionSort() {
			int c = 0;
			while(c < len && sorting) {

				int sm = c;
				current = c;
				passes = c;
				for(int i = c+1; i < len; i++) {

					if(!sorting)
						break;
					if(list[i] < list[sm]) {
						sm = i;
					}
					check = i;
					compare++;	acc+=2;
					Update();
					delay();
				}
				if(c != sm)
					swap(c,sm);
				c++;

			}
		}
		
		public void bubbleSort() {
			int c = 1;
			boolean noswaps = false;
			while(!noswaps && sorting) {

				current = len-c;
				passes = c;
				noswaps = true;
				for(int i = 0; i < len-c; i++) {
					if(!sorting)
						break;
					if(list[i+1] < list[i]) {
						noswaps = false;
						swap(i,i+1);
					}	
					check = i+1;
					compare++;	acc+=2;
					Update();
					delay();
				}
				c++;
			}
		}
		
		public void swap(int i1, int i2) {
			int temp = list[i1];	acc++;
			list[i1] = list[i2];	acc+=2;
			list[i2] = temp;	acc++;
			swapsCount++;
			
		}
	}
}
