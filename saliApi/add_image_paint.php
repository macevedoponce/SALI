<?php
$hostname_localhost="localhost";
$database_localhost="sali";
$username_localhost="root";
$password_localhost="";
$json=array();
$fecha = date("Y/m/d"); 


 
    $imagen= $_POST['imagen'];
    $nombreImagen = $_POST['nombreImagen'];

       // $ruta="imagenes";
		//$archivo=$_FILES['imagen']['tmp_name'];
        //$nombreArchivo=$_FILES['imagen']['name'];
        //move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		//$ruta=$ruta."/".$nombreArchivo;

        $path ="imagenes/$nombreImagen.png";
        $url="http://192.168.1.46/saliApi/$path";

        file_put_contents($path,base64_decode($imagen));
        $bytesArchivo = file_get_contents($path);
        
        $conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $insert="INSERT INTO tree_paint(id,imagen_url, alumno_id, fecha) VALUES (null,'{$url}','1','{$fecha}')";
        $resultado_insert=mysqli_query($conexion,$insert);
        

        // if($resultado_insert){
        //     file_put_contents($path,base64_decode($imagen));
        //     $consulta="SELECT * FROM tree_paint WHERE alumno_id = 1";
        //     $resultado=mysqli_query($conexion,$consulta);
            
        //     if($registro=mysqli_fetch_array($resultado)){
        //         $json['pelicula'][]=$registro;
        //     }
        //     mysqli_close($conexion);
        //     echo json_encode($json);

        // }
        // else{
        //     $resulta["id"]=0;
        //     $resulta["fecha"]='No registra';
        //     $resulta["ruta_imagen"]='No registra';
        //     $json['pelicula'][]=$resulta;
        //     echo json_encode($json);

        // }



?>