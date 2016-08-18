package simran.api.ifscLookup.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import simran.api.models.BranchDetails;
import simran.api.services.BankNames;

@Path("/banksList")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BankNameResource {
	
	private BankNames banknames = new BankNames();
	
	@GET
	public List<String> getBankList(){
		System.out.println("mapped");
		return banknames.getBankNames();
	}
	
	@GET
	@Path("/{bank}")
	public List<String> getCitiesWithBankName(@PathParam("bank")String bank){
		return banknames.getCitiesBankIsIn(bank); 
	}
	
	@GET
	@Path("/bankname/{bank}/cityname/{city}")
	public List<BranchDetails> getBranchesInCity(@PathParam("bank") String bank, @PathParam("city") String city){
		return banknames.getBranchDetails(bank,city);
	}
	
}
