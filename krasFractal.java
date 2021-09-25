import javax.swing.*;
import javax.swing.JFrame.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class krasFractal implements MouseListener{
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){
		if (e.getSource() == play){
			startWithParameters();
		}
		if (e.getSource() == delete){
			deleteColor(board);
		}
		if (e.getSource() == screenshot){
			screenshot();
		}
	}
	JFrame frame = new JFrame("Krasnokutskii's Fractal");
	byte [][] board = new byte[500][500];
	JLabel[][] field = new JLabel[500][500];
	JPanel[] row = new JPanel[500];
	JLabel play;
	JLabel toolBox;
	JLabel pickFill;
	JLabel delete;
	JLabel fieldPercent;
	JLabel screenshot;
	JTextField tf1, tf2, tf3, tf4;

	boolean isFinished = true;
	int filled = 1;
	int number = 1;

	public static void main(String[] args){
		new krasFractal().start();
	}

	//Change
	public void startWithParameters(){
		String s1 = tf1.getText();
		filled = Integer.parseInt(s1);
		play.setText("Generating...");
		for (int i = 0; i < filled; i++) {
			update();
	
		}
		load(board);
		fieldPercent.setText(String.valueOf(updateFill(board)/2400 + "%"));
	}
	//Fine
	public void screenshot(){
        try {
            Robot robot = new Robot();
 
            Rectangle rectangle = new Rectangle(1200, 850);
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            File file = new File("screen-capture" + number +".png");
            boolean status = ImageIO.write(bufferedImage, "png", file);
            number += 1;

        } catch (AWTException | IOException e) {

        }
	}
	//Change
	public int updateFill(byte [][] board){
		int filledPixels = 0;
		for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				if (board[i][j] != 0 && board[i][j] != 4){
					filledPixels+=1;
				}
			}
		}
		return filledPixels;
	}

	//Change
	public void deleteColor(byte [][] board){
		String s1 = tf3.getText();
		if (s1.equals("red")){
			for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				if (board[i][j] == 1){
					board[i][j] = 0;
				}
			}
			}	
		}
		if (s1.equals("green")){
			for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				if (board[i][j] == 2){
					board[i][j] = 0;
				}
			}
			}	
		}
		if (s1.equals("blue")){
			for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				if (board[i][j] == 3){
					board[i][j] = 0;
				}
			}
			}	

		}
		if (s1.equals("all")){
			for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){

				board[i][j] = 0;
				
			}
			}	

		}
		load(board);

	}

	public void start(){
		play = new JLabel();
		play.setBounds(900, 700, 200, 80);
		play.setBackground(new Color(220, 170, 30));
		play.setLayout(null);
		play.setText("Start");
		play.setOpaque(true);
		play.setFont(new Font("MV Boli",Font.BOLD,20));
		play.setHorizontalAlignment(JLabel.CENTER);
		play.addMouseListener(this);

		delete = new JLabel();
		delete.setBounds(910, 450, 180, 40);
		delete.setBackground(new Color(220, 170, 30));
		delete.setLayout(null);
		delete.setText("Delete color");
		delete.setOpaque(true);
		delete.setFont(new Font("MV Boli",Font.BOLD,20));
		delete.setHorizontalAlignment(JLabel.CENTER);
		delete.addMouseListener(this);

		screenshot = new JLabel();
		screenshot.setBounds(850, 150, 180, 40);
		screenshot.setBackground(new Color(220, 170, 30));
		screenshot.setLayout(null);
		screenshot.setText(" Screenshot ");
		screenshot.setOpaque(true);
		screenshot.setFont(new Font("MV Boli",Font.BOLD,20));
		screenshot.setHorizontalAlignment(JLabel.CENTER);
		screenshot.addMouseListener(this);

		fieldPercent = new JLabel();
		fieldPercent.setBounds(1050, 150, 100, 40);
		fieldPercent.setBackground(new Color(220, 170, 30));
		fieldPercent.setLayout(null);
		fieldPercent.setText("0");
		fieldPercent.setOpaque(true);
		fieldPercent.setFont(new Font("MV Boli",Font.BOLD,20));
		fieldPercent.setHorizontalAlignment(JLabel.CENTER);

		frame.setSize(1000, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(230,230,230));
		frame.setResizable(true);
		frame.setLayout(null);
		

		toolBox = new JLabel();
		toolBox.setBounds(800, 0, 400, 120);
		toolBox.setBackground(new Color(220, 170, 30));
		toolBox.setLayout(null);
		toolBox.setText("Tool box");
		toolBox.setOpaque(true);
		toolBox.setHorizontalAlignment(JLabel.CENTER);
		toolBox.setFont(new Font("MV Boli",Font.BOLD,40));

		tf1=new JTextField();
		tf1.setBounds(900, 600, 200, 50);

		tf2=new JTextField();
		tf2.setBounds(850, 540, 300, 50);
		tf2.setText("Number of shapes (recommended 45000)");

		tf3=new JTextField();
		tf3.setBounds(900, 390, 200, 50);

		tf4=new JTextField();
		tf4.setBounds(850, 330, 300, 50);
		tf4.setText("Type red/green/blue/all to delete a color.");

		for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				field[i][j] = new JLabel();
				field[i][j].setLayout(null);
				if (i == 0 || j == 0 || i == 499 || j == 499) {
				field[i][j].setBackground(new Color(0, 200, 200));
				} else {
				field[i][j].setBackground(new Color(30, 30, 30));
				}
				field[i][j].setOpaque(true);
				field[i][j].setBounds(0, j, 1, 1);
			}
		}
		for (int i = 0; i < 500; i++) {
				row[i] = new JPanel();
				row[i].setBackground(new Color(200, 200, 0));
				row[i].setLayout(null);
				row[i].setVisible(true);
				row[i].setBounds(i, 0, 1, 500);
				for(int j = 0; j < 500; j++){
					row[i].add(field[i][j]);
				}
			frame.add(row[i]);
		}
		for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++){
				if (i == 0 || j == 0 || i == 499 || j == 499){
					board[i][j] = 4;
				} else {
					board[i][j] = 0;
				}
			}
		}

		frame.setSize(1200, 820);
		frame.add(screenshot);
		frame.add(toolBox);
		frame.add(fieldPercent);
		frame.add(delete);
		frame.add(play);
		frame.add(tf1);
		frame.add(tf2);
		frame.add(tf3);
		frame.add(tf4);
	}

	public void update(){
		createShape(board);
	}

	public void createShape(byte[][] board){
		ArrayList<Integer> is = new ArrayList<Integer>();
		ArrayList<Integer> js = new ArrayList<Integer>();
		boolean shapeDone = false;
		int spawnStatus = 0;
		int firstColor = randomGen(3);
		int color = firstColor;
		int i = randomGen(498)+1;
		int j = randomGen(498)+1;
		while(shapeDone == false && firstColor == color){
			int direction = randomGen(4);
			if (direction == 0){
				i+=1;
			}
			if (direction == 1){
				i-=1;
			}
			if (direction == 2){
				j+=1;
			}
			if (direction == 3){
				j-=1;
			}
			spawnStatus = spawn(board, i, j, color, is, js);
			if (spawnStatus == 1){
				shapeDone = true;
			}
			if (spawnStatus == 2){
				is.add(i);
				js.add(j);
				shapeDone = true;
			}
			if (spawnStatus == 3){
				is.add(i);
				js.add(j);
				color = randomGen(3);
			}
		}
		for(int q = 0; q < is.size(); q++){
			if (color == 0){
				board[is.get(q)][js.get(q)] = 1;
			}
			if (color == 1){
				board[is.get(q)][js.get(q)] = 2;
			}
			if (color == 2){
				board[is.get(q)][js.get(q)] = 3;
			}
		}
		if (is.size() != 0){
		moveShape(board, is, js, firstColor);
		}
	}

	public int spawn(byte[][] board, int i, int j, int color, ArrayList<Integer> is, ArrayList<Integer> js){
		
		boolean touch = false;
		if (i >= 499 || j >= 499 || i <= 0 || j <= 0){
			return 1;
		}
		if (!(board[i][j] == 0)){
			
			return 1;
		}
		for(int q = 0; q < is.size(); q++){
			board[is.get(q)][js.get(q)] = 0;
		}
		touch = checkTouch(board, i, j);
		if ((board[i+1][j] == 0 && board[i-1][j] == 0 && board[i][j+1] == 0 && board[i][j-1] == 00 && touch == true)) {
			if (color == 0) {
				board[i][j] = 1;
			}
			if (color == 1) {
				board[i][j] = 2;
			}
			if (color == 2) {
				board[i][j] = 3;
			}
			return 3;
		} else if (touch == true) {
			if (color == 0) {
				board[i][j] = 1;
			}
			if (color == 1) {
				board[i][j] = 2;
			}
			if (color == 2) {
				board[i][j] = 3;
			}
			
			return 2;
		}
		for(int q = 0; q < is.size(); q++){
			if (color == 0){
				board[is.get(q)][js.get(q)] = 1;
			}
			if (color == 1){
				board[is.get(q)][js.get(q)] = 2;
			}
			if (color == 2){
				board[is.get(q)][js.get(q)] = 3;
			}
		}
		return 1;	
	}

	public boolean checkTouch(byte [][] board, int i, int j){
		if (board[i][j] == 0){
			return true;
		}
		return false;
	}

	public void moveShape(byte[][] board, ArrayList<Integer> is, ArrayList<Integer> js, int shapeColor){
		boolean moving = true;
		while(moving == true){
			int moveDirection = randomGen(4);
			for(int i = 0; i < is.size(); i++){
				moving = checkTouchExcluding(board, is.get(i), js.get(i), is, js, shapeColor);
			}
			if (moving == false){
				break;
			}
			for(int i = 0; i < is.size(); i++){
				if (moving == true){
				board[is.get(i)][js.get(i)] = 0;
				}
				if (moveDirection == 0){
					if (is.get(i) + 1 > 499){
						moving = false;
					}
					setColor(board, shapeColor, (is.get(i)+1), js.get(i));
					is.set(i, is.get(i)+1);
				}
				if (moveDirection == 1){
					setColor(board, shapeColor, (is.get(i)-1), js.get(i));
					is.set(i, is.get(i)-1);
					if (is.get(i) - 1 < 1){
						moving = false;
					}
				}
				if (moveDirection == 2){
					setColor(board, shapeColor, (is.get(i)), js.get(i)+1);
					js.set(i, js.get(i)+1);
					if (js.get(i) + 1 > 499){
						moving = false;
					}
				}
				if (moveDirection == 3){
					setColor(board, shapeColor, (is.get(i)), js.get(i)-1);
					js.set(i, js.get(i)-1);
					if (js.get(i) - 1 < 1){
						moving = false;
					}
				}
			}

		}

		//debug for future reference (test)
	}

	public boolean checkTouchExcluding(byte[][] board, int i, int j, ArrayList<Integer> is, ArrayList<Integer> js, int shapeColor){
		boolean notTouching = true;
		for(int q = 0; q < is.size(); q++){
			board[is.get(q)][js.get(q)] = 0;
		}
		for(int y = 0; y < is.size(); y++){
		if (is.get(y) + 1 >= 499 || js.get(y) + 1 >= 499 || is.get(y) - 1 <= 0 || js.get(y) -1 <= 0){
			notTouching = false;
		}
		}
		for (int y = 0; y < is.size(); y++) {
			try{
			if (!(board[is.get(y)+1][js.get(y)] == 0 && board[is.get(y)-1][js.get(y)] == 0 && board[is.get(y)][js.get(y)+1] == 0 && board[is.get(y)][js.get(y)-1] == 0)) {
				//something went wrong
				notTouching = false;
			}
			}
			catch(ArrayIndexOutOfBoundsException exception){
				notTouching= false;
			}
			if (notTouching == false){
				if (!(is.get(y) == 0 || is.get(y) == 499 || js.get(y) == 0 || js.get(y) == 499)) {
				setColor(board, shapeColor, is.get(y), js.get(y));
				}
			}
		}
		return notTouching;
	}

	public void setColor(byte[][] board, int shapeColor, int i, int j){
		if (board[i][j] == 0){
		if (shapeColor == 0){
			board[i][j] = 1;
		}
		if (shapeColor == 1){
			board[i][j] = 2;
		} 
		if (shapeColor == 2){
			board[i][j] = 3;
		}
		}
	}

	public int randomGen(int upperBound){
		Random rand = new Random();
		int output = rand.nextInt(upperBound);
		return output;
	}

	public void load(byte[][] board){
		for (int i = 0; i < 500; i++) {
			for(int j = 0; j < 500; j++) {
				if (board[i][j] == 0){
					field[i][j].setBackground(new Color(30, 30, 30));
				}
				if (board[i][j] == 1){
					field[i][j].setBackground(new Color(220, 10, 10));
				}
				if (board[i][j] == 2){
					field[i][j].setBackground(new Color(10, 220, 10));
				}
				if (board[i][j] == 3){
					field[i][j].setBackground(new Color(10, 10, 220));
				}
				if (board[i][j] == 4){
					field[i][j].setBackground(new Color(0, 200, 200));
				}
			}
		}
	}
}
