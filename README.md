# A Simple Java Client for Bit.yl
## Example Usage

    BitlyClient.create().links().expand(new URL("http://bit.ly/ze6poY")); \\ returns http://google.com/
    
  or 
    
    BitlyClient.create(userName, password).links().expand(new URL("http://bit.ly/ze6poY"));  \\ returns http://google.com/

  See the unit tests. They explain everything
  
## Why?  
The existing APIs either

    1. Only support OAUTH (Understandable but "actual" front end users may not be using Bit.ly)
    2. The other existing APIs are (IMVHO) simple/have weird APIs
    3. Why Not? (JK)
    
    
## Dependencies
 * [https://github.com/nycjv321/utilities](https://github.com/nycjv321/utilities) for a kickass Apache Http Client Wrapper
    