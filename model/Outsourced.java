package model;


/**
 * Outsourced.java Class
 *
 * @author Adam Mayer
 */

public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructor to build Outsourced part
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part inventory
     * @param min part min inventory
     * @param max part max inventory
     * @param companyName part company name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /***
     *
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /***
     *
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
