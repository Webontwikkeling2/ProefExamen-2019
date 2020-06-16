package domain;

public class Bike {
	private String itemId; // unique
	private String brand;
	private Category category;
	private String description;
	private double price;
	
	public Bike() {
	}
	
	public Bike(String itemId, String brand, Category category, String description, double price) {
		setItemId(itemId);
		setBrand(brand);
		setCategory(category);
		setDescription(description);
		setPrice(price);
	}

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		if (itemId == null || itemId.isEmpty())
			throw new DomainException("No valid itemid");
		this.itemId = itemId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		if (brand == null || brand.isEmpty())
			throw new DomainException("No valid brand");
		this.brand = brand;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if (description== null || description.isEmpty())
			throw new DomainException("No valid description");
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if (price<0)
			throw new DomainException("No valid price");
		this.price = price;
	}
	public boolean equals(Object object) {
		boolean equal = false;
		if (object instanceof Bike) {
			Bike other = (Bike) object;
			equal = getItemId().equals(other.getItemId()); 
		}
		return equal;
	}
	
}
