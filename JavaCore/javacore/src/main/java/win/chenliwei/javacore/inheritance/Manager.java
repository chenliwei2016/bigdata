package win.chenliwei.javacore.inheritance;

public class Manager extends Employee{
	private double bouns;
	
	public Manager(String employeeId, String name, double salary) {
		super(employeeId, name, salary);
		this.setBouns(0);
	}

	public double getBouns() {
		return bouns;
	}

	public void setBouns(double bouns) {
		this.bouns = bouns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(bouns);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		if (Double.doubleToLongBits(bouns) != Double.doubleToLongBits(other.bouns))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() +  " [bouns=" + bouns + "]";
	}
}