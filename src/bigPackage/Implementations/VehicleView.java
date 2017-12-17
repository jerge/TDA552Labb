package bigPackage.Implementations;


import bigPackage.models.AbstractModels.AMotorisedVehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class VehicleView extends JFrame {
    private final int X = 800;
    private final int Y = 800;

    DrawPanel drawPanel;

    private JPanel controlPanel = new JPanel();
    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private int gasAmount = 0;

    public int getGasAmount() {
        return gasAmount;
    }

    private JLabel gasLabel = new JLabel( "Gas amount (%)" );

    private final JButton gasButton = new JButton( "Gas" );
    private final JButton brakeButton = new JButton( "Brake" );
    private final JButton turboOnButton = new JButton( "Saab Turbo on" );
    private final JButton turboOffButton = new JButton( "Saab Turbo off" );
    private final JButton liftBedButton = new JButton( "Scania Lift Bed" );
    private final JButton lowerBedButton = new JButton( "Lower Lift Bed" );
    private final JButton startButton = new JButton( "Start all ACars" );
    private final JButton stopButton = new JButton( "Stop all ACars" );
    private final JButton addCarButton = new JButton( "Add car" );
    private final JButton removeCarButton = new JButton( "Remove car" );

    // Constructor
    public VehicleView( String framename, VehicleModel model ) {
        drawPanel = new DrawPanel( ( X ), Y - 240, model );
        initComponents( framename );
    }

    public int getMaxX() {
        return X;
    }

    public int getMaxY() {
        return Y - 240;
    }

    public JButton getGasButton() {
        return gasButton;
    }

    public JButton getBrakeButton() {
        return brakeButton;
    }

    public JButton getTurboOnButton() {
        return turboOnButton;
    }

    public JButton getTurboOffButton() {
        return turboOffButton;
    }

    public JButton getLiftBedButton() {
        return liftBedButton;
    }

    public JButton getLowerBedButton() {
        return lowerBedButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getAddCarButton() {
        return addCarButton;
    }

    public JButton getRemoveCarButton() {
        return removeCarButton;
    }

    // Sets everything in place and fits everything
    private void initComponents( String title ) {

        this.setTitle( title );
        this.setPreferredSize( new Dimension( X, Y ) );
        this.setLayout( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );

        this.add( drawPanel );


        SpinnerModel spinnerModel =
                new SpinnerNumberModel( 0, //initial value
                        0, //min
                        100, //max
                        1 );//step
        gasSpinner = new JSpinner( spinnerModel );
        gasSpinner.addChangeListener( new ChangeListener() {
            public void stateChanged( ChangeEvent e ) {
                gasAmount = (int) ( (JSpinner) e.getSource() ).getValue();
            }
        } );

        gasPanel.setLayout( new BorderLayout() );
        gasPanel.add( gasLabel, BorderLayout.PAGE_START );
        gasPanel.add( gasSpinner, BorderLayout.PAGE_END );

        this.add( gasPanel );

        controlPanel.setLayout( new GridLayout( 2, 4 ) );

        controlPanel.add( gasButton, 0 );
        controlPanel.add( turboOnButton, 1 );
        controlPanel.add( liftBedButton, 2 );
        controlPanel.add( addCarButton, 3);
        controlPanel.add( brakeButton, 4 );
        controlPanel.add( turboOffButton, 5 );
        controlPanel.add( lowerBedButton, 6 );
        controlPanel.add( removeCarButton, 7);
        controlPanel.setPreferredSize( new Dimension( ( X / 2 ) + 4, 200 ) );
        this.add( controlPanel );
        controlPanel.setBackground( Color.cyan );


        startButton.setBackground( Color.blue );
        startButton.setForeground( Color.green );
        startButton.setPreferredSize( new Dimension( X / 5 - 15, 200 ) );
        this.add( startButton );


        stopButton.setBackground( Color.red );
        stopButton.setForeground( Color.black );
        stopButton.setPreferredSize( new Dimension( X / 5 - 15, 200 ) );
        this.add( stopButton );

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation( dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 );
        // Make the frame visible
        this.setVisible( true );
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}