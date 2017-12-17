package bigPackage.Implementations;

import bigPackage.Interfaces.IVehicle;
import bigPackage.models.MotorizedVehicleFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * The type Vehicle model.
 */
public class VehicleModel implements Iterable<IVehicle> {
    private List<IVehicle> vehicles;
    private List<IObserver> observers;
    private int gasAmount;

    /**
     * Instantiates a new Vehicle model.
     *
     * @param list the vehicle list
     */
    public VehicleModel( List<IVehicle> list, List<IObserver> observers ) {
        vehicles = list;
        this.observers = observers;
    }

    public void addObserver( IObserver ob ) {
        observers.add( ob );
    }

    private void notifyObservers( IVehicle vehicle ) {
        for ( IObserver ob : observers ) {
            ob.actOnUpdate( vehicle );
        }

    }

    /**
     * Update vehicle position.
     *
     * @param v the v
     */
    public void updateVehiclePosition( IVehicle v, int maxX, int maxY ) {
        v.move();
        isOutOfBounds( v, maxX, maxY );
        amendPosition( v, maxX, maxY );
        notifyObservers( v );
    }

    /**
     * Turns the Vehicle if outside the universe height and width
     *
     * @param v the Vehicle
     */
    private void isOutOfBounds( IVehicle v, int maxX, int maxY ) {
        if ( ( 0 >= v.getY() || v.getY() >= ( maxY ) ||
                0 >= v.getX() || v.getX() >= ( maxX ) ) ) {
            v.turnRight();
            v.turnRight();
        }
    }

    /**
     * Amends the position of the vehicle according to the universe height and width
     *
     * @param v the Vehicle
     */
    private void amendPosition( IVehicle v, int maxX, int maxY ) {
        double x = v.getX();
        x = x <= maxX ? ( x <= 0 ? 0 : x ) : maxX;

        double y = v.getY();
        y = y <= maxY ? ( y <= 0 ? 0 : y ) : maxY;

        v.setPosition( x, y );
    }

    /**
     * Update gas amount.
     *
     * @param gasAmount the gas amount
     */
    public void updateGasAmount( int gasAmount ) {
        this.gasAmount = gasAmount;
    }

    /**
     * Gets gas amount.
     *
     * @return the gas amount
     */
    public int getGasAmount() {
        return gasAmount;
    }

    /**
     * Gets vehicle.
     *
     * @param index the index
     * @return the vehicle
     */
    public IVehicle getVehicle( int index ) {
        return vehicles.get( index );
    }

    /**
     * Gets vehicle list size.
     *
     * @return the vehicle list size
     */
    public int getVehicleListSize() {
        return vehicles.size();
    }

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    public List<IVehicle> getVehicles() {
        return vehicles;
    }

    public IVehicle addRandomVehicle(){
        if (vehicles.size() >= 10 ) {
            return null;
        }
        double n = Math.random();
        IVehicle v;
        if (n < 1.0/3) {
            v = MotorizedVehicleFactory.createSaab();
        } else if (n < 2.0/3) {
            v = MotorizedVehicleFactory.createVolvo();
        } else {
            v =  MotorizedVehicleFactory.createScania();
        }
        vehicles.add( v );
        v.setPosition( 0, n*500 );
        return v;
    }

    public void removeRandomVehicle() {
        if (vehicles.size() <= 0) {
            return;
        }
        double n = Math.random();
        for ( int i = 0; i < vehicles.size(); i++ ) {
            if ( n < i+1/vehicles.size()) {
                vehicles.remove( i );
                break;
            }
        }
    }

    @Override
    public Iterator<IVehicle> iterator() {
        return vehicles.iterator();
    }












    @Override
    public void forEach( Consumer<? super IVehicle> action ) {

    }

    @Override
    public Spliterator<IVehicle> spliterator() {
        return null;
    }
}
