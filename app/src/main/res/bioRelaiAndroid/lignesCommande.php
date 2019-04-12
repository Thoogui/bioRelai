<?php
require_once 'configs/param.php';
require_once 'modeles/dao/commandeDAO.php';

print(json_encode(lignesCommandeDAO::getLinesOrder($_POST['idCommande'])));