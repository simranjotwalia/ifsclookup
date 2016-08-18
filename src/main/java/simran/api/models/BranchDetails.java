package simran.api.models;

public class BranchDetails {

	private String ifsc;
	private String bank;
	private String branch_address;
	private String city;
	
	public BranchDetails(){
		
	}
	
	public BranchDetails(String ifsc, String bank, String branch_address, String city){
		this.ifsc=ifsc;
		this.bank=bank;
		this.branch_address=branch_address;
		this.city=city;
		
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch_address() {
		return branch_address;
	}

	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
