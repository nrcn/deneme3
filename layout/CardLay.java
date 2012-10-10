package layout;

/*
 * CardLayoutDemo.java
 *
 */
import HandleServer;
import Server_Threaded;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
//import java.util.*;
import javax.swing.*;

public class CardLay implements ItemListener, ActionListener
{
	
    JPanel cards; //a panel that uses CardLayout
    final static String msgNull = "   ";
    final static String msg1 = "80";
    final static String msg2 = "110";
    
    JTextField int1a, int1b, int1c, int1d, int2a, int2b, int2c;
    JButton entBut,entBut2, connectBut;
    JLabel lab;  //east1, east2;
    TextArea east;
    String s1,s2,s3,s4;
    
    //for connection
    ServerSocket listenSocket;
    Socket connection;

    InputStream inStream;
    DataInputStream inDataStream;
    
    OutputStream outStream;
    DataOutputStream outDataStream;

    String message, mes2, mes3, message2;
    
    public static final int DEFAULT_PORT = 8804;
    String host;
    int port = DEFAULT_PORT;

    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(new FlowLayout(0,0,FlowLayout.LEADING)); //use FlowLayout
        String comboBoxItems[] = { msgNull, msg1, msg2 };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        
        JLabel enterLabel = new JLabel("enter msg num: ");
        
        comboBoxPane.add(enterLabel);
        comboBoxPane.add(cb);
        
        //Create the "cards".
        //card1
        JPanel msgNullPane = new JPanel();
               
        //card2
        JPanel msgPane1 = new JPanel(new FlowLayout(0,0,FlowLayout.LEADING));
      
        
        JLabel paraIn1a = new JLabel("enter int1: ");
        int1a = new JTextField();
        int1a.setPreferredSize(new Dimension(200,30));
        
        JLabel paraIn1b = new JLabel("enter intger2: ");
        int1b = new JTextField();
        int1b.setPreferredSize(new Dimension(200,30));
        
        JLabel paraIn1c = new JLabel("enter parameter3: ");
        int1c = new JTextField();
        int1c.setPreferredSize(new Dimension(200,30));
        
        JLabel paraIn1d = new JLabel("enter intger4: ");
        int1d = new JTextField();
        int1d.setPreferredSize(new Dimension(200,30));
        
        
        entBut = new JButton("ENTER");
        entBut.addActionListener(this);
        
        lab = new JLabel("---");
        
        msgPane1.add(paraIn1a);
        msgPane1.add(int1a);
        
       
        msgPane1.add(paraIn1b);
        msgPane1.add(int1b);

       
        msgPane1.add(paraIn1c);
        msgPane1.add(int1c);
        
        msgPane1.add(paraIn1d);
        msgPane1.add(int1d);
        
        msgPane1.add(entBut);
        msgPane1.add(lab);
        
       
        //card3
        JPanel msgPane2 = new JPanel(new FlowLayout(0,0,FlowLayout.LEFT));
        
        
        JLabel paraIn2a = new JLabel("enter intger1: ");
        int2a = new JTextField();
        int2a.setPreferredSize(new Dimension(100,30));
        
        
        JLabel paraIn2b = new JLabel("enter intger2: ");
        int2b = new JTextField();
        int2b.setPreferredSize(new Dimension(100,30));
        
        JLabel paraIn2c = new JLabel("enter intger3: ");
        int2c = new JTextField();
        int2c.setPreferredSize(new Dimension(100,30));
       
        entBut2 = new JButton("ENTER");
        entBut2.addActionListener(this);
        
        
        msgPane2.add(paraIn2a);
        msgPane2.add(int2a);
        
        msgPane2.add(paraIn2b);
        msgPane2.add(int2b);
        
        msgPane2.add(paraIn2c);
        msgPane2.add(int2c);
        msgPane2.add(entBut2);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(msgNullPane, msgNull);
        cards.add(msgPane1, msg1);
        cards.add(msgPane2, msg2);
        
        //panels to order layout
        
        // EAST PANEL COMPONENTS...
        //east1 = new JLabel("Messages from client...");
        //east2 = new JLabel("---");
        east = new TextArea("Messages from client... \n");
        
        
        JPanel eastPanel = new JPanel(new BorderLayout());
        //eastPanel.setBackground(Color.GRAY);
        eastPanel.setPreferredSize(new Dimension(500,300));
        
        //eastPanel.add(east1, BorderLayout.PAGE_START);
        //eastPanel.add(east2, BorderLayout.CENTER);
        eastPanel.add(east, BorderLayout.PAGE_START);
       
        JPanel southPanel = new JPanel(new FlowLayout(0,0,FlowLayout.LEADING));
        southPanel.setPreferredSize(new Dimension(800,100));
         
        connectBut = new JButton("CONNECT SERVER");   
        connectBut.addActionListener(this);
         
        southPanel.add(connectBut);
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
        //pane.add(westPanel, BorderLayout.WEST);
        pane.add(eastPanel, BorderLayout.EAST);
        pane.add(southPanel, BorderLayout.SOUTH);
    }
    
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object s = e.getSource();
		
		//connectClient ();
    	try  {

    	      listenSocket = new ServerSocket ( port );
    	      

    	      try
    	      {
    	        InetAddress here = InetAddress.getLocalHost ();
    	        String host = here.getHostName ();
    	       east.appendText(host + "is received as host name... \n");
    	       }
    	      catch (UnknownHostException e1) 
    	      {
    	    	  e1.printStackTrace();
    	       }

    	      connection = listenSocket.accept ();
    	      east.appendText(" connection is accepted... \n");

    	      outStream = connection.getOutputStream ();
    	      outDataStream = new DataOutputStream ( outStream );
    	      
    	      
    	      inStream = connection.getInputStream();
    	      inDataStream = new DataInputStream( inStream );
    	      
    	      east.appendText(" objects are created... \n");

    	 }  // end try
	    catch ( IOException except)
	    {
	      except.printStackTrace ();
	    }  
		
		if ( s instanceof JButton )  {


	    	if( s == connectBut )
			{
	    		 east.appendText(" connectBut is entered... \n");
				try {
					
					while(true)
					{
						//connection = listenSocket.accept ();
						east.appendText(" IN WHILEEEEE... \n");
				            message = inDataStream.readUTF ();
				            east.appendText ( "\n" +message+"\n" );
				            
				            message2 = "\n" + "These parameters are received from client: \n" + message;
				            outDataStream.writeUTF (  message2 + "\n" );
				       
					}
				         
				         // end while
				    }  // end try for input
				 catch ( EOFException except )
				 {
				            try {
								connection.close ();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				            east.appendText ( "Connection closed\n" );
				    }  // end catch
				catch ( IOException except ) {
				        System.out.println ( "IO Exception");
				        except.printStackTrace ();
				        }  // end catch
				 east.appendText(" while ve connectBut dan ciktim... \n");
			}
			
			
			if ( s == entBut )  
	        {
	        	s1 = int1a.getText();
	        	s2 = int1b.getText();
	        	s3 = int1c.getText();
	        	s4 = int1d.getText();
	        	
	        	message = "message-80 ";
	        	mes2 = "is sent to client. \n";
	        	mes3 = 	"\n s1="+ s1 + "\n s2="+ s2 +"\n s3="+ s3 + "\n s4="+ s4;
	        	
	        	
	        	      try 
	        	      {
						outDataStream.writeUTF ( message + "is sent from  server." + mes3 );
					} 
	        	      catch (IOException e1)
	        	      {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	      
	        	      try {
	        	          
	        	          message = inDataStream.readUTF ();
	        	          east.appendText("\n" + message);
	        	          
	        	    
	        	        }  // end try for input
	        	     catch ( EOFException except ) {
	        	          try 
	        	          {
							connection.close ();
						}
	        	          catch (IOException e1)
	        	          {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	         
	        	      }  // end catch
	        	     catch ( IOException except ) {
	        	      System.out.println ( "IO Exception");
	        	      except.printStackTrace ();
	        	      }  // end catch
	        	   
	        	      
	        	   //   connection.close ();

	        	   // end catch
	            
	        }  // end listenButton

	        if ( s == entBut2 )  
	        {
	        	s1 = int2a.getText();
	        	s2 = int2b.getText();
	        	s3 = int2c.getText();
	        	
	        	
	        	message ="message-110 is sent from server.\n " +
	        			"\n s1="+ s1 + "\n s2="+ s2 +"\n s3="+ s3;
	        	
	        	
	        	      try 
	        	      {
						outDataStream.writeUTF ( message );
					} 
	        	      catch (IOException e1)
	        	      {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	      
	        	      try {
	        	          
	        	          message = inDataStream.readUTF ();
	        	          east.appendText(message);
	        	          
	        	    
	        	        }  // end try for input
	        	     catch ( EOFException except ) {
	        	          try 
	        	          {
							connection.close ();
						}
	        	          catch (IOException e1)
	        	          {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        	         
	        	      }  // end catch
	        	     catch ( IOException except ) {
	        	      System.out.println ( "IO Exception");
	        	      except.printStackTrace ();
	        	      }  // end catch
	        	   
	        	      
	        	      

	        	   // end catch
	            
	        }
	        
	      // end listenButton
	    }  // end 
		 east.appendText(" instance dan cýktým... \n");
		/*try 
		
			connection.close ();
		}
		 catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} */
		
		 east.appendText(" actionListener dan cýktým... \n");
	}
    
    // class ListenServer
    class ListenServer extends Thread
    {
    	CardLay source;
    	ServerSocket listenSocket;
    	int port;
    	Socket connection;
    	HandleServer handle;
    	boolean again = true;


    // **************  ListenServer constructor

        ListenServer ( CardLay s)  {

            super ();

            source = (CardLay ) s;

        }  // end constructor


    // **************  run

        public void run  ()  {

           
            try  {

                listenSocket = new ServerSocket ( port );
                source.east.setText ( "Server started:\n  listening on port " + port + "\n\n" );

                while ( true )  {

                    Socket connection = listenSocket.accept();
                    HandleServer handleServer = new HandleServer ( connection, source );
                    handleServer.start ();

                }  // end while

        }  catch ( IOException e )  {
            e.printStackTrace ();
            source.east.setText ( "Error is opening ServerSocket\n" );
            System.exit ( 1 );
        }  // end catch


    }  // end run


   }  // end ListenServer

    class HandleServer extends Thread  {

    	CardLay source;
    	Socket connection;

    	InputStream inStream;
    	DataInputStream inDataStream;
    	OutputStream outStream;
    	DataOutputStream outDataStream;

    	String message;


    	// **************  HandleServer constructor

    	    HandleServer ( Socket socket, CardLay s)  {

    	        super ();
    	        connection =  socket;
    	        source =  s;

    	    }  // end constructor


    	// **************  run

    	    public void run  ()  {

    	        String stringIn, stringOut;
    	        boolean again = true;

    	        InetAddress inet = connection.getInetAddress ();
    	        String origin = inet.getHostName ();
    	        int originport = connection.getPort ();

    	        source.east.appendText ( "Adding Client: \n  "+origin+":"+originport+"\n\n" );


    	        try  {

    	            outStream = connection.getOutputStream ();
    	            outDataStream = new DataOutputStream ( outStream );
    	            inStream = connection.getInputStream ();
    	            inDataStream = new DataInputStream ( inStream );

    	            while ( true )  {

    	                message = inDataStream.readUTF ();
    	                source.east.appendText ( "  Message, below, received\n" );
    	                source.east.setForeground ( Color.red );
    	                source.msgDisplay.setText ( message );

    	                outDataStream.writeUTF ( message );
    	                source.logDisplay.appendText ( "  Message returned to client \n\n" );

    	            }  // end while

    	        }  // end try

    	        catch ( EOFException except ) {
    	            source.logDisplay.appendText ( "  Connection closed by Client\n\n" );
    	            try  {
    	                connection.close ();
    	                return;
    	            }
    	            catch ( IOException e )  {
    	                e.printStackTrace ();
    	                return;
    	            }  // end IOException

    	         }  // end catch EOFException
    	         catch ( IOException e )  {
    	            source.logDisplay.appendText ( "  Connection closed abormally\n" );
    	            e.printStackTrace ();
    	            return;
    	         }  // end catch IOException


    	    }  // end run


    	}  // end HandleServer

    
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     * @throws UnsupportedLookAndFeelException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
    	//final int WID = 500;
    	//final int LEN = 300;
    	
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 300));
        
        //Create and set up the content pane.
        CardLay demo = new CardLay();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        //UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    	    }

	
	
}