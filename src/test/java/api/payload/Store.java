package api.payload;

public class Store {
	    //create id too, this API doesn't create the ID after the post	
		int id;
		int petId;
		int quantity;
		String shipdate;
		String status;
		boolean complete;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPetId() {
			return petId;
		}
		public void setPetId(int petId) {
			this.petId = petId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getShipdate() {
			return shipdate;
		}
		public void setShipdate(String shipdate) {
			this.shipdate = shipdate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public boolean isComplete() {
			return complete;
		}
		public void setComplete(boolean complete) {
			this.complete = complete;
		}
		
		
}
