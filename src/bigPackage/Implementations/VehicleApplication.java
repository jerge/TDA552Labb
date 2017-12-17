package bigPackage.Implementations;

import bigPackage.Interfaces.IVehicle;
import bigPackage.models.MotorizedVehicleFactory;

import java.util.ArrayList;
import java.util.List;

public class VehicleApplication {
    public static void main( String[] args ) {
        VehicleModel vehicleModel = new VehicleModel( initVehicles(), new ArrayList<>() );
        VehicleView vehicleView = new VehicleView( "CarSim 1.0", vehicleModel );
        VehicleController vehicleController = new VehicleController( vehicleModel, vehicleView );

        vehicleModel.addObserver( vehicleView.drawPanel );
        vehicleController.startTimer();
    }

    private static List<IVehicle> initVehicles() {
        List<IVehicle> vehicles = new ArrayList<>();

        vehicles.add( MotorizedVehicleFactory.createVolvo() );
        vehicles.add( MotorizedVehicleFactory.createScania() );
        vehicles.add( MotorizedVehicleFactory.createSaab() );
        for ( int i = 1; i <= vehicles.size(); i++ ) {
            vehicles.get( i - 1 ).getPosition()[1] += 100 * i;
        }
        return vehicles;
    }
}
