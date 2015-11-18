# A Simple Java Client for Bit.yl
## Example Usage

    // Expand the bit.ly using system arg values
    BitlyClient.create().links().expand(new URL("http://bit.ly/ze6poY")); // returns http://google.com/
    // Expand the bit.ly using variables
    BitlyClient.create(userName, password).links().expand(new URL("http://bit.ly/ze6poY"));  // returns http://google.com/
    // Query for bit.ly link 
    BitlyClient.create().links().lookup(new URL("http://google.com")) // returns "http://bit.ly/ze6poY
    
  See the unit tests. They explain everything.
  
## Why?  
The existing APIs either:

    1. Only support OAUTH (Understandable but "actual" front end users may not be the only consumers of Bit.ly)
    2. The other existing APIs are (IMVHO) not very intuitive
    3. Why Not? (JK)
    
    
## Dependencies
 * [https://github.com/nycjv321/utilities](https://github.com/nycjv321/utilities) for a kickass Apache Http Client Wrapper
    