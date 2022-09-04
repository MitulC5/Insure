package helper;

public class Validator {
	public static boolean isValidCredentials(String username, String password) {
		boolean isValid = false;
		if(username != null && !username.equals("") && 
				password != null && !password.equals("")) {
			isValid = true;
		}
		
		return isValid;
	}
	
	public static boolean isValidUser(String username, String password, String phoneNo, 
			String email, String fname, String lname, String address) {
		boolean isValid = false;
		if(username != null && !username.equals("") && 
				password != null && !password.equals("") && phoneNo != null && !phoneNo.equals("") && 
				email != null && !email.equals("") && fname != null && !fname.equals("") && 
						lname != null && !lname.equals("") && address != null && !address.equals("")) {
			isValid = true;
		}
		return isValid;
	}
	
	public static boolean isValidProduct(String name, String model, String year) {
		boolean isValid = false;
		if(name != null && !name.equals("") && 
				model != null && !model.equals("") && 
						year != null && !year.equals("")) {
			isValid = true;
		}
		
		return isValid;
	}
}
