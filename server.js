/** This programs creates a server which echo the URL it receives back to the client **/

const http = require('http');

let server = http.createServer(
	function(request, response){
		response.writeHead(200);
		
		let url = (request.url == undefined) ? "" : request.url;
		let host = request.headers.host;

		response.end(host + '' + url + '\n');
	});

server.listen(8080);
console.log('Server running...');