package model;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Item holds information about items.
 */
@XmlRootElement(name = "item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int itemKey;
	private int categoryKey;
	private String itemName;
	private int unitsAlways;
	private String available;
	
	private int numberOfUnits;
	
	private String categoryName;
	
	private int storageplaceKey;
	private String storageplaceName; 
	
	private int numberToBuy;

	// Constructors
	public Item(){}
	
	public Item(String itemName) {
		super();
		this.itemName = itemName;
	}
	
	public Item(int itemKey, String itemName) {
		super();
		this.itemKey = itemKey;
		this.itemName = itemName;
	}
	
	public Item(int itemKey, int categoryKey, String itemName, int unitsAlways, String available) {
		super();
		this.itemKey = itemKey;
		this.categoryKey = categoryKey;
		this.itemName = itemName;
		this.unitsAlways = unitsAlways;
		this.available = available;
	}
	
	public Item(int itemKey, int categoryKey, String itemName, int unitsAlways, String available, int numberOfUnits, int contentKey, String categoryName) {
		super();
		this.itemKey = itemKey;
		this.categoryKey = categoryKey;
		this.itemName = itemName;
		this.unitsAlways = unitsAlways;
		this.available = available;
		this.numberOfUnits = numberOfUnits;
		//this.contentKey = contentKey;
		this.categoryName = categoryName;
	}
	

	
	public Item(int itemKey, int categoryKey, String itemName, int unitsAlways, String available, int numberOfUnits,
			String categoryName, int storageplaceKey, String storageplaceName) {
		super();
		this.itemKey = itemKey;
		this.categoryKey = categoryKey;
		this.itemName = itemName;
		this.unitsAlways = unitsAlways;
		this.available = available;
		this.numberOfUnits = numberOfUnits;
		this.categoryName = categoryName;
		this.storageplaceKey = storageplaceKey;
		this.storageplaceName = storageplaceName;
	}
	
	public Item(int itemKey, int categoryKey, String itemName, int unitsAlways, String available, int numberOfUnits,
			String categoryName, int storageplaceKey, String storageplaceName, int numberToBuy) {
		super();
		this.itemKey = itemKey;
		this.categoryKey = categoryKey;
		this.itemName = itemName;
		this.unitsAlways = unitsAlways;
		this.available = available;
		this.numberOfUnits = numberOfUnits;
		this.categoryName = categoryName;
		this.storageplaceKey = storageplaceKey;
		this.storageplaceName = storageplaceName;
		this.numberToBuy = numberToBuy;
	}
	
	public Item(int itemKey, String itemName, int unitsAlways, String available, int numberOfUnits) {
		super();
		this.itemKey = itemKey;
		this.itemName = itemName;
		this.unitsAlways = unitsAlways;
		this.available = available;
		this.numberOfUnits = numberOfUnits;
}

		public Item(int categoryKey, String itemName, int unitsAlways, String available, int numberOfUnits,
			int storageplaceKey) {
			super();
			this.categoryKey = categoryKey;
			this.itemName = itemName;
			this.unitsAlways = unitsAlways;
			this.available = available;
			this.numberOfUnits = numberOfUnits;
			this.storageplaceKey = storageplaceKey;
	}

		// Getters and setters
		public int getItemKey() {
			return itemKey;
		}
		
		@XmlElement
		public void setItemKey(int itemKey) {
			this.itemKey = itemKey;
		}
		
		public int getCategoryKey() {
			return categoryKey;
		}
		
		@XmlElement
		public void setCategoryKey(int categoryKey) {
			this.categoryKey = categoryKey;
		}

		public String getItemName() {
			return itemName;
		}
		
		@XmlElement
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		
		
		public int getUnitsAlways() {
			return unitsAlways;
		}
		
		@XmlElement
		public void setUnitsAlways(int unitsAlways) {
			this.unitsAlways = unitsAlways;
		}
		
		public String getAvailable() {
			return available;
		}
		
		@XmlElement
		public void setAvailable(String available) {
			this.available = available;
		}
		
		public int getNumberOfUnits() {
			return numberOfUnits;
		}
		
		@XmlElement
		public void setNumberOfUnits(int numberOfUnits) {
			this.numberOfUnits = numberOfUnits;
		}

		public String getCategoryName() {
			return categoryName;
		}
		
		@XmlElement
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public int getStorageplaceKey() {
			return storageplaceKey;
		}
		
		@XmlElement
		public void setStorageplaceKey(int storageplaceKey) {
			this.storageplaceKey = storageplaceKey;
		}

		public String getStorageplaceName() {
			return storageplaceName;
		}
		
		@XmlElement
		public void setStorageplaceName(String storageplaceName) {
			this.storageplaceName = storageplaceName;
		}

		/**
		 * @return the numberToBuy
		 */
		public int getNumberToBuy() {
			return numberToBuy;
		}

		/**
		 * @param numberToBuy the numberToBuy to set
		 */
		public void setNumberToBuy(int numberToBuy) {
			this.numberToBuy = numberToBuy;
		}

		@Override
		   public boolean equals(Object object){
		      
			   if(object == null){
		         return false;
			   	} 
			  else if(!(object instanceof Item)){
		         return false;
		      	}
			  else {
		         Item item = (Item)object;
		         if(itemKey ==item.getItemKey()
		            && itemName.equals(item.getItemName())
		         ){
		            return true;
		         }			
		      }
		      return false;
		   }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Item [itemKey=" + itemKey + ", categoryKey=" + categoryKey + ", itemName=" + itemName
					+ ", unitsAlways=" + unitsAlways + ", available=" + available + ", numberOfUnits=" + numberOfUnits
					+ ", categoryName=" + categoryName + ", storageplaceKey=" + storageplaceKey + ", storageplaceName="
					+ storageplaceName +", numberToBuy=" + numberToBuy +"]";
		}
		
		
}
