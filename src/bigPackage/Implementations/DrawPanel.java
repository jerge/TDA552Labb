package bigPackage.Implementations;

import bigPackage.Interfaces.IVehicle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.
public class DrawPanel extends JPanel implements IObserver {
    private Map<String, BufferedImage> modelNameImgMap = new HashMap<>();
    private List<VehicleVisuals> vs;

    private void connectVehicleToImg( VehicleModel model ) {
        for ( IVehicle vehicle : model ) {
            connectVehicleToImg( vehicle );
        }
    }

    void connectVehicleToImg( IVehicle vehicle) {
        if ( vehicle != null && !modelNameImgMap.containsKey( vehicle.getModelName() ) ) {
            try {
                modelNameImgMap.put( vehicle.getModelName(),
                        ImageIO.read( new File( "src\\bigPackage\\pics\\" + vehicle.getClass().getSimpleName() + ".jpg" ) ) );
            } catch ( IOException ex ) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void actOnUpdate(IVehicle vehicle) {
        paintVehicle( (int)vehicle.getX(), (int)vehicle.getY(), vehicle.getModelName() );
    }

    public int getImgWidth( String modelName ) {
        return modelNameImgMap.get( modelName ).getWidth();
    }

    public int getImgHeight( String modelName ) {
        return modelNameImgMap.get( modelName ).getHeight();
    }

    public DrawPanel( int x, int y, VehicleModel model ) {
        this.vs = new ArrayList<>();
        this.setDoubleBuffered( true );
        this.setPreferredSize( new Dimension( x, y ) );
        this.setBackground( Color.decode( "0x09cdda" ) );
        connectVehicleToImg( model );
    }

    public synchronized void paintVehicle( int x, int y, String name ) {
        vs.add( new VehicleVisuals( x, y, name ) );
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected synchronized void paintComponent( Graphics g ) {
        super.paintComponent( g );
        for ( VehicleVisuals v : vs ) {
            g.drawImage( modelNameImgMap.get( v.name ), v.x, v.y, null );
        }
        vs.clear();
        //        for ( IVehicle vehicle : model.ge ) {
        //            g.drawImage( modelNameImgMap.get( vehicle.getModelName() ),
        //                    (int) vehicle.getPosition()[0],
        //                    (int) vehicle.getPosition()[1],
        //                    null );
        //            if ( vehicle instanceof IHasFlatbed && !( (IHasFlatbed) vehicle ).isFlatbedDown()) {
        //                g.drawChars( new char[]{'F'}, 0, 1,
        //                        (int) vehicle.getPosition()[0], (int) vehicle.getPosition()[1] );
        //            }
        //        }
    }

    private class VehicleVisuals {
        private int x, y;
        private String name;

        private VehicleVisuals( int x, int y, String name ) {
            this.x = x;
            this.y = y;
            this.name = name;
        }
    }
}