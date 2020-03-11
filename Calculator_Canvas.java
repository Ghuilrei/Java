
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculator_Canvas extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new Calculator_Canvas();
	}
	
	private String str[]={"AC", "Sqrt", "Square", "<-", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
	private JPanel p = new JPanel(new GridLayout(5,4,3,3));
	private JTextField t = new JTextField();
	private boolean over = false;
	
	public Calculator_Canvas(){
		super("Calculator");
        setLayout(new BorderLayout()); 
        JButton btn[];
        btn=new JButton[str.length];
        for(int i=0;i<str.length;i++){
        	btn[i]=new JButton(str[i]);
        	btn[i].setMargin(new Insets(0,0,0,0));//设置边距
        	btn[i].addActionListener(this);
        	p.add(btn[i]);
        }
        
        t.setPreferredSize(new Dimension(20,60));
        t.setBackground(this.getBackground());
        t.setHorizontalAlignment(JTextField.RIGHT);
        t.setBorder(BorderFactory.createEmptyBorder());
//        t.setCaretColor(this.getBackground());
        t.setSelectionColor(Color.white);
        t.setFont(new Font("宋体",Font.BOLD,40));
        t.setFocusable(true);
        t.setEditable(false);
        t.setText("0");
//        t.addKeyListener(new KeyAdapter(){
//			public void keyTyped(KeyEvent e) {
//				int keyChar = e.getKeyChar();				
//				if((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) || keyChar == '.' || keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/'){
//					
//				}else{
//					e.consume(); //关键，屏蔽掉非法输入
//				}
//			}
//		});
	    getContentPane().add(t,BorderLayout.NORTH); 
	    getContentPane().add(p,BorderLayout.CENTER);  
	    setVisible(true);
	    setSize(350,350);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null); 
	}
	
	public boolean enable_point(String content) {
		for (int i = content.length() - 1; i > 0; i--) {
			if (content.charAt(i) == '.') {
				return false;
			}
			if (content.charAt(i) == '+' || content.charAt(i) == '-' || content.charAt(i) == '*' || content.charAt(i) == '/') {
				break;
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String content = t.getText();
		if (content.equals("NaN") || content.equals("Infinity")) {
			content = "0";
		}
		if (((JButton)(e.getSource())).getText() == "=") {
			content = new Operation(content).toString();
			int i;
			for (i = content.length() - 1; i > 0; i--) {
				if (content.charAt(i) != '0') {
					break;
				}
			}
			if (content.charAt(i) == '.') {
				content = content.substring(0, i);
			}
			else {
				content = content.substring(0, i + 1);
			}
			t.setText(content);
			over = true;
		}
		else if (((JButton)(e.getSource())).getText() == "AC") {
			t.setText("0");
		}
		else if (((JButton)(e.getSource())).getText() == "Sqrt") {
			double result = 0;
			try {
				result = Double.valueOf(content);
			} catch (Exception e2) {
				result = Double.valueOf(new Operation(content).toString());
			}
			content = String.format("%.4f",  Math.sqrt(result));
			int i;
			for (i = content.length() - 1; i > 0; i--) {
				if (content.charAt(i) != '0') {
					break;
				}
			}
			if (content.charAt(i) == '.') {
				content = content.substring(0, i);
			}
			else {
				content = content.substring(0, i + 1);
			}
			t.setText(content);
			over = true;
		}
		else if (((JButton)(e.getSource())).getText() == "Square") {
			double result = 0;
			try {
				result = Double.valueOf(content);
			} catch (Exception e2) {
				result = Double.valueOf(new Operation(content).toString());
			}
			content = String.format("%.4f",  result * result);
			int i;
			for (i = content.length() - 1; i > 0; i--) {
				if (content.charAt(i) != '0') {
					break;
				}
			}
			if (content.charAt(i) == '.') {
				content = content.substring(0, i);
			}
			else {
				content = content.substring(0, i + 1);
			}
			t.setText(content);
			over = true;
		}
		else if (((JButton)(e.getSource())).getText() == "<-") {
			if (over) {
				t.setText("0");
				over = false;
			}
			else {
				if (content.length() - 1 != 0) {
					t.setText(content.substring(0, content.length() - 1));
				}
				else {
					t.setText("0");
				}
			}
		}
		else if ((((JButton)(e.getSource())).getText() == "+")){
			if (content.equals("0") || content.equals("-")) {
				content = "0";
			}
			else if (content.charAt(content.length()-1) == '+' || content.charAt(content.length()-1) == '-' || content.charAt(content.length()-1) == '*' || content.charAt(content.length()-1) == '/') {
				content = content.substring(0, content.length() - 1) + ((JButton)(e.getSource())).getText();
			}
			else {
				content = content + ((JButton)(e.getSource())).getText();
			}
			over = false;
			t.setText(content);
		}
		else if (((JButton)(e.getSource())).getText() == "-" ) {
			if (content.equals("0")) {
				content = "-";
			}
			else {
				content = content + ((JButton)(e.getSource())).getText();
			}
			over = false;
			t.setText(content);
		}
		else if (((JButton)(e.getSource())).getText() == "*" || ((JButton)(e.getSource())).getText() == "/" ) {
			if (content.equals("-")) {
				content = "0" + ((JButton)(e.getSource())).getText(); 
			}
			else if (content.charAt(content.length()-1) == '+' || content.charAt(content.length()-1) == '-' || content.charAt(content.length()-1) == '*' || content.charAt(content.length()-1) == '/') {
				content = content.substring(0, content.length() - 1) + ((JButton)(e.getSource())).getText();
			}
			else {
				content = content + ((JButton)(e.getSource())).getText();
			}
			over = false;
			t.setText(content);
		}
		else if (((JButton)(e.getSource())).getText() == ".") {
			if (enable_point(content)) {
				if (content.charAt(content.length() - 1) == '+' || content.charAt(content.length() - 1) == '-' || content.charAt(content.length() - 1) == '*' || content.charAt(content.length() - 1) == '/') {
					t.setText(content + "0.");
				}
				else {
					t.setText(content + ".");
				}
			}
			over = false;
		}
		else {
			if (content.equals("0") || over) {
				content = ((JButton)(e.getSource())).getText();
			}
			else {
				content = content + ((JButton)(e.getSource())).getText();
			}
			t.setText(content);
			over = false;
		}
	}
} 
