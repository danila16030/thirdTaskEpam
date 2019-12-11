package entity;

public class Bank {
    private String name;
    private String country;
    private String depositor;
    private int accountId;
    private int amountOfDeposit;
    private double profitability;
    private int timeConstraints;
    private String type;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public void setAmountOfDeposit(int amountOfDeposit) {
        this.amountOfDeposit = amountOfDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }


    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }


    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (accountId != bank.accountId) return false;
        if (amountOfDeposit != bank.amountOfDeposit) return false;
        if (Double.compare(bank.profitability, profitability) != 0) return false;
        if (timeConstraints != bank.timeConstraints) return false;
        if (id != bank.id) return false;
        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;
        if (country != null ? !country.equals(bank.country) : bank.country != null) return false;
        if (depositor != null ? !depositor.equals(bank.depositor) : bank.depositor != null) return false;
        return type != null ? type.equals(bank.type) : bank.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
        result = 31 * result + accountId;
        result = 31 * result + amountOfDeposit;
        temp = Double.doubleToLongBits(profitability);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + timeConstraints;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", depositor='" + depositor + '\'' +
                ", accountId=" + accountId +
                ", amountOfDeposit=" + amountOfDeposit +
                ", profitability=" + profitability +
                ", timeConstraints=" + timeConstraints +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
