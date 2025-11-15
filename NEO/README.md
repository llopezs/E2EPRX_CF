# E2E Access Point

Es la puerta de acceso a los sistemas de Lipigas permitiendo llegar a la logica del negocio en el ERP y todos los procesos necesarios relacionados con el proyecto de E2E.

<h2 style="color: rgb(16, 134, 212) !important;"><b>Direccion de acceso DEV</b></h2>
<a href="https://e2eprxhd428f378.us3.hana.ondemand.com/E2EPRX/mobile/" target="_blank">https://e2eprxhd428f378.us3.hana.ondemand.com/E2EPRX/mobile/</a>

<h2 style="color: rgb(16, 134, 212) !important;"><b>Direccion de acceso PRD</b></h2>
<b>Pendiente de definicion al momento de habilitar en PRD</b>

<h2 style="color: rgb(16, 134, 212) !important;"><b>Login</b></h2>
<p>Todavia pendiente de definicion para ingresar el usuario y contraseña...</p>
<blockquote>Logica NodeJS</blockquote>
<code>
const http = require('https');
const options = {
	method: 'POST',
	hostname: 'e2eprxhd428f378.us3.hana.ondemand.com',
	port: null,
	path: '/E2EPRX/mobile/',
	headers: { 'Content-Type': 'application/json', method: 'login', 'Content-Length': '4' }
};

const req = http.request(options, function (res) {
	const chunks = [];

	res.on('data', function (chunk) {
		chunks.push(chunk);
	});

	res.on('end', function () {
		const body = Buffer.concat(chunks);
		console.log(body.toString());
	});
});

req.write(JSON.stringify({ usuario: '', password: '' }));
req.end();

</code>

<blockquote>Respuesta del Login</blockquote>
<code>
{
  "acces_token": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NDA5ODc3MzEsInN1YiI6IntcImRcIjp7XCJyZXN1bHRzXCI6W3tcIl9fbWV0YWRhdGFcIjp7XCJpZFwiOlwiaHR0cDovL2xpcGlmaW9yaTo4MDAwL3NhcC9vcHUvb2RhdGEvU0FQL1pFMkVfU1JWL2NvbW1TZXQoQ2xhc3NQcng9J1pDTF9FMkVfU09MX0NPVCcsQ29udGVudD0nJTdCJTIyUkVTVUxUUyUyMiUzQSUyMiU3QiU1QyUyMk1BTkRUJTVDJTIyJTNBJTVDJTIyJTVDJTIyJTJDJTVDJTIyVVNVQVJJTyU1QyUyMiUzQSU1QyUyMiU1QyUyMiUyQyU1QyUyMlBBU1NXT3123121JEJTVDJTIyJTNBJTVDJTIyJTVDJTIyJTJDJTVDJTIyRU1BSUwlNUMlMjIlM0ElNUMlMjIlNUMlMjIlMkMlNUMlMjJQRVJGSUwlNUMlMjIlM0ElNUMlMjIlNUMlMjIlMkMlNUMlMjJBQ1RJVk8lNUMlMjIlM0ElNUMlMjIlNUMlMjIlN0QlMjIlMkMlMjJUT19JRCUyMiUzQSUyMiUyMiUyQyUyMk1TR1MlMjIlM0ElNUIlMjJVc3VhcmlvJTIwdmFjaW8lMjBvJTIwc2luJTIwY29udGVuaWRvJTIyJTVEJTdEJyxGaWxlPScnLE1ldGhvZD0nTE9HSU4nLFVzZXJpZD0nJyxJdGVtPSdYJylcIixcInVyaVwiOlwiaHR0cDovL2xpcGlmaW9yaTo4MDAwL3NhcC9vcHUvb2RhdGEvU0FQL1pFMkVfU1JWL2NvbW1TZXQoQ2xhc3NQcng9J1pDTF9FMkVfU09MX0NPVCcsQ29udGVudD0nJTdCJTIyUkVTVUxUUyUyMiUzQSUydf3d2d32d32eMiU3QiU1QyUyMk1BTkRUJTVDJTIyJTNBJTVDJTIyJTVDJTIyJTJDJTVDJTIyVVNVQVJJTyU1QyUyMiUzQSU1QyUyMiU1QyUyMiUyQyU1QyUyMlBBU1NXT1JEJTVDJTIyJTNBJTVDJTIyJTVDJTIyJTJDJTVDJTIyRU1BSUwlNUMlMjIlM0ElNUMlMjIlNUMlMjIlMkMlNUMlMjJQRVJGSUwlNUMlMjIlM0ElNUMlMjIlNUMlMjIlMkMlNUMlMjJBQ1RJVk8lNUMlMjIlM0ElNUMlMjIlNUMlMjIlN0QlMjIlMkMlMjJUT19JRCUyMiUzQSUyMiUyMiUyQyUyMk1TR1MlMjIlM0ElNUIlMjJVc3VhcmlvJTIwdmFjaW8lMjBvJTIwc2luJTIwY29udGVuaWRvJTIyJTVEJTdEJyxGaWxlPScnLE1ldGhvZD0nTE9HSU4nLFVzZXJpZD0nJyxJdGVtPSdYJylcIixcInR5cGVcIjpcIlpFMkVfU1JWLmNvbW1cIn0sXCJDbGFzc1ByeFwiOlwiWkNMX0UyRV9TT0xfQ09UXCIsXCJDb250ZW50XCI6XCJ7XFxcIlJFU1VMVFNcXFwiOlxcXCJ7XFxcXFxcXCJNQU5EVFxcXFxcXFwiOlxcXFxcXFwiXFxcXFxcXCIsXFxcXFxcXCJVU1VBUklPXFxcXFxcXCI6XFxcXFxcXCJcXFxcXFxcIixcXFxcXFxcIlBBU1NXT1JEXFxcXFxcXCI6XFxcXFxcXCJcXFxcXFxcIixcXFxcXFxcIkVNQUlMXFxcXFxcXCI6XFxcXFxcXCJcXFxcXFxcIixcXFxcXFxcIlBFUkZJTFxcXFxcXFwiOlxcXFxcXFwiXFxcXFxcXCIsXFxcXFxcXCJBQ1RJVk9cXFxcXFxcIjpcXFxcXFxcIlxcXFxcXFwifVxcXCIsXFxcIlRPX0lEXFxcIjpcXFwiXFxcIixcXFwiTVNHU1xcXCI6W1xcXCJVc3VhcmlvIHZhY2lvIG8gc2luIGNvbnRlbmlkb1xcXCJdfVwiLFwiRmlsZVwiOlwiXCIsXCJNZXRob2RcIjpcIkxPR0lOXCIsXCJVc2VyaWRcIjpcIlwiLFwiSXRlbVwiOlwiWFwifV19fSJ9.kM885_T1kn5bBJLY5OfXBnv-ezo4Nq2p7hwFm8-Bksxts8dn3p0kEXWYNZk_oNEuvvOeQIu6S3vJHnsVLPWCcA",
  "token_type": "bearer",
  "expires": "Fri Dec 31 21:55:31 UTC 2021",
  "resultado": true,
  "mensaje": "Ok"
}
</code>

<h2 style="color: rgb(16, 134, 212) !important;"><b>Odata</b></h2>
<blockquote>Logica NodeJS</blockquote>
<code>
const http = require("https");

const options = {
"method": "POST",
"hostname": "e2eprxhd428f378.us3.hana.ondemand.com",
"port": null,
"path": "/E2EPRX/mobile/?=",
"headers": {
"method": "odata",
"Content-Type": "application/json",
"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MzgyNzIwMDgsInN1YiI6IntcInVzZXJuYW1lXCI6XCJhZG1pblwiLFwicGFzc3dvcmRcIjpcImFkbWluXCIsXCJyb2xlXCI6XCJhZG1pblwifSJ9.1b4DP_vrykOsqlRfPoOi6m4tdpn1C6fhLufp8sAB_ysicJrwSGedTjQsSWK7TMx6t3kAabJ69Yj2AbhxBbS26w",
"Content-Length": "108"
}
};

const req = http.request(options, function (res) {
const chunks = [];

res.on("data", function (chunk) {
chunks.push(chunk);
});

res.on("end", function () {
const body = Buffer.concat(chunks);
console.log(body.toString());
});
});

req.write(JSON.stringify({
ClassPrx: 'ZCL_E2E_SOL_COT',
Content: '123',
Method: '123',
Userid: 'MRUIZ',
File: 'none'
}));
req.end();
</code>

<blockquote>Respuesta del Odata</blockquote>
<code>
{
  "d": {
    "results": [
      {
        "__metadata": {
          "id": "http://172.20.0.6:8000/sap/opu/odata/SAP/ZE2E_SRV/commSet(ClassPrx='ZCL_E2E_SOL_COT',Content='%7B%22RESULTS%22%3A%22%22%2C%22TO_ID%22%3A%22%22%2C%22MSGS%22%3A%5B%22Metodo%20no%20existe%20o%20mal%20tipeado.%22%5D%7D',File='none',Method='123',Userid='MRUIZ',Item='X')",
          "uri": "http://172.20.0.6:8000/sap/opu/odata/SAP/ZE2E_SRV/commSet(ClassPrx='ZCL_E2E_SOL_COT',Content='%7B%22RESULTS%22%3A%22%22%2C%22TO_ID%22%3A%22%22%2C%22MSGS%22%3A%5B%22Metodo%20no%20existe%20o%20mal%20tipeado.%22%5D%7D',File='none',Method='123',Userid='MRUIZ',Item='X')",
          "type": "ZE2E_SRV.comm"
        },
        "ClassPrx": "ZCL_E2E_SOL_COT",
        "Content": "{\"RESULTS\":\"\",\"TO_ID\":\"\",\"MSGS\":[\"Metodo no existe o mal tipeado.\"]}",
        "File": "none",
        "Method": "123",
        "Userid": "MRUIZ",
        "Item": "X"
      }
    ]
  }
}
</code>


