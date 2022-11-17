<?PHP

use GuzzleHttp\Psr7\Header;

$hostname_localhost="localhost";
$database_localhost="sali";
$username_localhost="root";
$password_localhost="";


$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="SELECT * FROM asistencia";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["fecha"]=$registro['fecha'];
			$result["estado"]=$registro['estado'];
			$result["alumno_id"]=$registro['alumno_id'];
			$json['estados'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		header('Content-Type: application/json');
		echo json_encode($json);
?>