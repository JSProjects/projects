package report;

public class HardwareData {

	@Override
	public String toString() {
		return "HardwareData [sku=" + sku + ", quantity=" + quantity
				+ ", unitsUsed=" + unitsUsed + ", hmId=" + hmId + "]";
	}
	
	
	public String sku;
	public long quantity;
	public int unitsUsed;
	public String hmId;
	
	public String getHmId() {
		return hmId;
	}
	public void setHmId(String hmId) {
		this.hmId = hmId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public int getUnitsUsed() {
		return unitsUsed;
	}
	public void setUnitsUsed(int unitsUsed) {
		this.unitsUsed = unitsUsed;
	}
	
	
}
