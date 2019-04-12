<?php
require_once 'dBConnexion.php';

class commandeDAO
{  
    public function getAllOrders()
    {
		$sql = "SELECT * FROM COMMANDE";
		
        $requete = DBConnex::getInstance()->prepare($sql);
        $requete->execute();
        $result = $requete->fetchAll(PDO::FETCH_ASSOC); 
		
		return $result;
            
    }
	
	
}