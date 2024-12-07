<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JFSD</title>
</head>
<body>
	<div style="width:100%">
		<table style="width:100%">
			<tr>
				<td style="width:100px">Faculty ID*</td>
				<td><input type="text" id="T1" /></td>
			</tr>
			<tr>
				<td style="width:100px">Faculty Name*</td>
				<td><input type="text" id="T2" /></td>
			</tr>
			<tr>
				<td style="width:100px">Designation*</td>
				<td><input type="text" id="T3" /></td>
			</tr>
			<tr>
				<td style="width:100px"></td>
				<td> 
					<button onclick="save()">Save</button> 
					<button onclick="read()">Read</button>
					<button onclick="update()">Update</button>
					<button onclick="del()">Delete</button>
				</td>
			</tr>
			<tr>
				<td></td>
				<td> <label id="L1"></label> </td>
			</tr>
		</table>
	</div>
	<div id='flist' style="width:100%"></div>
</body>
<script src="js/main.js"></script>
<script>
	function save()
	{
		var T1 = document.getElementById("T1");
		var T2 = document.getElementById("T2");
		var T3 = document.getElementById("T3");
		
		var data = JSON.stringify({
			id : T1.value,
			name : T2.value,
			designation : T3.value
		});
		
		var url = "http://localhost:8080/faculty/save";
		callApi("POST", url, data, saveHandler);
	}
	function saveHandler(response)
	{
		//alert(response.msg);
		if(response.code == "200")
			L1.style.color = "green";
		else
			L1.style.color = "red";
		L1.innerHTML = response.msg;
	}
	function read() {
		var url = "http://localhost:8080/faculty/read";
		callApi("GET", url, "", readHandler);
	}
	function readHandler(response) {
		var data = response;
		var table = `<table border="1">
						<tr>
							<th>Faculty ID</th>
							<th>Faculty Name</th>
							<th>Designation</th>
						</tr>`;
		for(var x in data){
			table += `<tr>
							<td>`+ data[x].id +`</td>
							<td>`+ data[x].name +`</td>
							<td>`+ data[x].designation +`</td>
					  </tr>`;
		}
		table += `</table>`;
		flist.innerHTML = table;
	}
	function update()
	{
		var T1 = document.getElementById("T1");
		var T2 = document.getElementById("T2");
		var T3 = document.getElementById("T3");
		
		var data = JSON.stringify({
			id : T1.value,
			name : T2.value,
			designation : T3.value
		});
		
		var url = "http://localhost:8080/faculty/update";
		callApi("PUT", url, data, updateHandler);
	}
	function updateHandler(response)
	{
		L1.style.color = "green";
		L1.innerHTML = response.msg;
	}
	function del()
	{
		var T1 = document.getElementById("T1");
				
		var url = "http://localhost:8080/faculty/delete?ID=" + T1.value;
		callApi("DELETE", url, "", deleteHandler);
	}
	function deleteHandler(response)
	{
		L1.style.color = "green";
		L1.innerHTML = response.msg;
	}
</script>
</html>