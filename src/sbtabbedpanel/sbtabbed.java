package sbtabbedpanel;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class sbtabbed extends JFrame {

	/**
	 * Chardy C. Fernando CS4
	 * StringBuffer Implementation: capacity(), append(), insert(), reverse(),
	 * delete() deleteCharAt(), replace() String: length() and contentEquals()
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JLabel strLen, ReverseLabelRes, startError, endError, deleteResult, delCharAtError, startReplaceError,
			EndReplaceError, StringReplaceError, ReplaceResult, ContentEqualsResult, sbCapacity, AppendedString,
			InsertResultLabelRes, InsertIndexError, DCAResults;

	private JTextField AppendTFStr1, CapacityTextField, AppendTFStr2, StrInsertTF, IndexInsertTF, StrToInsertTF,
			ReverseTF, DeleteTF, deleteStart, deleteEnd, locationTF, ReplaceTF, StartReplaceTF, EndReplaceTF,
			StrtoinsertTF, ContentEqualsStr1TF, ContentEqualsStr2TF;
	
	
	// Validate the index and set error labels if errors are found
	public int validator(String indexText, JLabel insertIndexError) {
	    int index = 0;

	    if (indexText.isEmpty()) {
	        insertIndexError.setText("cannot be null");
	    } else {
	        try {
	            index = Integer.parseInt(indexText);
	        } catch (NumberFormatException e) {
	            insertIndexError.setText("must be an integer");
	        }
	    }
	    return index;
	}

	//perform StringBuffer methods
	public void manipulateStr(String SBMethod) {
		String tfString1, tfString2;
		int startIndex, endIndex;
		StringBuffer sbManipulate;

		switch (SBMethod) {

		case "capacity":
			tfString1 = CapacityTextField.getText();
			int tfLen = tfString1.length();
			strLen.setText(Integer.toString(tfLen));
			sbManipulate = new StringBuffer(tfString1);
			sbCapacity.setText(Integer.toString(sbManipulate.capacity()));
			break;

		case "append":
			tfString1 = AppendTFStr1.getText();
			tfString2 = AppendTFStr2.getText();
			sbManipulate = new StringBuffer(tfString1);
			sbManipulate = sbManipulate.append(tfString2);
			AppendedString.setText(sbManipulate.toString());
			break;

		case "insert":
			tfString1 = StrInsertTF.getText();
			tfString2 = StrToInsertTF.getText();
			int insertIndex;

			InsertIndexError.setText(""); // Reset error label

			// Validate and parse the insertion index
			String indexText = IndexInsertTF.getText();
			insertIndex=validator(indexText,InsertIndexError);

			// Validate the bounds
			if (insertIndex < 0 || insertIndex > tfString1.length()) {
				InsertIndexError.setText("out of bounds");
			} else if (tfString2 != null) {
				// Perform the insertion
				sbManipulate = new StringBuffer(tfString1);
				sbManipulate.insert(insertIndex, tfString2);
				InsertResultLabelRes.setText(sbManipulate.toString());
			}
			break;

		case "reverse":
			tfString1 = ReverseTF.getText();
			sbManipulate = new StringBuffer(tfString1);
			sbManipulate = sbManipulate.reverse();
			ReverseLabelRes.setText(sbManipulate.toString());
			break;

		case "delete":
			tfString1 = DeleteTF.getText();
			int location = 0;

			startError.setText(""); // Reset error label
			endError.setText(""); // Reset error label
			delCharAtError.setText(""); // Reset error label

			// Validate and parse the insertion index
			String startIndexText = deleteStart.getText();
			startIndex=validator(startIndexText,startError);
			
			// Validate and parse the insertion index
			String endIndexText = deleteEnd.getText();
			endIndex = validator(endIndexText,endError);

			if (startIndex < 0 || startIndex > tfString1.length()) {
				startError.setText("out of bounds");
			} else if (endIndex < 0 || endIndex > tfString1.length()) {
				endError.setText("out of bounds");
			} else if (startIndex > endIndex) {
				startError.setText("start < end");
				endError.setText("start < end");
			}
			else {
				// Perform the deletion
				sbManipulate = new StringBuffer(tfString1);
				sbManipulate.delete(startIndex, endIndex);
				deleteResult.setText(sbManipulate.toString());
			}

			// Validate and parse the insertion index
			String LocationText = locationTF.getText();
			location = validator(LocationText,delCharAtError);

			if (location < 0 || location >= tfString1.length()) {
				delCharAtError.setText("out of bounds");
			} else {
				// Perform the CharAt deletion
				sbManipulate = new StringBuffer(tfString1);
				sbManipulate.deleteCharAt(location);
				DCAResults.setText(sbManipulate.toString());
			}
			break;

		case "replace":
			tfString1 = ReplaceTF.getText();
			tfString2 = StrtoinsertTF.getText();
			startIndex = 0;
			endIndex = 0;

			startReplaceError.setText(""); // Reset error label
			EndReplaceError.setText(""); // Reset error label
			StringReplaceError.setText(""); // Reset error label

			// Validate and parse the insertion index
			startIndexText = StartReplaceTF.getText();
			startIndex = validator(startIndexText,startReplaceError);
			

			// Validate and parse the insertion index
			endIndexText = EndReplaceTF.getText();
			endIndex = validator(endIndexText,EndReplaceError);

			if (startIndex < 0 || startIndex > tfString1.length()) {
				startReplaceError.setText("out of bounds");
			} else if (endIndex < 0 || endIndex > tfString1.length()) {
				EndReplaceError.setText("out of bounds");
			} else if (startIndex > endIndex) {
				startReplaceError.setText("start < end");
				EndReplaceError.setText("start < end");
			} else if (tfString2.isEmpty() == true) {
				StringReplaceError.setText("cannot be null");
			}

			else {
				// Perform the deletion
				sbManipulate = new StringBuffer(tfString1);
				sbManipulate.replace(startIndex, endIndex, tfString2);
				ReplaceResult.setText(sbManipulate.toString());
			}
			break;

		case "contentEquals":
			tfString1 = ContentEqualsStr1TF.getText();
			tfString2 = ContentEqualsStr2TF.getText();
			boolean res = tfString1.contentEquals(tfString2);
			ContentEqualsResult.setText(Boolean.toString(res));
			break;

		default:
			break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sbtabbed frame = new sbtabbed();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public sbtabbed() {
		setResizable(false);
		setTitle("StringBuffer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 504);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 504, 447);
		contentPane.add(tabbedPane);

		JPanel CapacityTab = new JPanel();
		tabbedPane.addTab("Capacity", null, CapacityTab, null);
		CapacityTab.setLayout(null);
		CapacityTextField = new JTextField();
		CapacityTextField.setBounds(91, 85, 318, 19);
		CapacityTab.add(CapacityTextField);
		CapacityTextField.setColumns(10);

		JLabel EnterStringLabel = new JLabel("Enter String");
		EnterStringLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EnterStringLabel.setBounds(91, 55, 119, 20);
		CapacityTab.add(EnterStringLabel);

		JLabel LenLabel = new JLabel("Length:");
		LenLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LenLabel.setBounds(91, 137, 45, 13);
		CapacityTab.add(LenLabel);

		strLen = new JLabel("0");
		strLen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		strLen.setBounds(176, 137, 45, 13);
		CapacityTab.add(strLen);

		JLabel CapacityLabel = new JLabel("Capacity:");
		CapacityLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CapacityLabel.setBounds(91, 162, 64, 13);
		CapacityTab.add(CapacityLabel);

		sbCapacity = new JLabel("0");
		sbCapacity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sbCapacity.setBounds(176, 162, 45, 13);
		CapacityTab.add(sbCapacity);

		JPanel AppendTab = new JPanel();
		tabbedPane.addTab("Append", null, AppendTab, null);
		AppendTab.setLayout(null);

		AppendTFStr1 = new JTextField();

		AppendTFStr1.setColumns(10);
		AppendTFStr1.setBounds(89, 87, 318, 19);
		AppendTab.add(AppendTFStr1);

		JLabel EnterStringLabel_1 = new JLabel("Enter String 1");
		EnterStringLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EnterStringLabel_1.setBounds(89, 57, 119, 20);
		AppendTab.add(EnterStringLabel_1);

		AppendTFStr2 = new JTextField();
		AppendTFStr2.setColumns(10);
		AppendTFStr2.setBounds(89, 157, 318, 19);
		AppendTab.add(AppendTFStr2);

		JLabel EnterStringLabel_1_1 = new JLabel("Enter String 2");
		EnterStringLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EnterStringLabel_1_1.setBounds(89, 127, 119, 20);
		AppendTab.add(EnterStringLabel_1_1);

		JLabel EnterStringLabel_1_1_1 = new JLabel("Appended String");
		EnterStringLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EnterStringLabel_1_1_1.setBounds(89, 209, 119, 20);
		AppendTab.add(EnterStringLabel_1_1_1);

		AppendedString = new JLabel("None");
		AppendedString.setFont(new Font("Dialog", Font.PLAIN, 12));
		AppendedString.setBounds(89, 239, 318, 19);
		AppendTab.add(AppendedString);

		JPanel InsertTab = new JPanel();
		tabbedPane.addTab("Insert", null, InsertTab, null);
		InsertTab.setLayout(null);

		JLabel StrInsertLabel = new JLabel("String");
		StrInsertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StrInsertLabel.setBounds(88, 26, 119, 20);
		InsertTab.add(StrInsertLabel);

		StrInsertTF = new JTextField();
		StrInsertTF.setColumns(10);
		StrInsertTF.setBounds(88, 56, 318, 19);
		InsertTab.add(StrInsertTF);

		JLabel StrIndexLabel = new JLabel("Index");
		StrIndexLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StrIndexLabel.setBounds(88, 95, 49, 20);
		InsertTab.add(StrIndexLabel);

		IndexInsertTF = new JTextField();
		IndexInsertTF.setText("0");
		IndexInsertTF.setColumns(10);
		IndexInsertTF.setBounds(88, 125, 318, 19);
		InsertTab.add(IndexInsertTF);

		JLabel StrtoinsertLabel = new JLabel("String to Insert");
		StrtoinsertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StrtoinsertLabel.setBounds(88, 161, 119, 20);
		InsertTab.add(StrtoinsertLabel);

		StrToInsertTF = new JTextField();
		StrToInsertTF.setColumns(10);
		StrToInsertTF.setBounds(88, 191, 318, 19);
		InsertTab.add(StrToInsertTF);

		JLabel InsertResultLabel = new JLabel("Resulting String");
		InsertResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		InsertResultLabel.setBounds(88, 241, 119, 20);
		InsertTab.add(InsertResultLabel);

		InsertResultLabelRes = new JLabel("None");
		InsertResultLabelRes.setFont(new Font("Dialog", Font.PLAIN, 12));
		InsertResultLabelRes.setBounds(88, 271, 318, 26);
		InsertTab.add(InsertResultLabelRes);

		InsertIndexError = new JLabel("");
		InsertIndexError.setForeground(Color.RED);
		InsertIndexError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		InsertIndexError.setBounds(132, 95, 274, 20);
		InsertTab.add(InsertIndexError);

		JPanel ReverseTab = new JPanel();
		tabbedPane.addTab("Reverse", null, ReverseTab, null);
		ReverseTab.setLayout(null);

		JLabel ReverseLabelStr = new JLabel("Enter String");
		ReverseLabelStr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ReverseLabelStr.setBounds(91, 61, 119, 20);
		ReverseTab.add(ReverseLabelStr);

		ReverseTF = new JTextField();
		ReverseTF.setColumns(10);
		ReverseTF.setBounds(91, 91, 318, 19);
		ReverseTab.add(ReverseTF);

		JLabel ReverseLabel = new JLabel("Reverse:");
		ReverseLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ReverseLabel.setBounds(91, 143, 78, 13);
		ReverseTab.add(ReverseLabel);

		ReverseLabelRes = new JLabel("None");
		ReverseLabelRes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ReverseLabelRes.setBounds(91, 166, 318, 13);
		ReverseTab.add(ReverseLabelRes);

		JPanel DeleteTab = new JPanel();
		tabbedPane.addTab("Delete", null, DeleteTab, null);
		DeleteTab.setLayout(null);

		JLabel DeleteLabelStr = new JLabel("Enter String");
		DeleteLabelStr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DeleteLabelStr.setBounds(98, 22, 119, 20);
		DeleteTab.add(DeleteLabelStr);

		DeleteTF = new JTextField();
		DeleteTF.setColumns(10);
		DeleteTF.setBounds(98, 52, 318, 19);
		DeleteTab.add(DeleteTF);

		deleteStart = new JTextField();
		deleteStart.setColumns(10);
		deleteStart.setBounds(251, 92, 37, 19);
		DeleteTab.add(deleteStart);

		JLabel deleteStartLabel = new JLabel("Start Index");
		deleteStartLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteStartLabel.setBounds(183, 90, 78, 20);
		DeleteTab.add(deleteStartLabel);

		JLabel deleteEndLabel = new JLabel("End Index");
		deleteEndLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteEndLabel.setBounds(298, 91, 64, 20);
		DeleteTab.add(deleteEndLabel);

		deleteEnd = new JTextField();
		deleteEnd.setColumns(10);
		deleteEnd.setBounds(365, 93, 37, 19);
		DeleteTab.add(deleteEnd);

		deleteResult = new JLabel("None");
		deleteResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteResult.setBounds(150, 144, 266, 13);
		DeleteTab.add(deleteResult);

		JLabel delMethodLabel = new JLabel("delete( ):");
		delMethodLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delMethodLabel.setBounds(98, 94, 56, 13);
		DeleteTab.add(delMethodLabel);

		JLabel delCharAtMethodLabel = new JLabel("deleteCharAt( ):");
		delCharAtMethodLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delCharAtMethodLabel.setBounds(98, 200, 140, 13);
		DeleteTab.add(delCharAtMethodLabel);

		JLabel locationLabel = new JLabel("Index");
		locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		locationLabel.setBounds(98, 221, 37, 20);
		DeleteTab.add(locationLabel);

		locationTF = new JTextField();
		locationTF.setText("0");
		locationTF.setColumns(10);
		locationTF.setBounds(139, 223, 37, 19);
		DeleteTab.add(locationTF);

		JLabel ResultsLabel = new JLabel("Results:");
		ResultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ResultsLabel.setBounds(98, 144, 48, 13);
		DeleteTab.add(ResultsLabel);

		JLabel DCAResultsLabel = new JLabel("Results:");
		DCAResultsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DCAResultsLabel.setBounds(98, 274, 48, 13);
		DeleteTab.add(DCAResultsLabel);

		DCAResults = new JLabel("None");
		DCAResults.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DCAResults.setBounds(150, 274, 266, 13);
		DeleteTab.add(DCAResults);

		startError = new JLabel("");
		startError.setForeground(Color.RED);
		startError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startError.setBounds(183, 121, 105, 13);
		DeleteTab.add(startError);

		endError = new JLabel("");
		endError.setForeground(Color.RED);
		endError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		endError.setBounds(297, 121, 105, 13);
		DeleteTab.add(endError);

		delCharAtError = new JLabel("");
		delCharAtError.setForeground(Color.RED);
		delCharAtError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delCharAtError.setBounds(98, 251, 105, 13);
		DeleteTab.add(delCharAtError);

		JPanel ReplaceTab = new JPanel();
		tabbedPane.addTab("Replace", null, ReplaceTab, null);
		ReplaceTab.setLayout(null);

		JLabel ReplaceLabelStr = new JLabel("Enter String");
		ReplaceLabelStr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ReplaceLabelStr.setBounds(94, 39, 119, 20);
		ReplaceTab.add(ReplaceLabelStr);

		ReplaceTF = new JTextField();
		ReplaceTF.setColumns(10);
		ReplaceTF.setBounds(94, 69, 318, 19);
		ReplaceTab.add(ReplaceTF);

		JLabel deleteStartLabel_1 = new JLabel("Start Index");
		deleteStartLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteStartLabel_1.setBounds(123, 106, 78, 20);
		ReplaceTab.add(deleteStartLabel_1);

		StartReplaceTF = new JTextField();
		StartReplaceTF.setColumns(10);
		StartReplaceTF.setBounds(191, 108, 37, 19);
		ReplaceTab.add(StartReplaceTF);

		JLabel deleteEndLabel_1 = new JLabel("End Index");
		deleteEndLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		deleteEndLabel_1.setBounds(256, 107, 64, 20);
		ReplaceTab.add(deleteEndLabel_1);

		EndReplaceTF = new JTextField();
		EndReplaceTF.setColumns(10);
		EndReplaceTF.setBounds(323, 109, 37, 19);
		ReplaceTab.add(EndReplaceTF);

		JLabel InsertLabel = new JLabel("String to Insert");
		InsertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		InsertLabel.setBounds(94, 159, 87, 20);
		ReplaceTab.add(InsertLabel);

		StrtoinsertTF = new JTextField();
		StrtoinsertTF.setColumns(10);
		StrtoinsertTF.setBounds(191, 161, 169, 19);
		ReplaceTab.add(StrtoinsertTF);

		JLabel DCAResultsLabel_1 = new JLabel("Results:");
		DCAResultsLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DCAResultsLabel_1.setBounds(94, 224, 48, 13);
		ReplaceTab.add(DCAResultsLabel_1);

		ReplaceResult = new JLabel("None");
		ReplaceResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ReplaceResult.setBounds(146, 224, 266, 13);
		ReplaceTab.add(ReplaceResult);

		startReplaceError = new JLabel("");
		startReplaceError.setForeground(Color.RED);
		startReplaceError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startReplaceError.setBounds(123, 136, 105, 13);
		ReplaceTab.add(startReplaceError);

		EndReplaceError = new JLabel("");
		EndReplaceError.setForeground(Color.RED);
		EndReplaceError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		EndReplaceError.setBounds(256, 136, 105, 13);
		ReplaceTab.add(EndReplaceError);

		StringReplaceError = new JLabel("");
		StringReplaceError.setForeground(Color.RED);
		StringReplaceError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		StringReplaceError.setBounds(191, 190, 169, 13);
		ReplaceTab.add(StringReplaceError);

		JPanel ContentEqualsTab = new JPanel();
		tabbedPane.addTab("Content Equals", null, ContentEqualsTab, null);
		ContentEqualsTab.setLayout(null);

		JLabel CESTR1Label = new JLabel("Enter String 1");
		CESTR1Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CESTR1Label.setBounds(91, 40, 119, 20);
		ContentEqualsTab.add(CESTR1Label);

		ContentEqualsStr1TF = new JTextField();

		ContentEqualsStr1TF.setColumns(10);
		ContentEqualsStr1TF.setBounds(91, 70, 318, 19);
		ContentEqualsTab.add(ContentEqualsStr1TF);

		JLabel CESTR1Label2 = new JLabel("Enter String 2");
		CESTR1Label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CESTR1Label2.setBounds(91, 110, 119, 20);
		ContentEqualsTab.add(CESTR1Label2);

		ContentEqualsStr2TF = new JTextField();

		ContentEqualsStr2TF.setColumns(10);
		ContentEqualsStr2TF.setBounds(91, 140, 318, 19);
		ContentEqualsTab.add(ContentEqualsStr2TF);

		JLabel CEEqualsSTR = new JLabel("Are the Content Equals?");
		CEEqualsSTR.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CEEqualsSTR.setBounds(91, 192, 142, 20);
		ContentEqualsTab.add(CEEqualsSTR);

		ContentEqualsResult = new JLabel("None");
		ContentEqualsResult.setFont(new Font("Dialog", Font.PLAIN, 12));
		ContentEqualsResult.setBounds(91, 222, 318, 19);
		ContentEqualsTab.add(ContentEqualsResult);

		//capacity listener for the textfield
		CapacityTextField.getDocument().addDocumentListener((SimpleDocumentListener) e -> { manipulateStr("capacity"); });

		// append listener for the textfield
		SimpleDocumentListener appendListener = e -> manipulateStr("append");
		AppendTFStr1.getDocument().addDocumentListener(appendListener);
		AppendTFStr2.getDocument().addDocumentListener(appendListener);

		// delete listener for the textfield
		SimpleDocumentListener deleteListener = e -> manipulateStr("delete");
		locationTF.getDocument().addDocumentListener(deleteListener);
		DeleteTF.getDocument().addDocumentListener(deleteListener);
		deleteStart.getDocument().addDocumentListener(deleteListener);
		deleteEnd.getDocument().addDocumentListener(deleteListener);

		//reverse listener for the textfield
		ReverseTF.getDocument().addDocumentListener((SimpleDocumentListener) e -> {manipulateStr("reverse");});

		//replace listener for the textfield
		SimpleDocumentListener replaceListener = e -> manipulateStr("replace");
		ReplaceTF.getDocument().addDocumentListener(replaceListener);
		StartReplaceTF.getDocument().addDocumentListener(replaceListener);
		EndReplaceTF.getDocument().addDocumentListener(replaceListener);
		StrtoinsertTF.getDocument().addDocumentListener(replaceListener);

		//insert listener for the textfield
		SimpleDocumentListener insertListener = e -> manipulateStr("insert");
		StrtoinsertTF.getDocument().addDocumentListener(insertListener);
		IndexInsertTF.getDocument().addDocumentListener(insertListener);
		StrInsertTF.getDocument().addDocumentListener(insertListener);
		StrToInsertTF.getDocument().addDocumentListener(insertListener);

		//contentEquals listener for the textfield
		SimpleDocumentListener contentEqualsListener = e -> manipulateStr("contentEquals");
		ContentEqualsStr1TF.getDocument().addDocumentListener(contentEqualsListener);
		ContentEqualsStr2TF.getDocument().addDocumentListener(contentEqualsListener);
	}
}
