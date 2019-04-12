<?php
require_once 'configs/param.php';
require_once 'modeles/dao/dBConnexion.php';

print(json_encode(DBConnex::verification($_POST['login'], $_POST['mdp'])));