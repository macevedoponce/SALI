<?PHP

use GuzzleHttp\Psr7\Header;

$hostname_localhost="localhost";
$database_localhost="sali";
$username_localhost="root";
$password_localhost="";


$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="SELECT * FROM alumnos";
		$resultado=mysqli_query($conexion,$consulta);

		while($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["dni"]=$registro['aluDni'];
			$result["nombres"]=$registro['aluNombre'];
			$result["apellidoPaterno"]=$registro['aluApellidoPaterno'];
			$result["apellidoMaterno"]=$registro['aluApellidoMaterno'];
			$json['alumnos'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}

		$consuEstado="SELECT * FROM asistencia inner join alumnos where asistencia.alumno_id = alumnos.id";
		$resuEstado=mysqli_query($conexion,$consuEstado);
		
		while($registroEstado=mysqli_fetch_array($resuEstado)){
			$resultEstado["fecha"]=$registroEstado['fecha'];
			$resultEstado["estado"]=$registroEstado['estado'];
			$resultEstado["alumno_id"]=$registroEstado['alumno_id'];
			$resultEstado["nombres"]=$registroEstado['aluNombres'];
			$resultEstado["apellidoPaterno"]=$registroEstado['aluApellidoPaterno'];
			$resultEstado["apellidoMaterno"]=$registroEstado['aluApellidoMaterno'];
			$resultEstado["selected"]=false;
			$json['estados'][]=$resultEstado;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}

		mysqli_close($conexion);
		header('Content-Type: application/json');
		echo json_encode($json);
?>
