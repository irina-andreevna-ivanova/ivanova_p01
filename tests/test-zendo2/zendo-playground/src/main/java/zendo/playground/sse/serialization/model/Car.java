/**
 * 
 */
package zendo.playground.sse.serialization.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author mocanu
 */
public class Car implements Serializable {

    private String licenseNumber;
    private String color;
    private int age;
    private transient String model;
    private List<Wheel> wheels = new ArrayList<Wheel>();

    /**
     * Returns the licenseNumber
     * 
     * @return the licenseNumber
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the licenseNumber to the given value.
     * 
     * @param licenseNumber
     *            the licenseNumber to set
     */
    public void setLicenseNumber( String licenseNumber ) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Returns the color
     * 
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color to the given value.
     * 
     * @param color
     *            the color to set
     */
    public void setColor( String color ) {
        this.color = color;
    }

    /**
     * Returns the age
     * 
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age to the given value.
     * 
     * @param age
     *            the age to set
     */
    public void setAge( int age ) {
        this.age = age;
    }

    /**
     * Returns the wheels
     * 
     * @return the wheels
     */
    public List<Wheel> getWheels() {
        return wheels;
    }

    /**
     * Sets the wheels to the given value.
     * 
     * @param wheels
     *            the wheels to set
     */
    public void setWheels( List<Wheel> wheels ) {
        this.wheels = wheels;
    }

    /**
     * Returns the model
     * 
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model to the given value.
     * 
     * @param model
     *            the model to set
     */
    public void setModel( String model ) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Car [age=" );
        builder.append( age ).append( "\n" );
        builder.append( ", color=" );
        builder.append( color ).append( "\n" );
        builder.append( ", licenseNumber=" );
        builder.append( licenseNumber ).append( "\n" );
        builder.append( ", model=" );
        builder.append( model ).append( "\n" );
        builder.append( ", wheels=" );
        builder.append( wheels ).append( "\n" );
        builder.append( "]" );
        return builder.toString();
    }

}
