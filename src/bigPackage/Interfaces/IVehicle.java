package bigPackage.Interfaces;

public interface IVehicle extends IMovable {
    void gas( double amount );

    void brake( double amount );

    void setPosition( double[] position );

    void setPosition( double x, double y );

    double[] getPosition();

    double getX();

    double getY();

    String getModelName();

    void startEngine();

    void stopEngine();

    boolean isOnTransport();
}
