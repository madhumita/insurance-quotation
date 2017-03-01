package com.quotation;
import java.util.ArrayList;
import java.util.List;

import com.quotation.entity.Insurer;
import com.quotation.entity.Occupation;
import com.quotation.entity.PostCode;
import com.quotation.model.CustomerQuery;


/**Class to generate some testdata
 * @author Madhumita
 *
 */
public class TestDataUtil {
	
	public static CustomerQuery getRequestQuery(){
		
		CustomerQuery query = new CustomerQuery();
		 query.setName("MADHUMITA");
		 query.setOccupation("PAINTER");
		 query.setPostCode("2100");
		 query.setTurnOver(186000);
		 
		 return query;
	}
	
	public static List<Insurer> getInsurers(){
		//create 1 insurer
		 PostCode postCode1 = new PostCode();
		 postCode1.setPostCode("2009");
		 
		 PostCode postCode2 = new PostCode();
		 postCode2.setPostCode("2019");
		 
		 Occupation occupation = new Occupation();
		 occupation.setOccupation("COBBLER");
		 
		 Insurer insurer1 = new Insurer();
		 insurer1.setMinimumTurnOver(116000);
		 insurer1.getExcludedPostCodes().add(postCode1);
		 insurer1.getExcludedPostCodes().add(postCode2);
		 insurer1.getExcludedOccupations().add(occupation);
		 insurer1.setName("Insurer1");
		 
		 
		 //create second insurer
		 PostCode postCode3 = new PostCode();
		 postCode1.setPostCode("2004");
		 
		 PostCode postCode4 = new PostCode();
		 postCode2.setPostCode("2014");
		 
		 Occupation occupation2 = new Occupation();
		 occupation.setOccupation("BARBER");
		 
		 Insurer insurer2 = new Insurer();
		 insurer2.setMinimumTurnOver(166000);
		 insurer2.getExcludedPostCodes().add(postCode3);
		 insurer2.getExcludedPostCodes().add(postCode4);
		 insurer2.getExcludedOccupations().add(occupation2);
		 insurer2.setName("Insurer2");
		 
		 List<Insurer> insurers = new ArrayList<Insurer>();
		 insurers.add(insurer1);
		 insurers.add(insurer2);
		 
		 
		 return insurers;
		
	}
	
	public static List<Insurer> getExInsurersWithGreaterTurnOver(){
		//create 1 insurer
		//create 1 insurer
		 PostCode postCode1 = new PostCode();
		 postCode1.setPostCode("2009");
		 
		 PostCode postCode2 = new PostCode();
		 postCode2.setPostCode("2019");
		 
		 Occupation occupation = new Occupation();
		 occupation.setOccupation("COBBLER");
		 
		 Insurer insurer1 = new Insurer();
		 insurer1.setMinimumTurnOver(116000);
		 insurer1.getExcludedPostCodes().add(postCode1);
		 insurer1.getExcludedPostCodes().add(postCode2);
		 insurer1.getExcludedOccupations().add(occupation);
		 insurer1.setName("Insurer1");
		 List<Insurer> insurers = new ArrayList<Insurer>();
		 insurers.add(insurer1);
		
		 return insurers;
		
	}
	
	public static List<Insurer> getInsurersWithExcludedPostCodeAndOccupation(){
		//create 1 insurer
		 PostCode postCode1 = new PostCode();
		 postCode1.setPostCode("2005");
		 		 
		 Occupation occupation = new Occupation();
		 occupation.setOccupation("BUTCHER");
		 
		 Insurer insurer1 = new Insurer();
		 insurer1.setMinimumTurnOver(136000);
		 insurer1.getExcludedPostCodes().add(postCode1);
		 insurer1.getExcludedOccupations().add(occupation);
		 insurer1.setName("Insurer1");
		 		 
		 //create second insurer
		 PostCode postCode3 = new PostCode();
		 postCode1.setPostCode("2005");
			 
		 Occupation occupation2 = new Occupation();
		 occupation.setOccupation("BUTCHER");
		 
		 Insurer insurer2 = new Insurer();
		 insurer2.setMinimumTurnOver(166000);
		 insurer2.getExcludedPostCodes().add(postCode3);
		 insurer2.getExcludedOccupations().add(occupation2);
		 insurer2.setName("Insurer2");
		 		 
		 List<Insurer> insurers = new ArrayList<Insurer>();
		 insurers.add(insurer1);
		 insurers.add(insurer2);
		 		 
		 return insurers;
		
	}
	
	public static Insurer getInsurer(String postCodeA,String postCodeB,String occupationA,String name,long turnover){
		
		 PostCode postCode1 = new PostCode();
		 postCode1.setPostCode(postCodeA);
		 
		 PostCode postCode2 = new PostCode();
		 postCode2.setPostCode(postCodeB);
				 
		 Occupation occupation = new Occupation();
		 occupation.setOccupation(occupationA);
		 
		 Insurer insurer1 = new Insurer();
		 insurer1.setMinimumTurnOver(turnover);
		 insurer1.getExcludedPostCodes().add(postCode1);
		 insurer1.getExcludedPostCodes().add(postCode2);
		 insurer1.getExcludedOccupations().add(occupation);
		 insurer1.setName(name);
		 
		 return insurer1;
		
	}

}
