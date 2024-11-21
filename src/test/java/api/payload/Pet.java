package api.payload;

import java.util.List;

public class Pet {

	// to understand the PET payload consider
	//web site https://jsonformatter.com
    private Integer id;
    private Category category; // category is JSON object
    private String name;
    private List<String> photoUrls = null; //Array of strings
    //private List<Tag> tag = null;
    private Tag[] tags;  //tag is Array of objects
    private String status;
    
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public Tag[] getTags() {
		return tags;
	}
	public void setTags(Tag[] tags) {
		this.tags = tags;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
}
