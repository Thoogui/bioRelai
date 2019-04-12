<?php
require_once 'configs/param.php';
require_once 'modeles/dao/commandeDAO.php';
$idAdherent = null;
$dailyOnly = null;
if(isset($_POST['idAdherent']))
	$idAdherent = $_POST['idAdherent'];

if(isset($_POST['dailyOnly']))
	$dailyOnly = $_POST['dailyOnly'];

//problème confusion param

print(json_encode(commandeDAO::getAllOrders($idAdherent, $dailyOnly)));