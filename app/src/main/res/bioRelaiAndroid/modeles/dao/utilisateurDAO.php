<?php
require_once 'dBConnexion.php';

class utilisateurDAO
{  

	public function getAllUtilisateur()
	{
		$requete = DBConnex::getInstance()->prepare("SELECT IDUTILISATEUR, STATUT, NOMUTILISATEUR, PRENOMUTILISATEUR, LOGIN FROM UTILISATEUR");
        $requete->execute();
        $result = $requete->fetchAll(PDO::FETCH_ASSOC); 
		
		return $result;
	}	
}


