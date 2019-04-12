<?php
require_once 'configs/param.php';
require_once 'modeles/dao/utilisateurDAO.php';

print(json_encode(utilisateurDAO::getAllUtilisateur()));