<?php
$hostname_localhost ="localhost";
$database_localhost ="sali";
$username_localhost ="root";
$password_localhost ="";

$json=array();

    if(isset($_GET["texto"]) ){
        $cuento=$_GET['texto'];
        

        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $insert="INSERT INTO cuentos(id,cuento,alumno_id,imagen_id) VALUES (null,'{$cuento}','1','1')";
        $resultado_insert=mysqli_query($conexion,$insert);
        

        if($resultado_insert){
            $consulta="SELECT * FROM cuentos WHERE alumno_id = 1";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['cuentos'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);

        }
        else{
            
            $resulta["cuento"]='No registra';
            $json['cuentos'][]=$resulta;
            echo json_encode($json);

        }
    }
    else{
        
        $resulta["cuento"]='WS no retorna';
        $json['cuentos'][]=$resulta;
            echo json_encode($json);
        }


?>