<?php
$hostname_localhost="localhost";
$database_localhost="sali";
$username_localhost="root";
$password_localhost="";
$json=array();

    if(isset($_GET["fecha"]) && isset($_GET["alumno_id"]) && isset($_GET["estado"])){
        $fecha=$_GET['fecha'];
        $alumno_id=$_GET['alumno_id'];
        $estado=$_GET['estado'];

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $result = $conexion->query("select * from asistencia where alumno_id = '".$alumno_id."' and fecha = '".$fecha."'");
        $num_row = $result->num_rows;
        if($num_row >=1 ){
            $insert = "UPDATE asistencia SET estado = '{$estado}' WHERE alumno_id = '{$alumno_id}'";
            $resultado_insert=mysqli_query($conexion,$insert);
            
        }else{

            $insert="INSERT INTO asistencia(fecha, estado, alumno_id) VALUES ('{$fecha}','{$estado}','{$alumno_id}')";
            $resultado_insert=mysqli_query($conexion,$insert);
        }

        if($resultado_insert){
            $consulta="SELECT * FROM asistencia WHERE alumno_id = '{$alumno_id}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['asistencia'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

        }
        else{
            $resulta["id"]=0;
            $resulta["fecha"]='No registra';
            $resulta["estado"]='No registra';
            $resulta["alumno_id"]='No registra';
            echo json_encode($json);

        }

        
    }
    else{
        $resulta["id"]=0;
        $resulta["fecha"]='WS no retorna';
        $resulta["estado"]='WS no retorna';
        $resulta["alumno_id"]='WS no retorna';
        echo json_encode($json);
        }


?>