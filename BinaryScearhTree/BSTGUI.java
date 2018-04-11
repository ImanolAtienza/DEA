/**
 * BSTGUI demonstrates a binary search tree with a graphical user
 * interface.
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

import jss2.*;
import jss2.exceptions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.awt.FontMetrics; //to calculate string width in pixels

public class BSTGUI
{
  static JFrame myFrame = new JFrame("Binary Search Tree Demonstration");
  static JPanel drawPane;
  private JTextField inputTF;
  private JTextArea contentsTA;
  private JScrollPane contentsSP, outputSP, drawPaneSP;
  private JTextArea outputTA = new JTextArea();
  private JLabel sizeLbl = new JLabel("Number of elements: 0");
  static boolean showFunctions = false;
  
  static Graphics2D g2;
  static Color textColor = new Color(210,210,255);
  static Color nodeColor = new Color(1,110,218);
  static Color bgColor = new Color(47,47,63);
  static Color ptrColor = new Color(7,223,7);
  static Color valueColor = new Color(255,255,255);
  static Color objColor = Color.ORANGE;
  static Color strColor = Color.MAGENTA;
  static Color logColor = new Color(34,110,61);
  
  private ArrayBinarySearchTree<String> myBST = 
          new ArrayBinarySearchTree<String>();
  private Object[] treeArray;
    
  public Component createComponents()
  {
    sizeLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
    sizeLbl.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel inputLbl = new JLabel("Enter data to add: ");
    inputLbl.setHorizontalAlignment(SwingConstants.CENTER);
    inputLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
    JLabel contentsLbl = new JLabel("Output of myBST.toString():");
    contentsLbl.setHorizontalAlignment(SwingConstants.CENTER);
    contentsLbl.setFont(new Font("Verdana", Font.PLAIN, 12));
    
    /**
     * Panel to show the graphical representation of the tree
     */
    class DrawBSTPanel extends JPanel
    {
      public void paintComponent(Graphics g)
      {
        int curX = 80;
        int curY = 25;
        int boxHt = 30;
        int counter = 1;
        int level = 1;
        int xPadding = 0;
        
        g2 = (Graphics2D) g;
        super.paintComponent(g2); //paint background
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, 
                            RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, 
                            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setStroke(new BasicStroke((float)1.75,BasicStroke.CAP_ROUND, 
                         BasicStroke.JOIN_MITER));
        
        /** draws each box for the border */
        Dimension drawDim = this.getSize();
        g2.setBackground(bgColor);
        g2.clearRect(0,0,drawDim.width,drawDim.height);	
		
        g2.drawRect(0,0,drawDim.width-1,drawDim.height-1);
        
        g2.setFont(new Font("Verdana", Font.BOLD, 12));
        FontMetrics fm = g2.getFontMetrics();
        g2.setPaint(textColor);
        
        g2.drawString("myBST",curX-6,curY); // draws the myBST node
        
        curY += 5;
        
        /** the root box */
        g2.setPaint(objColor);
        g2.drawRect(curX,curY,boxHt,2*boxHt);
        
        /** the separator */
        g2.drawLine(curX,curY + boxHt,curX + boxHt,curY + boxHt);
        
        /** tree count display */
        int nowSize = myBST.size();
        String curSize = "" + nowSize;
        g2.setPaint(valueColor);
        int countPad = (boxHt - fm.stringWidth(curSize))/2;
        g2.drawString(curSize,curX + countPad,curY + boxHt - 10);
        
        /** draw labels on left side */
        g2.setPaint(textColor);
        g2.drawString("count",curX-45,curY+boxHt-10);
        curY += boxHt;
        g2.drawString("root",curX-45,curY+boxHt-10);
        
        if (nowSize == 0)
        {
          /** draw null symbols */
          g2.setPaint(ptrColor);
          g2.drawLine(curX, curY, curX + boxHt, curY+boxHt);
        }
        else
        {
          /** draw the front arrow to the right */
          g2.setPaint(ptrColor);
          g2.drawLine(curX + (boxHt/2), curY + boxHt/2, curX + 
                     (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2, curY + boxHt/2);
          
          /** draw the arrow down */
          g2.setPaint(ptrColor);
          g2.drawLine(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2, curY + boxHt/2, curX + 
                      (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2, curY + boxHt*2);
          
          /** arrowpoints */
          g2.drawLine(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2, curY + boxHt*2, curX + 
                      (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2 - 4, curY + boxHt*2 - 4);
          g2.drawLine(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2, curY + boxHt*2, curX + 
                      (int)(boxHt*Math.pow(2, myBST.getHeight() - 
                      level) - 1) - boxHt/2 + 4, curY + boxHt*2 - 4);
        }
        
        curX -= boxHt;
        curY += boxHt*2;
        treeArray = myBST.getArray();
        
        for (int i = 0; i <= myBST.getMaxIndex(); i++)
        {
          String top =  (String)(treeArray[i]);
          
          if (i == counter)
          {
            counter = counter*2 + 1;
            level++;
            xPadding = 0;
          }
          else
            xPadding++;
          
          if (i == 0)
            xPadding = 0;
          
          if (treeArray[i] != null)
          {
            int elementLen = fm.stringWidth("\"0\"");
            
            /** if the current node has a left child */
            if ((2*i + 1) < treeArray.length && treeArray[2*i + 1] != null)
            {
              /** draw a line to the current node's left child */
              g2.setPaint(ptrColor);
              g2.drawLine(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() 
                          - level) - 1) + (int)(xPadding*boxHt*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2)))))
                          + (elementLen + 10)/2, curY + (level - 1)*(boxHt*3/2) 
                          + boxHt/2, curX + (int)(boxHt*Math.pow(2, myBST.getHeight()
                          - level) - 1) + (int)(xPadding*boxHt*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2)))))
                          + (elementLen + 10)/2 - (int)(boxHt/2*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2))-1))),
                          curY + (level)*(boxHt*3/2) + boxHt/2);
            }
            
            /** if the current node has a right child */
            if ((2*i + 2) < treeArray.length && treeArray[2*i + 2] != null)
            {
              /** draw a line to the current node's right node */
              g2.setPaint(ptrColor);
              g2.drawLine(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() 
                          - level) - 1) + (int)(xPadding*boxHt*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2)))))
                          + (elementLen + 10)/2, curY + (level - 1)*(boxHt*3/2)
                          + boxHt/2, curX + (int)(boxHt*Math.pow(2, myBST.getHeight() 
                          - level) - 1) + (int)(xPadding*boxHt*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2)))))
                          + (elementLen + 10)/2 + (int)(boxHt/2*(Math.pow
                          (2,myBST.getHeight() - (int)(Math.log(i+1)/Math.log(2))-1))),
                          curY + (level)*(boxHt*3/2) + boxHt/2);
            }
            
            elementLen = fm.stringWidth("\"" + top + "\"");
            
            g2.setPaint(strColor);
            /** draw the element node */
            g2.fillOval(curX + (int)(boxHt*Math.pow(2, myBST.getHeight() - level) - 1)
                             + (int)(xPadding*boxHt*(Math.pow(2,myBST.getHeight() 
                             - (int)(Math.log(i+1)/Math.log(2))))), curY 
                             + (level - 1)*(boxHt*3/2), elementLen + 10, boxHt);
            
            g2.setPaint(valueColor);
            /** element data */
            g2.drawString("\"" + top + "\"", curX + 5 + (int)(boxHt*(int)Math.pow
                         (2, myBST.getHeight() - level) - 1) + 
                         (int)(xPadding*boxHt*(Math.pow(2,myBST.getHeight() - 
                         (int)(Math.log(i+1)/Math.log(2))))), curY + boxHt - 
                         10 + (level - 1)*(boxHt*3/2));
          }
        }
        
        /** updates the scrollbars if drawing goes out of view */
        drawPane.setPreferredSize(new Dimension(curX + boxHt*(int)Math.pow
                                 (2,myBST.getHeight()) + boxHt, curY + 
                                  myBST.getHeight()*boxHt*3/2));
        drawPane.revalidate();
      }
    }
    
    /**
     * Action listener for add command.
     */
    class AddListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        String curInput = inputTF.getText();
        
        if (!curInput.equals(""))
        {
          myBST.addElement(curInput);
          
          if (showFunctions)
          {
            outputTA.append("\nCalled: myBST.add(\"" + curInput + "\");  // \"" 
                            + curInput + "\" has been added");
            outputTA.setText(outputTA.getText()); //forces scroll down
          }
          else
          {
            outputTA.append("\nAdded \"" + curInput + "\"");
            outputTA.setText(outputTA.getText());
          }
          
          sizeLbl.setText("Number of elements: " + myBST.size());
          inputTF.selectAll();
          contentsTA.setText(myBST.toString());
          drawPane.repaint();
        }
        
        else 
        {
          outputTA.append("\nCannot add empty data!");
          outputTA.setText(outputTA.getText());
        }
      }
    }
    
    JButton addButton = new JButton("addElement");
    addButton.setMnemonic(KeyEvent.VK_A);
    addButton.addActionListener(new AddListener());
    addButton.setFont(new Font("Verdana", Font.PLAIN, 22));      
    
    JButton removeButton = new JButton("removeElement");
    removeButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    removeButton.addActionListener(new ActionListener()
    {
      /**
       * Action listener for remove command.
       */
      public void actionPerformed(ActionEvent e)
      {
        if (myBST.size() > 0)
        {
          String curInput = inputTF.getText();
          boolean valueExists = false;
          treeArray = myBST.getArray();
          
          for (int i = 0; i < treeArray.length; i++)
          {
            if (treeArray[i] != null)
              if (treeArray[i].equals(curInput))
                valueExists = true;
          }
          
          if (valueExists)
          {
            String current =  myBST.removeElement(curInput);
            
            if (showFunctions)
            {
              outputTA.append("\nCalled: String current = myBST.removeElement(curInput);  " +
                              "// Now, current is \"" + current + "\"");
              outputTA.setText(outputTA.getText());
            }
            
            else
            {
              outputTA.append("\nRemoved \"" + current + "\" from the tree");
              outputTA.setText(outputTA.getText());
            }
          }
          
          else
          {
            outputTA.append("\nThe element \"" + curInput + "\" doesn't exist in the tree.");
            outputTA.setText(outputTA.getText());
          }
          
          sizeLbl.setText("Number of elements: " + myBST.size());
          contentsTA.setText(myBST.toString());
          drawPane.repaint();
        }
        
        else
        {
          outputTA.append("\nCannot remove an element from an empty tree!");
          outputTA.setText(outputTA.getText());
        }
      }
    });
    

    JButton findMinButton = new JButton("findMin");
    findMinButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    findMinButton.addActionListener(new ActionListener()
    {
      /**
       * Action listener for findMin command.
       */
      public void actionPerformed(ActionEvent e)
      {
        if (myBST.size() > 0)
        {
          String curMin =  myBST.findMin();
          
          if (showFunctions)
          {
            outputTA.append("\nCalled: String curMin =  myBST.findMin();  " +
                            "// Now, curMin is \"" + curMin + "\"");
            outputTA.setText(outputTA.getText());
          } 
          
          else
          {
            outputTA.append("\nMinumum value is \"" + curMin + "\"");
            outputTA.setText(outputTA.getText());
          }
          
          sizeLbl.setText("Number of elements: " + myBST.size());
          contentsTA.setText(myBST.toString());
          drawPane.repaint();
        }
        
        else
        {
          outputTA.append("\nCannot find an element in an empty tree!");
          outputTA.setText(outputTA.getText());
        }
      }
    });
    
    JButton findMaxButton = new JButton("findMax");
    findMaxButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    findMaxButton.addActionListener(new ActionListener()
    {
      /**
       * Action listener for findMax command.
       */
      public void actionPerformed(ActionEvent e)
      {
        if (myBST.size() > 0)
        {
          String curMax =  myBST.findMax();
          
          if (showFunctions)
          {
            outputTA.append("\nCalled: String curMax =  myBST.findMax();  " +
                            "// Now, curMax is \"" + curMax + "\"");
            outputTA.setText(outputTA.getText());
          }
          
          else
          {
            outputTA.append("\nMaximum value is \"" + curMax + "\"");
            outputTA.setText(outputTA.getText());
          }
          
          sizeLbl.setText("Number of elements: " + myBST.size());
          contentsTA.setText(myBST.toString());
          drawPane.repaint();
        }
        
        else
        {
          outputTA.append("\nCannot find an element in an empty tree!");
          outputTA.setText(outputTA.getText());
        }
      }
    });
    
    JButton sizeButton = new JButton("Size");
    sizeButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    sizeButton.addActionListener(new ActionListener()
    {
      /**
       * Action listener for size command.
       */
      public void actionPerformed(ActionEvent e)
      {
        int curSize = myBST.size();
        
        if (showFunctions)
        {
          outputTA.append("\nCalled: int curSize = myBST.size();  " +
                          "// Now, curSize is " + curSize);
          outputTA.setText(outputTA.getText());
        } 
        
        else 
        {
          outputTA.append("\nChecked the size of the tree, and it has " 
                          + curSize + " elements");
          outputTA.setText(outputTA.getText());
        }
      }
    });
    
    JButton isEmptyButton = new JButton("isEmpty");
    isEmptyButton.setFont(new Font("Verdana", Font.PLAIN, 22));
    isEmptyButton.addActionListener(new ActionListener()
    {
      /**
       * Action listener for isEmpty command.
       */
      public void actionPerformed(ActionEvent e)
      {
        boolean empty = myBST.isEmpty();
        
        if (showFunctions)
        {
          String msg = "Called: boolean empty = myBST.isEmpty();  " +
                       "// Now, empty == ";
          
          if (empty)
            msg += "true";
          
          else
            msg += "false";
                    
          outputTA.append("\n" + msg);
          outputTA.setText(outputTA.getText());
        }
        
        else
        {
          String msg = "Checked if the tree was empty,";
          
          if (empty)
            msg += " and it is empty";
          
          else
            msg += " and it is NOT empty";
          
          outputTA.append("\n" + msg);
          outputTA.setText(outputTA.getText());
        }
      }
    });
    
    inputTF = new JTextField(10);
    inputTF.setFont(new Font("Verdana", Font.BOLD, 12));
    inputTF.setHorizontalAlignment(SwingConstants.CENTER);
    inputTF.addActionListener(new AddListener());

    contentsTA = new JTextArea();
    contentsTA.setFont(new Font("Verdana", Font.BOLD, 12));
    contentsTA.setEditable(false);
    contentsTA.setToolTipText("This area displays the current " +
                              "contents of the tree as returned  " +
                              "by the toString() method.");
    
    contentsSP = new JScrollPane(contentsTA);
    
    outputTA = new JTextArea(5,20);
    outputTA.setText("Enter data into the binary search tree to see " +
                     "a demonstration");
    outputTA.setEditable(false);
    outputTA.setFont(new Font("Verdana", Font.BOLD, 16));
    outputTA.setForeground(logColor);
    outputTA.setBackground(Color.WHITE);
    
    outputSP = new JScrollPane(outputTA);
    outputSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    JPanel pane = new JPanel();      
    pane.setLayout(new BorderLayout());

    JPanel leftPane = new JPanel();
    leftPane.setLayout(new BorderLayout());
          
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BorderLayout());
    inputPanel.add("West", inputLbl);
    inputPanel.add("Center", inputTF);
    
    JPanel south = new JPanel();
    south.setLayout(new GridLayout(8,1));
    south.add(inputPanel);
    addButton.setToolTipText("Press this button to add an Object to " +
                             "the tree.");
    south.add(addButton);
    removeButton.setToolTipText("Press this button to remove an Object " +
                                "from the tree.");
    south.add(removeButton);
    findMinButton.setToolTipText("Press this button to find the minimum " +
                                 "value in the tree.");
    south.add(findMinButton);
    findMaxButton.setToolTipText("Press this button to find the maximum " +
                                 "value in the tree.");
    south.add(findMaxButton);
    sizeButton.setToolTipText("Press this button to return the number of " +
                              "elements currently contained within the tree.");
    south.add(sizeButton);
    isEmptyButton.setToolTipText("Press this button to check if the tree is empty.");
    south.add(isEmptyButton);
    
    leftPane.add("South", south);
    
    JPanel center = new JPanel();
    center.setLayout(new BorderLayout());
    center.add("North", contentsLbl);
    center.add("Center", contentsSP);
    center.add("South", sizeLbl);
         
    leftPane.add("Center", center);
      
    drawPane = new DrawBSTPanel();
    drawPaneSP = new JScrollPane(drawPane);
    
    pane.add("West",leftPane);      
    pane.add("Center",drawPaneSP);
    pane.add("South", outputSP);

    return pane;
  }

  /**
   * Displays the main application frame.
   */
  public void display()
  {
    try
    {
      UIManager.setLookAndFeel
                (UIManager.getCrossPlatformLookAndFeelClassName());
    } 
    
    catch (Exception e) { }
    
    /** create the top-level container and add contents to it */
    BSTGUI bstApp = new BSTGUI();
    Component contents = bstApp.createComponents();
    myFrame.getContentPane().add(contents, BorderLayout.CENTER);
    
    myFrame.addWindowListener(new WindowAdapter()
    {
      /**
       * Exits if window is closed.
       */
      public void windowClosing(WindowEvent e)
      {
         System.exit(0);
      }
    });
    
    /** setup the menu bar at the top of the frame */
    
    JMenuBar myMenuBar = new JMenuBar();
    myFrame.setJMenuBar(myMenuBar);
    JMenu optionsMenu = new JMenu("Options");
    JMenu colorMenu = new JMenu("Color");
    
    ButtonGroup colorGroup = new ButtonGroup();
    JRadioButtonMenuItem selectColor = new JRadioButtonMenuItem
                                      ("Show Color Display");
    colorGroup.add(selectColor);
    colorMenu.add(selectColor);
    selectColor.setSelected(true);

    /**
     * Action listen for color or black & white choice.
     */
    class ColorListener implements ActionListener
    {
      /**
       * Makes changes when a menu selection is made.
       */
      public void actionPerformed(ActionEvent e)
      {
        if (e.getActionCommand().equals("Show Monochrome Display"))
        {
          /** change to black and white */
          textColor = new Color(0,0,0);
          nodeColor = new Color(0,0,0);
          bgColor = new Color(255,255,255);
          ptrColor = new Color(0,0,0);
          valueColor = new Color(255,255,255);
          objColor = new Color(0,0,0);
          strColor = new Color(0,0,0);
          drawPane.repaint();
          
        }
        
        else
        {
          /** change to color **/
          textColor = new Color(210,210,255);
          nodeColor = new Color(1,110,218);
          bgColor = new Color(47,47,63);
          ptrColor = new Color(7,223,7);
          valueColor = new Color(255,255,255);
          objColor = Color.ORANGE;
          strColor = Color.MAGENTA;
          drawPane.repaint();
        }
      }
    }
    
    selectColor.addActionListener(new ColorListener());
    JRadioButtonMenuItem selectMono = new JRadioButtonMenuItem
                                     ("Show Monochrome Display");
    colorGroup.add(selectMono);
    colorMenu.add(selectMono);
    selectMono.addActionListener(new ColorListener());
    optionsMenu.add(colorMenu);
    optionsMenu.addSeparator();

    JCheckBoxMenuItem showCalls = new JCheckBoxMenuItem
                                 ("Show ADT function calls?");
    showCalls.setToolTipText("Check this box if you wish to see the " +
                             "function call that is executed for each button.");
    showCalls.addItemListener( new ItemListener()
    {
      /**
       * Shows or hides function calls based on selection.
       */
      public void itemStateChanged(ItemEvent e)
      {
        if (e.getStateChange() == ItemEvent.SELECTED)
          showFunctions = true;
        
        else
          showFunctions= false;
      }
    });
    
    optionsMenu.add(showCalls);      
    myMenuBar.add(optionsMenu);      
    
    JMenu helpMenu = new JMenu("Help");
    JMenuItem aboutMenuItem = new JMenuItem("About BSTDemo");
    aboutMenuItem.addActionListener(new ActionListener()
    {
      /**
       * Diplays the About BSTDemo screen upon menu selection.
       */
      public void actionPerformed(ActionEvent e)
      {
        JOptionPane.showMessageDialog(myFrame, 
            "Binary Search Tree Demonstration\n\nlast modified Aug.15.2005\n" + 
            "Massjouni\\Park \nmods Coleman\\McPherson",
            "About BSTDemo", JOptionPane.PLAIN_MESSAGE);
      }
    });
    
    helpMenu.add(aboutMenuItem);      
    myMenuBar.add(helpMenu);      

    myFrame.pack();
    myFrame.setSize(800,650);      
    myFrame.setVisible(true);
  }
}

