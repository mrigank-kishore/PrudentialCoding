# PrudentialCoding
it will list all the media type, internal link and external link in tabular format.
## Logic Flow
- Take the root website and domain name as parameter to run the jar.
- create a HTTP.get request to the root wesite and retrive the markup.
- extract the unique media link, external links and internal links.
- append the output to a html file in a readable format
- loop the internal links excute the same step 2, 3 and 4.

## Running the code
- requires java 1.8 and maven 3.5+
- download the code
- build the code with **mvn clean install** command
- change directry to newly create target folder
- execute **java -jar L1Crawaler-0.0.1-SNAPSHOT.jar http://web.mit.edu/ mit.edu** where first parameter is root website and second param is domain name
- new pagedetail.html will generate in same directory. It can be opened with any web browser to see the result.

Sample output is commited in root of the project for reference.
