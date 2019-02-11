package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class Category implements Serializable {
	  
	private static final long serialVersionUID = 1L;
	private int categoryKey;
	private String categoryName;

	// Constructors
	public Category(){}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	public Category(int categoryKey, String categoryName) {
		super();
		this.categoryKey = categoryKey;
		this.categoryName = categoryName;
	}

	// Getters and setters
	public int getCategoryKey() {
		return categoryKey;
	}
	
	@XmlElement
	public void setCategoryKey(int categoryKey) {
		this.categoryKey = categoryKey;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	@XmlElement
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	   public boolean equals(Object object){
	      
		   if(object == null){
	         return false;
		   	} 
		  else if(!(object instanceof Category)){
	         return false;
	      	}
		  else {
	         Category category = (Category)object;
	         if(categoryKey ==category.getCategoryKey()
	            && categoryName.equals(category.getCategoryName())
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
		return "Category [categoryKey=" + categoryKey + ", categoryName=" + categoryName + "]";
	}	
	
	
}
