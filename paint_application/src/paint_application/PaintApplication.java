package paint_application;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.MouseMotionAdapter;

public class PaintApplication {

	private JFrame frame;

	Circle cr;
	Rectangle re;
	Line li;
	int kind = 1;
	Color cl = Color.BLACK;

	public static void main(int userId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintApplication window = new PaintApplication(userId);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaintApplication(int userId) {
		initialize(userId);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int mYuserID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getX() + arg0.getY();
				if ((x & 1) == 0) {
					panel.repaint();
				} else {
					switch (kind) {
					case 1:

						li.setP2(arg0.getX(), arg0.getY());
						li.setColor(cl);
						li.draws(panel.getGraphics());
						break;
					case 2:

						cr.setP2(arg0.getX(), arg0.getY());
						cr.setColor(cl);
						cr.draws(panel.getGraphics());
						break;
					case 3:

						re.setP2(arg0.getX(), arg0.getY());
						re.setColor(cl);
						re.draws(panel.getGraphics());
						break;
					default:
						break;
					}

					Shape.getAllshapes(mYuserID, panel.getGraphics());
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				switch (kind) {
				case 1:
					li = new Line();
					li.setP1(arg0.getX(), arg0.getY());
					break;
				case 2:
					cr = new Circle();
					cr.setP1(arg0.getX(), arg0.getY());
					break;
				case 3:
					re = new Rectangle();
					re.setP1(arg0.getX(), arg0.getY());
					break;
				default:
					break;
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				Shape.getAllshapes(mYuserID, panel.getGraphics());
				switch (kind) {
				case 1:
					li.setP2(arg0.getX(), arg0.getY());
					li.setColor(cl);
					li.draws(panel.getGraphics());
					li.kind = kind;
					li.userId = mYuserID;
					try {
						li.addShape();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 2:
					cr.setP2(arg0.getX(), arg0.getY());
					cr.setColor(cl);
					cr.draws(panel.getGraphics());
					cr.kind = kind;
					cr.userId = mYuserID;
					try {
						cr.addShape();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					re.setP2(arg0.getX(), arg0.getY());
					re.setColor(cl);
					re.draws(panel.getGraphics());
					re.userId = mYuserID;
					re.kind = kind;
					try {
						re.addShape();
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					break;
				}

			}
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(28, 28, 500, 500);
		frame.getContentPane().add(panel);

		JButton lineBut = new JButton("\u062E\u0637");
		lineBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kind = 1;
			}
		});
		lineBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lineBut.setBounds(604, 25, 122, 23);
		frame.getContentPane().add(lineBut);

		JButton circleBut = new JButton("\u062F\u0627\u06CC\u0631\u0647");
		circleBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kind = 2;
			}
		});
		circleBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		circleBut.setBounds(604, 85, 122, 23);
		frame.getContentPane().add(circleBut);

		JButton recBut = new JButton("\u0645\u0633\u062A\u0637\u06CC\u0644");
		recBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kind = 3;
			}
		});
		recBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		recBut.setBounds(604, 145, 122, 23);
		frame.getContentPane().add(recBut);

		JButton exitBut = new JButton("\u062E\u0631\u0648\u062C");
		exitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exitBut.setBounds(604, 505, 122, 23);
		frame.getContentPane().add(exitBut);

		JLabel label = new JLabel(
				"\u0627\u0646\u062A\u062E\u0627\u0628 \u0631\u0646\u06AF:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(637, 205, 89, 23);
		frame.getContentPane().add(label);

		JRadioButton blackBut = new JRadioButton("\u0645\u0634\u06A9\u06CC");
		blackBut.setSelected(true);
		blackBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl = Color.BLACK;
			}
		});
		blackBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blackBut.setBounds(653, 265, 73, 23);
		frame.getContentPane().add(blackBut);

		JRadioButton redBut = new JRadioButton("\u0642\u0631\u0645\u0632");
		redBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl = Color.RED;
			}
		});
		redBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		redBut.setBounds(653, 325, 73, 23);
		frame.getContentPane().add(redBut);

		JRadioButton greenBut = new JRadioButton("\u0633\u0628\u0632");
		greenBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl = Color.GREEN;
			}
		});
		greenBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		greenBut.setBounds(653, 385, 73, 23);
		frame.getContentPane().add(greenBut);

		JRadioButton blueBut = new JRadioButton("\u0622\u0628\u06CC");
		blueBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl = Color.BLUE;
			}
		});
		blueBut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		blueBut.setBounds(653, 445, 73, 23);
		frame.getContentPane().add(blueBut);

		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(blackBut);
		bgroup.add(redBut);
		bgroup.add(blueBut);
		bgroup.add(greenBut);
		
		JButton colorBut = new JButton("\u062A\u063A\u06CC\u06CC\u0631 \u0631\u0646\u06AF");
		colorBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		colorBut.setBounds(538, 205, 89, 41);
		frame.getContentPane().add(colorBut);
		
		JButton zoomBut = new JButton("\u0628\u0632\u0631\u06AF \u0646\u0645\u0627\u06CC\u06CC");
		zoomBut.setBounds(538, 275, 89, 41);
		frame.getContentPane().add(zoomBut);
	}
}
