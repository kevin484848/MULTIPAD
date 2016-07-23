    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GRACIAS
 */
//import static HighlightExample.word;
//import static HighlightExample.word;

import static com.sun.management.jmx.Trace.isSelected;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.FileDialog;
import java.awt.*;
import static java.awt.Color.red;
import static java.awt.SystemColor.window;
import java.io.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import javax.swing.undo.UndoManager;
import javax.swing.undo.*;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.net.URL;



public class textpad extends javax.swing.JFrame implements DropTargetListener {

    String ProgramName = "Multipad";
     public static String word,double_click,double_fn;
     public static int flagpoint;
     int flag,replace_mark,switch_flag;

    String filename1 = "", filename2 = "", filename3 = "", filename4 = "",file_pth=null;
    String fn1, dir1, fn2, dir2, holdText1, holdText2, holdText3, holdText4, filetitle, fn3, dir3, fn4, dir4;
    int endindex = 0, startindex = 0, len = 0;
    String get_what = new String("");
    String get_whole;

    int Switchtab;
    
     
     UndoManager manager = new UndoManager();
       DropTarget dt1,dt2,dt3,dt4;


   // String fn[],dir[],filename[];
    boolean textChanged;
    //Clipboard clip=getToolkit().getSystemClipboard();

    /**
     * Creates new form textpad
     */
    public textpad() {
        //super("Multipad");
  manager = new UndoManager();
        initComponents();
        
       
        
       this.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        
        
         //goto_field.setVisible(false);
         
        setTitle("MULTIPAD");
       // if(switch_flag==10)
        if(flagpoint==1)
        
        {
            textarea1.setText(double_click);
           filename1=double_fn;
           flagpoint=0;
        }
        
        
    
      
       dt2 = new DropTarget(textarea2, this); 
        dt3 = new DropTarget(textarea3, this); 
        dt4= new DropTarget(textarea4, this); 
        dt1= new DropTarget(textarea1, this); 
        textarea1.requestFocus();
      

    }
    
    
    public void dragEnter(DropTargetDragEvent dtde) {
 
  }

  public void dragExit(DropTargetEvent dte) {

 
  }

  public void dragOver(DropTargetDragEvent dtde) {
    
  }

  public void dropActionChanged(DropTargetDragEvent dtde) {
   
  }

  public void drop(DropTargetDropEvent dtde) {
  String str4=null;
      try {
        JTextArea comp = null;
      
        if(Switchtab==2)
            comp=textarea1;
         if(Switchtab==3)
            comp=textarea2;
          if(Switchtab==4)
            comp=textarea3;
           if(Switchtab==1)
            comp=textarea4;
          
      // Ok, get the dropped object and try to figure out what it is
      Transferable tr = dtde.getTransferable();
      DataFlavor[] flavors = tr.getTransferDataFlavors();
      for (int i = 0; i < flavors.length; i++) {
        System.out.println("Possible flavor: "
            + flavors[i].getMimeType());
        // Check for file lists specifically
        if (flavors[i].isFlavorJavaFileListType()) {
          // Great! Accept copy drops...
          dtde.acceptDrop(DnDConstants.ACTION_COPY);
   

          // And add the list of file names to our text area
          java.util.List list = (java.util.List) tr
              .getTransferData(flavors[i]);
          for (int j = 0; j < list.size(); j++) {
           
           str4=list.get(j).toString();
           
          }
         
          System.out.println(str4);
          //Open the file
         
        
            try {
               
                    if(Switchtab==2)
                        filename1=str4;
                        
                    if(Switchtab==3)
                        filename2=str4;
                   
                    if(Switchtab==4)
                        filename3=str4;
                     
                     if(Switchtab==1)
                        filename4=str4;
                   
                      
                    File f = new File(str4);   
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    comp.setText(str4);

               
                //setTitle(str4);
               
            } catch (Exception e) {
                System.out.println("Caught::here" + e);
            }

          // If we made it this far, everything worked.
          dtde.dropComplete(true);
          return;
        }
      }
      // Hmm, the user must not have dropped a file list
      System.out.println("Drop failed: " + dtde);
      dtde.rejectDrop();
    } catch (Exception e) {
      e.printStackTrace();
      dtde.rejectDrop();
    }
 
  }
    
    
    

 
    
    
    public void checkFile() {
        BufferedReader read;
        StringBuffer sb = new StringBuffer();
        try {
            read = new BufferedReader(new FileReader(filetitle));
            String line;
            while ((line = read.readLine()) != null) {
                sb.append(line + "\n");
            }
            textarea1.setText(sb.toString());
            read.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public void openFile(String filepth)
    { 
        try{
        String str4;
                    File f = new File(filepth);
 
                    
                   FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }
                    double_click=str4;
                    flagpoint=1;
                 

                   

                
                
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
          //  filename1=filepth;
            textarea1.requestFocus();
       
    }
    public void gotomethod(JTextArea comp)
    {
     
        
                
       Highlighter.HighlightPainter painter;
        
         Highlighter highlighter = comp.getHighlighter();

    // Remove any existing highlights for last word
    Highlighter.Highlight[] highlights = highlighter.getHighlights();
    for (int i = 0; i < highlights.length; i++) {
      Highlighter.Highlight h = highlights[i];
      if (h.getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
        highlighter.removeHighlight(h);
      }
    }
        
        //goto_field.setVisible(true);
String str = goto_field.getText();  
                int index = Integer.parseInt(str);  
                comp.setCaretPosition(comp.getDocument()  
                        .getDefaultRootElement().getElement(index-1)  
                        .getStartOffset());  
                comp.requestFocusInWindow();  
                
                try{
                int startIndex = comp.getLineStartOffset(index-1);
                        int endIndex = comp.getLineEndOffset(index-1);
                
                painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
                            
                
                    comp.getHighlighter().addHighlight(startIndex, endIndex, painter);
                    goto_field.requestFocus();
                }
catch(Exception e){}
    }
    
    public void removehighlight(JTextArea comp)
    {
    
            Highlighter.HighlightPainter painter;
        
         Highlighter highlighter = comp.getHighlighter();

    // Remove any existing highlights for last word
    Highlighter.Highlight[] highlights = highlighter.getHighlights();
    for (int i = 0; i < highlights.length; i++) {
      Highlighter.Highlight h = highlights[i];
      if (h.getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
        highlighter.removeHighlight(h);
      }
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        search_field = new javax.swing.JTextField();
        popmenu1 = new javax.swing.JPopupMenu();
        Cut1 = new javax.swing.JMenuItem(new DefaultEditorKit.CutAction());
        Copy1 = new javax.swing.JMenuItem(new DefaultEditorKit.CopyAction());
        Paste1 = new javax.swing.JMenuItem(new DefaultEditorKit.PasteAction());
        select_all_1 = new javax.swing.JMenuItem();
        font_pop = new javax.swing.JMenuItem();
        goto_field = new javax.swing.JTextField();
        line_row = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        replace_find_field = new javax.swing.JTextField();
        replace_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        replace_find_btn = new javax.swing.JButton();
        replaceall_btn = new javax.swing.JButton();
        font_dialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        list_font = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        list_style = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        list_size = new javax.swing.JList();
        font_size_field = new javax.swing.JTextField();
        font_field = new javax.swing.JTextField();
        font_style_field = new javax.swing.JTextField();
        sample_lbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        alwaysontop_lbl = new javax.swing.JLabel();
        shortcut_list = new javax.swing.JDialog();
        jScrollPane9 = new javax.swing.JScrollPane();
        timer_table = new javax.swing.JTable();
        jScrollBar1 = new javax.swing.JScrollBar();
        contact_dialog = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        about_dialog = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tipsdialog = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea1 = new javax.swing.JTextArea();
        SwitchtoNext = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        open1 = new javax.swing.JMenuItem();
        save1 = new javax.swing.JMenuItem();
        saveas1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        selectall1 = new javax.swing.JMenuItem();
        goto1 = new javax.swing.JMenuItem();
        replace1 = new javax.swing.JMenuItem();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        textarea2 = new javax.swing.JTextArea();
        jMenuBar3 = new javax.swing.JMenuBar();
        open2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        save2 = new javax.swing.JMenuItem();
        saveas2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        selectall2 = new javax.swing.JMenuItem();
        goto2 = new javax.swing.JMenuItem();
        replace2 = new javax.swing.JMenuItem();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        textarea4 = new javax.swing.JTextArea();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        save4 = new javax.swing.JMenuItem();
        SaveAs4 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        selectall4 = new javax.swing.JMenuItem();
        goto4 = new javax.swing.JMenuItem();
        replace4 = new javax.swing.JMenuItem();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        textarea3 = new javax.swing.JTextArea();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        save3 = new javax.swing.JMenuItem();
        SaveAs3 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        selectall3 = new javax.swing.JMenuItem();
        goto3 = new javax.swing.JMenuItem();
        replace3 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        minimize_win = new javax.swing.JMenuItem();
        maximize_win = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem(new DefaultEditorKit.CopyAction());
        jMenuItem20 = new javax.swing.JMenuItem(new DefaultEditorKit.CutAction());
        undo1 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem(new DefaultEditorKit.PasteAction());
        redo1 = new javax.swing.JMenuItem();
        useful = new javax.swing.JMenu();
        SwitchNext = new javax.swing.JMenuItem();
        AlwaysOnTop2 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        about_menu = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        search_field.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        search_field.setText("Search");
        search_field.setToolTipText("Finds or Searches for word or sentence in the document");
        search_field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        search_field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        search_field.setMaximumSize(new java.awt.Dimension(6000, 600));
        search_field.setMinimumSize(new java.awt.Dimension(10, 20));
        search_field.setPreferredSize(new java.awt.Dimension(15, 20));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, popmenu1, org.jdesktop.beansbinding.ObjectProperty.create(), search_field, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        search_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_fieldFocusLost(evt);
            }
        });
        search_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_fieldKeyReleased(evt);
            }
        });

        Cut1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        Cut1.setText("Cut");
        jMenuItem20.setMnemonic(KeyEvent.VK_T);
        Cut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cut1ActionPerformed(evt);
            }
        });
        popmenu1.add(Cut1);

        Copy1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        Copy1.setText("Copy");
        jMenuItem19.setMnemonic(KeyEvent.VK_C);
        Copy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Copy1ActionPerformed(evt);
            }
        });
        popmenu1.add(Copy1);

        Paste1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        Paste1.setMnemonic('P');
        Paste1.setText("Paste");
        Paste1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Paste1ActionPerformed(evt);
            }
        });
        popmenu1.add(Paste1);

        select_all_1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        select_all_1.setText("Select All");
        select_all_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_all_1ActionPerformed(evt);
            }
        });
        popmenu1.add(select_all_1);

        font_pop.setText("Font");
        font_pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font_popActionPerformed(evt);
            }
        });
        popmenu1.add(font_pop);

        goto_field.setVisible(false);
        goto_field.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        goto_field.setText("GoTo");
        goto_field.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        goto_field.setMaximumSize(new java.awt.Dimension(6000, 600));
        goto_field.setPreferredSize(new java.awt.Dimension(6, 20));
        goto_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                goto_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                goto_fieldFocusLost(evt);
            }
        });
        goto_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                goto_fieldKeyReleased(evt);
            }
        });

        jDialog1.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());

        replace_find_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace_find_fieldActionPerformed(evt);
            }
        });
        replace_find_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                replace_find_fieldKeyReleased(evt);
            }
        });

        jLabel1.setText("Find what ");

        jLabel2.setText("Replace with");

        replace_find_btn.setText("Find");
        replace_find_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace_find_btnActionPerformed(evt);
            }
        });

        replaceall_btn.setText("Replace All");
        replaceall_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceall_btnActionPerformed(evt);
            }
        });
        replaceall_btn.setEnabled(false);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(replace_find_field, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(replace_field))
                .addGap(38, 38, 38)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(replaceall_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(replace_find_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(81, 81, 81))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(replace_find_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(replace_find_btn))
                .addGap(43, 43, 43)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(replace_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(replaceall_btn))
                    .addComponent(jLabel2))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        font_dialog.setResizable(false);
        font_dialog.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());

        jLabel3.setText("Font");

        jLabel4.setText("Font Style");

        jLabel5.setText("Font Size");

        list_font.setModel(new javax.swing.AbstractListModel() {
            GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] strings = e.getAvailableFontFamilyNames();

            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
            /*
            GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font[] fonts = e.getAllFonts(); // Get the fonts
            for (Font f : fonts) {
                System.out.println(f.getFontName());
            }*/
        });
        list_font.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_font.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_fontValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(list_font);

        list_style.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Plain", "BOLD", "Italic", "BOLD Italic" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_style.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_style.setSelectedIndex(0);
        list_style.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_styleValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(list_style);

        list_size.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30", "32", "36", "40", "48", "56", "72" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        list_size.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list_size.setToolTipText("");
        list_size.setAutoscrolls(false);
        list_size.setSelectedIndex(5);
        list_size.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_sizeValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(list_size);

        font_size_field.setText("14");
        font_size_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                font_size_fieldFocusGained(evt);
            }
        });

        font_field.setText("Consolas");
        font_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                font_fieldFocusGained(evt);
            }
        });
        font_field.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                font_fieldInputMethodTextChanged(evt);
            }
        });
        font_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                font_fieldKeyReleased(evt);
            }
        });

        font_style_field.setText("0");
        font_style_field.setToolTipText("");
        font_style_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                font_style_fieldFocusGained(evt);
            }
        });

        sample_lbl.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        sample_lbl.setText("Hello world from Kevin");

        jButton1.setText("Set this Font");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Restore to Default");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout font_dialogLayout = new javax.swing.GroupLayout(font_dialog.getContentPane());
        font_dialog.getContentPane().setLayout(font_dialogLayout);
        font_dialogLayout.setHorizontalGroup(
            font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(font_dialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(font_dialogLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(font_dialogLayout.createSequentialGroup()
                        .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, font_dialogLayout.createSequentialGroup()
                                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(font_dialogLayout.createSequentialGroup()
                                        .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(font_dialogLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(38, 38, 38))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, font_dialogLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(font_field, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(22, 22, 22)))
                                        .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(font_dialogLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(75, 75, 75))
                                            .addGroup(font_dialogLayout.createSequentialGroup()
                                                .addComponent(font_style_field)
                                                .addGap(26, 26, 26))))
                                    .addGroup(font_dialogLayout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)))
                                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(font_size_field, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, font_dialogLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addGap(29, 29, 29))))
            .addGroup(font_dialogLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(sample_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        font_dialogLayout.setVerticalGroup(
            font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(font_dialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(font_dialogLayout.createSequentialGroup()
                        .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(font_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(font_style_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(font_size_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(font_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(38, 38, 38)
                .addComponent(sample_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        font_dialog.setVisible(false);

        alwaysontop_lbl.setFont(new java.awt.Font("Constantia", 1, 14)); // NOI18N
        alwaysontop_lbl.setForeground(new java.awt.Color(255, 0, 0));
        alwaysontop_lbl.setText("Always on Top : ON");
        alwaysontop_lbl.setVisible(false);

        shortcut_list.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        shortcut_list.setTitle("List of Keyboard Shortcuts");
        shortcut_list.setVisible(false);

        timer_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ctrl + F4", "Exit without Saving"},
                {"Ctrl + B", "Always on Top"},
                {"Ctrl + G", "Go to a specific line"},
                {"Ctrl + Windows", "Switches to Next Tab/Document"},
                {"Ctrl + 4", "Split the multipad into 4 pads"},
                {"Ctrl + 3", "Split the multipad into 4 pads"},
                {"Ctrl + 2", "Split the multipad into 2 pads"},
                {"Ctrl + 1", "Split the multipad into 1 pad"},
                {"Ctrl + A", "Select all content"},
                {"Ctrl + X", "Cut"},
                {"Ctrl + C", "Copy "},
                {"Ctrl + V", "Paste"},
                {"Ctrl + Z", "Undo"},
                {"Ctrl + Y", "Redo"},
                {"Ctrl + N", "Open New File"},
                {"Ctrl + O", "Open a File"},
                {"Ctrl + S", "Save a File"},
                {"Ctrl + Shift + S", "Save as"},
                {"Ctrl + F", "Find in the document"},
                {"Ctrl + R", "Replace "},
                {"Alt + F4", "Exit"},
                {"Ctrl + Space", "Shows Top Menu"},
                {"Ctrl + Shift + F", "Font Format"},
                {"Ctrl + K", "List of Keyboard Shortcuts"},
                {"Ctrl + M", "Minimize the window "},
                {"Ctrl + Shift + M", "Maximize the Window"},
                {null, null}
            },
            new String [] {
                "Shortcut", "Event"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(timer_table);

        javax.swing.GroupLayout shortcut_listLayout = new javax.swing.GroupLayout(shortcut_list.getContentPane());
        shortcut_list.getContentPane().setLayout(shortcut_listLayout);
        shortcut_listLayout.setHorizontalGroup(
            shortcut_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shortcut_listLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        shortcut_listLayout.setVerticalGroup(
            shortcut_listLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shortcut_listLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        contact_dialog.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage()); contact_dialog.setVisible(false);
        contact_dialog.setTitle("CONTACT");

        jLabel6.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 14)); // NOI18N
        jLabel6.setText("For any queries or problems related to MULTIPAD ");

        jLabel7.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 14)); // NOI18N
        jLabel7.setText("you can contact KEVIN at");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("ultimateprofessional48@gmail.com");

        javax.swing.GroupLayout contact_dialogLayout = new javax.swing.GroupLayout(contact_dialog.getContentPane());
        contact_dialog.getContentPane().setLayout(contact_dialogLayout);
        contact_dialogLayout.setHorizontalGroup(
            contact_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contact_dialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contact_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contact_dialogLayout.setVerticalGroup(
            contact_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contact_dialogLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        about_dialog.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage()); about_dialog.setVisible(false);

        jLabel9.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N
        jLabel9.setText("Multipad  has been created and developed by Sagar [Kevin] ");

        jLabel10.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 14)); // NOI18N
        jLabel10.setText("Multipad Version: 1.2");

        jLabel11.setFont(new java.awt.Font("Simplified Arabic Fixed", 0, 14)); // NOI18N
        jLabel11.setText("© Kevin");

        javax.swing.GroupLayout about_dialogLayout = new javax.swing.GroupLayout(about_dialog.getContentPane());
        about_dialog.getContentPane().setLayout(about_dialogLayout);
        about_dialogLayout.setHorizontalGroup(
            about_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(about_dialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(about_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(about_dialogLayout.createSequentialGroup()
                        .addGroup(about_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        about_dialogLayout.setVerticalGroup(
            about_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(about_dialogLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jLabel12.setText("1.Multipad [MP] can be split into 1,2,3 or 4 different Pads.");

        jLabel13.setText("2. Multipad supports Drag and Drop feature. Just Drag and Drop the file");

        jLabel14.setText(" into any of the Pad to open that file.");

        jLabel15.setText("Tip: Remember to first place your Cursor into the Pad or ");

        jLabel16.setText("even when other Applications are running on the desktop.");

        jLabel17.setText("3.MP also has Always on Top feature[Goto Useful->Always on Top] in which MP can be accessed easily ");

        jLabel18.setText("5.Explore MP to find out more its features.");

        jLabel20.setText("4.MP can be minimized and maximized with Keyboard Shortcuts.Goto [ Useful->Keyboard Shortcuts]");

        jLabel19.setText("Click on the Pad in which the file is to be opened ");

        javax.swing.GroupLayout tipsdialogLayout = new javax.swing.GroupLayout(tipsdialog.getContentPane());
        tipsdialog.getContentPane().setLayout(tipsdialogLayout);
        tipsdialogLayout.setHorizontalGroup(
            tipsdialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tipsdialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tipsdialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addGroup(tipsdialogLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(tipsdialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 46, Short.MAX_VALUE))
        );
        tipsdialogLayout.setVerticalGroup(
            tipsdialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tipsdialogLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(16, 16, 16)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jInternalFrame1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        //jInternalFrame1.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        jInternalFrame1.setFrameIcon(null);
        jInternalFrame1.setVerifyInputWhenFocusTarget(false);
        //hiding the title bar of internal frame
        javax.swing.plaf.InternalFrameUI ifu= jInternalFrame1.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
        jInternalFrame1.setVisible(true);

        textarea1.setColumns(20);
        textarea1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textarea1.setRows(5);
        textarea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, popmenu1, org.jdesktop.beansbinding.ObjectProperty.create(), textarea1, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        textarea1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textarea1CaretUpdate(evt);
            }
        });
        textarea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textarea1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textarea1FocusLost(evt);
            }
        });
        textarea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textarea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(textarea1);

        SwitchtoNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SwitchtoNext.setToolTipText("");
        SwitchtoNext.setBorderPainted(false);
        SwitchtoNext.setOpaque(false);
        SwitchtoNext.setPreferredSize(new java.awt.Dimension(40, 15));
        SwitchtoNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwitchtoNextMouseClicked(evt);
            }
        });

        jMenu3.setMnemonic('f');
        jMenu3.setText("File");

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setMnemonic('n');
        jMenuItem7.setText("New");
        jMenuItem7.setToolTipText("");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        open1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        open1.setText("Open");
        open1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open1ActionPerformed(evt);
            }
        });
        jMenu3.add(open1);

        save1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save1.setText("Save");
        save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save1ActionPerformed(evt);
            }
        });
        jMenu3.add(save1);

        saveas1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveas1.setText("Save As");
        saveas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveas1ActionPerformed(evt);
            }
        });
        jMenu3.add(saveas1);

        SwitchtoNext.add(jMenu3);

        jMenu4.setMnemonic('e');
        jMenu4.setText("Edit");

        selectall1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectall1.setText("Select All");
        selectall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectall1ActionPerformed(evt);
            }
        });
        jMenu4.add(selectall1);

        goto1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        goto1.setText("GoTo");
        goto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goto1ActionPerformed(evt);
            }
        });
        jMenu4.add(goto1);

        replace1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        replace1.setText("Replace");
        replace1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace1ActionPerformed(evt);
            }
        });
        jMenu4.add(replace1);

        SwitchtoNext.add(jMenu4);
        SwitchtoNext.add(alwaysontop_lbl);

        jInternalFrame1.setJMenuBar(SwitchtoNext);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jInternalFrame2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jInternalFrame2.setFrameIcon(null);
        //hiding the title bar of internal frame
        javax.swing.plaf.InternalFrameUI ifu2= jInternalFrame2.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu2).setNorthPane(null);
        jInternalFrame2.setVisible(true);

        textarea2.setColumns(20);
        textarea2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textarea2.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, popmenu1, org.jdesktop.beansbinding.ObjectProperty.create(), textarea2, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        textarea2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textarea2CaretUpdate(evt);
            }
        });
        textarea2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textarea2FocusGained(evt);
            }
        });
        textarea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textarea2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(textarea2);

        jMenuBar3.setBorderPainted(false);
        jMenuBar3.setOpaque(false);
        jMenuBar3.setPreferredSize(new java.awt.Dimension(40, 15));

        open2.setMnemonic('f');
        open2.setText("File");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("New");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        open2.add(jMenuItem8);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Open");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        open2.add(jMenuItem10);

        save2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save2.setText("Save");
        save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save2ActionPerformed(evt);
            }
        });
        open2.add(save2);

        saveas2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveas2.setText("Save As");
        saveas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveas2ActionPerformed(evt);
            }
        });
        open2.add(saveas2);

        jMenuBar3.add(open2);

        jMenu6.setMnemonic('e');
        jMenu6.setText("Edit");
        jMenu6.setToolTipText("");

        selectall2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectall2.setText("Select All");
        selectall2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectall2ActionPerformed(evt);
            }
        });
        jMenu6.add(selectall2);

        goto2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        goto2.setText("GoTo");
        goto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goto2ActionPerformed(evt);
            }
        });
        jMenu6.add(goto2);

        replace2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        replace2.setText("Replace");
        replace2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace2ActionPerformed(evt);
            }
        });
        jMenu6.add(replace2);

        jMenuBar3.add(jMenu6);

        jInternalFrame2.setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );

        jInternalFrame4.setFrameIcon(null);
        //hiding the title bar of internal frame
        javax.swing.plaf.InternalFrameUI ifu4= jInternalFrame4.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu4).setNorthPane(null);
        jInternalFrame4.setVisible(true);

        textarea4.setColumns(20);
        textarea4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textarea4.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, popmenu1, org.jdesktop.beansbinding.ObjectProperty.create(), textarea4, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        textarea4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textarea4CaretUpdate(evt);
            }
        });
        textarea4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textarea4FocusGained(evt);
            }
        });
        textarea4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textarea4KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(textarea4);

        jMenuBar5.setBorderPainted(false);
        jMenuBar5.setOpaque(false);
        jMenuBar5.setPreferredSize(new java.awt.Dimension(40, 15));

        jMenu9.setMnemonic('f');
        jMenu9.setText("File");

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setText("New");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setText("Open");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem17);

        save4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save4.setText("Save");
        save4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save4ActionPerformed(evt);
            }
        });
        jMenu9.add(save4);

        SaveAs4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SaveAs4.setText("Save As");
        SaveAs4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAs4ActionPerformed(evt);
            }
        });
        jMenu9.add(SaveAs4);

        jMenuBar5.add(jMenu9);

        jMenu10.setMnemonic('e');
        jMenu10.setText("Edit");

        selectall4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectall4.setText("Select All");
        selectall4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectall4ActionPerformed(evt);
            }
        });
        jMenu10.add(selectall4);

        goto4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        goto4.setText("GoTo");
        goto4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goto4ActionPerformed(evt);
            }
        });
        jMenu10.add(goto4);

        replace4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        replace4.setText("Replace");
        replace4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace4ActionPerformed(evt);
            }
        });
        jMenu10.add(replace4);

        jMenuBar5.add(jMenu10);

        jInternalFrame4.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );

        jInternalFrame3.setFrameIcon(null);
        //hiding the title bar of internal frame
        javax.swing.plaf.InternalFrameUI ifu3= jInternalFrame3.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu3).setNorthPane(null);
        jInternalFrame3.setVisible(true);

        textarea3.setColumns(20);
        textarea3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        textarea3.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, popmenu1, org.jdesktop.beansbinding.ObjectProperty.create(), textarea3, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        textarea3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textarea3CaretUpdate(evt);
            }
        });
        textarea3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textarea3FocusGained(evt);
            }
        });
        textarea3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textarea3KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(textarea3);

        jMenuBar4.setBorderPainted(false);
        jMenuBar4.setOpaque(false);
        jMenuBar4.setPreferredSize(new java.awt.Dimension(40, 15));

        jMenu7.setMnemonic('f');
        jMenu7.setText("File");

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setText("New");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setText("Open");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem16);

        save3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save3.setText("Save");
        save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save3ActionPerformed(evt);
            }
        });
        jMenu7.add(save3);

        SaveAs3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SaveAs3.setText("Save As");
        SaveAs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAs3ActionPerformed(evt);
            }
        });
        jMenu7.add(SaveAs3);

        jMenuBar4.add(jMenu7);

        jMenu8.setMnemonic('e');
        jMenu8.setText("Edit");

        selectall3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectall3.setText("Select All");
        selectall3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectall3ActionPerformed(evt);
            }
        });
        jMenu8.add(selectall3);

        goto3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        goto3.setText("GoTo");
        goto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goto3ActionPerformed(evt);
            }
        });
        jMenu8.add(goto3);

        replace3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        replace3.setText("Replace");
        replace3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replace3ActionPerformed(evt);
            }
        });
        jMenu8.add(replace3);

        jMenuBar4.add(jMenu8);

        jInternalFrame3.setJMenuBar(jMenuBar4);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );

        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setOpaque(false);
        jMenuBar1.setPreferredSize(new java.awt.Dimension(169, 20));
        //jMenuBar1.add(new JTextField("Search"));
        jMenuBar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jMenuBar1KeyReleased(evt);
            }
        });

        jMenu1.setText("File");

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setMnemonic('e');
        jMenuItem9.setText("Exit all without Saving");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        minimize_win.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        minimize_win.setText("Minimize Window");
        minimize_win.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimize_winActionPerformed(evt);
            }
        });
        jMenu1.add(minimize_win);

        maximize_win.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        maximize_win.setText("Maximize Window");
        maximize_win.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximize_winActionPerformed(evt);
            }
        });
        jMenu1.add(maximize_win);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setMnemonic('f');
        jMenuItem13.setText("Find");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setText("Copy");
        jMenuItem19.setMnemonic(KeyEvent.VK_C);
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem19);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setText("Cut");
        jMenuItem20.setMnemonic(KeyEvent.VK_T);
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

        undo1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undo1.setMnemonic('u');
        undo1.setText("Undo");
        undo1.setToolTipText("Erases the last change done to the document");
        undo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.undo();
                } catch (Exception ex) {
                }
            }
        });

        textarea1.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });
        textarea2.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });
        textarea3.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });
        textarea4.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });
        search_field.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                manager.addEdit(e.getEdit());
            }
        });

        /*
        undo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undo1ActionPerformed(evt);
            }
        });
        */
        jMenu2.add(undo1);

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setMnemonic('P');
        jMenuItem21.setText("Paste");
        jMenu2.add(jMenuItem21);

        redo1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redo1.setMnemonic('r');
        redo1.setText("Redo");
        redo1.setToolTipText("Reverses the action done by Undo");
        redo1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.redo();
                } catch (Exception ex) {
                }
            }
        });
        redo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redo1ActionPerformed(evt);
            }
        });
        jMenu2.add(redo1);

        jMenuBar1.add(jMenu2);

        useful.setText("Useful");

        SwitchNext.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_WINDOWS, java.awt.event.InputEvent.CTRL_MASK));
        SwitchNext.setText("Switch to Next Pad ");
        SwitchNext.setToolTipText("Switches or points to next Tab");
        SwitchNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchNextActionPerformed(evt);
            }
        });
        useful.add(SwitchNext);

        AlwaysOnTop2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        AlwaysOnTop2.setMnemonic('a');
        AlwaysOnTop2.setSelected(false);
        AlwaysOnTop2.setText("Always on Top");
        AlwaysOnTop2.setToolTipText("Makes MULTIPAD appear on top or above others applications. Very useful feature if multipad is needed frequently.");
        AlwaysOnTop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlwaysOnTop2ActionPerformed(evt);
            }
        });
        useful.add(AlwaysOnTop2);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Font Format");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        useful.add(jMenuItem11);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setText("Keyboard Shortcuts");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        useful.add(jMenuItem18);

        jMenuItem23.setText("Tips");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        useful.add(jMenuItem23);

        jMenuBar1.add(useful);

        about_menu.setText("About");

        jMenuItem12.setText("About Multipad");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        about_menu.add(jMenuItem12);

        jMenuItem22.setText("Contact");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        about_menu.add(jMenuItem22);

        jMenuBar1.add(about_menu);

        jMenu11.setText("Split");
        jMenu11.setToolTipText("Splits / Divides MULTIPAD into 1 Pad");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setMnemonic('4');
        jMenuItem2.setText("Split into 4");
        jMenuItem2.setToolTipText("Splits / Divides MULTIPAD into 4 Pads");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem2);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Split into 3");
        jMenuItem6.setToolTipText("Splits / Divides MULTIPAD into 3 Pads");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setMnemonic('2');
        jMenuItem5.setText("Split into 2");
        jMenuItem5.setToolTipText("Splits / Divides MULTIPAD into 2 Pads");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem5);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setMnemonic('1');
        jMenuItem3.setText("Split into 1");
        jMenuItem3.setToolTipText("Splits / Divides MULTIPAD into 1 Pad");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem3);

        jMenuBar1.add(jMenu11);
        jMenuBar1.add(Box.createHorizontalGlue());
        //search_field.setLength(10);
        jMenuBar1.add(line_row);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(goto_field);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(search_field);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame3)
                .addGap(0, 0, 0)
                .addComponent(jInternalFrame4))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1)
                .addGap(0, 0, 0)
                .addComponent(jInternalFrame2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame2)
                    .addComponent(jInternalFrame1))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame3)
                    .addComponent(jInternalFrame4))
                .addGap(0, 0, 0))
        );

        getAccessibleContext().setAccessibleName("Frame1");

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jInternalFrame2.setVisible(true);
        jInternalFrame3.setVisible(true);
        jInternalFrame4.setVisible(true);
switch_flag=4;// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jInternalFrame2.setVisible(false);
        jInternalFrame3.setVisible(false);
        jInternalFrame4.setVisible(false);
switch_flag=1;// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jInternalFrame2.setVisible(true);
        jInternalFrame3.setVisible(true);
        jInternalFrame4.setVisible(false);
        switch_flag=3;
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jInternalFrame2.setVisible(true);
        jInternalFrame3.setVisible(false);
        jInternalFrame4.setVisible(false);
    switch_flag=2;// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save1ActionPerformed
        if (filename1.equals("")) {
            saveas1();
        } else {
            save1(filename1);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_save1ActionPerformed

    private void saveas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveas1ActionPerformed
        saveas1();
        // TODO add your handling code here:
    }//GEN-LAST:event_saveas1ActionPerformed

    private void save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save2ActionPerformed
        // TODO add your handling code here:
        if (filename2.equals("")) {
            saveas2();
        } else {
            save2(filename2);
        }
    }//GEN-LAST:event_save2ActionPerformed

    private void saveas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveas2ActionPerformed
        saveas2();
        // TODO add your handling code here:
    }//GEN-LAST:event_saveas2ActionPerformed

    private void textarea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textarea2KeyPressed
        textChanged = true;
        save2.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea2KeyPressed

    private void open1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open1ActionPerformed
        String str4;
        if (textarea1.getText().length() < 1) {
            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                //this.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename1 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename1);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea1.setText(str4);

                }
                setTitle(filename1);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea1.requestFocus();
        } else if (!textChanged) {

            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
               // this.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename1 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename1);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea1.setText(str4);

                }
                setTitle(filename1);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea1.requestFocus();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename1.equals("")) {
                    saveas1();
                } else {
                    save1(filename1);
                }
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                   // this.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename1 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename1);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea1.setText(str4);

                    }
                    setTitle(filename1);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            } else if (confirm == JOptionPane.NO_OPTION) {
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                    //fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename1 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename1);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea1.setText(str4);

                    }
                    setTitle(filename1);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            }
        }

        // TODO add your handling code here:

    }//GEN-LAST:event_open1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        newFile1();
      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void textarea2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textarea2FocusGained
        if (filename2.equals("")) {
            setTitle("MULTIPAD - Untitled");
        } else {
            setTitle(filename2);
        }
        Switchtab = 3;
        
        //Removing Highlights
        removehighlight(textarea1);
        removehighlight(textarea3);
         removehighlight(textarea4);
         
         if(flag!=2)
             goto_field.setVisible(false);revalidate();repaint();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea2FocusGained

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        String str4;
        if (textarea2.getText().length() < 1) {
            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
              // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename2 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename2);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea2.setText(str4);

                }
                setTitle(filename2);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea2.requestFocus();
        } else if (!textChanged) {

            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                //fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename2 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename2);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea2.setText(str4);

                }
                setTitle(filename2);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea2.requestFocus();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename2.equals("")) {
                    saveas2();
                } else {
                    save2(filename2);
                }
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                  // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename2 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename2);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea2.setText(str4);

                    }
                    setTitle(filename2);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            } else if (confirm == JOptionPane.NO_OPTION) {
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                   // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename2 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename2);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea2.setText(str4);

                    }
                    setTitle(filename2);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        if ("".equals(textarea1.getText()) && "".equals(textarea2.getText())) {
            System.exit(0);
        } else if (!textChanged) {
            System.exit(0);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Warning.Some file(s) are not saved. Do you want to exit ?");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        System.exit(0);
       
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ("".equals(textarea1.getText()) && "".equals(textarea2.getText())) {
            System.exit(0);
        } else if (!textChanged) {
            System.exit(0);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Warning.Some file(s) are not saved. Do you want to exit ?");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        }
// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void SwitchtoNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchtoNextMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchtoNextMouseClicked

    private void AlwaysOnTop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlwaysOnTop2ActionPerformed
        if (AlwaysOnTop2.isSelected() == true) {
           this.setResizable(false);

            alwaysontop_lbl.setVisible(true);
           
            //alwaysontop_lbl.setText("Always on Top : ON");

            jInternalFrame2.setVisible(false);
            jInternalFrame3.setVisible(false);
            jInternalFrame4.setVisible(false);
            this.setSize(400, 400);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
            int x = (int) rect.getMaxX() - this.getWidth();
            int y = (int) rect.getMaxY() - 3 * this.getHeight() / 2;
            //int y = this.getHeight();
            this.setLocation(x, y);
            /* this.setLocation((GraphicsEnvironment.getMaximumWindowBounds().getWidth() - this.getWidth()), 0);**/

            this.setAlwaysOnTop(true);
        } else {
            this.setAlwaysOnTop(false);
            alwaysontop_lbl.setVisible(false);
            this.setResizable(true);

            jInternalFrame2.setVisible(true);
            jInternalFrame3.setVisible(true);
            jInternalFrame4.setVisible(true);
            this.setSize(600, 600);
            // this.setSize(400,400);
            this.setLocationRelativeTo(null);

        }
//this.setAlwaysOnTop(true);       
// TODO add your handling code here:
    }//GEN-LAST:event_AlwaysOnTop2ActionPerformed

    private void SwitchNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchNextActionPerformed

        try {
            if (Switchtab == 2) {
                jInternalFrame2.setSelected(true);
            }

            if (Switchtab == 3) {
                if(switch_flag==2)
                    jInternalFrame1.setSelected(true);
                else
                jInternalFrame3.setSelected(true);
            }

            if (Switchtab == 4) {
               if(switch_flag==3)
                   jInternalFrame1.setSelected(true);
               else
                jInternalFrame4.setSelected(true);
               
            }

            if (Switchtab == 1) {
                jInternalFrame1.setSelected(true);
            }
        } catch (Exception e) {
            System.out.println("Caught::" + e);
        }
        // TODO add your handling code here:*/
    }//GEN-LAST:event_SwitchNextActionPerformed

    private void textarea3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textarea3FocusGained
        if (filename3.equals("")) {
            setTitle("MULTIPAD - Untitled");
        } else {
            setTitle(filename3);
        }
        Switchtab = 4;        

//Removing Highlights
        removehighlight(textarea1);
        removehighlight(textarea2);
         removehighlight(textarea4);
         
         //Hide Goto field if focus is lost
         if(flag!=3)
             goto_field.setVisible(false);revalidate();repaint();
// Used for Switching between tabs
    }//GEN-LAST:event_textarea3FocusGained

    private void textarea4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textarea4FocusGained
        Switchtab = 1;
        if (filename4.equals("")) {
            setTitle("MULTIPAD - Untitled");
        } else {
            setTitle(filename4);
        }
        
        
        //Removing Highlights
        removehighlight(textarea1);
        removehighlight(textarea2);
         removehighlight(textarea3);
         
            //Hide Goto field if focus is lost
         if(flag!=4)
             goto_field.setVisible(false);revalidate();repaint();
// Used for Switching between tabs
// TODO add your handling code here:
    }//GEN-LAST:event_textarea4FocusGained

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
search_field.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void SaveAs4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAs4ActionPerformed
        saveas4();
// TODO add your handling code here:
    }//GEN-LAST:event_SaveAs4ActionPerformed

    private void save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save3ActionPerformed
        if (filename3.equals("")) {
            saveas3();
        } else {
            save3(filename3);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_save3ActionPerformed

    private void SaveAs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAs3ActionPerformed
        saveas3();
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveAs3ActionPerformed

    private void textarea3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textarea3KeyPressed
        textChanged = true;
        save3.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea3KeyPressed

    private void textarea4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textarea4KeyPressed

        textChanged = true;
        save4.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_textarea4KeyPressed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        newFile3();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        newFile4();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void save4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save4ActionPerformed
        if (filename4.equals("")) {
            saveas4();
        } else {
            save4(filename4);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_save4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        newFile2();

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        String str4;
        if (textarea3.getText().length() < 1) {
            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
               // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename3 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename3);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea3.setText(str4);

                }
                setTitle(filename3);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea3.requestFocus();
        } else if (!textChanged) {

            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
               // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename3 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename3);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea3.setText(str4);

                }
                setTitle(filename3);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea3.requestFocus();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename3.equals("")) {
                    saveas3();
                } else {
                    save3(filename3);
                }
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                //  fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename3 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename3);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea3.setText(str4);

                    }
                    setTitle(filename3);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            } else if (confirm == JOptionPane.NO_OPTION) {
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                 //  fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename3 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename3);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea3.setText(str4);

                    }
                    setTitle(filename3);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        String str4;
        if (textarea4.getText().length() < 1) {
            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
             //   fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                fd.show();
                if (fd.getFile() != null) {
                    filename4 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename4);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea4.setText(str4);

                }
                setTitle(filename4);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea4.requestFocus();
        } else if (!textChanged) {

            try {
                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
               // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
               // fd.show();
                if (fd.getFile() != null) {
                    filename4 = fd.getDirectory() + fd.getFile();
                    File f = new File(filename4);
                    FileInputStream fobj = new FileInputStream(f);
                    int len = (int) f.length();
                    str4 = "";
                    for (int j = 0; j < len; j++) {
                        char str5 = (char) fobj.read();
                        str4 = str4 + str5;

                    }

                    textarea4.setText(str4);

                }
                setTitle(filename4);
                checkFile();
            } catch (Exception e) {
                System.out.println("Caught::" + e);
            }
            textarea4.requestFocus();
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename4.equals("")) {
                    saveas4();
                } else {
                    save4(filename4);
                }
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                  //fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename4 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename4);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea4.setText(str4);

                    }
                    setTitle(filename4);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            } else if (confirm == JOptionPane.NO_OPTION) {
                try {
                    FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                  // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
                    fd.show();
                    if (fd.getFile() != null) {
                        filename4 = fd.getDirectory() + fd.getFile();
                        File f = new File(filename4);
                        FileInputStream fobj = new FileInputStream(f);
                        int len = (int) f.length();
                        str4 = "";
                        for (int j = 0; j < len; j++) {
                            char str5 = (char) fobj.read();
                            str4 = str4 + str5;

                        }

                        textarea4.setText(str4);

                    }
                    setTitle(filename4);
                    checkFile();
                } catch (Exception e) {
                    System.out.println("Caught::" + e);
                }
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void search_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_fieldKeyReleased
final WordSearcher searcher1 = new WordSearcher(textarea1);

    word = search_field.getText().trim();
        int offset = searcher1.search(word);
        if (offset != -1) {
          try {
            textarea1.scrollRectToVisible(textarea1
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        }
        
        final WordSearcher searcher2 = new WordSearcher(textarea2);

    
        offset = searcher2.search(word);
        if (offset != -1) {
          try {
            textarea2.scrollRectToVisible(textarea2
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        }
        
        final WordSearcher searcher3 = new WordSearcher(textarea3);

    
         offset = searcher3.search(word);
        if (offset != -1) {
          try {
            textarea3.scrollRectToVisible(textarea3
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        }
        
        final WordSearcher searcher4 = new WordSearcher(textarea4);

   
         offset = searcher4.search(word);
        if (offset != -1) {
          try {
            textarea4.scrollRectToVisible(textarea4
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        }
         
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldKeyReleased

    private void search_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_fieldFocusGained
search_field.selectAll();
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldFocusGained

    private void jMenuBar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuBar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1KeyReleased

    private void search_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_fieldFocusLost
if(search_field.getText().equals(""))
    search_field.setText("Search");
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldFocusLost

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void Cut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cut1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cut1ActionPerformed

    private void Copy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Copy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Copy1ActionPerformed

    private void textarea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textarea1KeyPressed
      
            textChanged = true;
            save1.setEnabled(true);
            //}       // TODO add your handling code here:
    }//GEN-LAST:event_textarea1KeyPressed
// TODO add your handling code here:
                                              

    private void textarea1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textarea1FocusGained
        Switchtab = 2;
        if (filename1.equals("")) {
            setTitle("MULTIPAD - Untitled");
        } else {
            setTitle(filename1);
        }
       
        removehighlight(textarea2);
        removehighlight(textarea3);
         removehighlight(textarea4);
         
         if(flag!=1)
             goto_field.setVisible(false);revalidate();repaint();
        
        //Get ROWS and Columns
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea1FocusGained

    private void undo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undo1ActionPerformed

    
        // TODO add your handling code here:
    }//GEN-LAST:event_undo1ActionPerformed

    private void selectall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectall1ActionPerformed
textarea1.selectAll();        // TODO add your handling code here:
    }//GEN-LAST:event_selectall1ActionPerformed

    private void selectall2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectall2ActionPerformed
    textarea2.selectAll();    // TODO add your handling code here:
    }//GEN-LAST:event_selectall2ActionPerformed

    private void selectall3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectall3ActionPerformed
      textarea3.selectAll();  // TODO add your handling code here:
    }//GEN-LAST:event_selectall3ActionPerformed

    private void selectall4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectall4ActionPerformed
      textarea4.selectAll();  // TODO add your handling code here:
    }//GEN-LAST:event_selectall4ActionPerformed

    private void textarea1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textarea1CaretUpdate

        
         int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = textarea1.getCaretPosition();
                    linenum = textarea1.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - textarea1.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }
                
                line_row.setText("Row:"+Integer.toString(linenum)+" "+"Col:"+Integer.toString(columnnum));
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea1CaretUpdate

    private void textarea2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textarea2CaretUpdate
int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = textarea2.getCaretPosition();
                    linenum = textarea2.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - textarea2.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }
                
                line_row.setText("Row:"+Integer.toString(linenum)+" "+"Col:"+Integer.toString(columnnum));
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea2CaretUpdate

    private void textarea3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textarea3CaretUpdate
int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = textarea3.getCaretPosition();
                    linenum = textarea3.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - textarea3.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }
                
                line_row.setText("Row:"+Integer.toString(linenum)+" "+"Col:"+Integer.toString(columnnum));
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea3CaretUpdate

    private void textarea4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textarea4CaretUpdate
int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = textarea4.getCaretPosition();
                    linenum = textarea4.getLineOfOffset(caretpos);

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = caretpos - textarea4.getLineStartOffset(linenum);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    linenum += 1;
                }
                catch(Exception ex) { }
                
                line_row.setText("Row:"+Integer.toString(linenum)+" "+"Col:"+Integer.toString(columnnum));
        // TODO add your handling code here:
    }//GEN-LAST:event_textarea4CaretUpdate

    private void redo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redo1ActionPerformed

    private void goto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goto1ActionPerformed
//goto_field.setVisible(true);
        flag=1;

goto_field.setVisible(true);
revalidate();
repaint();
goto_field.requestFocus();



// TODO add your handling code here:
    }//GEN-LAST:event_goto1ActionPerformed

    private void goto_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_goto_fieldKeyReleased
        // TODO add your handling code here:
       if(flag==1)
             gotomethod(textarea1);
       
    
       if(flag==2)
              gotomethod(textarea2);
       
    
       if(flag==3)
              gotomethod(textarea3);
        
    
       if(flag==4)
          gotomethod(textarea4);
        
         
    }//GEN-LAST:event_goto_fieldKeyReleased

    private void goto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goto2ActionPerformed
        flag=2;
        goto_field.setVisible(true);
        revalidate();
        repaint();
        goto_field.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_goto2ActionPerformed

    private void goto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goto3ActionPerformed
flag=3;
goto_field.setVisible(true);
        revalidate();
        repaint();
goto_field.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_goto3ActionPerformed

    private void goto4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goto4ActionPerformed
flag=4;
goto_field.setVisible(true);
        revalidate();
        repaint();
goto_field.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_goto4ActionPerformed

    private void goto_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_goto_fieldFocusGained
goto_field.selectAll();        // TODO add your handling code here:
    }//GEN-LAST:event_goto_fieldFocusGained

    private void goto_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_goto_fieldFocusLost

    if(goto_field.getText().equals(""))
        goto_field.setText("GoTo");
    
        // TODO add your handling code here:
    }//GEN-LAST:event_goto_fieldFocusLost

    private void replace_find_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace_find_btnActionPerformed
final WordSearcher searcher1 = new WordSearcher(textarea1);

    word = replace_find_field.getText().trim();
        int offset = searcher1.search(word);
        if (offset != -1) {
          try {
            textarea1.scrollRectToVisible(textarea1
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_replace_find_btnActionPerformed

    private void replace1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace1ActionPerformed
replace_mark=1;
jDialog1.setVisible(true);
jDialog1.setSize(480,250);
jDialog1.setTitle("Find and Replace");
jDialog1.setLocation(200,200);
        // TODO add your handling code here:
    }//GEN-LAST:event_replace1ActionPerformed

    private void replaceall_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceall_btnActionPerformed
if(replace_mark==1)
{
final WordSearcher searcher1 = new WordSearcher(textarea1);
searcher1.flag_replace=1;
searcher1.replace_field=replace_field.getText();
    word = replace_find_field.getText().trim();
        int offset = searcher1.search(word);
        if (offset != -1) {
          try {
            textarea1.scrollRectToVisible(textarea1
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        } 
}

if(replace_mark==2)
{
final WordSearcher searcher2 = new WordSearcher(textarea2);
searcher2.flag_replace=1;
searcher2.replace_field=replace_field.getText();
    word = replace_find_field.getText().trim();
        int offset = searcher2.search(word);
        if (offset != -1) {
          try {
            textarea2.scrollRectToVisible(textarea2
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        } 
}

if(replace_mark==3)
{
final WordSearcher searcher3 = new WordSearcher(textarea3);
searcher3.flag_replace=1;
searcher3.replace_field=replace_field.getText();
    word = replace_find_field.getText().trim();
        int offset = searcher3.search(word);
        if (offset != -1) {
          try {
            textarea3.scrollRectToVisible(textarea3
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        } 
}

if(replace_mark==4)
{
final WordSearcher searcher4 = new WordSearcher(textarea4);
searcher4.flag_replace=1;
searcher4.replace_field=replace_field.getText();
    word = replace_find_field.getText().trim();
        int offset = searcher4.search(word);
        if (offset != -1) {
          try {
            textarea4.scrollRectToVisible(textarea4
                .modelToView(offset));
          } catch (BadLocationException e) {
          }
        } 
}// TODO add your handling code here:
    }//GEN-LAST:event_replaceall_btnActionPerformed

    private void replace2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace2ActionPerformed
replace_mark=2;
jDialog1.setVisible(true);
jDialog1.setSize(480,250);
jDialog1.setTitle("Find and Replace");
jDialog1.setLocation(200,200);        // TODO add your handling code here:
    }//GEN-LAST:event_replace2ActionPerformed

    private void replace3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace3ActionPerformed
replace_mark=3;
jDialog1.setVisible(true);
jDialog1.setSize(480,250);
jDialog1.setTitle("Find and Replace");
jDialog1.setLocation(200,200);
        // TODO add your handling code here:
    }//GEN-LAST:event_replace3ActionPerformed

    private void replace4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace4ActionPerformed
replace_mark=4;
jDialog1.setVisible(true);
jDialog1.setSize(480,250);
jDialog1.setTitle("Find and Replace");
jDialog1.setLocation(200,200);        // TODO add your handling code here:
    }//GEN-LAST:event_replace4ActionPerformed

    private void replace_find_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replace_find_fieldActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_replace_find_fieldActionPerformed

    private void replace_find_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_replace_find_fieldKeyReleased
if(replace_find_field.getText().equals(""))
    replaceall_btn.setEnabled(false);
else
    replaceall_btn.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_replace_find_fieldKeyReleased

    private void select_all_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_all_1ActionPerformed
        // TODO add your handling code here:
        if(Switchtab==2)
            textarea1.selectAll();
          if(Switchtab==3)
            textarea2.selectAll();
            if(Switchtab==4)
            textarea3.selectAll();
              if(Switchtab==1)
            textarea4.selectAll();
    }//GEN-LAST:event_select_all_1ActionPerformed

    private void Paste1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Paste1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Paste1ActionPerformed

    private void list_sizeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_sizeValueChanged
if(list_size.getSelectedIndex()==-1)
{}
else
    font_size_field.setText(list_size.getSelectedValue().toString());
font_size_field.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_list_sizeValueChanged

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
font_dialog.setTitle("Font Format");
font_dialog.setVisible(true);

font_dialog.setSize(420,600);

// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void list_styleValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_styleValueChanged
   if(list_style.getSelectedIndex()==-1)
{}
else
       if(list_style.getSelectedValue().toString()=="Plain")
           font_style_field.setText("0");
   
    if(list_style.getSelectedValue().toString()=="BOLD")
           font_style_field.setText("1");
    
     if(list_style.getSelectedValue().toString()=="Italic")
           font_style_field.setText("2");
     
      if(list_style.getSelectedValue().toString()=="BOLD Italic")
           font_style_field.setText("3");
      font_style_field.requestFocus();
   // font_style_field.setText(list_style.getSelectedValue().toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_list_styleValueChanged

    private void list_fontValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_fontValueChanged
if(list_font.getSelectedIndex()==-1)
{}
else
    font_field.setText(list_font.getSelectedValue().toString()); 
font_field.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_list_fontValueChanged

    private void font_fieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_font_fieldInputMethodTextChanged
sample_lbl.setFont(new java.awt.Font(font_field.getText(), 0, 14));        // TODO add your handling code here:
    }//GEN-LAST:event_font_fieldInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
textarea1.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText()))); 
if(Switchtab==2)
textarea1.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText()))); 
if(Switchtab==3)
textarea2.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText()))); 
if(Switchtab==4)
textarea3.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText()))); 
if(Switchtab==1)
textarea4.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText()))); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void font_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_font_fieldKeyReleased
sample_lbl.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText())));         // TODO add your handling code here:
    }//GEN-LAST:event_font_fieldKeyReleased

    private void font_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_font_fieldFocusGained
sample_lbl.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText())));           // TODO add your handling code here:
    }//GEN-LAST:event_font_fieldFocusGained

    private void font_style_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_font_style_fieldFocusGained
sample_lbl.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText())));           // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_font_style_fieldFocusGained

    private void font_size_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_font_size_fieldFocusGained
sample_lbl.setFont(new java.awt.Font(font_field.getText(), Integer.parseInt(font_style_field.getText()), Integer.parseInt(font_size_field.getText())));           // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_font_size_fieldFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
font_field.setText("Consolas");
font_style_field.setText("0");
font_size_field.setText("14");
sample_lbl.setFont(new java.awt.Font("Consolas",0,14));
list_font.clearSelection();
list_style.setSelectedIndex(0);
list_size.setSelectedIndex(5);
if(Switchtab==2)
    textarea1.setFont(new java.awt.Font("Consolas",0,14));
if(Switchtab==3)
    textarea2.setFont(new java.awt.Font("Consolas",0,14));
if(Switchtab==4)
    textarea3.setFont(new java.awt.Font("Consolas",0,14));
if(Switchtab==1)
    textarea4.setFont(new java.awt.Font("Consolas",0,14));

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void font_popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_font_popActionPerformed
font_dialog.setTitle("Font Format");
font_dialog.setVisible(true);
font_dialog.setSize(420,600);        // TODO add your handling code here:
    }//GEN-LAST:event_font_popActionPerformed

    private void textarea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textarea1FocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_textarea1FocusLost

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
shortcut_list.setLocationRelativeTo(null);

        shortcut_list.setVisible(true);
shortcut_list.setSize(400,580);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
about_dialog.setLocationRelativeTo(null);
        about_dialog.setVisible(true);
about_dialog.setSize(400,400);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed

      // textpad obj1=new textpad();
       
        contact_dialog.setLocationRelativeTo(null);

        contact_dialog.setVisible(true);
contact_dialog.setSize(400,400);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void minimize_winActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimize_winActionPerformed
this.setState(1);        // TODO add your handling code here:
    }//GEN-LAST:event_minimize_winActionPerformed

    private void maximize_winActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximize_winActionPerformed
    this.setExtendedState(MAXIMIZED_BOTH);        // TODO add your handling code here:
    }//GEN-LAST:event_maximize_winActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
       tipsdialog.setLocationRelativeTo(null);

        tipsdialog.setVisible(true);
tipsdialog.setSize(700,300);  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {    
        
       
        
        
      
      if (args.length > 0) {
            new textpad().openFile(args[0]);
            double_fn=args[0];
     }
        //java.io.File f = new java.io.File("E:/HighlightExample.java");
      
       
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(textpad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(textpad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(textpad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(textpad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
             
               new textpad().setVisible(true);
                 new textpad().setAlwaysOnTop(true);  
                //new textpad().setAlwaysOnTop(true);
                
            }
        });
        /* SwingUtilities.invokeLater(new Runnable() {

         @Override //annotation if you are using Java >= 1.5
         public void run() {
         JFrame f = new textpad();
           
         // f.setAlwaysOnTop();
         f.setVisible(true);
         }
         });*/

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem AlwaysOnTop2;
    private javax.swing.JMenuItem Copy1;
    private javax.swing.JMenuItem Cut1;
    private javax.swing.JMenuItem Paste1;
    private javax.swing.JMenuItem SaveAs3;
    private javax.swing.JMenuItem SaveAs4;
    private javax.swing.JMenuItem SwitchNext;
    private javax.swing.JMenuBar SwitchtoNext;
    private javax.swing.JDialog about_dialog;
    private javax.swing.JMenu about_menu;
    private javax.swing.JLabel alwaysontop_lbl;
    private javax.swing.JDialog contact_dialog;
    private javax.swing.JMenuItem exit;
    private javax.swing.JDialog font_dialog;
    private javax.swing.JTextField font_field;
    private javax.swing.JMenuItem font_pop;
    private javax.swing.JTextField font_size_field;
    private javax.swing.JTextField font_style_field;
    private javax.swing.JMenuItem goto1;
    private javax.swing.JMenuItem goto2;
    private javax.swing.JMenuItem goto3;
    private javax.swing.JMenuItem goto4;
    private javax.swing.JTextField goto_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel line_row;
    private javax.swing.JList list_font;
    private javax.swing.JList list_size;
    private javax.swing.JList list_style;
    private javax.swing.JMenuItem maximize_win;
    private javax.swing.JMenuItem minimize_win;
    private javax.swing.JMenuItem open1;
    private javax.swing.JMenu open2;
    private javax.swing.JPopupMenu popmenu1;
    private javax.swing.JMenuItem redo1;
    private javax.swing.JMenuItem replace1;
    private javax.swing.JMenuItem replace2;
    private javax.swing.JMenuItem replace3;
    private javax.swing.JMenuItem replace4;
    private javax.swing.JTextField replace_field;
    private javax.swing.JButton replace_find_btn;
    private javax.swing.JTextField replace_find_field;
    private javax.swing.JButton replaceall_btn;
    private javax.swing.JLabel sample_lbl;
    private javax.swing.JMenuItem save1;
    private javax.swing.JMenuItem save2;
    private javax.swing.JMenuItem save3;
    private javax.swing.JMenuItem save4;
    private javax.swing.JMenuItem saveas1;
    private javax.swing.JMenuItem saveas2;
    private javax.swing.JTextField search_field;
    private javax.swing.JMenuItem select_all_1;
    private javax.swing.JMenuItem selectall1;
    private javax.swing.JMenuItem selectall2;
    private javax.swing.JMenuItem selectall3;
    private javax.swing.JMenuItem selectall4;
    private javax.swing.JDialog shortcut_list;
    private javax.swing.JTextArea textarea1;
    private javax.swing.JTextArea textarea2;
    private javax.swing.JTextArea textarea3;
    private javax.swing.JTextArea textarea4;
    private javax.swing.JTable timer_table;
    private javax.swing.JDialog tipsdialog;
    private javax.swing.JMenuItem undo1;
    private javax.swing.JMenu useful;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void saveas1() {
        FileDialog fd = new FileDialog(textpad.this, "Save1", FileDialog.SAVE);
       // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        fd.show();
        if (fd.getFile() != null) {
            if(fd.getFile().indexOf(".")!=-1)
            {
            fn1 = fd.getFile();
            dir1 = fd.getDirectory();
            filename1 = dir1 + fn1;
            }
            else
            {
                fn1 = fd.getFile();
            dir1 = fd.getDirectory();
            filename1 = dir1 + fn1+".txt";
            }
            setTitle(filename1);
            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(filename1));
                holdText1 = textarea1.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText1));
                while ((holdText1 = br.readLine()) != null) {
                    d.writeBytes(holdText1 + "\r\n");
                    d.close();
                }
            } catch (Exception e) {
                System.out.println("File not Found");
            }
            textarea1.requestFocus();
            save1(filename1);
        }
        else{
            System.out.println("Blank name");
        }
//  throw new UnsupportedOperationException("Not supported yet.");
        //  setTitle(filename);
//To change body of generated methods, choose Tools | Templates.
    }

    private void save1(String filename1) {
        setTitle(filename1);
        try {
            /*FileWriter out;
             out=new Filewriter(fn);
             out.write(textarea1.getText());
             out.close();*/
            FileOutputStream obj1 = new FileOutputStream(filename1);
            obj1.write(textarea1.getText().getBytes());
            obj1.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        textChanged = false;
        save1.setEnabled(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveas2() {
        FileDialog fd = new FileDialog(textpad.this, "Save2", FileDialog.SAVE);
       //fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        fd.show();
        if (fd.getFile() != null) {
          if(fd.getFile().indexOf(".")!=-1)
            {
            fn2 = fd.getFile();
            dir2 = fd.getDirectory();
            filename2 = dir2 + fn2;
            }
            else
            {
                fn2 = fd.getFile();
            dir2 = fd.getDirectory();
            filename2 = dir2 + fn2+".txt";
            }
            setTitle(filename2);
            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(filename2));
                holdText2 = textarea1.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText2));
                while ((holdText2 = br.readLine()) != null) {
                    d.writeBytes(holdText2 + "\r\n");
                    d.close();
                }
            } catch (Exception e) {
                System.out.println("File not Found");
            }
            textarea2.requestFocus();
            save2(filename2);

        }
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void save2(String filename) {
        setTitle(filename2);
        try {
            /*FileWriter out;
             out=new Filewriter(fn);
             out.write(textarea1.getText());
             out.close();*/
            FileOutputStream obj2 = new FileOutputStream(filename2);
            obj2.write(textarea2.getText().getBytes());
            obj2.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        textChanged = false;
        save2.setEnabled(false);

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveas3() {
        FileDialog fd = new FileDialog(textpad.this, "Save3", FileDialog.SAVE);
       // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        fd.show();
        if (fd.getFile() != null) {
            if(fd.getFile().indexOf(".")!=-1)
            {
            fn3 = fd.getFile();
            dir3 = fd.getDirectory();
            filename3 = dir3 + fn3;
            }
            else
            {
                fn3 = fd.getFile();
            dir3 = fd.getDirectory();
            filename3 = dir3 + fn3+".txt";
            }
            setTitle(filename3);
            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(filename3));
                holdText3 = textarea3.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText3));
                while ((holdText3 = br.readLine()) != null) {
                    d.writeBytes(holdText3 + "\r\n");
                    d.close();
                }
            } catch (Exception e) {
                System.out.println("File not Found");
            }
            textarea3.requestFocus();
            save3(filename3);
        }
//  throw new UnsupportedOperationException("Not supported yet.");
        //  setTitle(filename);
//To change body of generated methods, choose Tools | Templates.
    }

    private void save3(String filename3) {
        setTitle(filename3);
        try {
          
            FileOutputStream obj1 = new FileOutputStream(filename3);
            obj1.write(textarea3.getText().getBytes());
            obj1.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        textChanged = false;
        save3.setEnabled(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveas4() {
        FileDialog fd = new FileDialog(textpad.this, "Save4", FileDialog.SAVE);
      // fd.setIconImage(new ImageIcon(getClass().getResource("logo_multipad_kevin.png")).getImage());
        fd.show();
        if (fd.getFile() != null) {
           
            if(fd.getFile().indexOf(".")!=-1)
            {
            fn4 = fd.getFile();
            dir4 = fd.getDirectory();
            filename4 = dir4 + fn4;
            }
            else
            {
                fn4 = fd.getFile();
            dir4 = fd.getDirectory();
            filename4 = dir4 + fn4+".txt";
            }
            setTitle(filename4);
            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(filename4));
                holdText4 = textarea4.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText4));
                while ((holdText4 = br.readLine()) != null) {
                    d.writeBytes(holdText4 + "\r\n");
                    d.close();
                }
            } catch (Exception e) {
                System.out.println("File not Found");
            }
            textarea4.requestFocus();
            save4(filename4);
        }
//  throw new UnsupportedOperationException("Not supported yet.");
        //  setTitle(filename);
//To change body of generated methods, choose Tools | Templates.
    }

    private void save4(String filename4) {
        setTitle(filename4);
        try {
            /*FileWriter out;
             out=new Filewriter(fn);
             out.write(textarea1.getText());
             out.close();*/
            FileOutputStream obj1 = new FileOutputStream(filename4);
            obj1.write(textarea4.getText().getBytes());
            obj1.close();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
        textChanged = false;
        save4.setEnabled(false);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void newFile1() {
        if (textarea1.getText().length() < 1) {
            filename1 = "";
            textarea1.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");
        } else if (!textChanged) {
            filename1 = "";
            textarea1.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
           
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename1.equals("")) {
                    saveas1();
                } else {
                    save1(filename1);
                }

                filename1 = "";
                textarea1.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            } else if (confirm == JOptionPane.NO_OPTION) {
                filename1 = "";
                textarea1.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    // NEW FILE EVENT 2
    private void newFile2() {
        if (textarea2.getText().length() < 1) {
            filename2 = "";
            textarea2.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");
        } else if (!textChanged) {
            filename2 = "";
            textarea2.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename2.equals("")) {
                    saveas2();
                } else {
                    save2(filename1);
                }

                filename2 = "";
                textarea2.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            } else if (confirm == JOptionPane.NO_OPTION) {
                filename2 = "";
                textarea2.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    //NEW FILE EVENT 3
    private void newFile3() {
        if (textarea1.getText().length() < 1) {
            filename3 = "";
            textarea3.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");
        } else if (!textChanged) {
            filename3 = "";
            textarea3.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename3.equals("")) {
                    saveas3();
                } else {
                    save3(filename1);
                }

                filename3 = "";
                textarea3.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            } else if (confirm == JOptionPane.NO_OPTION) {
                filename3 = "";
                textarea3.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    //NEW FILE EVENT 4
    private void newFile4() {
        if (textarea4.getText().length() < 1) {
            filename4 = "";
            textarea4.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");
          
        } else if (!textChanged) {
            filename4 = "";
            textarea4.setText("");
            textChanged = false;
            setTitle("MULTIPAD-Untitled");

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "This File is not saved. Do you want to save before exiting?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (filename4.equals("")) {
                    saveas4();
                } else {
                    save4(filename4);
                }

                filename4 = "";
                textarea4.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            } else if (confirm == JOptionPane.NO_OPTION) {
                filename4 = "";
                textarea4.setText("");
                textChanged = false;
                setTitle("MULTIPAD-Untitled");
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    

    
    // Finding or Searching a word
 
}
class WordSearcher {
    public int start_replace=0,end_replace=0,flag_replace=0;
    public String replace_field;
  public WordSearcher(JTextArea comp) {
    this.comp = comp;
   // this.painter = new UnderlineHighlighter.UnderlineHighlightPainter(
     //   Color.red);
      this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
  }

 
  // Search for a word and return the offset of the
  // first occurrence. Highlights are added for all
  // occurrences found.
  public int search(String word) {
    int firstOffset = -1;
    Highlighter highlighter = comp.getHighlighter();

    // Remove any existing highlights for last word
    Highlighter.Highlight[] highlights = highlighter.getHighlights();
    for (int i = 0; i < highlights.length; i++) {
      Highlighter.Highlight h = highlights[i];
      if (h.getPainter() instanceof DefaultHighlighter.DefaultHighlightPainter) {
        highlighter.removeHighlight(h);
      }
    }

    if (word == null || word.equals("")) {
      return -1;
    }

    // Look for the word we are given - insensitive search
    String content = null;
    try {
      Document d = comp.getDocument();
      content = d.getText(0, d.getLength()).toLowerCase();
    } catch (BadLocationException e) {
      // Cannot happen
      return -1;
    }

    word = word.toLowerCase();
     int lastIndex = 0;
    int wordSize = word.length();

    while ((lastIndex = content.indexOf(word, lastIndex)) != -1) {
      int endIndex = lastIndex + wordSize;
      try {
        highlighter.addHighlight(lastIndex, endIndex, painter);
        
        //REPLACE word
        if(flag_replace==1) 
        comp.replaceRange(replace_field, lastIndex, endIndex);
        
        start_replace=lastIndex;
        end_replace=endIndex;
      } catch (BadLocationException e) {
   
        // Nothing to do
      }
      if (firstOffset == -1) {
        firstOffset = lastIndex;
      }
      lastIndex = endIndex;
    }
  
    return firstOffset;
  }

  protected JTextArea comp;

  protected Highlighter.HighlightPainter painter;

}





