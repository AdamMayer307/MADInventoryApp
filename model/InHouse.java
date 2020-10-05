package model;


/**
 * InHouse.java Class extends to Part.java class
 *
 * @author Adam Mayer
 */

public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor to build In-House part
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part inventory
     * @param min part min inventory
     * @param max part max inventory
     * @param machineId part machine id
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /***
     * gets machine id
     * @return the machineId
     */
    public int getMachineId() {
        return machineId;
    }

    /***
     * sets machine id
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
