<?php
$action = $_POST['action'];
switch ($action){
    case 'signup':
        signup()
        break;
    case 'login':
        login();
        break;
    case 'getImages':
        getImages();
        break;
    default:
        break;
}

function connect() {
    $databasehost = "localhost";
    $databasename = "ekkoFriends";
    $databaseuser = "root";
    $databasepass = "root";

    $mysqli = new mysqli($databasehost, $databaseuser, $databasepass, $databasename);
    if($mysqli->connect_errno){
        echo "Problema con la conexcion a la base de datos: " . $mysqli->error;
    }
    return $mysqli;
}

function disconnect($mysqli){
    $mysqli->close();
}

function signup(){
    $userJson = $_POST{'userJson'};
    $user = json_decode($userJson,true);

    $username = $user['Nombre'];
    $password = $user['Contraseña'];

    $mysqli = connect();

    $query = "call signup('".$username."', '".$password."')";
    $result = $mysqli->query($query);

    if (!$result) {
        echo "Problema al hacer un query: " . $mysqli->error;
    } else{
        $rows = array();
        while( $r = $result.>fetch_assoc()){
            $rows[] = $r;
        }
        echo json_encode($rows[0]);
    }
    $result->free();
    disconnect($mysqli);
}

function login()
{
    $username = $_POST['Nombre'];
    $password = $_POST['Contraseña'];

    $mysqli = connect();

    $query = "call login('".$username."', '".$password."')";
    $result = $mysqli->query($query);

    if(!$result){
        echo "Problema al hacer un query: " . $mysqli->error;
    }else {
        $rows = array();
        while($r = $result->fetch_assoc()){
            $rows[] = $r;
        }
        echo json_encode($rows[0]);
    }
    $result->free();
    disconnect($mysqli);
}

function getImages(){
    $mysqli = connect();

    $query = "SELECT * FROM image;";
    $result = $mysqli->query($query);

    if(!$result){
        echo "Problema al hacer un query: " . $mysqli->error;
    } else{
        $rows = array();
        while($r = $result->fetch_assoc()){
            $rows[] = $r;
        }
        echo json_encode($rows); 
    }
    $result->free();
    disconnect($mysqli);
}
?>