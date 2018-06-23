<?php
	// Datenbankverbindung herstellen
	require_once(__DIR__."/database_connection.php");
	
	// einzelne Suchkriterien aus POST Daten extrahieren
	$EmployeeID = $_POST["EmployeeID"];
	$full_name = $_POST["full_name"];
	$address = $_POST["address"];
	$zip_code = $_POST["zip_code"];
	$city = $_POST["city"];
	
	
	//Datenbankabfrage durchführen
	
	$sql= "SELECT * FROM Employee WHERE EmployeeID LIKE :employeeID AND Name LIKE :name AND Address LIKE :address AND ZipCode LIKE :zipcode AND City LIKE :city";
	$stmt= $con->prepare($sql);
	$stmt->bindValue(':employeeID', '%'.$EmployeeID.'%');
	$stmt->bindValue(':name', '%'.$full_name.'%');
	$stmt->bindValue(':address', '%'.$address.'%');
	$stmt->bindValue(':zipcode', '%'.$zip_code.'%');
	$stmt->bindValue(':city', '%'.$city.'%');
	$stmt->execute();
	
	
	$result= $stmt->fetchAll(PDO::FETCH_ASSOC); //array()
      
	if(!empty($result))
	{
	    // Ausgabe der gefundenen Employees
	    echo "Found ".count($result)." employee(s)!<br/><br/>";
	    foreach($result as $row)
	    {
?>
	      <h2>Employee #<?php echo $row["EMPLOYEEID"]; ?></h2><br/>
	      <b>Name: </b><?php echo htmlentities($row["NAME"]); ?><br/>
	      <b>Address: </b><?php echo htmlentities($row["ADDRESS"]); ?><br/>
	      <b>ZIP/City: </b><?php echo $row["ZIPCODE"].", ".htmlentities($row["CITY"]);?><br/><br/>
<?php
		}
	 }
	 // Ausgabe wenn keine Employees gefunden wurden
	 else { echo "No employee(s) found!<br>"; }
?>
