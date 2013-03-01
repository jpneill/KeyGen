package com.jneill.KeyGen;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.util.Random;

public class KeyGen {

	protected Shell shlKeyGenerator;
	private Text tbKey;
	private char[] partOne, partTwo, partThree;
	private String strOne, strTwo, strThree, finalKey;
	private int i, j;
	private int number, letter;
	private Random rand;
	private String alphabet;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			KeyGen window = new KeyGen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlKeyGenerator.open();
		shlKeyGenerator.layout();
		while (!shlKeyGenerator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKeyGenerator = new Shell();
		shlKeyGenerator.setSize(450, 142);
		shlKeyGenerator.setText("Key Generator");
		
		tbKey = new Text(shlKeyGenerator, SWT.BORDER | SWT.CENTER);
		tbKey.setEditable(false);
		tbKey.setBounds(86, 66, 262, 21);
		
		Button btnGenKey = new Button(shlKeyGenerator, SWT.NONE);
		btnGenKey.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * generate the key
				 * key has 3 parts:
				 * "xxxx-xxxxx-xxxx"
				 * all parts can be letters or numbers
				 */
				strOne = "";
				strTwo = "";
				strThree = "";
				finalKey = "";
				partOne = new char[4];
				partTwo = new char[5];
				partThree = new char[4];
				
				for(i = 0; i < 4; i++)
					partOne[i] = genCharacter();
				for(char x : partOne)
					strOne += x;
				
				for(i = 0; i < 5; i++)
					partTwo[i] = genCharacter();
				for(char x : partTwo)
					strTwo += x;
				
				for(i = 0; i < 4; i++)
					partThree[i] = genCharacter();
				for(char x : partThree)
					strThree += x;
				
				finalKey = strOne + '-' + strTwo + '-' + strThree;
				
				tbKey.setText(finalKey);
			}
		});
		btnGenKey.setBounds(172, 35, 89, 25);
		btnGenKey.setText("Generate Key");
	}
	
	/**
	 * Method for randomly determining a character for the key
	 * It's pretty simple at the moment, but the algorithm can be made really complicated and convoluted
	 * It uses util.rand which is pseudo random which means this isn't a very secure key
	 */
	private char genCharacter(){
		char a = '0';
		rand = new Random();
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int num = rand.nextInt(2);
		//if num is 1 the generate a random number between 0 and 9
		if(num == 1){
			number = rand.nextInt(10);
			a = (char)(((int)'0') + number);
		} else {
			a = alphabet.charAt(rand.nextInt(alphabet.length()));
		}
		
		return a;
	}
}
