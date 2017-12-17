package bigPackage.Implementations;

import bigPackage.Interfaces.IHasFlatbed;
import bigPackage.Interfaces.ITurbo;
import bigPackage.Interfaces.IVehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleController {
    private final int interval = 10;
    private VehicleView frame;
    private VehicleModel model;

    VehicleController( VehicleModel model, VehicleView frame ) {
        this.model = model;
        this.frame = frame;

        initListeners();
    }

    private void initListeners() {
        frame.getGasButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                gas( frame.getGasAmount() );
            }
        } );

        frame.getBrakeButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                brake( frame.getGasAmount() );
            }
        } );

        frame.getStopButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                stopEngine();
            }
        } );

        frame.getStartButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                startEngine();
            }
        } );

        frame.getTurboOnButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setTurboOn();
            }
        } );

        frame.getTurboOffButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setTurboOff();
            }
        } );

        frame.getLiftBedButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                raiseFlatbed();
            }
        } );

        frame.getLowerBedButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                lowerFlatbed();
            }
        } );
        frame.getAddCarButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                addCar();
            }
        } );
        frame.getRemoveCarButton().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                removeCar();
            }
        } );
    }

    public void startTimer() {
        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                for ( IVehicle vehicle : model ) {
                    model.updateVehiclePosition( vehicle, suitX( vehicle ), suitY( vehicle ) );
                }
                frame.drawPanel.repaint();
            }
        }, 0, interval ); // Delay : Before start
    }

    private int suitX( IVehicle vehicle ) {
        return frame.getMaxX() - frame.drawPanel.getImgWidth( vehicle.getModelName() );
    }

    private int suitY( IVehicle vehicle ) {
        return frame.getMaxY() - frame.drawPanel.getImgHeight( vehicle.getModelName() );
    }

    //    private void paintVehicle( IVehicle vehicle ) {
    //        frame.drawPanel.paintVehicle( (int) vehicle.getX(), (int) vehicle.getY(), vehicle.getModelName() );
    //    }

    private void gas( int amount ) {
        double gas = ( (double) amount ) / 100;
        for ( IVehicle vehicle : model ) {
            try {
                vehicle.gas( gas );
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }

    private void brake( int amount ) {
        double brake = ( (double) amount ) / 100;
        for ( IVehicle vehicle : model ) {
            vehicle.brake( brake );
        }
    }

    private void stopEngine() {
        for ( IVehicle vehicle : model ) {

            vehicle.stopEngine();

        }
    }

    private void startEngine() {
        for ( IVehicle vehicle : model ) {
            try {
                vehicle.startEngine();
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }

    }

    private void setTurboOn() {
        for ( IVehicle vehicle : model ) {
            if ( vehicle instanceof ITurbo ) {
                ( (ITurbo) vehicle ).setTurboOn();
            }
        }
    }

    private void setTurboOff() {
        for ( IVehicle vehicle : model ) {
            if ( vehicle instanceof ITurbo ) {
                ( (ITurbo) vehicle ).setTurboOff();
            }
        }
    }

    private void raiseFlatbed() {
        for ( IVehicle vehicle : model ) {
            if ( vehicle instanceof IHasFlatbed ) {
                try {
                    ( (IHasFlatbed) vehicle ).raiseFlatbed();
                } catch ( Exception ex ) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void lowerFlatbed() {
        for ( IVehicle vehicle : model ) {
            if ( vehicle instanceof IHasFlatbed ) {
                try {
                    ( (IHasFlatbed) vehicle ).lowerFlatbed();
                } catch ( Exception ex ) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void addCar() {
        IVehicle v = model.addRandomVehicle();
        frame.drawPanel.connectVehicleToImg( v );
    }

    private void removeCar() {
        model.removeRandomVehicle();
    }
}