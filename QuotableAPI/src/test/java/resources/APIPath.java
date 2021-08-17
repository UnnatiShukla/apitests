package resources;

public enum APIPath {
	ListQuotesAPI("/quotes"),
	ListRandomQuotesAPI("/random"),
	ListAuthorsOfQuotesAPI("/authors");
	private String APIPath;
	
	APIPath(String APIPath) {
		this.APIPath=APIPath;
	}
	
	public String getPath() {
		return APIPath;
	}
	
}
