package hu.pte.mik.zhgyak3.model;

public class Pet {

	private Long id;
	private String name;
	private String chipCode;
	private String address;
	private String species;

	public Pet(Long id, String name, String chipCode, String address, String species) {
		this.id = id;
		this.name = name;
		this.chipCode = chipCode;
		this.address = address;
		this.species = species;
	}

	public Pet(String name, String chipCode, String address, String species) {
		this.name = name;
		this.chipCode = chipCode;
		this.address = address;
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChipCode() {
		return chipCode;
	}

	public void setChipCode(String chipCode) {
		this.chipCode = chipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", chipCode=" + chipCode + ", address=" + address + ", species="
				+ species + "]";
	}

}
